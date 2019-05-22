package com.serverlog;

/**
 * 所有的数据类型
 */
public class DataType {

    private DataType() {
    }

    /**
     * 表示这是一个心跳包的类型
     */
    public static final String TYPE_HEARTBEAT = "--------heartbeat--------";

    /**
     * 客户端提供的名称
     */
    public static final String TYPE_SET_DISPLAY_NAME = "setDisplayName";

    /**
     * 客户端设置数据提供的类型
     */
    public static final String TYPE_SET_DATA_PROVIDER = "setDataProviderType";

    /**
     * 客户端设置自己感兴趣的类型的数据
     */
    public static final String TYPE_SET_DATA_SUBSCIBE = "setDataSubscibeType";

    /**
     * 表示获取某几个数据类型的数据提供 Client 的信息
     */
    public static final String TYPE_GETDATAPROVIDERS = "getDataProviders";

    // 下面的类型其实消息中转站不会用到,但是作为各个端自己统一的这里就全部罗列一下

    /**
     * 表示数据是一个网络请求的格式
     */
    public static final String TYPE_NETWORK = "network";

    /**
     * 数据是 tomcat 的日志
     */
    public static final String TYPE_TOMCAT_LOG = "tomcatLog";

}
