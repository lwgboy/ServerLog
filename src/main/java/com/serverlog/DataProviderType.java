package com.serverlog;

/**
 * 所有的数据类型
 */
public class DataProviderType {

    private DataProviderType() {
    }

    /**
     * 表示一个网络请求数据的提供者
     */
    public static final String TYPE_NETWORK = "networkProvider";

    /**
     * 表示这是一个 tomcat 的 log 数据的提供类者
     */
    public static final String TYPE_TOMCAT_LOG = "tomcatLogProvider";

}
