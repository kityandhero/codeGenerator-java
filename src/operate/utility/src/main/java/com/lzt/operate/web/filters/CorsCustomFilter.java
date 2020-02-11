package com.lzt.operate.web.filters;

import com.lzt.operate.entities.CorsConfigEntity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * @author luzhitao
 */
public class CorsCustomFilter extends CorsFilter {

    public CorsCustomFilter(CorsConfigEntity corsConfigEntity) {
        super(configurationSource(corsConfigEntity));
    }

    private static UrlBasedCorsConfigurationSource configurationSource(CorsConfigEntity corsConfigEntity) {
        CorsConfiguration config = new CorsConfiguration();

        List<String> allowedOrigins = corsConfigEntity.getAccessControlAllowOriginList();

        List<String> allowedHeaders = corsConfigEntity.getAccessControlAllowHeaderList();
        List<String> exposedHeaders = corsConfigEntity.getAccessControlAllowHeaderList();

        List<String> allowedMethods = corsConfigEntity.getAccessControlAllowMethodList();

        config.setAllowCredentials(corsConfigEntity.getAccessControlAllowCredentials());

        config.setAllowedOrigins(allowedOrigins);

        config.setAllowedHeaders(allowedHeaders);
        config.setExposedHeaders(exposedHeaders);

        config.setAllowedMethods(allowedMethods);

        config.setMaxAge(36000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
