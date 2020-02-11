package com.lzt.operate.codetools.app.assists;

import com.lzt.operate.codetools.app.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.app.entity.Operator;
import com.lzt.operate.codetools.app.repositories.OperatorRepository;
import com.lzt.operate.utility.permissions.CustomJsonWebToken;

import java.util.Optional;

/**
 * Operator辅助方法集合
 *
 * @author luzhitao
 */
public class OperatorAssist {

	private CustomJsonWebTokenConfig jwtConfig;

	private OperatorRepository operatorRepository;

	public OperatorAssist(OperatorRepository operatorRepository, CustomJsonWebTokenConfig jwtConfig) {
		this.operatorRepository = operatorRepository;
		this.jwtConfig = jwtConfig;
	}

	public Optional<Operator> getCurrent() {
		Optional<CustomJsonWebToken> op = CustomJsonWebToken.getFromCurrentHttpToken(jwtConfig);

		return op.flatMap(customIdentificationToken -> {
			try {
				return operatorRepository.findById(customIdentificationToken.getId());
			} catch (Exception e) {
				e.printStackTrace();

				return Optional.empty();
			}
		});
	}

}
