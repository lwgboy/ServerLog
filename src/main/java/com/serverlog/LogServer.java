package com.serverlog;


import com.google.gson.Gson;
import com.serverlog.dto.DataDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 消息转发的中央处理,单例的类.
 * 此类负责的事情这里罗列一下：
 * 负责中央转发消息,调用 {@link LogServer#send(String)} 就可以发送到目标的客户端
 */
public class LogServer implements Client.OnMessageListener, Runnable {

    /**
     * Json 的转化器
     */
    public static final Gson gson = new Gson();

    /**
     * 这是一个心跳类型的数据
     */
    public static final String DATA_HEARTBEAT = gson.toJson(DataDto.HEARTBEAT);

    /**
     * 单例对象
     */
    public static final LogServer instance = new LogServer();

    /**
     * 获取单例对象
     *
     * @return
     */
    public static LogServer getInstance() {
        return instance;
    }

    // 构造函数私有化
    private LogServer() {
    }

    /**
     * 所有的客户端
     */
    private List<Client> mClients = new Vector<>();


    public final void addClient(@NotNull Client client) {
        mClients.add(client);
        // 设置每一个Client的监听
        client.setOnMessageListener(this);
    }

    public final void removeClient(@NotNull Client client) {
        mClients.remove(client);
    }

    @Override
    public synchronized void accept(@NotNull Client originClient, @NotNull String msg) {
        send(msg);
    }

    /**
     * 发送数据给所有满足条件的客户端
     *
     * @param data 要发送的数据
     */
    public synchronized void send(@NotNull String data) {
        try {
            // 解析成为 DataDto 对象
            DataDto dataDto = gson.fromJson(data, DataDto.class);
            // 如果是心跳包则不管
            if (DataType.TYPE_HEARTBEAT.equals(dataDto.getType())) {
                return;
            }
            // 获取数据的类型
            String type = dataDto.getType();
            // 获取到要发送的客户端
            List<Client> clients = getClientsWithType(type);
            for (Client entity : clients) {
                entity.send(data);
            }
        } catch (Exception ignore) {
            // ignore
        }
    }

    /**
     * 发送给指定的客户端
     *
     * @param targetName 客户端的唯一的名称
     * @param data       要发送的数据
     */
    public synchronized void sendToTarget(@NotNull String targetName, @NotNull String data) {
        if (targetName == null) {
            return;
        }
        // 获取到要发送的客户端
        List<Client> clients = mClients;
        Client targetClient = null;
        for (Client entity : clients) {
            if (targetName.equals(entity.getUniqueName())) {
                targetClient = entity;
            }
        }
        targetClient.send(data);
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
        for (Client client : mClients) {
            if (client.getInterestedType().contains(type)) {
                result.add(client);
            }
        }
        return result;
    }


    /**
     * 用于检测所有客户端是否还有效,否则踢出
     */
    @Override
    public void run() {
        // 一秒钟刷新一些存活的
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
            }
            // client 的个数
            int clientSize = mClients.size();
            for (int i = clientSize - 1; i >= 0; i--) {
                Client client = mClients.get(i);
                if (client.isDisable()) {
                    // 移出
                    mClients.remove(i);
                    client.close();
                }
            }
        }
    }


}
