package com.serverlog.tomcatlog;

import okhttp3.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class TomcatApplication {

    public static void main(String[] args) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://localhost:8080/serverLog/websocket").build();

        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                webSocket.send("{type:\"setDisplayName\",data:\"helloworld\"}");
                webSocket.send("{type:\"setDataProviderType\",data:\"['1','2','3','4']\"}");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
            }
        });

        //SpringApplication.run(TomcatApplication.class, args);
    }

}