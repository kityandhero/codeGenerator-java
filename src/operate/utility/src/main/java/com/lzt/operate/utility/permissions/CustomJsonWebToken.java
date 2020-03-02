package com.lzt.operate.utility.permissions;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.lzt.operate.utility.assists.RequestAssist;
import com.lzt.operate.utility.assists.StringAssist;
import com.lzt.operate.utility.components.bases.JsonWebToken.BaseCustomJsonWebTokenConfig;
import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.pojo.BaseOperator;
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

	private CustomJsonWebToken(String token) {
		this.token = token;
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

	/**
	 * 从JWTToken解析Token
	 *
	 * @param token JWTToken
	 * @return Optional<CustomJsonWebToken>
	 */
	private static ExecutiveResult<CustomJsonWebToken> getFromHttpToken(String token) {
		if (!StringAssist.isNullOrEmpty(token)) {
			return new ExecutiveResult<>(ReturnDataCode.Ok.toMessage(), new CustomJsonWebToken(token));
		}

		return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("无效Token"));
	}

	/**
	 * 获取当前的Token，忽略是否过期
	 *
	 * @param customJsonWebTokenConfig customJsonWebTokenConfig
	 * @return Optional<CustomJsonWebToken>
	 */
	public static ExecutiveResult<CustomJsonWebToken> getFromCurrentHttpToken(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		if (!Optional.ofNullable(customJsonWebTokenConfig).isPresent()) {
			return new ExecutiveResult<>(ReturnDataCode.NoData.toMessage("无效Token"));
		}

		HttpServletRequest request = RequestAssist.getCurrentHttpServletRequest();

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

			if (customJsonWebToken.checkTokenTimeEffective()) {
				return new ExecutiveResult<>(ReturnDataCode.Ok, customJsonWebToken);
			}

			return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.toMessage("Token过期"));
		}

		return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.toMessage("缺少Token"));
	}

	public static ExecutiveResult<CustomJsonWebToken> checkToken(BaseCustomJsonWebTokenConfig customJsonWebTokenConfig) {
		ExecutiveResult<CustomJsonWebToken> result = CustomJsonWebToken.getFromCurrentHttpToken(customJsonWebTokenConfig);

		if (result.getSuccess()) {
			CustomJsonWebToken customJsonWebToken = result.getData();

			if (customJsonWebToken.checkTokenTimeEffective()) {
				return new ExecutiveResult<>(ReturnDataCode.Ok, result.getData());
			}

			return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.toMessage("登录超时，请重新登录"));
		}

		return new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.toMessage("请登录后访问"));
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

	public Optional<BaseOperator> getOperator() {
		ExecutiveResult<Map<String, Claim>> result = parseToken();

		if (result.getSuccess()) {
			Optional<Map<String, Claim>> op = Optional.of(result.getData());

			long id = op.map(longClaimMap -> longClaimMap.get("id").asLong()).orElse((long) 0);
			String userName = op.map(stringClaimMap -> stringClaimMap.get("userName").asString()).orElse("");

			BaseOperator operator = new BaseOperator();

			operator.setOperatorId(id);
			operator.setUserName(userName);

			return Optional.of(operator);
		}

		return Optional.empty();
	}

	public long getId() {
		Optional<BaseOperator> result = getOperator();

		return result.map(BaseOperator::getOperatorId).orElse(0L);
	}

	public String getUserName() {
		Optional<BaseOperator> result = getOperator();

		if (result.isPresent()) {
			return result.get().getUserName();
		}

		return "";
	}

	private ExecutiveResult<Date> getExpiration() {
		ExecutiveResult<DecodedJWT> result = decode();

		if (result.getSuccess()) {
			return new ExecutiveResult<>(ReturnDataCode.Ok, result.getData().getExpiresAt());
		}

		return new ExecutiveResult<>(result.getCode());
	}

	/**
	 * 校验token是否过期
	 *
	 * @return boolean
	 */
	private boolean checkTokenTimeEffective() {
		ExecutiveResult<Date> result = getExpiration();

		if (result.getSuccess()) {
			return !result.getData().before(new Date());
		}

		return false;
	}

	public ExecutiveResult<DecodedJWT> decode() {
		try {
			Optional<DecodedJWT> optional = Optional.of(JWT.decode(token));

			return optional.map(decodedJwt -> new ExecutiveResult<>(ReturnDataCode.Ok.toMessage(), decodedJwt))
						   .orElseGet(() -> new ExecutiveResult<>(ReturnDataCode.Authentication_FAIL.toMessage("Token数据无效")));
		} catch (Exception e) {
			logger.info("解析token出错");

			return new ExecutiveResult<>(ReturnDataCode.Exception.toMessage(e.getMessage()));
		}
	}

	/**
	 * 解析JWT token
	 *
	 * @return Map<String, Claim>
	 */
	private ExecutiveResult<Map<String, Claim>> parseToken() {
		ExecutiveResult<DecodedJWT> result = decode();

		if (result.getSuccess()) {
			Optional<Map<String, Claim>> optional = Optional.of(result.getData()).map(Payload::getClaims);

			return optional.map(stringClaimMap -> new ExecutiveResult<>(ReturnDataCode.Ok.toMessage(), stringClaimMap))
						   .orElseGet(() -> new ExecutiveResult<>(ReturnDataCode.Exception.toMessage("解析Token失败")));
		}

		return new ExecutiveResult<>(result.getCode());
	}

	public Object getToken() {
		return this.token;
	}
}
