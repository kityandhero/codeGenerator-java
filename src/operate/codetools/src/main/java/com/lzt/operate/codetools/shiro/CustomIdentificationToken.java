package com.lzt.operate.codetools.shiro;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.secret.SecretAssist;
import com.lzt.operate.secret.SecretDecryptResult;
import com.lzt.operate.utility.RequestAssist;
import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 唯一标识Token
 *
 * @author lzt
 */
public class CustomIdentificationToken implements AuthenticationToken {

	private String uniqueIdentification;

	public CustomIdentificationToken(String uniqueIdentification) {
		this.uniqueIdentification = uniqueIdentification;
	}

	public String buildHttpToken() throws Exception {
		return SecretAssist.encryptWithExpirationTime(this.uniqueIdentification, 1440);
	}

	public static Optional<CustomIdentificationToken> getFromHttpToken(String token) {
		SecretDecryptResult decryptResult = SecretAssist.decryptWithExpirationTime(token);

		if (decryptResult.getSuccess()) {
			String id = decryptResult.getDecryptResult();

			return Optional.of(new CustomIdentificationToken(id));
		}

		return Optional.empty();
	}

	public static Optional<CustomIdentificationToken> getFromCurrentHttpToken() {
		HttpServletRequest request = RequestAssist.getHttpServletRequest();

		String token = request.getHeader(GlobalString.AUTH_TOKEN);

		return getFromHttpToken(token);
	}

	@Override
	public Object getPrincipal() {
		return this.uniqueIdentification;
	}

	@Override
	public Object getCredentials() {
		return null;
	}
}
