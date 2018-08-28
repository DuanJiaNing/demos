package com.duan.websocketdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Slf4j
@com.duan.websocketdemo.annotations.WebSocketHandler
public class MyHandler implements WebSocketHandler {

    @Override // 连接建立后回调
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    }

    @Override // 收到消息时回调
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    @Override // 传输出错时回调
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override // 连接关闭后回调
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override // 是否处理分片消息
    public boolean supportsPartialMessages() {
        return false;
    }

}
