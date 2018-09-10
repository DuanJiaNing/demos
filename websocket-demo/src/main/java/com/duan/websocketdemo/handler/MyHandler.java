package com.duan.websocketdemo.handler;

import com.duan.websocketdemo.entity.UserTextMessage;
import com.duan.websocketdemo.manager.MessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Slf4j // 每次连接都会创建一个，不能用 DI 注入，除非使之为 static 成员
public class MyHandler implements WebSocketHandler {

    public static Map<Long, Set<WebSocketSession>> userSocketSessionMap = new ConcurrentHashMap<>();

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

        Set<WebSocketSession> sessions = userSocketSessionMap.get(msg.getTo());
        if (!CollectionUtils.isEmpty(sessions)) {
            MessageSender.sendMessages(sessions,
                    new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd-HH:mm:ss").create().toJson(msg)));
        }

    }

    @Override // 传输出错时回调
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {

        if (session.isOpen()) {
            session.close();
        }
        removeSession(session);

    }

    private void removeSession(WebSocketSession session) {

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
        removeSession(session);
    }

    @Override // 是否处理分片消息
    public boolean supportsPartialMessages() {
        return false;
    }

}
