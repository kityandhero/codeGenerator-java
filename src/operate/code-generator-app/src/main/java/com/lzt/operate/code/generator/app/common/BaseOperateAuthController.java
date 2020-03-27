package com.lzt.operate.code.generator.app.common;

import com.lzt.operate.code.generator.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;
import com.lzt.operate.utility.pojo.BaseOperator;
import com.lzt.operate.utility.pojo.results.ExecutiveResult;

import java.util.Optional;

/**
 * @author luzhitao
 */
public abstract class BaseOperateAuthController extends OperateBaseController {

	private final CustomJsonWebTokenConfig customJsonWebTokenConfig;

	protected BaseOperateAuthController(CustomJsonWebTokenConfig customJsonWebTokenConfig) {
		this.customJsonWebTokenConfig = customJsonWebTokenConfig;
	}

	protected CustomJsonWebTokenConfig getCustomJsonWebTokenConfig() {
		Optional<CustomJsonWebTokenConfig> optional = Optional.ofNullable(this.customJsonWebTokenConfig);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new RuntimeException("CustomJsonWebTokenConfig获取失败");
	}

	protected Optional<BaseOperator> getOperator() {
		ExecutiveResult<CustomJsonWebToken> resultCustomJsonWebToken = CustomJsonWebToken.getFromCurrentHttpToken(this.getCustomJsonWebTokenConfig());

		if (resultCustomJsonWebToken.getSuccess()) {
			CustomJsonWebToken customJsonWebToken = resultCustomJsonWebToken.getData();

			return customJsonWebToken.getOperator();
		}

		return Optional.empty();
	}

	protected long getOperatorId() {
		Optional<BaseOperator> optional = getOperator();

		return optional.map(BaseOperator::getOperatorId).orElse(0L);
	}

}
