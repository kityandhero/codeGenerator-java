package com.lzt.operate.swagger2;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.lzt.operate.swagger2.model.ApiJsonObject;
import com.lzt.operate.swagger2.model.ApiJsonProperty;
import com.lzt.operate.swagger2.model.ApiJsonResult;
import com.lzt.operate.swagger2.model.ApiSingleParam;
import org.springframework.plugin.core.OrderAwarePluginRegistry;
import org.springframework.plugin.core.PluginRegistry;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.schema.JacksonEnumTypeDeterminer;
import springfox.documentation.schema.Model;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.TypeNameProviderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.spi.service.contexts.DocumentationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static com.lzt.operate.swagger2.CommonData.getJsonErrorCode;
import static com.lzt.operate.swagger2.CommonData.getJsonErrorMsg;
import static com.lzt.operate.swagger2.CommonData.getJsonPageSize;
import static com.lzt.operate.swagger2.CommonData.getJsonStartPageNum;
import static com.lzt.operate.swagger2.CommonData.getJsonTotalCount;
import static com.lzt.operate.swagger2.CommonData.getResultTypeNormal;
import static com.lzt.operate.swagger2.CommonData.getResultTypeOther;
import static com.lzt.operate.swagger2.CommonData.getResultTypePage;
import static org.springframework.util.ObjectUtils.isEmpty;
import static springfox.documentation.spi.schema.contexts.ModelContext.inputParam;

/**
 * @author luzhitao
 * @date 2019-05-08 16:45
 */
@SuppressWarnings("Guava")
public class ModelCache {

	private Map<String, Model> knownModels = new HashMap<>();
	private DocumentationContext context;
	private Function<ResolvedType, ? extends ModelReference> factory;
	private TypeResolver typeResolver = new TypeResolver();
	private Map<String, ApiSingleParam> paramMap = new HashMap<>();
	private Class<?> cls;

	private ModelCache() {
	}

	public static ModelCache getInstance() {
		return ModelCacheSub.instance;
	}

	public ModelCache setParamMap(Map<String, ApiSingleParam> paramMap) {
		this.paramMap = paramMap;
		return getInstance();
	}

	public Class<?> getParamClass() {
		return this.cls;
	}

	private void setParamClass(Class<?> cls) {
		this.cls = cls;
	}

	void setParamClass(String className) {
		try {
			Class<?> c = Class.forName(className);
			setParamClass(c);
		} catch (ClassNotFoundException ex) {
			setParamClass(GlobalStringInner.class);
		}
	}

	public DocumentationContext getContext() {
		return this.context;
	}

	public void setContext(DocumentationContext context) {
		this.context = context;
	}

	public Map<String, Model> getKnownModels() {
		return this.knownModels;
	}

	public void addModel(ApiJsonObject jsonObj) {
		String modelName = jsonObj.name();

		this.knownModels.put(modelName,
				new Model(modelName,
						modelName,
						new TypeResolver().resolve(String.class),
						"com.lzt.operate.swagger2.CommonData",
						this.toPropertyMap(jsonObj.value(), modelName),
						"POST参数",
						"",
						"",
						newArrayList(), null, null
				));
		String resultName = jsonObj.name() + "-" + "result";

		this.knownModels.put(resultName,
				new Model(resultName,
						resultName,
						new TypeResolver().resolve(String.class),
						"com.lzt.operate.swagger2.CommonData",
						this.toResultMap(jsonObj.result(), resultName),
						"返回模型",
						"",
						"",
						newArrayList(), null, null
				));
	}

	private Map<String, ModelProperty> toResultMap(ApiJsonResult jsonResult, String groupName) {
		List<String> values = Arrays.asList(jsonResult.value());
		List<String> outer = new ArrayList<>();

		if (!getResultTypeOther().equals(jsonResult.type())) {
			outer.add(getJsonErrorCode());
			outer.add(getJsonErrorMsg());
			if (!getResultTypeNormal().equals(jsonResult.type())) {
				//model
				String subModelName = groupName + "-" + jsonResult.name();
				this.knownModels.put(subModelName,
						new Model(subModelName,
								subModelName,
								new TypeResolver().resolve(String.class),
								"com.lzt.operate.swagger2.CommonData",
								this.transResultMap(values),
								"返回模型",
								"",
								"",
								newArrayList(), null, null
						));

				//prop
				Map<String, ModelProperty> propertyMap = new HashMap<>(1);

				ResolvedType type = new TypeResolver().resolve(List.class);
				ModelProperty mp = new ModelProperty(
						jsonResult.name(),
						type,
						"",
						0,
						false,
						false,
						true,
						false,
						"",
						null,
						"",
						null,
						"",
						null,
						newArrayList()
				);// new AllowableRangeValues("1", "2000"),//.allowableValues(new AllowableListValues(["ABC", "ONE", "TWO"], "string"))
				mp.updateModelRef(this.getModelRef());
				// ResolvedType collectionElementType = collectionElementType(type);
				try {
					Field f = ModelProperty.class.getDeclaredField("modelRef");
					f.setAccessible(true);
					f.set(mp, new ModelRef("List", new ModelRef(subModelName)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				propertyMap.put(jsonResult.name(), mp);

				if (getResultTypePage().equals(jsonResult.type())) {
					outer.add(getJsonStartPageNum());
					outer.add(getJsonPageSize());
					outer.add(getJsonTotalCount());
				}

				propertyMap.putAll(this.transResultMap(outer));
				return propertyMap;
			}

			outer.addAll(values);
			return this.transResultMap(outer);
		}

		return this.transResultMap(values);
	}

	private Map<String, ModelProperty> transResultMap(List<String> values) {
		Map<String, ModelProperty> propertyMap = new HashMap<>();
		for (String resultName : values) {
			ApiSingleParam param = this.paramMap.get(resultName);
			if (isEmpty(param)) {
				continue;
			}
			Class<?> type = param.type();
			if (!isEmpty(param)) {
				type = param.type();
			} else if (isEmpty(type)) {
				type = String.class;
			}

			boolean allowMultiple = param.allowMultiple();
			if (!isEmpty(param)) {
				allowMultiple = param.allowMultiple();
			}
			ResolvedType resolvedType;
			if (!allowMultiple) {
				resolvedType = new TypeResolver().resolve(type);
			} else {
				resolvedType = new TypeResolver().resolve(List.class, type);
			}
			ModelProperty mp = new ModelProperty(
					resultName,
					resolvedType,
					param.type().getName(),
					0,
					false,
					false,
					true,
					false,
					param.value(),
					null,
					param.example(),
					null,
					"",
					null,
					newArrayList()
			);// new AllowableRangeValues("1", "2000"),//.allowableValues(new AllowableListValues(["ABC", "ONE", "TWO"], "string"))
			mp.updateModelRef(this.getModelRef());
			propertyMap.put(resultName, mp);
		}

		return propertyMap;
	}

	private Map<String, ModelProperty> toPropertyMap(ApiJsonProperty[] jsonProp, String modelName) {
		Map<String, ModelProperty> propertyMap = new HashMap<>(1);

		for (ApiJsonProperty property : jsonProp) {
			String propertyName = property.name();
			ApiSingleParam param = this.paramMap.get(propertyName);

			String description = property.description();
			if (isNullOrEmpty(description) && !isEmpty(param)) {
				description = param.value();
			}
			String example = property.description();
			if (isNullOrEmpty(example) && !isEmpty(param)) {
				example = param.example();
			}
			Class<?> type = property.type();
			if (isEmpty(param)) {
				if (isEmpty(type)) {
					type = String.class;
				}
			} else {
				type = param.type();
			}

			boolean allowMultiple = property.allowMultiple();
			if (!isEmpty(param)) {
				allowMultiple = param.allowMultiple();
			}
			ResolvedType resolvedType;
			if (allowMultiple) {
				resolvedType = new TypeResolver().resolve(List.class, type);
			} else {
				resolvedType = new TypeResolver().resolve(type);
			}

			ModelProperty mp = new ModelProperty(
					propertyName,
					resolvedType,
					type.toString(),
					0,
					property.required(),
					false,
					property.readOnly(),
					null,
					description,
					null,
					example,
					null,
					property.defaultValue(),
					null,
					newArrayList()
			);// new AllowableRangeValues("1", "2000"),//.allowableValues(new AllowableListValues(["ABC", "ONE", "TWO"], "string"))
			mp.updateModelRef(this.getModelRef());
			propertyMap.put(property.name(), mp);
		}

		return propertyMap;
	}

	private Function<ResolvedType, ? extends ModelReference> getModelRef() {
		// ModelReference stringModel = factory.apply(typeResolver.resolve(List.class, String.class));
		return this.getFactory();

	}

	public Function<ResolvedType, ? extends ModelReference> getFactory() {
		if (this.factory == null) {

			List<DefaultTypeNameProvider> providers = newArrayList();
			providers.add(new DefaultTypeNameProvider());
			PluginRegistry<TypeNameProviderPlugin, DocumentationType> modelNameRegistry =
					OrderAwarePluginRegistry.create(providers);
			TypeNameExtractor typeNameExtractor = new TypeNameExtractor(
					this.typeResolver,
					modelNameRegistry,
					new JacksonEnumTypeDeterminer());
			ModelContext modelContext = inputParam(
					this.context.getGroupName(),
					String.class,
					this.context.getDocumentationType(),
					this.context.getAlternateTypeProvider(),
					this.context.getGenericsNamingStrategy(),
					this.context.getIgnorableParameterTypes());
			this.factory = ResolvedTypes.modelRefFactory(modelContext, typeNameExtractor);
		}
		return this.factory;
	}

	public ModelCache setFactory(Function<ResolvedType, ? extends ModelReference> factory) {
		this.factory = factory;
		return getInstance();
	}

	private static class ModelCacheSub {

		private static ModelCache instance = new ModelCache();

	}
}