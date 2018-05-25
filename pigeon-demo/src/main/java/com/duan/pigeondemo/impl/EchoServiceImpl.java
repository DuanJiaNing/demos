package com.duan.pigeondemo.impl;

import com.duan.pigeondemo.service.EchoService;

/**
 * Created on 2018/5/22.
 *
 * @author 段佳宁
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String msg) {
        return "Hello " + msg;
    }

}
