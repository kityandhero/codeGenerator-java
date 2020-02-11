package com.lzt.operate.codetools.assists;

import com.lzt.operate.codetools.components.CustomJsonWebTokenConfig;
import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.permissions.CustomJsonWebToken;

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
