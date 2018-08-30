package com.duan.websocketdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import java.util.Map;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {

        Object uid = ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid");
        log.info("Websocket: user [uid:" + uid + "] connected");

        if (uid != null) {
            // https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#websocket
            attributes.put("uid", uid);
        } else {
            return false;
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {

    }
}
