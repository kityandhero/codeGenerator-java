package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.components.CustomJsonWebTokenConfig;
import lombok.var;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lzt
 */
@Configuration
public class ShiroConfig {
	private CustomJsonWebTokenConfig jwtConfig;

	@Autowired
	public ShiroConfig(CustomJsonWebTokenConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		//自定义过滤器 ，验证token等
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("customAuthenticationFilter", new CustomAuthenticationFilter(jwtConfig));
		shiroFilterFactoryBean.setFilters(filterMap);

		shiroFilterFactoryBean.setSecurityManager(securityManager);

		var filterChainDefinitionMap = new LinkedHashMap<String, String>();

		//注意过滤器配置顺序 不能颠倒
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		//filterChainDefinitionMap.put("/logout", "logout");

		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/entrance/**", "anon");
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

		// 跨域配置 有另外模块提供支持，此处废弃
		filterChainDefinitionMap.put("/business/**", "customAuthenticationFilter");

		//配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		//shiroFilterFactoryBean.setLoginUrl("/unauth");
		// shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean
	public CustomShiroRealm customShiroRealm() {
		return new CustomShiroRealm();
	}

	@Bean
	public SecurityManager securityManager(CustomShiroRealm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		//设置自定义的realm
		securityManager.setRealm(realm);
		//自定义的shiro session 缓存管理器
		securityManager.setSessionManager(sessionManager());
		//将缓存对象注入到SecurityManager中
		securityManager.setCacheManager(ehCacheManager());

		return securityManager;

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

	/**
	 * shiro缓存管理器
	 * 1 添加相关的maven支持
	 * 2 注册这个bean，将缓存的配置文件导入
	 * 3 在securityManager 中注册缓存管理器，之后就不会每次都会去查询数据库了，相关的权限和角色会保存在缓存中，但需要注意一点，更新了权限等操作之后，需要及时的清理缓存
	 */
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return cacheManager;
	}

	/**
	 * 自定义的 shiro session 缓存管理器，用于跨域等情况下使用 token 进行验证，不依赖于sessionId
	 *
	 * @return SessionManager SessionManager
	 */
	@Bean
	public SessionManager sessionManager() {
		//将我们继承后重写的shiro session 注册
		CustomSessionManager shiroSession = new CustomSessionManager();
		//如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡
		shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
		return shiroSession;
	}

}
