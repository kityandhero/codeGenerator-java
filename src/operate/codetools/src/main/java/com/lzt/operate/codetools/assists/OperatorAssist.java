package com.lzt.operate.codetools.assists;

import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.codetools.repository.OperatorRepository;
import com.lzt.operate.secret.DesAssist;
import com.lzt.operate.utility.RequestAssist;
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

        String token = request.getHeader("token");

        String operatorId = DesAssist.decryptWithCBC(token);

        var optionalResult = this.operatorRepository.findById(operatorId);

        return optionalResult.orElse(null);
    }
}
