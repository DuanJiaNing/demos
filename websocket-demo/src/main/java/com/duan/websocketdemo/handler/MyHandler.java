package com.duan.websocketdemo.handler;

import com.duan.websocketdemo.entity.UserTextMessage;
import com.duan.websocketdemo.manager.MessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Slf4j
@com.duan.websocketdemo.annotations.WebSocketHandler
public class MyHandler implements WebSocketHandler {

    private static Map<Long, Set<WebSocketSession>> userSocketSessionMap = new ConcurrentHashMap<>();

    @Autowired
    private MessageSender sender;

    @Override // 连接建立后回调
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Long uid = ((Long) session.getAttributes().get("uid"));
        if (!userSocketSessionMap.containsKey(uid)) {
            userSocketSessionMap.put(uid, new HashSet<>(Collections.singletonList(session)));
        }

        log.info("current online user count " + userSocketSessionMap.size());
    }

    @Override // 收到消息时回调
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        if (message.getPayloadLength() == 0) {
            return;
        }

        UserTextMessage msg = new Gson().fromJson(message.getPayload().toString(), UserTextMessage.class);
        msg.setDate(new Date());

        sender.sendMessages(userSocketSessionMap.get(msg.getTo()),
                new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd-HH:mm:ss").create().toJson(msg)));

    }

    @Override // 传输出错时回调
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {

        if (session.isOpen()) {
            session.close();
        }

        for (Map.Entry<Long, Set<WebSocketSession>> entry : userSocketSessionMap.entrySet()) {
            Long key = entry.getKey();
            Set<WebSocketSession> value = entry.getValue();

            if (value.contains(session)) {
                value.remove(session);
                if (value.size() == 0) {
                    userSocketSessionMap.remove(key);
                    log.info("remove sessions for user " + key);
                }
            }
        }
    }

    @Override // 连接关闭后回调
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        //noinspection Duplicates
        for (Map.Entry<Long, Set<WebSocketSession>> entry : userSocketSessionMap.entrySet()) {
            Long key = entry.getKey();
            Set<WebSocketSession> value = entry.getValue();

            if (value.contains(session)) {
                value.remove(session);
                if (value.size() == 0) {
                    userSocketSessionMap.remove(key);
                    log.info("remove sessions for user " + key);
                }
            }
        }
    }

    @Override // 是否处理分片消息
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     */
    public void broadcast(final TextMessage message) throws IOException {
        // 多线程群发
        for (Set<WebSocketSession> item : userSocketSessionMap.values()) {
            for (final WebSocketSession session : item) {
                if (session.isOpen()) {
                    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                            new BasicThreadFactory.Builder().namingPattern("socket-schedule-pool-%d").daemon(true).build());
                    for (int i = 0; i < 3; i++) {
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (session.isOpen()) {
                                        logger.debug("broadcast output:" + message.toString());
                                        session.sendMessage(message);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}
