package com.duan.springdemo.disc;

import org.springframework.core.env.Environment;

/**
 * Created on 2018/7/2.
 *
 * @author DuanJiaNing
 */
public class MyDisc implements CompactDisc {

    private String name;

    public MyDisc() {

    }

    public MyDisc(Environment environment) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play(int trackNumber) {
        System.out.println("MyDisc#play#trackNumber = " + trackNumber);
    }

}
