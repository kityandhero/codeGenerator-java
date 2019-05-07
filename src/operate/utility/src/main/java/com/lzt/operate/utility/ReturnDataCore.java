package com.lzt.operate.utility;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author lzt
 * @date 2019-05-07 19:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnDataCore extends SerializableData {
    @ApiModelProperty(notes = "返回码", example = "200", position = 1)
    public int code;

    @ApiModelProperty(notes = "是否执行成功", position = 2)
    public boolean success;

    @ApiModelProperty(notes = "消息描述", example = "success", position = 3)
    public String message;

    @ApiModelProperty(notes = "其他数据", position = 5)
    public HashMap<String, Serializable> extra;
}
