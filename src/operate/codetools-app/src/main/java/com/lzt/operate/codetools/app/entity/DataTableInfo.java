package com.lzt.operate.codetools.app.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luzhitao
 */
@Data
public class DataTableInfo implements Serializable {
    public String name;

    public DataTableInfo() {
    }

    public DataTableInfo(String name) {
        this.name = name;
    }
}
