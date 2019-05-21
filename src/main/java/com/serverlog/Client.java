package com.serverlog;


import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * 这是一个抽象类,表示连接上来的一个端
 * 可能是 Socket 也可能是 WebSocket
 */
public abstract class Client {

    /**
     * 这个端的名称,这个名称要保证唯一,
     * 在客户端连接上服务器的时候,服务器会自动
     * 生成一个唯一的名称,之后客户端需要告诉服务器自己是谁
     * (其实就是给自己起个名字别名,比如 Android 端的某个机器为 '一嗨租车 huawei 8.0')
     * 唯一的名字用来标记每一个客户端,别名用来展示
     */
    private String name = UUID.randomUUID().toString();

    protected OnMessageListener mOnMessageListener;

    /**
     * 向这个端发送数据,数据类型是 String
     *
     * @param data
     */
    public abstract void send(@NotNull String data);

    /**
     * 获取客户端感兴趣的类型
     *
     * @return 可能感兴趣多重类型
     */
    @NotNull
    public abstract List<String> getInterestedType();

    public String getName() {
        return name;
    }

    /**
     * 处理数据
     *
     * @param data 客户端发送过来的数据
     */
    protected void relsoveMessage(@NotNull String data) {
        if (mOnMessageListener == null) {
            mOnMessageListener.accept(data);
        }
    }

    public void setOnMessageListener(OnMessageListener l) {
        this.mOnMessageListener = l;
    }

    /**
     * 接受消息的接口
     */
    public interface OnMessageListener {

        /**
         * 当客户端发送消息过来的时候,此方法会被调用
         *
         * @param msg 客户端发送过来的消息
         */
        void accept(@NotNull String msg);

    }

}
