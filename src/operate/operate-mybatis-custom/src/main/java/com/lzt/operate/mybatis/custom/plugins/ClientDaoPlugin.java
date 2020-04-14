package com.lzt.operate.mybatis.custom.plugins;

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
		FullyQualifiedJavaType calculateJavaType = javaTypeResolver
				.calculateJavaType(introspectedTable.getPrimaryKeyColumns().get(0));

		FullyQualifiedJavaType superInterfaceType = new FullyQualifiedJavaType(
				new StringBuilder("BaseMapper<")
						.append(introspectedTable.getBaseRecordType())
						.append(",")
						.append(introspectedTable.getExampleType())
						.append(",")
						.append(calculateJavaType.getShortName())
						.append(">")
						.toString()
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
			interfaze.getImportedTypes().removeIf(javaType -> javaType.getFullyQualifiedName().equals("java.util.List")
					|| javaType.getFullyQualifiedName().equals("org.apache.ibatis.annotations.Param"));
		}

		return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
	}

}