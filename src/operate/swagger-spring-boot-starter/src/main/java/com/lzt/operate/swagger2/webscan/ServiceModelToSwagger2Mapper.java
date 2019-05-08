package com.lzt.operate.swagger2.webscan;

import com.google.common.collect.Multimap;
import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.Model;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.parameters.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Documentation;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResourceListing;
import springfox.documentation.swagger2.mappers.LicenseMapper;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ParameterMapper;
import springfox.documentation.swagger2.mappers.SecurityMapper;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;
import springfox.documentation.swagger2.mappers.VendorExtensionsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newTreeMap;

/**
 * @author lzt
 * @date 2019-05-08 16:45
 */
@Primary
@Component
public class ServiceModelToSwagger2Mapper extends ServiceModelToSwagger2MapperImpl {

    private ModelMapper modelMapper;
    private ParameterMapper parameterMapper;
    private SecurityMapper securityMapper;
    private LicenseMapper licenseMapper;
    private VendorExtensionsMapper vendorExtensionsMapper;

    @Autowired
    public ServiceModelToSwagger2Mapper(ModelMapper modelMapper, ParameterMapper parameterMapper, SecurityMapper securityMapper, LicenseMapper licenseMapper, VendorExtensionsMapper vendorExtensionsMapper) {
        this.modelMapper = modelMapper;
        this.parameterMapper = parameterMapper;
        this.securityMapper = securityMapper;
        this.licenseMapper = licenseMapper;
        this.vendorExtensionsMapper = vendorExtensionsMapper;
    }

    private Map<String, Model> modelsFromApiListings(ModelMapper modelMapper, Multimap<String, ApiListing> apiListings) {
        Map<String, springfox.documentation.schema.Model> definitions = newTreeMap();
        for (ApiListing each : apiListings.values()) {
//            System.out.println(each.getModels());
//            System.out.println(each.getModels().get("BusinessUser").getBaseModel());
//            System.out.println(each.getModels().get("BusinessUser").getExample());
//            System.out.println(each.getModels().get("BusinessUser").getName());
//            System.out.println(each.getModels().get("BusinessUser").getProperties());
//            System.out.println(each.getModels().get("BusinessUser").getProperties().get("realName").getDefaultValue());
            definitions.putAll(each.getModels());
        }
        return modelMapper.mapModels(definitions);
    }

    @Override
    public Swagger mapDocumentation(Documentation from) {
        if (from == null) {
            return null;
        }

        Swagger swagger = new Swagger();

        swagger.setVendorExtensions(this.vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        swagger.setSchemes(this.mapSchemes(from.getSchemes()));
        swagger.setPaths(this.mapApiListings(from.getApiListings()));
        swagger.setHost(from.getHost());
        swagger.setDefinitions(this.modelsFromApiListings(this.modelMapper, from.getApiListings()));
        swagger.setSecurityDefinitions(this.securityMapper.toSecuritySchemeDefinitions(from.getResourceListing()));
        ApiInfo info = this.fromResourceListingInfo(from);
        if (info != null) {
            swagger.setInfo(this.mapApiInfo(info));
        }
        swagger.setBasePath(from.getBasePath());
        swagger.setTags(this.tagSetToTagList(from.getTags()));
        List<String> list2 = from.getConsumes();
        if (list2 != null) {
            swagger.setConsumes(new ArrayList<>(list2));
        } else {
            swagger.setConsumes(null);
        }
        List<String> list3 = from.getProduces();
        if (list3 != null) {
            swagger.setProduces(new ArrayList<>(list3));
        } else {
            swagger.setProduces(null);
        }

        return swagger;
    }

    @Override
    protected Info mapApiInfo(ApiInfo from) {
        if (from == null) {
            return null;
        }

        Info info = new Info();

        info.setLicense(this.licenseMapper.apiInfoToLicense(from));
        info.setVendorExtensions(this.vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        info.setTermsOfService(from.getTermsOfServiceUrl());
        info.setContact(this.map(from.getContact()));
        info.setDescription(from.getDescription());
        info.setVersion(from.getVersion());
        info.setTitle(from.getTitle());

        return info;
    }

    protected Contact map(Contact from) {
        if (from == null) {
            return null;
        }

        Contact contact = new Contact();

        contact.setName(from.getName());
        contact.setUrl(from.getUrl());
        contact.setEmail(from.getEmail());

        return contact;
    }

    @Override
    protected io.swagger.models.Operation mapOperation(Operation from) {
        if (from == null) {
            return null;
        }

        io.swagger.models.Operation operation = new io.swagger.models.Operation();

        operation.setSecurity(this.mapAuthorizations(from.getSecurityReferences()));
        operation.setVendorExtensions(this.vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        operation.setDescription(from.getNotes());
        operation.setOperationId(from.getUniqueId());
        operation.setResponses(this.mapResponseMessages(from.getResponseMessages()));
        operation.setSchemes(this.stringSetToSchemeList(from.getProtocol()));
        Set<String> set = from.getTags();
        if (set != null) {
            operation.setTags(new ArrayList<>(set));
        } else {
            operation.setTags(null);
        }
        operation.setSummary(from.getSummary());
        Set<String> set1 = from.getConsumes();
        if (set1 != null) {
            operation.setConsumes(new ArrayList<>(set1));
        } else {
            operation.setConsumes(null);
        }
        Set<String> set2 = from.getProduces();
        if (set2 != null) {
            operation.setProduces(new ArrayList<>(set2));
        } else {
            operation.setProduces(null);
        }
        operation.setParameters(this.parameterListToParameterList(from.getParameters()));
        if (from.getDeprecated() != null) {
            operation.setDeprecated(Boolean.parseBoolean(from.getDeprecated()));
        }

        return operation;
    }

    @Override
    protected Tag mapTag(springfox.documentation.service.Tag from) {
        if (from == null) {
            return null;
        }

        Tag tag = new Tag();

        tag.setVendorExtensions(this.vendorExtensionsMapper.mapExtensions(from.getVendorExtensions()));
        tag.setName(from.getName());
        tag.setDescription(from.getDescription());

        return tag;
    }

    private ApiInfo fromResourceListingInfo(Documentation documentation) {
        if (documentation == null) {
            return null;
        }
        ResourceListing resourceListing = documentation.getResourceListing();
        if (resourceListing == null) {
            return null;
        }
        ApiInfo info = resourceListing.getInfo();
        if (info == null) {
            return null;
        }
        return info;
    }

    @Override
    protected List<Tag> tagSetToTagList(Set<springfox.documentation.service.Tag> set) {
        if (set == null) {
            return null;
        }

        List<Tag> list = new ArrayList<>(set.size());
        for (springfox.documentation.service.Tag tag : set) {
            list.add(this.mapTag(tag));
        }

        return list;
    }

    @Override
    protected List<Scheme> stringSetToSchemeList(Set<String> set) {
        if (set == null) {
            return null;
        }

        List<Scheme> list = new ArrayList<>(set.size());
        for (String string : set) {
            list.add(Enum.valueOf(Scheme.class, string));
        }

        return list;
    }

    @Override
    protected List<Parameter> parameterListToParameterList(List<springfox.documentation.service.Parameter> list) {
        if (list == null) {
            return null;
        }

        List<Parameter> list1 = new ArrayList<>(list.size());
        for (springfox.documentation.service.Parameter parameter : list) {
            list1.add(this.parameterMapper.mapParameter(parameter));
        }

        return list1;
    }
}