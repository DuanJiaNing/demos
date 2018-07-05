package com.duan.nettydemo.heartbeat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2018/7/5.
 *
 * @author DuanJiaNing
 */
@Configuration
public class HeartBeatConfig {

    @Value("${channel.id}")
    private long id;

    /*@Bean
    public CustomProtocol heartBeat() {
        return new CustomProtocol(id, "ping");
    }*/

}
