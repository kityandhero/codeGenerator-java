package com.lzt.operate.web.filters;

import com.lzt.operate.entity.CorsConfigEntity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author lzt
 */
public class CorsCustomFilter extends CorsFilter {

    public CorsCustomFilter(CorsConfigEntity corsConfigEntity) {

        super(configurationSource(corsConfigEntity));
    }

    private static UrlBasedCorsConfigurationSource configurationSource(CorsConfigEntity corsConfigEntity) {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(corsConfigEntity.getAccessControlAllowCredentials());
        config.addAllowedOrigin(corsConfigEntity.getAccessControlAllowOrigins());
        config.addAllowedHeader(corsConfigEntity.getAccessControlAllowHeaders());
        config.addAllowedMethod(corsConfigEntity.getAccessControlAllowMethods());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
