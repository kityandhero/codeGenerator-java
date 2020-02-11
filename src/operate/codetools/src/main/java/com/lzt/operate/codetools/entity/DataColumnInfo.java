package com.lzt.operate.codetools.entity;

import lombok.Data;

/**
 * @author luzhitao
 */
@Data
public class DataColumnInfo {
    /**
     * 表标示
     */
    public String tableId;

    /**
     * 列明
     */
    public String name;

    /**
     * 列数据类型
     */
    public String type;

    /**
     * 列别名
     */
    public String alias;
}
