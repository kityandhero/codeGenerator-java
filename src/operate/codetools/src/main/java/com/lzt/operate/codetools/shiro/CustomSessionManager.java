package com.lzt.operate.codetools.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

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
	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String token = httpServletRequest.getHeader("token");

		System.out.println("token：" + token);

		if (!StringUtils.isEmpty(token)) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return token;
		} else {
			return super.getSessionId(request, response);
		}
	}

}
