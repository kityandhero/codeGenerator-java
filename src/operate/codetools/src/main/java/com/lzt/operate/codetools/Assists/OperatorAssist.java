package com.lzt.operate.codetools.Assists;

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

    public static Operator getCurrent(OperatorRepository operatorRepository) {
        HttpServletRequest request = RequestAssist.getHttpServletRequest();

        String token = request.getHeader("token");

        String operatorId = DesAssist.decryptWithCBC(token);

        var optionalResult = operatorRepository.findById(operatorId);

        return optionalResult.orElse(null);
    }
}
