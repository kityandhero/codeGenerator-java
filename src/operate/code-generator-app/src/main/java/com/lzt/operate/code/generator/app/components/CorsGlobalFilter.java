package com.lzt.operate.code.generator.app.components;

import com.lzt.operate.utility.web.filters.CorsCustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author luzhitao
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsGlobalFilter extends CorsCustomFilter {

    @Autowired
    public CorsGlobalFilter(CorsConfig corsConfig) {
        super(corsConfig);
    }
}
