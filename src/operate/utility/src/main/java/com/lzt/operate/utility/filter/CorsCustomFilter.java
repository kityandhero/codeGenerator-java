package com.lzt.operate.utility.filter;

import com.lzt.operate.utility.CorsConfigEntity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
