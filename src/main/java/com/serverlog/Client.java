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
    private String uniqueName = UUID.randomUUID().toString();

    protected OnMessageListener mOnMessageListener;

    /**
     * 向这个端发送数据,数据类型是 String
     *
     * @param data 发送的消息
     */
    public final void send(@NotNull String data) {
        try {
            doSend(data);
        } catch (Exception ignore) {
            // ignore
        }
    }

    /**
     * 执行发送信息
     *
     * @param data 发送的消息
     * @throws Exception 可能抛出的异常
     */
    protected abstract void doSend(@NotNull String data) throws Exception;

    /**
     * 获取客户端感兴趣的类型
     *
     * @return 可能感兴趣多重类型
     */
    @NotNull
    public abstract List<String> getInterestedType();

    /**
     * 获取唯一的名称
     *
     * @return
     */
    @NotNull
    public String getUniqueName() {
        return uniqueName;
    }

    /**
     * 处理数据
     *
     * @param data 客户端发送过来的数据
     */
    protected void relsoveMessage(@NotNull String data) {
        if (mOnMessageListener == null) {
            mOnMessageListener.accept(this, data);
        }
    }

    /**
     * 是否失效了
     *
     * @return 表示这个 Client 是否已经掉线了
     */
    public boolean isDisable() {
        try {
            doSend(LogServer.DATA_HEARTBEAT);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void setOnMessageListener(OnMessageListener l) {
        this.mOnMessageListener = l;
    }

    /**
     * 关闭这个Client
     */
    public abstract void close();

    /**
     * 接受消息的接口
     */
    public interface OnMessageListener {

        /**
         * 当客户端发送消息过来的时候,此方法会被调用
         *
         * @param client 发送消息的客户端实例
         * @param msg    客户端发送过来的消息
         */
        void accept(@NotNull Client client, @NotNull String msg);

    }

}
