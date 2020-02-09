package com.lzt.operate.codetools.assists;

import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.codetools.shiro.CustomIdentificationToken;
import com.lzt.operate.utility.ConvertAssist;

import java.util.Optional;

/**
 * Operator辅助方法集合
 *
 * @author lzt
 */
public class OperatorAssist {

	private OperatorRepository operatorRepository;

	public OperatorAssist(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	public Optional<Operator> getCurrent() {
		Optional<CustomIdentificationToken> op = CustomIdentificationToken.getFromCurrentHttpToken();

		return op.flatMap(customIdentificationToken -> operatorRepository.findById(ConvertAssist.stringToLong(customIdentificationToken
				.getPrincipal().toString())));
	}

}
