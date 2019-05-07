package com.lzt.operate.utility;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author lzt
 * @date 2019-05-07 19:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnList extends ReturnDataCore {
    @ApiModelProperty(notes = "列表数据体", example = SerializableData.EMPTY_SERIALIZE_VALU, position = 4)
    public ArrayList list;

    public ReturnList() {
    }

    public ReturnList(int code, boolean success, String message, ArrayList list, HashMap<String, Serializable> extra) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.list = list;
        this.extra = extra;
    }
}
