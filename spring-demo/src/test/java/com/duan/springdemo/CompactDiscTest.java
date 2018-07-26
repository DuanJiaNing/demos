package com.duan.springdemo;


import com.duan.springdemo.disc.CompactDisc;
import com.duan.springdemo.player.CDPlayer;
import com.duan.springdemo.player.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2018/6/25.
 *
 * @author DuanJiaNing
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CompactDiscTest {

    @Autowired
    @Qualifier("sgtPeppers")
    private CompactDisc compactDisc;

    @Autowired
    @Qualifier("setPeppers")
    private CompactDisc compactDisc2;

    @Autowired
    private CDPlayer cdPlayer;

    @Test
    public void play() {
        compactDisc.play(1);
        System.out.println(compactDisc);
        System.out.println(compactDisc2);
        cdPlayer.play();
    }


}