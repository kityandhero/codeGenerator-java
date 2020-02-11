package com.lzt.operate.utility.pojo;

import com.lzt.operate.utility.enums.ReturnDataCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhitao
 * @date 2019-05-07 19:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultListData extends BaseResultData {
    @ApiModelProperty(notes = "列表数据体", example = SerializableData.EMPTY_SERIALIZE_VALUE, position = 4)
    public List list;

    public ResultListData() {
        super();
        this.list = new ArrayList<>();
    }

    public ResultListData(@NonNull ReturnDataCode returnDataCode) {
        this(returnDataCode, new ArrayList<>());
    }

    public ResultListData(@NonNull ReturnDataCode returnDataCode, ArrayList list) {
        this(returnDataCode, list, new SerializableData());
    }

    public ResultListData(@NonNull ReturnDataCode returnDataCode, ArrayList list, SerializableData extra) {
        super(returnDataCode, extra);
        this.list = list;
    }
}
