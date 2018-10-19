package com.duan.springmvcdemo.app.mvc;

/**
 * Created on 2018/10/19.
 *
 * @author DuanJiaNing
 */
public class SpecHandler {

    public static final String view_name = "view_name";
    private final String uri;

    public SpecHandler(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
