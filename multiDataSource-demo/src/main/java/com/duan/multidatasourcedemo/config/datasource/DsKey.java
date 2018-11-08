package com.duan.multidatasourcedemo.config.datasource;

/**
 * Created on 2018/11/8.
 *
 * @author DuanJiaNing
 */
public enum DsKey {

    DATASOURCE("datasource"),

    DB1("db1"),

    DB2("db2");

    private final String code;

    DsKey(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
