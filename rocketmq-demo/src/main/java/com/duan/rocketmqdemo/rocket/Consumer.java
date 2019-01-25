package com.duan.rocketmqdemo.rocket;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/4/28.
 *
 * @author 段佳宁
 */
@Component
public class Consumer extends BaseRocketMQ{

    @Value("${rocketmq.topic.test}")
    private String topic;

    private DefaultMQPushConsumer consumer;

    public void init(MessageListenerConcurrently messageListener) {

        // 用来表示一个消费消息应用，一个 Consumer Group 下包含多个 Consumer 实例（机器，进程，实例），
        // 集群方式下，同一个 Consumer Group 下的多个 Consumer 以均摊方式消费消息
        // 如果设置为广播方式，那么这个 Consumer Group 下的每个实例都消费全量数据
        consumer = new DefaultMQPushConsumer(consumerGroup);

        // Consumer 与 Name Server 集群中的其中一个节点（随机选择）建立长连接，定期从 Name Server 取 Topic 路由信息
        consumer.setNamesrvAddr(nameSrvAddr);

        try {

            // * 订阅一个主题
            consumer.subscribe(topic,null);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        consumer.registerMessageListener(messageListener);

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
