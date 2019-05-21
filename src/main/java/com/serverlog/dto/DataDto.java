package com.serverlog.dto;


/**
 * 表示整个项目转发的数据类型
 */
public class DataDto {

    /**
     * 表示数据是一个网络请求的格式
     */
    public static final String TYPE_NETWORK = "network";

    /**
     * 数据是 tomcat 的日志
     */
    public static final String TYPE_TOMCAT_LOG = "tomcatLog";

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
