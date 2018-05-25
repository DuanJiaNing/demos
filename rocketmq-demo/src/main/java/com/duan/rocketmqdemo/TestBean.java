package com.duan.rocketmqdemo;

import com.duan.rocketmqdemo.rocket.Consumer;
import com.duan.rocketmqdemo.rocket.DefaultMessageListener;
import com.duan.rocketmqdemo.rocket.Producer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/4/28.
 *
 * @author 段佳宁
 */
@Component
public class TestBean implements InitializingBean {

    @Value("${rocketmq.topic.test}")
    private String topic;

    @Value("${rocketmq.tag.test}")
    private String tag;

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Autowired
    private DefaultMessageListener listener;

    @Override
    public void afterPropertiesSet(){

        consumer.init(listener);
        producer.init();

    }

    public void send(Object obj){
        producer.send(obj.toString(),topic,tag);
    }

}


