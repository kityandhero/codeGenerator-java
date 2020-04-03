package com.lzt.operate.utility.net;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author luzhitao
 */
public class CustomServletRequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		//TODO something
	}

	@Override
	public void destroy() {
		//TODO something
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		//取Body数据
		CustomBodyReaderHttpServletRequestWrapper requestWrapper = new CustomBodyReaderHttpServletRequestWrapper(request);

		String body = requestWrapper.getBody();

		//TODO something

		filterChain.doFilter(requestWrapper, servletResponse);
	}

}
