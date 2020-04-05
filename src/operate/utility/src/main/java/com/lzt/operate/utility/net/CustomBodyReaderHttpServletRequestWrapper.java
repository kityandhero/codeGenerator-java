package com.lzt.operate.utility.net;

import com.lzt.operate.utility.assists.RequestAssist;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lizjotap
 */
public class CustomBodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final String body;

	public CustomBodyReaderHttpServletRequestWrapper(HttpServletRequest request)
			throws IOException {
		super(request);

		this.body = RequestAssist.read(request);
	}

	public String getBody() {
		return body;
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

		return new ServletInputStream() {

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}

			@Override
			public int read() {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}
