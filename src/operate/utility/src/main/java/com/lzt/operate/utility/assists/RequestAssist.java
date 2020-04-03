package com.lzt.operate.utility.assists;

import com.lzt.operate.utility.pojo.SerializableData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Optional;

/**
 * @author luzhitao
 */
public class RequestAssist {

	private static final int BUFFER_SIZE = 1024 * 8;

	public static String read(HttpServletRequest request) throws IOException {
		BufferedReader bufferedReader = request.getReader();
		StringWriter writer = new StringWriter();
		write(bufferedReader, writer);
		return writer.getBuffer().toString();
	}

	public static long write(Reader reader, Writer writer) throws IOException {
		return write(reader, writer, BUFFER_SIZE);
	}

	public static long write(Reader reader, Writer writer, int bufferSize) throws IOException {
		int read;
		long total = 0;
		char[] buf = new char[bufferSize];
		while ((read = reader.read(buf)) != -1) {
			writer.write(buf, 0, read);
			total += read;
		}
		return total;
	}

	private static ServletRequestAttributes getServletRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

	}

	/**
	 * 获取web请求
	 *
	 * @return 返回HttpServletRequest
	 */
	public static Optional<HttpServletRequest> getCurrentHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
		if (Optional.ofNullable(servletRequestAttributes).isPresent()) {
			return Optional.of(servletRequestAttributes.getRequest());
		}

		return Optional.empty();
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
	public static SerializableData getRequestHeaderData(@NotNull HttpServletRequest request) {
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
	public static SerializableData getRequestFormData(@NotNull HttpServletRequest request) {
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
	public static String getRequestBody(@NotNull HttpServletRequest request) {
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

	/**
	 * 获取请求路径
	 *
	 * @param request HttpServletRequest
	 * @return String
	 */
	public static String getRequestOriginalUrl(@NotNull HttpServletRequest request) {

		String url = (request).getRequestURL().toString();

		String queryString = request.getQueryString();

		if (StringAssist.isNullOrEmpty(queryString)) {
			return url;
		}

		return StringAssist.merge(url, "?", queryString);
	}

	/**
	 * getCurrentRequestOriginalUrl
	 *
	 * @return String
	 */
	public static String getCurrentRequestRemoteAddress() {
		Optional<HttpServletRequest> optional = getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			return optional.get().getRemoteAddr();
		}

		return "";
	}

	/**
	 * getCurrentRequestOriginalUrl
	 *
	 * @return String
	 */
	public static String getCurrentRequestOriginalUrl() {
		Optional<HttpServletRequest> optional = getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			return getRequestOriginalUrl(optional.get());
		}

		return "";
	}

	public static String getCurrentRequestHeaderJson() {
		Optional<HttpServletRequest> optional = getCurrentHttpServletRequest();

		if (optional.isPresent()) {
			HttpServletRequest request = optional.get();

			Enumeration<String> headerNames = request.getHeaderNames();

			SerializableData headers = new SerializableData();

			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();

				headers.append(headerName, request.getHeader(headerName));
			}

			return headers.serialize();
		}

		return "";
	}

	public String getRequestHeaderJson(@NotNull HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();

		SerializableData headers = new SerializableData();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();

			headers.append(headerName, request.getHeader(headerName));
		}

		return headers.serialize();
	}

}
