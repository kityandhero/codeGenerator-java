package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.entities.ResultDataFactory;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.enums.ReturnDataCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author ：lzt
 * 创建时间： 2020/01/25. 06:25
 * 地点：郑州
 * 目的: 过滤OPTIONS请求
 * 继承shiro 的form表单过滤器，对 OPTIONS 请求进行过滤。
 * 前后端分离项目中，由于跨域，会导致复杂请求，即会发送preflighted request，这样会导致在GET／POST等请求之前会先发一个OPTIONS请求，但OPTIONS请求并不带shiro
 * 的'authToken'字段（shiro的SessionId），即OPTIONS请求不能通过shiro验证，会返回未认证的信息。
 * <p>
 * 备注说明： 需要在 shiroConfig 进行注册
 */
public class CustomAuthenticationFilter extends FormAuthenticationFilter {
	/**
	 * 直接过滤可以访问的请求类型
	 */
	private static final String REQUEST_TYPE = "OPTIONS";

	public CustomAuthenticationFilter() {
		super();
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return false;

		// if (((HttpServletRequest) request).getMethod().toUpperCase().equals(REQUEST_TYPE)) {
		// 	return true;
		// }
		// return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String token = getRequestToken((HttpServletRequest) request);

		Optional<CustomIdentificationToken> optional = CustomIdentificationToken.getFromHttpToken(token);

		if (optional.isPresent()) {
			return true;
		} else {
			HttpServletResponse res = (HttpServletResponse) response;

			res.setStatus(HttpServletResponse.SC_OK);
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = res.getWriter();

			ResultSingleData result = ResultDataFactory.failData(ReturnDataCode.Authentication_FAIL);
			writer.write(result.serialize());
			writer.close();
		}
		return false;

	}

	private String getRequestToken(HttpServletRequest request) {
		//默认从请求头中获得token
		String token = request.getHeader(GlobalString.AUTH_TOKEN);

		//如果header中不存在token，则从参数中获取token
		if (StringUtils.isBlank(token)) {
			token = request.getParameter(GlobalString.AUTH_TOKEN);
		}

		return token;
	}

}
