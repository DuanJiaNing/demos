package com.duan.websocketdemo.controller;

import com.duan.websocketdemo.entity.UserTextMessage;
import com.duan.websocketdemo.handler.MyHandler;
import com.duan.websocketdemo.manager.MessageSender;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2018/8/28.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/socket")
public class WebSocketMsgController {

    @PostMapping("/{fromUid}/send/{toUid}")
    public String send(@RequestParam("msg") String msg, @PathVariable Long toUid, @PathVariable Long fromUid) {
        UserTextMessage textMessage = new UserTextMessage();
        textMessage.setDate(new Date());
        textMessage.setFrom(fromUid);
        textMessage.setTo(toUid);
        textMessage.setText("[系统广播-发送给目标] " + msg);

        Set<WebSocketSession> sessions = MyHandler.userSocketSessionMap.get(textMessage.getTo());
        MessageSender.sendMessages(sessions,
                new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(textMessage)));

        return "send to " + textMessage.toString();
    }

    @GetMapping("/{fromUid}/broadcast")
    public String broadcast(@RequestParam("msg") String msg, @PathVariable Long fromUid) {
        UserTextMessage textMessage = new UserTextMessage();
        textMessage.setDate(new Date());
        textMessage.setFrom(fromUid);
        textMessage.setText("[系统广播-群发] " + msg);

        Set<WebSocketSession> sessions = new HashSet<>();
        MyHandler.userSocketSessionMap.values().forEach(sessions::addAll);
        MessageSender.broadcast(sessions,
                new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(textMessage)));

        return "broadcast to " + textMessage.toString();
    }
}
