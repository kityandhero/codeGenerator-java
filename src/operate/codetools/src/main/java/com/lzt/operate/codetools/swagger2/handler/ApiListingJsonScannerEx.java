package com.lzt.operate.codetools.swagger2.handler;

import com.lzt.operate.swagger2.handler.ApiListingJsonScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiModelReader;

@Component
@Primary
public class ApiListingJsonScannerEx extends ApiListingJsonScanner {
    @Autowired
    public ApiListingJsonScannerEx(
            ApiDescriptionReader apiDescriptionReader,
            ApiModelReader apiModelReader,
            DocumentationPluginsManager pluginsManager) {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }
}
