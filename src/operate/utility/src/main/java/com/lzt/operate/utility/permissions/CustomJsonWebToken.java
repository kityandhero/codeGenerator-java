package com.lzt.operate.utility.permissions;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.lzt.operate.utility.assists.DateAssist;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.components.bases.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * 唯一标识Token
 *
 * @author luzhitao
 */
public class CustomJsonWebToken {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String token;

	public CustomJsonWebToken(String token) {
		this.token = token;
	}

	public Long getId() {
		Optional<Map<String, Claim>> op = parseToken();

		return op.map(longClaimMap -> longClaimMap.get("id").asLong()).orElse((long) 0);
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
	 * 校验token是否过期
	 *
	 * @return boolean
	 */
	public boolean isTokenExpired() {
		Date expirationTime = getExpiration();

		return expirationTime.before(new Date());
	}

	// /**
	//  * 校验token是否正确
	//  *
	//  * @param token    密钥
	//  * @param userName 登录名
	//  * @param password 密码
	//  * @return boolean
	//  */
	// public static boolean verify(String token, Long id, String userName, String password) {
	// 	try {
	// 		Algorithm algorithm = Algorithm.HMAC256(password);
	//
	// 		JWTVerifier verifier = JWT.require(algorithm).withClaim("id", id).withClaim("userName", userName).build();
	//
	// 		DecodedJWT jwt = verifier.verify(token);
	//
	// 		return true;
	// 	} catch (Exception e) {
	// 		return false;
	// 	}
	// }

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
			Date date = new Date(System.currentTimeMillis() + config.getExpire() * 1000);

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

	/**
	 * 从JWTToken解析Token
	 *
	 * @param token JWTToken
	 * @return Optional<CustomJsonWebToken>
	 */
	public static ExecutiveResult<CustomJsonWebToken> getFromHttpToken(String token) {
		if (!StringAssist.isNullOrEmpty(token)) {
			return new ExecutiveResult<>(ReturnDataCode.Ok, new CustomJsonWebToken(token));
		}

		return new ExecutiveResult<>(ReturnDataCode.NoData.setMessage("无效Token"));
	}

	/**
	 * 获取当前的Token，忽略是否过期
	 *
	 * @param customJsonWebTokenConfig customJsonWebTokenConfig
	 * @return Optional<CustomJsonWebToken>
	 */
	public static ExecutiveResult<CustomJsonWebToken> getFromCurrentHttpToken(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		if (!Optional.ofNullable(customJsonWebTokenConfig).isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.setMessage("无效Token"));
		}

		HttpServletRequest request = RequestAssist.getHttpServletRequest();

		String token = request.getHeader(customJsonWebTokenConfig.getHeader());

		return getFromHttpToken(token);
	}

	/**
	 * 获取当前有效的Token
	 *
	 * @param customJsonWebTokenConfig customJsonWebTokenConfig
	 * @return Optional<CustomJsonWebToken>
	 */
	public static ExecutiveResult<CustomJsonWebToken> getEffectiveCurrent(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		ExecutiveResult<CustomJsonWebToken> result = CustomJsonWebToken.getFromCurrentHttpToken(customJsonWebTokenConfig);

		if (result.getSuccess()) {
			CustomJsonWebToken customJsonWebToken = result.getData();

			if (!customJsonWebToken.isTokenExpired()) {
				return new ExecutiveResult<>(ReturnDataCode.Ok, customJsonWebToken);
			}

			return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.setMessage("Token过期"));
		}

		return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.setMessage("缺少Token"));
	}

	public static ExecutiveResult<CustomJsonWebToken> checkToken(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		ExecutiveResult<CustomJsonWebToken> result = CustomJsonWebToken.getFromCurrentHttpToken(customJsonWebTokenConfig);

		if (result.getSuccess()) {
			CustomJsonWebToken customJsonWebToken = result.getData();

			if (!customJsonWebToken.isTokenExpired()) {
				return new ExecutiveResult<>(ReturnDataCode.Ok, result.getData());
			}

			return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.setMessage("登录超时，请重新登录"));
		}

		return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.setMessage("请登录后访问"));
	}

	public Object getToken() {
		return this.token;
	}
}
