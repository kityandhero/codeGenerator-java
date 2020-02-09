package com.lzt.operate.codetools.shiro;

import com.lzt.operate.secret.SecretAssist;
import com.lzt.operate.secret.SecretDecryptResult;
import org.apache.shiro.authc.AuthenticationToken;

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

	@Override
	public Object getPrincipal() {
		return this.uniqueIdentification;
	}

	@Override
	public Object getCredentials() {
		return null;
	}
}
