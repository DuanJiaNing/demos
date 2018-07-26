package com.duan.springdemo.aspect;

import com.duan.springdemo.disc.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/24.
 *
 * @author DuanJiaNing
 */
@Component
public class AspectJInvoker implements Performance {

    @Autowired
    private TrackCounter trackCounter;

    @Autowired
    @Qualifier("disc")
    private CompactDisc myDisc;

    @Override
    public void performance() {
        for (int i = 0; i < 13; i++) {
            myDisc.play(1);
        }

        System.out.println("PerformanceImpl.performance");
        System.out.println("trackCounter.getPlayCount(1) = " + trackCounter.getPlayCount(1));
    }
}
