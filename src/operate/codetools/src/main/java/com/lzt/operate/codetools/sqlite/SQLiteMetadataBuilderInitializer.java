package com.lzt.operate.codetools.sqlite;

import com.lzt.operate.codetools.CodeToolsApplication;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuilderInitializer;
import org.hibernate.engine.jdbc.dialect.internal.DialectResolverSet;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQLite工具
 */
public class SQLiteMetadataBuilderInitializer implements MetadataBuilderInitializer {

    private static final Logger logger = LoggerFactory.getLogger(CodeToolsApplication.class);


    static private final SQLiteDialect dialect = new SQLiteDialect();
    static private final DialectResolver resolver = (DialectResolver) info -> {
        if (info.getDatabaseName().equals("SQLite")) {
            return SQLiteMetadataBuilderInitializer.dialect;
        }

        return null;
    };

    @Override
    public void contribute(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
        DialectResolver dialectResolver = serviceRegistry.getService(DialectResolver.class);

        if (!(dialectResolver instanceof DialectResolverSet)) {
            SQLiteMetadataBuilderInitializer.logger.warn("DialectResolver '%s' is not an instance of DialectResolverSet, not registering SQLiteDialect",
                    dialectResolver);
            return;
        }

        ((DialectResolverSet) dialectResolver).addResolver(SQLiteMetadataBuilderInitializer.resolver);
    }
}