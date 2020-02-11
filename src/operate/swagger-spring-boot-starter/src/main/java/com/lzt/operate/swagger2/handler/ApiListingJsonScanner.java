package com.lzt.operate.swagger2.handler;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.lzt.operate.swagger2.ModelCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.schema.Model;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.PathAdjuster;
import springfox.documentation.service.ResourceGroup;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.spring.web.paths.PathMappingAdjuster;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;
import static springfox.documentation.builders.BuilderDefaults.nullToEmptyList;
import static springfox.documentation.spi.service.contexts.Orderings.methodComparator;
import static springfox.documentation.spi.service.contexts.Orderings.resourceGroupComparator;

/**
 * @author luzhitao
 * @date 2019-05-08 16:45
 */
@SuppressWarnings({"Guava", "UnstableApiUsage"})
@Component
@Primary
public class ApiListingJsonScanner extends ApiListingScanner {
    // public static final String ROOT = "/";

    private final ApiDescriptionReader apiDescriptionReader;
    private final ApiModelReader apiModelReader;
    private final DocumentationPluginsManager pluginsManager;

    @Autowired
    public ApiListingJsonScanner(
            ApiDescriptionReader apiDescriptionReader,
            ApiModelReader apiModelReader,
            DocumentationPluginsManager pluginsManager) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
        this.apiDescriptionReader = apiDescriptionReader;
        this.apiModelReader = apiModelReader;
        this.pluginsManager = pluginsManager;
    }

    private static java.util.Optional<String> longestCommonPath(List<ApiDescription> apiDescriptions) {

        List<String> commons = newArrayList();
        if (null == apiDescriptions || apiDescriptions.isEmpty()) {
            return java.util.Optional.empty();
        }
        List<String> firstWords = urlParts(apiDescriptions.get(0));

        for (int position = 0; position < firstWords.size(); position++) {
            String word = firstWords.get(position);
            boolean allContain = true;
            for (int i = 1; i < apiDescriptions.size(); i++) {
                List<String> words = urlParts(apiDescriptions.get(i));
                if (words.size() < position + 1 || !words.get(position).equals(word)) {
                    allContain = false;
                    break;
                }
            }
            if (allContain) {
                commons.add(word);
            }
        }

        Joiner joiner = Joiner.on("/").skipNulls();
        return java.util.Optional.of("/" + joiner.join(commons));
    }

    private static List<String> urlParts(ApiDescription apiDescription) {
        return Splitter.on('/')
                       .omitEmptyStrings()
                       .trimResults()
                       .splitToList(apiDescription.getPath());
    }

    private static Iterable<ResourceGroup> collectResourceGroups(Collection<ApiDescription> apiDescriptions) {
        return apiDescriptions.stream().map(toResourceGroups()).collect(toList());
    }

    private static Iterable<ResourceGroup> sortedByName(Set<ResourceGroup> resourceGroups) {
        return resourceGroups.stream().sorted(resourceGroupComparator()).collect(toList());
    }

    private static Predicate<ApiDescription> belongsTo(final String groupName) {
        return input -> !Objects.requireNonNull(input).getGroupName().isPresent()
                || groupName.equals(input.getGroupName().or(""));
    }

    private static Function<ApiDescription, ResourceGroup> toResourceGroups() {
        return input -> new ResourceGroup(
                Objects.requireNonNull(input).getGroupName().or(Docket.DEFAULT_GROUP_NAME),
                null);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
        final Multimap<String, ApiListing> apiListingMap = LinkedListMultimap.create();
        int position = 0;
        Map<ResourceGroup, List<RequestMappingContext>> requestMappingsByResourceGroup
                = context.getRequestMappingsByResourceGroup();
        Collection<ApiDescription> additionalListings = this.pluginsManager.additionalListings(context);
        Set<ResourceGroup> allResourceGroups = from(collectResourceGroups(additionalListings))
                .append(requestMappingsByResourceGroup.keySet())
                .toSet();

        List<SecurityReference> securityReferences = newArrayList();
        for (final ResourceGroup resourceGroup : sortedByName(allResourceGroups)) {

            DocumentationContext documentationContext = context.getDocumentationContext();
            Set<String> produces = new LinkedHashSet<>(documentationContext.getProduces());
            Set<String> consumes = new LinkedHashSet<>(documentationContext.getConsumes());
            String host = documentationContext.getHost();
            Set<String> protocols = new LinkedHashSet<>(documentationContext.getProtocols());
            Set<ApiDescription> apiDescriptions = newHashSet();

            Map<String, Model> models = new LinkedHashMap<>();
            List<RequestMappingContext> requestMappings = nullToEmptyList(requestMappingsByResourceGroup.get(resourceGroup));

            //url
            for (RequestMappingContext each : this.sortedByMethods(requestMappings)) {
                // Map<String, Model> knownModels = new HashMap<>(1);
                models.putAll(this.apiModelReader.read(each.withKnownModels(models)));

                apiDescriptions.addAll(this.apiDescriptionReader.read(each));
            }

            models.putAll(ModelCache.getInstance().getKnownModels());

            List<ApiDescription> additional = from(additionalListings)
                    .filter(and(
                            belongsTo(resourceGroup.getGroupName()),
                            this.onlySelectedApis(documentationContext)))
                    .toList();
            apiDescriptions.addAll(additional);

            List<ApiDescription> sortedApis = apiDescriptions.stream()
                                                             .sorted(documentationContext.getApiDescriptionOrdering())
                                                             .collect(toList());
            java.util.Optional<String> o = longestCommonPath(sortedApis);
            String resourcePath = new ResourcePathProvider(resourceGroup)
                    .resourcePath()
                    .or(Optional.fromNullable(o.orElse(null)))
                    .orNull();

            PathProvider pathProvider = documentationContext.getPathProvider();
            String basePath = pathProvider.getApplicationBasePath();
            PathAdjuster adjuster = new PathMappingAdjuster(documentationContext);
            ApiListingBuilder apiListingBuilder = new ApiListingBuilder(context.apiDescriptionOrdering())
                    .apiVersion(documentationContext.getApiInfo().getVersion())
                    .basePath(adjuster.adjustedPath(basePath))
                    .resourcePath(resourcePath)
                    .produces(produces)
                    .consumes(consumes)
                    .host(host)
                    .protocols(protocols)
                    .securityReferences(securityReferences)
                    .apis(sortedApis)
                    .models(models)
                    .position(position++)
                    .availableTags(documentationContext.getTags());

            ApiListingContext apiListingContext = new ApiListingContext(
                    context.getDocumentationType(),
                    resourceGroup,
                    apiListingBuilder);
            apiListingMap.put(resourceGroup.getGroupName(), this.pluginsManager.apiListing(apiListingContext));
        }
        return apiListingMap;
    }

    private Predicate<ApiDescription> onlySelectedApis(final DocumentationContext context) {
        return input -> context.getApiSelector().getPathSelector().apply(Objects.requireNonNull(input).getPath());
    }

    private Iterable<RequestMappingContext> sortedByMethods(List<RequestMappingContext> contexts) {
        return contexts.stream().sorted(methodComparator()).collect(toList());
    }

    class ResourcePathProvider {
        private final ResourceGroup resourceGroup;

        ResourcePathProvider(ResourceGroup resourceGroup) {
            this.resourceGroup = resourceGroup;
        }

        Optional<String> resourcePath() {
            return Optional.fromNullable(
                    Strings.emptyToNull(this.controllerClass()
                                            .transform(this.resourcePathExtractor())
                                            .or("")));
        }

        private Function<Class<?>, String> resourcePathExtractor() {
            return input -> {
                String path = Iterables.getFirst(Arrays.asList(ResourcePathProvider.this.paths(input)), "");
                if (Strings.isNullOrEmpty(path)) {
                    return "";
                }

                String start = "/";
                if (path.startsWith(start)) {
                    return path;
                }
                return "/" + path;
            };
        }

        @VisibleForTesting
        String[] paths(Class<?> controller) {
            RequestMapping annotation
                    = AnnotationUtils.findAnnotation(controller, RequestMapping.class);
            if (annotation != null) {
                return annotation.path();
            }
            return new String[]{};
        }

        private Optional<? extends Class<?>> controllerClass() {
            return this.resourceGroup.getControllerClass();
        }
    }
}