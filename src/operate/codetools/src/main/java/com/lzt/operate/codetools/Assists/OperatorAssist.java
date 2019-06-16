package com.lzt.operate.codetools.Assists;

import com.lzt.operate.codetools.domain.Operator;
import com.lzt.operate.utility.RequestAssist;
import lombok.var;

public class OperatorAssist {
    public static Operator GetCurrent() {
        var request = RequestAssist.getHttpServletRequest();

        var token = request.getHeader("token");

        return null;
    }
}
