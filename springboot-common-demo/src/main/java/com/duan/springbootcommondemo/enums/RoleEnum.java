package com.duan.springbootcommondemo.enums;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
public enum RoleEnum {

    ADMIN("ADMIN"),

    USER("USER"),

    MANAGER("MANAGER");

    private final String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
