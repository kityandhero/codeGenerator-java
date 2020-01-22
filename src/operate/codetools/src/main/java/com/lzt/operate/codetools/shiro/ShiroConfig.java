package com.lzt.operate.codetools.shiro;

import lombok.var;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author lzt
 */
@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		var filterChainDefinitionMap = new LinkedHashMap<String, String>();

		//注意过滤器配置顺序 不能颠倒
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		//filterChainDefinitionMap.put("/logout", "logout");

		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/entrance/signIn", "anon");
		filterChainDefinitionMap.put("/entrance/register", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources", "anon");
		filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");
		filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		// filterChainDefinitionMap.put("/#/**", "anon");
		// filterChainDefinitionMap.put("/**", "authc");

		//// 跨域配置 有另外模块提供支持，此处废弃
		// filterChainDefinitionMap.put("/**", "corsAuthenticationFilter");

		//配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		//shiroFilterFactoryBean.setLoginUrl("/unauth");
		// shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// //自定义过滤器 ，跨域配置 有另外模块提供支持，此处废弃
		// Map<String, Filter> filterMap = new LinkedHashMap<>();
		// filterMap.put("corsAuthenticationFilter", corsAuthenticationFilter());
		// shiroFilterFactoryBean.setFilters(filterMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public CustomShiroRealm customShiroRealm() {
		return new CustomShiroRealm();
	}

	@Bean
	public SecurityManager securityManager(CustomShiroRealm realm, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		securityManager.setSessionManager(sessionManager);

		return securityManager;
	}

	@Bean
	public SessionManager sessionManager() {
		System.out.println("******sessionManager()");
		return new CustomSessionManager();
	}

	//// 跨域配置 有另外模块提供支持，此处废弃
	// public CORSAuthenticationFilter corsAuthenticationFilter() {
	// 	return new CORSAuthenticationFilter();
	// }

	/**
	 * 开启shiro aop注解支持.
	 * 使用代理方式;所以需要开启代码支持;
	 *
	 * @param securityManager securityManager
	 * @return AuthorizationAttributeSourceAdvisor AuthorizationAttributeSourceAdvisor
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

		return authorizationAttributeSourceAdvisor;
	}

}
