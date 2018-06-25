package com.duan.springdemo.player;

import com.duan.springdemo.disc.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisc disc;

    @Autowired
    public CDPlayer(CompactDisc disc) {
        this.disc = disc;
    }

    @Override
    public void play() {
        disc.play();
    }
}
