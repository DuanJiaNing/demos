package com.duan.springdemo.disc;

import org.springframework.stereotype.Component;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@Component
public class SgtPeppers implements CompactDisc {

    public void play(int trackNumber) {
        System.out.println(this.getClass().getCanonicalName());
    }

}
