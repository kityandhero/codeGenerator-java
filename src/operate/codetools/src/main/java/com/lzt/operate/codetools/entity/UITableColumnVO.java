package com.lzt.operate.codetools.entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Owen on 6/22/16.
 */
public class UITableColumnVO {

    /**
     * Default set to true
     */
    private BooleanProperty checked;

    private StringProperty columnName;

    private StringProperty javaType;

    private StringProperty jdbcType;

    private StringProperty propertyName;

    private StringProperty typeHandle;

    public UITableColumnVO() {
        this.checked = new SimpleBooleanProperty(true);

        this.columnName = new SimpleStringProperty();

        this.javaType = new SimpleStringProperty();

        this.jdbcType = new SimpleStringProperty();

        this.propertyName = new SimpleStringProperty();

        this.typeHandle = new SimpleStringProperty();
    }

    public String getColumnName() {
        return this.columnName.get();
    }

    public void setColumnName(String columnName) {
        this.columnName.set(columnName);

        if (this.propertyName.isEmpty().get()) {
            this.propertyName.set(columnName);
        }
    }

    public String getJdbcType() {
        return this.jdbcType.get();
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType.set(jdbcType);
    }

    public String getPropertyName() {
        return this.propertyName.get();
    }

    public void setPropertyName(String propertyName) {
        this.propertyName.set(propertyName);
    }

    public BooleanProperty checkedProperty() {
        return this.checked;
    }

    public Boolean getChecked() {
        return this.checked.get();
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public StringProperty typeHandleProperty() {
        return this.typeHandle;
    }

    public String getTypeHandle() {
        return this.typeHandle.get();
    }

    public void setTypeHandle(String typeHandle) {
        this.typeHandle.set(typeHandle);
    }

    public StringProperty columnNameProperty() {
        return this.columnName;
    }

    public StringProperty jdbcTypeProperty() {
        return this.jdbcType;
    }

    public StringProperty propertyNameProperty() {
        return this.propertyName;
    }

    public String getJavaType() {
        return this.javaType.get();
    }

    public void setJavaType(String javaType) {
        this.javaType.set(javaType);
    }

    public StringProperty javaTypeProperty() {
        return this.javaType;
    }
}
