package com.lzt.operate.swagger2;

import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 翟永超
 * Create date ：2017/8/7.
 * My blog： http://blog.didispace.com
 */
@PropertySource(value = "classpath:swagger.properties")
@ConfigurationProperties(prefix = "swagger")
@Component
@Configuration
// @ConfigurationProperties("swagger")
public class SwaggerProperties {

	public Contact contact = new Contact();

	/**
	 * 页面功能配置
	 **/
	public UiConfig uiConfig = new UiConfig();

	/**
	 * 全局统一鉴权配置
	 **/
	public Authorization authorization = new Authorization();

	/**
	 * 是否开启swagger
	 **/
	private Boolean enabled;
	/**
	 * 标题
	 **/
	private String title = "";
	/**
	 * 描述
	 **/
	private String description = "";
	/**
	 * 版本
	 **/
	private String version = "";
	/**
	 * 许可证
	 **/
	private String license = "";
	/**
	 * 许可证URL
	 **/
	private String licenseUrl = "";
	/**
	 * 服务条款URL
	 **/
	private String termsOfServiceUrl = "";
	/**
	 * 忽略的参数类型
	 **/
	private List<Class<?>> ignoredParameterTypes = new ArrayList<>();
	/**
	 * swagger会解析的包路径
	 **/
	private String basePackage = "";
	/**
	 * swagger会解析的url规则
	 **/
	private List<String> basePath = new ArrayList<>();
	/**
	 * 在basePath基础上需要排除的url规则
	 **/
	private List<String> excludePath = new ArrayList<>();
	/**
	 * jsonCommonKey信息
	 **/
	private String jsonCommonKeyAggregation = "";
	/**
	 * 分组文档
	 **/
	private Map<String, DocketInfo> docket = new LinkedHashMap<>();
	/**
	 * host信息
	 **/
	private String host = "";
	/**
	 * 全局参数配置
	 **/
	private List<GlobalOperationParameter> globalOperationParameters;
	/**
	 * 是否使用默认预定义的响应消息 ，默认 true
	 **/
	private Boolean applyDefaultResponseMessages = true;
	/**
	 * 全局响应消息
	 **/
	private GlobalResponseMessage globalResponseMessage;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public UiConfig getUiConfig() {
		return uiConfig;
	}

	public void setUiConfig(UiConfig uiConfig) {
		this.uiConfig = uiConfig;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public void setTermsOfServiceUrl(String termsOfServiceUrl) {
		this.termsOfServiceUrl = termsOfServiceUrl;
	}

	public List<Class<?>> getIgnoredParameterTypes() {
		return ignoredParameterTypes;
	}

	public void setIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {
		this.ignoredParameterTypes = ignoredParameterTypes;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public List<String> getBasePath() {
		return basePath;
	}

	public void setBasePath(List<String> basePath) {
		this.basePath = basePath;
	}

	public List<String> getExcludePath() {
		return excludePath;
	}

	public void setExcludePath(List<String> excludePath) {
		this.excludePath = excludePath;
	}

	public String getJsonCommonKeyAggregation() {
		return jsonCommonKeyAggregation;
	}

	public void setJsonCommonKeyAggregation(String jsonCommonKeyAggregation) {
		this.jsonCommonKeyAggregation = jsonCommonKeyAggregation;
	}

	public Map<String, DocketInfo> getDocket() {
		return docket;
	}

	public void setDocket(Map<String, DocketInfo> docket) {
		this.docket = docket;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<GlobalOperationParameter> getGlobalOperationParameters() {
		return globalOperationParameters;
	}

	public void setGlobalOperationParameters(List<GlobalOperationParameter> globalOperationParameters) {
		this.globalOperationParameters = globalOperationParameters;
	}

	public Boolean getApplyDefaultResponseMessages() {
		return applyDefaultResponseMessages;
	}

	public void setApplyDefaultResponseMessages(Boolean applyDefaultResponseMessages) {
		this.applyDefaultResponseMessages = applyDefaultResponseMessages;
	}

	public GlobalResponseMessage getGlobalResponseMessage() {
		return globalResponseMessage;
	}

	public void setGlobalResponseMessage(GlobalResponseMessage globalResponseMessage) {
		this.globalResponseMessage = globalResponseMessage;
	}

	@NoArgsConstructor
	static class GlobalOperationParameter {
		/**
		 * 参数名
		 **/
		private String name;

		/**
		 * 描述信息
		 **/
		private String description;

		/**
		 * 指定参数类型
		 **/
		private String modelRef;

		/**
		 * 参数放在哪个地方:header,query,path,body.form
		 **/
		private String parameterType;

		/**
		 * 参数是否必须传
		 **/
		private String required;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getModelRef() {
			return modelRef;
		}

		public void setModelRef(String modelRef) {
			this.modelRef = modelRef;
		}

		public String getParameterType() {
			return parameterType;
		}

		public void setParameterType(String parameterType) {
			this.parameterType = parameterType;
		}

		public String getRequired() {
			return required;
		}

		public void setRequired(String required) {
			this.required = required;
		}
	}

	@NoArgsConstructor
	static class DocketInfo {

		/**
		 * 标题
		 **/
		private String title = "";
		/**
		 * 描述
		 **/
		private String description = "";
		/**
		 * 版本
		 **/
		private String version = "";
		/**
		 * 许可证
		 **/
		private String license = "";
		/**
		 * 许可证URL
		 **/
		private String licenseUrl = "";
		/**
		 * 服务条款URL
		 **/
		private String termsOfServiceUrl = "";

		private Contact contact = new Contact();

		/**
		 * swagger会解析的包路径
		 **/
		private String basePackage = "";

		/**
		 * swagger会解析的url规则
		 **/
		private List<String> basePath = new ArrayList<>();
		/**
		 * 在basePath基础上需要排除的url规则
		 **/
		private List<String> excludePath = new ArrayList<>();

		private List<GlobalOperationParameter> globalOperationParameters;

		/**
		 * 忽略的参数类型
		 **/
		private List<Class<?>> ignoredParameterTypes = new ArrayList<>();

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getLicense() {
			return license;
		}

		public void setLicense(String license) {
			this.license = license;
		}

		public String getLicenseUrl() {
			return licenseUrl;
		}

		public void setLicenseUrl(String licenseUrl) {
			this.licenseUrl = licenseUrl;
		}

		public String getTermsOfServiceUrl() {
			return termsOfServiceUrl;
		}

		public void setTermsOfServiceUrl(String termsOfServiceUrl) {
			this.termsOfServiceUrl = termsOfServiceUrl;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}

		public String getBasePackage() {
			return basePackage;
		}

		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}

		public List<String> getBasePath() {
			return basePath;
		}

		public void setBasePath(List<String> basePath) {
			this.basePath = basePath;
		}

		public List<String> getExcludePath() {
			return excludePath;
		}

		public void setExcludePath(List<String> excludePath) {
			this.excludePath = excludePath;
		}

		public List<GlobalOperationParameter> getGlobalOperationParameters() {
			return globalOperationParameters;
		}

		public void setGlobalOperationParameters(List<GlobalOperationParameter> globalOperationParameters) {
			this.globalOperationParameters = globalOperationParameters;
		}

		public List<Class<?>> getIgnoredParameterTypes() {
			return ignoredParameterTypes;
		}

		public void setIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {
			this.ignoredParameterTypes = ignoredParameterTypes;
		}
	}

	@NoArgsConstructor
	public static class Contact {

		/**
		 * 联系人
		 **/
		public String name = "";

		/**
		 * 联系人url
		 **/
		public String url = "";

		/**
		 * 联系人email
		 **/
		public String email = "";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}

	@NoArgsConstructor
	static class GlobalResponseMessage {

		/**
		 * POST 响应消息体
		 **/
		List<GlobalResponseMessageBody> post = new ArrayList<>();

		/**
		 * GET 响应消息体
		 **/
		List<GlobalResponseMessageBody> get = new ArrayList<>();

		/**
		 * PUT 响应消息体
		 **/
		List<GlobalResponseMessageBody> put = new ArrayList<>();

		/**
		 * PATCH 响应消息体
		 **/
		List<GlobalResponseMessageBody> patch = new ArrayList<>();

		/**
		 * DELETE 响应消息体
		 **/
		List<GlobalResponseMessageBody> delete = new ArrayList<>();

		/**
		 * HEAD 响应消息体
		 **/
		List<GlobalResponseMessageBody> head = new ArrayList<>();

		/**
		 * OPTIONS 响应消息体
		 **/
		List<GlobalResponseMessageBody> options = new ArrayList<>();

		/**
		 * TRACE 响应消息体
		 **/
		List<GlobalResponseMessageBody> trace = new ArrayList<>();

		public List<GlobalResponseMessageBody> getPost() {
			return post;
		}

		public void setPost(List<GlobalResponseMessageBody> post) {
			this.post = post;
		}

		public List<GlobalResponseMessageBody> getGet() {
			return get;
		}

		public void setGet(List<GlobalResponseMessageBody> get) {
			this.get = get;
		}

		public List<GlobalResponseMessageBody> getPut() {
			return put;
		}

		public void setPut(List<GlobalResponseMessageBody> put) {
			this.put = put;
		}

		public List<GlobalResponseMessageBody> getPatch() {
			return patch;
		}

		public void setPatch(List<GlobalResponseMessageBody> patch) {
			this.patch = patch;
		}

		public List<GlobalResponseMessageBody> getDelete() {
			return delete;
		}

		public void setDelete(List<GlobalResponseMessageBody> delete) {
			this.delete = delete;
		}

		public List<GlobalResponseMessageBody> getHead() {
			return head;
		}

		public void setHead(List<GlobalResponseMessageBody> head) {
			this.head = head;
		}

		public List<GlobalResponseMessageBody> getOptions() {
			return options;
		}

		public void setOptions(List<GlobalResponseMessageBody> options) {
			this.options = options;
		}

		public List<GlobalResponseMessageBody> getTrace() {
			return trace;
		}

		public void setTrace(List<GlobalResponseMessageBody> trace) {
			this.trace = trace;
		}
	}

	@NoArgsConstructor
	static class GlobalResponseMessageBody {

		/**
		 * 响应码
		 **/
		private int code;

		/**
		 * 响应消息
		 **/
		private String message;

		/**
		 * 响应体
		 **/
		private String modelRef;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getModelRef() {
			return modelRef;
		}

		public void setModelRef(String modelRef) {
			this.modelRef = modelRef;
		}
	}

	@NoArgsConstructor
	public static class UiConfig {

		public Boolean deepLinking;
		public String apiSorter = "alpha";
		/**
		 * Network
		 */
		public String validatorUrl;
		/**
		 * 是否启用json编辑器
		 **/
		private Boolean jsonEditor = false;
		/**
		 * 是否显示请求头信息
		 **/
		private Boolean showRequestHeaders = true;
		/**
		 * 支持页面提交的请求类型
		 **/
		private String submitMethods = "get,post,put,delete,patch";
		/**
		 * 请求超时时间
		 **/
		private Long requestTimeout = 10000L;
		private Boolean displayOperationId;
		private Integer defaultModelsExpandDepth;
		private Integer defaultModelExpandDepth;
		private ModelRendering defaultModelRendering;
		/**
		 * 是否显示请求耗时，默认false
		 */
		private Boolean displayRequestDuration = true;
		/**
		 * 可选 none | list
		 */
		private DocExpansion docExpansion;
		/**
		 * Boolean=false OR String
		 */
		private Object filter;
		private Integer maxDisplayedTags;
		private OperationsSorter operationsSorter;
		private Boolean showExtensions;
		private TagsSorter tagsSorter;

		public Boolean getDeepLinking() {
			return deepLinking;
		}

		public void setDeepLinking(Boolean deepLinking) {
			this.deepLinking = deepLinking;
		}

		public String getApiSorter() {
			return apiSorter;
		}

		public void setApiSorter(String apiSorter) {
			this.apiSorter = apiSorter;
		}

		public String getValidatorUrl() {
			return validatorUrl;
		}

		public void setValidatorUrl(String validatorUrl) {
			this.validatorUrl = validatorUrl;
		}

		public Boolean getJsonEditor() {
			return jsonEditor;
		}

		public void setJsonEditor(Boolean jsonEditor) {
			this.jsonEditor = jsonEditor;
		}

		public Boolean getShowRequestHeaders() {
			return showRequestHeaders;
		}

		public void setShowRequestHeaders(Boolean showRequestHeaders) {
			this.showRequestHeaders = showRequestHeaders;
		}

		public String getSubmitMethods() {
			return submitMethods;
		}

		public void setSubmitMethods(String submitMethods) {
			this.submitMethods = submitMethods;
		}

		public Long getRequestTimeout() {
			return requestTimeout;
		}

		public void setRequestTimeout(Long requestTimeout) {
			this.requestTimeout = requestTimeout;
		}

		public Boolean getDisplayOperationId() {
			return displayOperationId;
		}

		public void setDisplayOperationId(Boolean displayOperationId) {
			this.displayOperationId = displayOperationId;
		}

		public Integer getDefaultModelsExpandDepth() {
			return defaultModelsExpandDepth;
		}

		public void setDefaultModelsExpandDepth(Integer defaultModelsExpandDepth) {
			this.defaultModelsExpandDepth = defaultModelsExpandDepth;
		}

		public Integer getDefaultModelExpandDepth() {
			return defaultModelExpandDepth;
		}

		public void setDefaultModelExpandDepth(Integer defaultModelExpandDepth) {
			this.defaultModelExpandDepth = defaultModelExpandDepth;
		}

		public ModelRendering getDefaultModelRendering() {
			return defaultModelRendering;
		}

		public void setDefaultModelRendering(ModelRendering defaultModelRendering) {
			this.defaultModelRendering = defaultModelRendering;
		}

		public Boolean getDisplayRequestDuration() {
			return displayRequestDuration;
		}

		public void setDisplayRequestDuration(Boolean displayRequestDuration) {
			this.displayRequestDuration = displayRequestDuration;
		}

		public DocExpansion getDocExpansion() {
			return docExpansion;
		}

		public void setDocExpansion(DocExpansion docExpansion) {
			this.docExpansion = docExpansion;
		}

		public Object getFilter() {
			return filter;
		}

		public void setFilter(Object filter) {
			this.filter = filter;
		}

		public Integer getMaxDisplayedTags() {
			return maxDisplayedTags;
		}

		public void setMaxDisplayedTags(Integer maxDisplayedTags) {
			this.maxDisplayedTags = maxDisplayedTags;
		}

		public OperationsSorter getOperationsSorter() {
			return operationsSorter;
		}

		public void setOperationsSorter(OperationsSorter operationsSorter) {
			this.operationsSorter = operationsSorter;
		}

		public Boolean getShowExtensions() {
			return showExtensions;
		}

		public void setShowExtensions(Boolean showExtensions) {
			this.showExtensions = showExtensions;
		}

		public TagsSorter getTagsSorter() {
			return tagsSorter;
		}

		public void setTagsSorter(TagsSorter tagsSorter) {
			this.tagsSorter = tagsSorter;
		}
	}

	/**
	 * securitySchemes 支持方式之一 ApiKey
	 */
	@NoArgsConstructor
	public static class Authorization {

		/**
		 * 鉴权策略，可选 ApiKey | BasicAuth | None，默认ApiKey
		 */
		public String type = "ApiKey";
		/**
		 * 鉴权策略ID，对应 SecurityReferences ID
		 */
		private String name = "Authorization";
		/**
		 * 鉴权传递的Header参数
		 */
		private String keyName = "TOKEN";

		/**
		 * 需要开启鉴权URL的正则
		 */
		private String authRegex = "^.*$";

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKeyName() {
			return keyName;
		}

		public void setKeyName(String keyName) {
			this.keyName = keyName;
		}

		public String getAuthRegex() {
			return authRegex;
		}

		public void setAuthRegex(String authRegex) {
			this.authRegex = authRegex;
		}
	}

}


