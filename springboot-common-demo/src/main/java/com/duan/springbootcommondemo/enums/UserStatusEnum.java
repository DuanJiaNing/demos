package com.duan.springbootcommondemo.enums;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
public enum UserStatusEnum {

    DISABLE(0),

    ENABLE(1);

    private final int code;

    UserStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
