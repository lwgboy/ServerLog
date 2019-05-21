package com.serverlog;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

/**
 * 针对 websocket 协议的
 */
@Component
@ServerEndpoint(value = "/websocket")
public class WebSocketClient extends Client {

    @Override
    public void send(@NotNull String data) {

    }

    @Override
    public @NotNull List<String> getInterestedType() {
        return null;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("123123123");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(Session session, String message) {
    }


    @OnError
    public void onError(Session session, Throwable error) {
    }

}
