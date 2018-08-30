package com.duan.websocketdemo.handler;

import com.duan.websocketdemo.annotations.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Slf4j
@WebSocketHandler
public class TextHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }
}
