package com.lzt.operate.codetools.components;

import com.lzt.operate.web.filters.CorsCustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lzt
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsGlobalFilter extends CorsCustomFilter {

    @Autowired
    public CorsGlobalFilter(CorsConfig corsConfig) {
        super(corsConfig);
    }
}
