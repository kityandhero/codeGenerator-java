package com.lzt.operate.utility;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lzt
 * @date 2019-05-07 19:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultSingleData extends ResultDataCore {

    @ApiModelProperty(notes = "数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
    public SerializableData data;

    public ResultSingleData() {
        super();
        this.data = new SerializableData();
    }

    public ResultSingleData(int code, boolean success, String message) {
        this(code, success, message, new SerializableData());
    }

    private ResultSingleData(int code, boolean success, String message, SerializableData data) {
        this(code, success, message, data, new SerializableData());
    }

    ResultSingleData(int code, boolean success, String message, SerializableData data, SerializableData extra) {
        super(code, success, message, extra);
        this.data = data;
    }
}
