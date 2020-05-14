package com.lzt.operate.mybatis.custom.plugins;

import com.lzt.operate.mybatis.base.dao.BaseMapper;
import com.lzt.operate.mybatis.base.model.BaseExample;
import com.lzt.operate.mybatis.base.service.BaseService;
import com.lzt.operate.mybatis.base.service.impl.BaseServiceImpl;
import com.lzt.operate.mybatis.common.PageInfo;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * @author luzhitao
 */
public class FullyQualifiedJavaTypeProxyFactory extends FullyQualifiedJavaType {

	private static final FullyQualifiedJavaType pageInfoInstance = new FullyQualifiedJavaType(PageInfo.class.getName());
	private static final FullyQualifiedJavaType baseExampleInstance = new FullyQualifiedJavaType(BaseExample.class.getName());
	private static final FullyQualifiedJavaType baseMapperInstance = new FullyQualifiedJavaType(BaseMapper.class.getName());
	private static final FullyQualifiedJavaType baseServiceInstance = new FullyQualifiedJavaType(BaseService.class.getName());
	private static final FullyQualifiedJavaType baseServiceImplInstance = new FullyQualifiedJavaType(BaseServiceImpl.class
			.getName());

	public FullyQualifiedJavaTypeProxyFactory(String fullTypeSpecification) {
		super(fullTypeSpecification);
	}

	public static final FullyQualifiedJavaType getPageInfoInstanceInstance() {

		return pageInfoInstance;
	}

	public static final FullyQualifiedJavaType getBaseExampleInstance() {

		return baseExampleInstance;
	}

	public static final FullyQualifiedJavaType getBaseMapperInstance() {

		return baseMapperInstance;
	}

	public static final FullyQualifiedJavaType getBaseServiceInstance() {

		return baseServiceInstance;
	}

	public static final FullyQualifiedJavaType getBaseServiceImplInstance() {

		return baseServiceImplInstance;
	}
}