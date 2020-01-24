package com.lzt.operate.codetools.assists;

import com.lzt.operate.codetools.common.GlobalString;
import com.lzt.operate.codetools.entity.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.secret.DesAssist;
import com.lzt.operate.utility.RequestAssist;
import com.lzt.operate.utility.StringAssist;
import lombok.var;

import javax.servlet.http.HttpServletRequest;

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

	public Operator getCurrent() {
		HttpServletRequest request = RequestAssist.getHttpServletRequest();

		String token = request.getHeader(GlobalString.AUTH_TOKEN);

		String operatorId = DesAssist.decryptWithCBC(token);

		if (StringAssist.isNullOrEmpty(operatorId)) {
			return null;
		}

		var optionalResult = operatorRepository.findById(operatorId);

		return optionalResult.orElse(null);
	}
}
