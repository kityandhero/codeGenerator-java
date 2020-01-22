package com.lzt.operate.codetools.shiro;

import com.alibaba.fastjson.JSON;
import com.lzt.operate.enums.ReturnDataCode;
import lombok.var;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {
	public CORSAuthenticationFilter() {
		super();
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (request instanceof HttpServletRequest) {
			if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
				System.out.println("OPTIONS请求");
				return true;
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setStatus(HttpServletResponse.SC_OK);
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter writer = res.getWriter();

		var map = new HashMap<String, Object>(2);
		map.put("code", ReturnDataCode.Authentication_FAIL.getCode());
		map.put("msg", ReturnDataCode.Authentication_FAIL.getMessage());

		writer.write(JSON.toJSONString(map));
		writer.close();
		return false;
	}

}
