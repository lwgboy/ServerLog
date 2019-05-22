package com.serverlog.controller;

import com.serverlog.LogServer;
import com.serverlog.dto.DataDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

/**
 * 消息接受的控制器
 */
@Controller
public class MessageController {

    /**
     * 发送一个数据
     *
     * @param data 客户端通过 http 发送上来的数据
     */
    @PostMapping("send")
    public void send(@NotNull DataDto data) {
        // 发送数据
        LogServer.getInstance().send(data);
    }

}
