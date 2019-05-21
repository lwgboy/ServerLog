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
     * 数据的类型
     */
    private String type;

    /**
     * 数据,数据类型决定了数据的格式
     *
     * @see #type
     * <p>
     * 数据的所有类型
     * @see #TYPE_NETWORK
     * @see #TYPE_TOMCAT_LOG
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
