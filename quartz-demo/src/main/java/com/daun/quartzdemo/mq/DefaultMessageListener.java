package com.daun.quartzdemo.mq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018/4/28.
 *
 * @author 段佳宁
 */
@Component
public class DefaultMessageListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        StringBuilder b = new StringBuilder();
        msgs.forEach(msg -> b.append(new String(msg.getBody())));
        System.out.println("msg received "+b+" ---"+msgs.get(0).getTopic());
        return null;
    }

}
