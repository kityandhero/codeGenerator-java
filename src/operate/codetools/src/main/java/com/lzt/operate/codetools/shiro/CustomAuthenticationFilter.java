package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.components.CustomJsonWebTokenConfig;
import com.lzt.operate.entities.ResultDataFactory;
import com.lzt.operate.entities.ResultSingleData;
import com.lzt.operate.enums.ReturnDataCode;
import com.lzt.operate.utility.StringAssist;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

	CustomJsonWebTokenConfig jwtConfig;

	public CustomAuthenticationFilter(CustomJsonWebTokenConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		//获取请求token
		Optional<String> optionalToken = getRequestToken((HttpServletRequest) request);

		if (optionalToken.isPresent()) {
			String token = optionalToken.get();

			if (StringUtils.isBlank(token)) {
				return null;
			}

			return new CustomJsonWebToken(token);
		} else {
			return null;
		}
	}

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		boolean pass = true;
		String message = "";

		Optional<String> optionalToken = getRequestToken((HttpServletRequest) request);

		if (!optionalToken.isPresent()) {
			pass = false;
			message = "凭据无效";
		} else {
			String token = optionalToken.get();

			if (StringUtils.isBlank(token)) {
				pass = false;
				message = "凭据无效";
			} else {
				Optional<CustomJsonWebToken> op = CustomJsonWebToken.getFromHttpToken(token);

				if (op.isPresent()) {
					CustomJsonWebToken customJsonWebToken = op.get();

					try {
						if (jwtConfig.isTokenExpired(customJsonWebToken.getExpiration())) {
							pass = false;
							message = "凭据过期";
						}
					} catch (Exception e) {
						e.printStackTrace();

						pass = false;
						message = e.getMessage();
					}
				} else {
					pass = false;
					message = "凭据无效";
				}
			}

		}

		if (pass) {
			// return executeLogin(request, response);

			return true;
		} else {
			HttpServletResponse res = (HttpServletResponse) response;

			res.setStatus(HttpServletResponse.SC_OK);
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/json; charset=UTF-8");
			PrintWriter writer = res.getWriter();

			ReturnDataCode returnDataCode = ReturnDataCode.Authentication_FAIL;

			returnDataCode.setMessage(message);

			ResultSingleData result = ResultDataFactory.failData(returnDataCode);
			writer.write(result.serialize());
			writer.close();

			return true;
		}
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;

		res.setStatus(HttpServletResponse.SC_OK);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json; charset=UTF-8");

		PrintWriter writer;

		try {
			writer = res.getWriter();

			ReturnDataCode returnDataCode = ReturnDataCode.Authentication_FAIL;

			ResultSingleData result = ResultDataFactory.failData(returnDataCode);
			writer.write(result.serialize());
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	/**
	 * 获取请求中的token,首先从请求头中获取,如果没有,则尝试从请求参数中获取
	 *
	 * @param request request
	 * @return String
	 */
	private Optional<String> getRequestToken(HttpServletRequest request) {
		String token = request.getHeader(jwtConfig.getHeader());

		if (StringUtils.isBlank(token)) {
			token = request.getParameter(jwtConfig.getHeader());
		}

		if (!StringAssist.isNullOrEmpty(token)) {
			return Optional.of(token);
		}

		return Optional.empty();
	}

}
