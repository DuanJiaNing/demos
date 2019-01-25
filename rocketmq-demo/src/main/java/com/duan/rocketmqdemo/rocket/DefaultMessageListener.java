package com.duan.rocketmqdemo.rocket;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2018/4/28.
 *
 * @author 段佳宁
 */
@Component
@Slf4j
public class DefaultMessageListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        StringBuilder b = new StringBuilder();
        msgs.forEach(msg ->
                b.append(new String(msg.getBody()))
                        .append(" msgId: ")
                        .append(msg.getMsgId())
                        .append(" topic: ")
                        .append(msg.getTopic())
        );
        log.info("msg received {}", b);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
