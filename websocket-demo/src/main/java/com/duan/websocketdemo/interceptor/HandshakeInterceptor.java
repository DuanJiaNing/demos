package com.duan.websocketdemo.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Websocket: 用户[ID:"
                + ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("uid")
                + "]已经建立连接");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            // 标记用户
            Long uid = (Long) session.getAttribute("uid");
            if (uid != null) {
                // https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#websocket
//                attributes.put("uid", uid);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {

    }
}
