package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import com.lzt.operate.utility.assists.ConvertAssist;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author luzhitao
 * @date 2019-05-07 19:43
 */
@Data
public abstract class BaseResultData implements Serializable {
    public static final int CODE_ACCESS_SUCCESS = 200;

    public static final String MESSAGE_ACCESS_SUCCESS = "OK";

    @ApiModelProperty(notes = "返回码", example = "200", position = 1)
    public int code;

    @ApiModelProperty(notes = "是否执行成功", position = 2)
    public boolean success;

    @ApiModelProperty(notes = "消息描述", example = "success", position = 3)
    public String message;

    @ApiModelProperty(notes = "其他数据", position = 5)
    public Serializable extra;

    BaseResultData() {
        this.code = ReturnDataCode.OK.getCode();
        this.success = ReturnDataCode.OK.getSuccess();
        this.message = ReturnDataCode.OK.getMessage();
        this.extra = new SerializableData();
    }

    protected BaseResultData(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.extra = new SerializableData();
    }

    protected BaseResultData(@NonNull ReturnDataCode returnDataCode, SerializableData extra) {
        this.code = returnDataCode.getCode();
        this.success = returnDataCode.getSuccess();
        this.message = returnDataCode.getMessage();
        this.extra = Optional.of(extra).orElse(new SerializableData());
    }

    public String serialize() {
        return ConvertAssist.serializeObject(this);
    }
}
