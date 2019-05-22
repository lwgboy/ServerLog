package com.serverlog.dto;


import com.serverlog.DataType;

/**
 * 表示整个项目转发的数据类型
 */
public class DataDto {

    /**
     * 表示一个心跳的数据
     */
    public static final DataDto HEARTBEAT = new DataDto(DataType.TYPE_HEARTBEAT, null);

    /**
     * 目标接收者,如果有值的话会直接发送给此目标
     */
    private String receiver;

    /**
     * 数据的类型,如果 {@link #receiver} 为 null,表示消息是
     */
    private String type;

    /**
     * 数据,数据类型决定了数据的格式
     *
     * @see #type
     * <p>
     * 数据的所有类型
     * @see DataType#TYPE_NETWORK
     * @see DataType#TYPE_TOMCAT_LOG
     */
    private String data;

    public DataDto() {
    }

    public DataDto(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataDto{" +
                "type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

}
