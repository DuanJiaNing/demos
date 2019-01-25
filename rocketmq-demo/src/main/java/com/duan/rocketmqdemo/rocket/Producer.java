package com.duan.rocketmqdemo.rocket;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/4/27.
 *
 * @author 段佳宁
 */
@Component
public class Producer extends BaseRocketMQ{

    private DefaultMQProducer producer;

    public void init() {

        // Producer Group 表示一个发送消息应用，一个 Producer Group 下包含多个 Producer 实例（机器，进程，实例）
        producer = new DefaultMQProducer(producerGroup);

        // 与 Name Server 集群中的其中一个节点（随机选择）建立长连接，定期从 Name Server 取 Topic 路由信息
        producer.setNamesrvAddr(nameSrvAddr);

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    public SendResult send(String msg, String topic,String tag){
        Message msag = new Message(topic, tag,msg.getBytes());
//        msag.putUserProperty();
//        msag.setDelayTimeLevel();

        try {
            return producer.send(msag);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void shutdown() {
        if (producer != null) {
            producer.shutdown();
        }
    }
}
