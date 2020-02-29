package com.lzt.operate.utility.assists;

import com.lzt.operate.utility.pojo.SerializableData;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;

public class RequestAssist {

	private static ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

	}

	/**
	 * 获取web请求
	 *
	 * @return 返回HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
		return servletRequestAttributes.getRequest();

	}

	/**
	 * 获取Web响应
	 *
	 * @return 返回HttpServletResponse
	 */
	public static HttpServletResponse getHttpServletResponse() {
		ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
		return servletRequestAttributes.getResponse();
	}

	/**
	 * 获取请求头信息集合
	 *
	 * @param request HttpServletRequest
	 * @return SerializableData
	 */
	public static SerializableData getRequestHeaderData(@NonNull HttpServletRequest request) {
		Enumeration<String> names = request.getHeaderNames();

		SerializableData data = new SerializableData();

		while (names.hasMoreElements()) {
			String name = names.nextElement();

			data.append(name, request.getHeader(name));
		}

		return data;
	}

	/**
	 * 获取请求表单信息集合
	 *
	 * @param request HttpServletRequest
	 * @return SerializableData
	 */
	public static SerializableData getRequestFormData(@NonNull HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();

		SerializableData data = new SerializableData();

		while (names.hasMoreElements()) {
			String name = names.nextElement();

			data.append(name, request.getParameter(name));
		}

		return data;
	}

	/**
	 * 获取请求Body
	 *
	 * @param request HttpServletRequest
	 * @return String
	 */
	public static String getRequestBody(@NonNull HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			String v;
			BufferedReader br = request.getReader();
			while ((v = br.readLine()) != null) {
				stringBuilder.append(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

}
