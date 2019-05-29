package com.lzt.operate.codetools.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lzt
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
