package com.duan.websocketdemo.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;

/**
 * Created on 2018/8/30.
 *
 * @author DuanJiaNing
 */
@Slf4j
@Component
public class MessageSender {

    public void sendMessages(Set<WebSocketSession> sessions, WebSocketMessage<?> message) {

        sessions.forEach(sess -> {
            try {
                sess.sendMessage(message);
                log.info("send message [" + message.toString() + "]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
