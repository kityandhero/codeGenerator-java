package com.lzt.operate.codetools.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 自定义SessionManager
 *
 * @author lzt
 */
public class CustomSessionManager extends DefaultWebSessionManager {
	/**
	 * 定义的请求头中使用的标记key，用来传递 token
	 */
	private static final String AUTH_TOKEN = "token";

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String token = httpServletRequest.getHeader(AUTH_TOKEN);

		System.out.println("token：" + token);

		//请求头中如果有 authToken, 则其值为sessionId
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
		//token
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
		request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

		return token;

		// if (!StringUtils.isEmpty(token)) {
		// 	request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
		// 	request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
		// 	request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
		// 	return token;
		// } else {
		// 	return super.getSessionId(request, response);
		// }
	}

}
