package com.duan.websocketdemo.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2018/8/30.
 *
 * @author DuanJiaNing
 */
@Slf4j
@Component
public class MessageSender {

    private static final ExecutorService executorService = Executors.newScheduledThreadPool(10, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

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


    /**
     * 给所有在线用户发送消息
     */
    public void broadcast(Set<WebSocketSession> sessions, WebSocketMessage<?> message) {

        sessions.forEach(session -> executorService.execute(() -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                    log.debug("broadcast output:" + message.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
