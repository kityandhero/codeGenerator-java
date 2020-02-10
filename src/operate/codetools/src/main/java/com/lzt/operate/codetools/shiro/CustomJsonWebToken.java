package com.lzt.operate.codetools.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.lzt.operate.entities.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.DateAssist;
import com.lzt.operate.utility.RequestAssist;
import com.lzt.operate.utility.StringAssist;
import org.apache.shiro.authc.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * 唯一标识Token
 *
 * @author lzt
 */
public class CustomJsonWebToken implements AuthenticationToken {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String token;

	public CustomJsonWebToken(String token) {
		this.token = token;
	}

	public Long getId() {
		Optional<Map<String, Claim>> op = parseToken();

		return op.map(longClaimMap -> longClaimMap.get("userName").asLong()).orElse((long) 0);
	}

	public String getUserName() {
		Optional<Map<String, Claim>> op = parseToken();

		return op.map(stringClaimMap -> stringClaimMap.get("userName").asString()).orElse("");
	}

	public Date getExpiration() {
		Optional<DecodedJWT> op = decode();

		if (op.isPresent()) {
			return op.get().getExpiresAt();
		}

		return DateAssist.addDays(DateAssist.now(), -90);
	}

	/**
	 * 校验token是否正确
	 *
	 * @param token    密钥
	 * @param userName 登录名
	 * @param password 密码
	 * @return boolean
	 */
	public static boolean verify(String token, Long id, String userName, String password) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(password);

			JWTVerifier verifier = JWT.require(algorithm).withClaim("id", id).withClaim("userName", userName).build();

			DecodedJWT jwt = verifier.verify(token);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 成JWT token
	 *
	 * @param id       id
	 * @param userName userName
	 * @param password password
	 * @param config   BaseCustomJsonWebTokenConfig
	 * @return String
	 */
	public static String generateToken(Long id, String userName, String password, BaseCustomJsonWebTokenConfig config) {
		try {
			// 指定过期时间
			Date date = new Date(System.currentTimeMillis() + config.getExpire());

			Algorithm algorithm = Algorithm.HMAC256(password);

			return JWT.create()
					  .withClaim("id", id)
					  .withClaim("userName", userName)
					  .withExpiresAt(date)
					  .sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

	public Optional<DecodedJWT> decode() {
		try {
			return Optional.of(JWT.decode(token));
		} catch (Exception e) {
			logger.info("解析token出错");

			return Optional.empty();
		}
	}

	/**
	 * 解析JWT token
	 *
	 * @return Map<String, Claim>
	 */
	public Optional<Map<String, Claim>> parseToken() {
		Optional<DecodedJWT> op = decode();

		return op.map(Payload::getClaims);
	}

	public static Optional<CustomJsonWebToken> getFromHttpToken(String token) {
		if (!StringAssist.isNullOrEmpty(token)) {
			return Optional.of(new CustomJsonWebToken(token));
		}

		return Optional.empty();
	}

	public static Optional<CustomJsonWebToken> getFromCurrentHttpToken(BaseCustomJsonWebTokenConfig config) {

		HttpServletRequest request = RequestAssist.getHttpServletRequest();

		String token = request.getHeader(config.getHeader());

		return getFromHttpToken(token);
	}

	@Override
	public Object getPrincipal() {
		return this.token;
	}

	@Override
	public Object getCredentials() {
		return this.token;
	}
}
