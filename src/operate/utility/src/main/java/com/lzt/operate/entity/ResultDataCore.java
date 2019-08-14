package com.lzt.operate.entity;

import com.lzt.operate.utility.Convert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author lzt
 * @date 2019-05-07 19:43
 */
@Data
public abstract class ResultDataCore implements Serializable {
    @ApiModelProperty(notes = "返回码", example = "200", position = 1)
    public int code;

    @ApiModelProperty(notes = "是否执行成功", position = 2)
    public boolean success;

    @ApiModelProperty(notes = "消息描述", example = "success", position = 3)
    public String message;

    @ApiModelProperty(notes = "其他数据", position = 5)
    public Serializable extra;

    ResultDataCore() {
        this.code = 0;
        this.success = false;
        this.message = "";
        this.extra = new SerializableData();
    }

    ResultDataCore(int code, boolean success, String message, SerializableData extra) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.extra = Optional.of(extra).orElse(new SerializableData());
    }

    public String serialize() {
        return Convert.serializeObject(this);
    }
}
