package com.duan.springdemo.player;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/12.
 *
 * @author DuanJiaNing
 */
@Component
@Qualifier("dvd")
public class DVDPlayer implements MediaPlayer {
    @Override
    public void play() {

    }
}
