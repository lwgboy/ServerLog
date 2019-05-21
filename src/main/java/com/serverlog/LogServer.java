package com.serverlog;


import com.google.gson.Gson;
import com.serverlog.dto.DataDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 消息转发的中央处理
 */
public class LogServer implements Client.OnMessageListener {

    public static final LogServer instance = new LogServer();

    public static LogServer getInstance() {
        return instance;
    }

    private LogServer() {
    }

    /**
     * 所有的客户端
     */
    private List<Client> clients = new Vector<>();

    private Gson gson = new Gson();


    public final void addClient(@NotNull Client client) {
        clients.add(client);
        // 设置每一个Client的监听
        client.setOnMessageListener(this);
    }

    public final void removeClient(@NotNull Client client) {
        client.setOnMessageListener(null);
        clients.remove(client);
    }


    @Override
    public synchronized void accept(@NotNull String msg) {

        // 解析成为 DataDto 对象
        DataDto dataDto = gson.fromJson(msg, DataDto.class);
        // 获取数据的类型
        String type = dataDto.getType();
        // 获取到要发送的客户端
        List<Client> clients = getClientsWithType(type);
        for (Client client : clients) {
            client.send(msg);
        }

    }

    /**
     * 获取对某一个 type 感兴趣的客户端
     *
     * @param type
     * @return
     */
    @NotNull
    private synchronized List<Client> getClientsWithType(@NotNull String type) {
        List<Client> result = new ArrayList<>();
        for (Client client : clients) {
            if (client.getInterestedType().contains(type)) {
                result.add(client);
            }
        }
        return result;
    }


}
