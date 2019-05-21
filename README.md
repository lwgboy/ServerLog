
ServerLog
-------

`ServerLog` 本质上是一个消息中转站
和 `ServerLog` 消息中心建立连接有两种方式<bt/>
- `Socket` 连接
- `Websocket` 连接

每一个连接到 `ServerLog` 中心的的客户端都可以充当数据的提供者和数据的消费者.<br/>
甚至一个客户端可以是多种数据提供者的身份,同时也能是数据的消费者

### 顶层的固定数据格式

发送到 `ServerLog` 中心的任何一种数据都需要遵守以下的 `Json` 数据格式：

```
{
type:"<数据的类型>",
data:"<对应类型的数据,可能是任何格式,不做限制,除了内置的几种消息类型有格式的限制>"
}
```

### 数据交互内置的几种类型

- 设置 `Client` 自己的显示名称(displayName),type="setDisplayName"
- 设置 `Client` 自己的数据提供类型,type="setDataProviderType"
- 和 `ServerLog` 中心保持连接会使用到的心跳包类型,type="heartbeat"
- `Client` 去订阅自己感兴趣的数据的类型,type="subscibeData"
