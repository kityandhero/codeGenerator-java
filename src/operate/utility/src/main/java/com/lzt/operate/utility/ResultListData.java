package com.lzt.operate.utility;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * @author lzt
 * @date 2019-05-07 19:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultListData extends ResultDataCore {
    @ApiModelProperty(notes = "列表数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
    public ArrayList list;

    public ResultListData() {
        super();
        this.list = new ArrayList<>();
    }

    public ResultListData(int code, boolean success, String message) {
        this(code, success, message, new ArrayList<>());
    }

    private ResultListData(int code, boolean success, String message, ArrayList list) {
        this(code, success, message, list, new SerializableData());
    }

    ResultListData(int code, boolean success, String message, ArrayList list, SerializableData extra) {
        super(code, success, message, extra);
        this.list = list;
    }
}
