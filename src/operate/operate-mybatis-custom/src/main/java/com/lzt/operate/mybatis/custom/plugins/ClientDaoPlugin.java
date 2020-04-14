package com.lzt.operate.mybatis.custom.plugins;

import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.general.ConstantCollection;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaTypeResolver;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lzt
 */
public class ClientDaoPlugin extends EntityCommentPlugin {

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {

        JavaTypeResolver javaTypeResolver = new JavaTypeResolverDefaultImpl();

        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();

        if (ConstantCollection.ZERO_INT.equals(primaryKeyColumns.size())) {
            throw new RuntimeException(StringAssist.merge("表", introspectedTable.getFullyQualifiedTableNameAtRuntime(), "缺少主键设置"));
        }

        FullyQualifiedJavaType calculateJavaType = javaTypeResolver
                .calculateJavaType(introspectedTable.getPrimaryKeyColumns().get(0));

        FullyQualifiedJavaType superInterfaceType = new FullyQualifiedJavaType(
                "BaseMapper<" +
                        introspectedTable.getBaseRecordType() +
                        "," +
                        introspectedTable.getExampleType() +
                        "," +
                        calculateJavaType.getShortName() +
                        ">"
        );
        FullyQualifiedJavaType baseMapperInstance = FullyQualifiedJavaTypeProxyFactory.getBaseMapperInstance();

        interfaze.addSuperInterface(superInterfaceType);
        interfaze.addImportedType(baseMapperInstance);

        List<Method> changeMethods = interfaze.getMethods().stream()
                                              .filter(method -> method.getName().endsWith("WithBLOBs")
                                                      || method.getReturnType().toString().endsWith("WithBLOBs")
                                                      || Arrays.toString(method.getParameters().toArray())
                                                               .contains("WithBLOBs"))
                                              .collect(Collectors.toList());

        interfaze.getMethods().retainAll(changeMethods);

        if (changeMethods.isEmpty()) {
            interfaze.getImportedTypes()
                     .removeIf(javaType -> javaType.getFullyQualifiedName().equals(List.class.getName())
                             || javaType.getFullyQualifiedName().equals(Param.class.getName()));
        }

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

}