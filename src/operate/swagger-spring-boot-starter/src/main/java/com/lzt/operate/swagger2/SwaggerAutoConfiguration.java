package com.lzt.operate.swagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.lzt.operate.swagger2.handler.ApiListingJsonScanner;
import com.lzt.operate.swagger2.handler.ParametersReader;
import com.lzt.operate.swagger2.handler.ResponseMessageReader;
import com.lzt.operate.swagger2.webscan.ServiceModelToSwagger2Mapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author 翟永超
 * Create date：2017/8/7.
 * My blog： http://blog.didispace.com
 */
@SuppressWarnings("Guava")
@Configuration
@Import({
        Swagger2Configuration.class,
        ServiceModelToSwagger2Mapper.class,
        ApiListingJsonScanner.class,
        ParametersReader.class,
        ResponseMessageReader.class
})
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public UiConfiguration uiConfiguration(SwaggerProperties swaggerProperties) {
        return UiConfigurationBuilder.builder()
                                     .deepLinking(swaggerProperties.getUiConfig().getDeepLinking())
                                     .defaultModelExpandDepth(swaggerProperties.getUiConfig()
                                                                               .getDefaultModelExpandDepth())
                                     .defaultModelRendering(swaggerProperties.getUiConfig().getDefaultModelRendering())
                                     .defaultModelsExpandDepth(swaggerProperties.getUiConfig()
                                                                                .getDefaultModelsExpandDepth())
                                     .displayOperationId(swaggerProperties.getUiConfig().getDisplayOperationId())
                                     .displayRequestDuration(swaggerProperties.getUiConfig()
                                                                              .getDisplayRequestDuration())
                                     .docExpansion(swaggerProperties.getUiConfig().getDocExpansion())
                                     .maxDisplayedTags(swaggerProperties.getUiConfig().getMaxDisplayedTags())
                                     .operationsSorter(swaggerProperties.getUiConfig().getOperationsSorter())
                                     .showExtensions(swaggerProperties.getUiConfig().getShowExtensions())
                                     .tagsSorter(swaggerProperties.getUiConfig().getTagsSorter())
                                     .validatorUrl(swaggerProperties.getUiConfig().getValidatorUrl())
                                     .build();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(UiConfiguration.class)
    @ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
    public List<Docket> createRestApi(SwaggerProperties swaggerProperties) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;

        ModelCache.getInstance().setParamClass(swaggerProperties.getJsonCommonKeyAggregation());

        // 没有分组
        if (swaggerProperties.getDocket().size() == 0) {
            return buildSingleDocket(swaggerProperties, configurableBeanFactory);
        }

        return buildMultiDocket(swaggerProperties, configurableBeanFactory);
    }

    private List<Docket> buildSingleDocket(SwaggerProperties swaggerProperties, ConfigurableBeanFactory configurableBeanFactory) {
        List<Docket> docketList = new LinkedList<>();

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(swaggerProperties.getLicense())
                .licenseUrl(swaggerProperties.getLicenseUrl())
                .contact(new Contact(swaggerProperties.getContact().getName(),
                        swaggerProperties.getContact().getUrl(),
                        swaggerProperties.getContact().getEmail()))
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();

        // base-path处理
        // 当没有配置任何path的时候，解析/**
        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add("/**");
        }
        ArrayList<Predicate<String>> basePath = buildListBasePath(swaggerProperties.getBasePath());

        // exclude-path处理
        List<Predicate<String>> excludePath = new ArrayList<>();
        for (String path : swaggerProperties.getExcludePath()) {
            excludePath.add(PathSelectors.ant(path));
        }

        Docket docketForBuilder = new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo)
                .securityContexts(Collections.singletonList(securityContext()))
                .globalOperationParameters(buildGlobalOperationParametersFromSwaggerProperties(
                        swaggerProperties.getGlobalOperationParameters()));

        String basicAuth = "BasicAuth";
        String none = "None";

        if (basicAuth.equalsIgnoreCase(swaggerProperties.getAuthorization().getType())) {
            docketForBuilder.securitySchemes(Collections.singletonList(basicAuth()));
        } else if (!none.equalsIgnoreCase(swaggerProperties.getAuthorization().getType())) {
            docketForBuilder.securitySchemes(Collections.singletonList(apiKey()));
        }

        // 全局响应消息
        if (!swaggerProperties.getApplyDefaultResponseMessages()) {
            buildGlobalResponseMessage(swaggerProperties, docketForBuilder);
        }

        Docket docket = docketForBuilder.select()
                                        .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                                        .paths(
                                                Predicates.and(
                                                        Predicates.not(Predicates.or(excludePath)),
                                                        Predicates.or(basePath)
                                                )
                                        ).build();

        /* ignoredParameterTypes **/
        Class<?>[] array = new Class[swaggerProperties.getIgnoredParameterTypes().size()];
        Class<?>[] ignoredParameterTypes = swaggerProperties.getIgnoredParameterTypes().toArray(array);
        docket.ignoredParameterTypes(ignoredParameterTypes);

        configurableBeanFactory.registerSingleton("defaultDocket", docket);
        docketList.add(docket);
        return docketList;
    }

    private ArrayList<Predicate<String>> buildListBasePath(List<String> basePathList) {
        ArrayList<Predicate<String>> result = new ArrayList<>();

        for (String path : basePathList) {
            result.add(PathSelectors.ant(path));
        }

        return result;
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private List<Docket> buildMultiDocket(SwaggerProperties swaggerProperties, ConfigurableBeanFactory configurableBeanFactory) {
        List<Docket> docketList = new LinkedList<>();

        // 分组创建
        for (String groupName : swaggerProperties.getDocket().keySet()) {
            SwaggerProperties.DocketInfo docketInfo = swaggerProperties.getDocket().get(groupName);

            ApiInfo apiInfo = new ApiInfoBuilder()
                    .title(docketInfo.getTitle().isEmpty() ? swaggerProperties.getTitle() : docketInfo.getTitle())
                    .description(docketInfo.getDescription().isEmpty() ? swaggerProperties.getDescription() : docketInfo
                            .getDescription())
                    .version(docketInfo.getVersion()
                                       .isEmpty() ? swaggerProperties.getVersion() : docketInfo.getVersion())
                    .license(docketInfo.getLicense()
                                       .isEmpty() ? swaggerProperties.getLicense() : docketInfo.getLicense())
                    .licenseUrl(docketInfo.getLicenseUrl()
                                          .isEmpty() ? swaggerProperties.getLicenseUrl() : docketInfo.getLicenseUrl())
                    .contact(
                            new Contact(
                                    docketInfo.getContact().getName().isEmpty() ? swaggerProperties.getContact()
                                                                                                   .getName() : docketInfo
                                            .getContact()
                                            .getName(),
                                    docketInfo.getContact().getUrl().isEmpty() ? swaggerProperties.getContact()
                                                                                                  .getUrl() : docketInfo
                                            .getContact()
                                            .getUrl(),
                                    docketInfo.getContact().getEmail().isEmpty() ? swaggerProperties.getContact()
                                                                                                    .getEmail() : docketInfo
                                            .getContact()
                                            .getEmail()
                            )
                    )
                    .termsOfServiceUrl(docketInfo.getTermsOfServiceUrl()
                                                 .isEmpty() ? swaggerProperties.getTermsOfServiceUrl() : docketInfo.getTermsOfServiceUrl())
                    .build();

            // base-path处理
            // 当没有配置任何path的时候，解析/**
            if (docketInfo.getBasePath().isEmpty()) {
                docketInfo.getBasePath().add("/**");
            }

            ArrayList<Predicate<String>> basePath = buildListBasePath(docketInfo.getBasePath());

            // exclude-path处理
            ArrayList<Predicate<String>> excludePath = new ArrayList<>();
            for (String path : docketInfo.getExcludePath()) {
                excludePath.add(PathSelectors.ant(path));
            }

            Docket docketForBuilder = new Docket(DocumentationType.SWAGGER_2)
                    .host(swaggerProperties.getHost())
                    .apiInfo(apiInfo)
                    .securityContexts(Collections.singletonList(securityContext()))
                    .globalOperationParameters(assemblyGlobalOperationParameters(swaggerProperties.getGlobalOperationParameters(),
                            docketInfo.getGlobalOperationParameters()));

            if (!"BasicAuth".equalsIgnoreCase(swaggerProperties.getAuthorization().getType())) {
                if (!"None".equalsIgnoreCase(swaggerProperties.getAuthorization().getType())) {
                    docketForBuilder.securitySchemes(Collections.singletonList(apiKey()));
                }
            } else {
                docketForBuilder.securitySchemes(Collections.singletonList(basicAuth()));
            }

            // 全局响应消息
            if (!swaggerProperties.getApplyDefaultResponseMessages()) {
                buildGlobalResponseMessage(swaggerProperties, docketForBuilder);
            }

            Docket docket = docketForBuilder.groupName(groupName)
                                            .select()
                                            .apis(RequestHandlerSelectors.basePackage(docketInfo.getBasePackage()))
                                            .paths(
                                                    Predicates.and(
                                                            Predicates.not(Predicates.or(excludePath)),
                                                            Predicates.or(basePath)
                                                    )
                                            )
                                            .build();

            /* ignoredParameterTypes **/
            Class<?>[] array = new Class[docketInfo.getIgnoredParameterTypes().size()];
            Class<?>[] ignoredParameterTypes = docketInfo.getIgnoredParameterTypes().toArray(array);
            docket.ignoredParameterTypes(ignoredParameterTypes);

            configurableBeanFactory.registerSingleton(groupName, docket);
            docketList.add(docket);
        }
        return docketList;
    }

    /**
     * 配置基于 ApiKey 的鉴权对象
     *
     * @return ApiKey
     */
    private ApiKey apiKey() {
        return new ApiKey(swaggerProperties().getAuthorization().getName(),
                swaggerProperties().getAuthorization().getKeyName(),
                ApiKeyVehicle.HEADER.getValue());
    }

    /**
     * 配置基于 BasicAuth 的鉴权对象
     *
     * @return BasicAuth
     */
    private BasicAuth basicAuth() {
        return new BasicAuth(swaggerProperties().getAuthorization().getName());
    }

    /**
     * 配置默认的全局鉴权策略的开关，以及通过正则表达式进行匹配；默认 ^.*$ 匹配所有URL
     * 其中 securityReferences 为配置启用的鉴权策略
     *
     * @return SecurityContext
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                              .securityReferences(defaultAuth())
                              .forPaths(PathSelectors.regex(swaggerProperties().getAuthorization().getAuthRegex()))
                              .build();
    }

    /**
     * 配置默认的全局鉴权策略；其中返回的 SecurityReference 中，reference 即为ApiKey对象里面的name，保持一致才能开启全局鉴权
     *
     * @return List<SecurityReference>
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(SecurityReference.builder()
                                                          .reference(swaggerProperties().getAuthorization().getName())
                                                          .scopes(authorizationScopes).build());
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    private List<Parameter> buildGlobalOperationParametersFromSwaggerProperties(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters) {
        List<Parameter> parameters = newArrayList();

        if (Objects.isNull(globalOperationParameters)) {
            return parameters;
        }
        for (SwaggerProperties.GlobalOperationParameter globalOperationParameter : globalOperationParameters) {
            parameters.add(new ParameterBuilder()
                    .name(globalOperationParameter.getName())
                    .description(globalOperationParameter.getDescription())
                    .modelRef(new ModelRef(globalOperationParameter.getModelRef()))
                    .parameterType(globalOperationParameter.getParameterType())
                    .required(Boolean.parseBoolean(globalOperationParameter.getRequired()))
                    .build());
        }
        return parameters;
    }

    /**
     * 局部参数按照name覆盖局部参数
     *
     * @param globalOperationParameters globalOperationParameters
     * @param docketOperationParameters globalOperationParameters
     * @return List<Parameter>
     */
    private List<Parameter> assemblyGlobalOperationParameters(
            List<SwaggerProperties.GlobalOperationParameter> globalOperationParameters,
            List<SwaggerProperties.GlobalOperationParameter> docketOperationParameters) {

        if (Objects.isNull(docketOperationParameters) || docketOperationParameters.isEmpty()) {
            return buildGlobalOperationParametersFromSwaggerProperties(globalOperationParameters);
        }

        Set<String> docketNames = docketOperationParameters.stream()
                                                           .map(SwaggerProperties.GlobalOperationParameter::getName)
                                                           .collect(Collectors.toSet());

        List<SwaggerProperties.GlobalOperationParameter> resultOperationParameters = newArrayList();

        if (Objects.nonNull(globalOperationParameters)) {
            for (SwaggerProperties.GlobalOperationParameter parameter : globalOperationParameters) {
                if (!docketNames.contains(parameter.getName())) {
                    resultOperationParameters.add(parameter);
                }
            }
        }

        resultOperationParameters.addAll(docketOperationParameters);
        return buildGlobalOperationParametersFromSwaggerProperties(resultOperationParameters);
    }

    /**
     * 设置全局响应消息
     *
     * @param swaggerProperties swaggerProperties 支持 POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE
     * @param docketForBuilder  swagger docket builder
     */
    private void buildGlobalResponseMessage(SwaggerProperties swaggerProperties, Docket docketForBuilder) {

        SwaggerProperties.GlobalResponseMessage globalResponseMessages =
                swaggerProperties.getGlobalResponseMessage();

        /* POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE 响应消息体 **/
        List<ResponseMessage> postResponseMessages = getResponseMessageList(globalResponseMessages.getPost());
        List<ResponseMessage> getResponseMessages = getResponseMessageList(globalResponseMessages.getGet());
        List<ResponseMessage> putResponseMessages = getResponseMessageList(globalResponseMessages.getPut());
        List<ResponseMessage> patchResponseMessages = getResponseMessageList(globalResponseMessages.getPatch());
        List<ResponseMessage> deleteResponseMessages = getResponseMessageList(globalResponseMessages.getDelete());
        List<ResponseMessage> headResponseMessages = getResponseMessageList(globalResponseMessages.getHead());
        List<ResponseMessage> optionsResponseMessages = getResponseMessageList(globalResponseMessages.getOptions());
        List<ResponseMessage> trackResponseMessages = getResponseMessageList(globalResponseMessages.getTrace());

        docketForBuilder.useDefaultResponseMessages(swaggerProperties.getApplyDefaultResponseMessages())
                        .globalResponseMessage(RequestMethod.POST, postResponseMessages)
                        .globalResponseMessage(RequestMethod.GET, getResponseMessages)
                        .globalResponseMessage(RequestMethod.PUT, putResponseMessages)
                        .globalResponseMessage(RequestMethod.PATCH, patchResponseMessages)
                        .globalResponseMessage(RequestMethod.DELETE, deleteResponseMessages)
                        .globalResponseMessage(RequestMethod.HEAD, headResponseMessages)
                        .globalResponseMessage(RequestMethod.OPTIONS, optionsResponseMessages)
                        .globalResponseMessage(RequestMethod.TRACE, trackResponseMessages);
    }

    /**
     * 获取返回消息体列表
     *
     * @param globalResponseMessageBodyList 全局Code消息返回集合
     * @return List<ResponseMessage>
     */
    private List<ResponseMessage> getResponseMessageList
    (List<SwaggerProperties.GlobalResponseMessageBody> globalResponseMessageBodyList) {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        for (SwaggerProperties.GlobalResponseMessageBody globalResponseMessageBody : globalResponseMessageBodyList) {
            ResponseMessageBuilder responseMessageBuilder = new ResponseMessageBuilder();
            responseMessageBuilder.code(globalResponseMessageBody.getCode())
                                  .message(globalResponseMessageBody.getMessage());

            if (!StringUtils.isEmpty(globalResponseMessageBody.getModelRef())) {
                responseMessageBuilder.responseModel(new ModelRef(globalResponseMessageBody.getModelRef()));
            }
            responseMessages.add(responseMessageBuilder.build());
        }

        return responseMessages;
    }
}
