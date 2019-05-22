
ServerLog
-------

`ServerLog` 本质上是一个消息中转站
和 `ServerLog` 消息中心建立连接有两种方式<bt/>
- `Socket` 连接
- `Websocket` 连接

每一个连接到 `ServerLog` 中心的的客户端都可以充当数据的提供者和数据的消费者.<br/>
甚至一个客户端可以是多种数据提供者的身份,同时也能是数据的消费者

`ServerLog` 中心为每一个连接上来的连接都会了一个 `Client` 对象.`Client` 表示一个连接成功的端.

实现的功能
----

- `Client` 能设置自己的显示名称
- `Client` 能设置自己能提供的数据类型
- `Client` 能设置自己感兴趣的数据类型
- `Client` 能给另一个 `Client` 发送消息
- `Client` 能获取某种数据类型提供者的信息(是哪些 `Client`)

### 顶层的固定数据格式

- `type` 表示数据的类型,如果没有特别指定目标 `Client`,那么消息就是发送给订阅此类 `type` 的所有 `Client`

发送到 `ServerLog` 中心的任何一种数据都需要遵守以下的 `Json` 数据格式：

```
{
    receiver:"<可以不传,传了表示消息是有目标的>",
    type:"<数据的类型>",
    data:"<对应类型的数据,可能是任何格式,不做限制,除了内置的几种消息类型有格式的限制>"
}

```

`ServerLog` 中心发送出去的数据都是以下的 `json` 格式

```
{
    sender:"<发送消息的是谁,这个是 ServerLog 中心对每一个消息转发的时候都会加上的>",
    type:"<数据的类型>",
    data:"<对应类型的数据,可能是任何格式,不做限制,除了内置的几种消息类型有格式的限制>" 
}
```

### 数据交互内置的几种类型

- 和 `ServerLog` 中心保持连接会使用到的心跳包类型,type="--------heartbeat--------"
- 设置自己的显示名称(displayName),type="setDisplayName"
- 设置自己的数据提供类型,type="setDataProviderType"
- 订阅订阅自己感兴趣的数据的类型,type="setDataSubscibeType"
- 获取某几种数据类型的数据提供者的信息,type="getDataProviders"
- 发送消息给某一个 `Client`,type="forward@<这里写目标的uid>"

#### setDisplayName

`Client` 实例会有一个默认的名称,但是这并不能让用户看了就能知道是什么设备的连接,
所以需要您通过以下方式传递数据设置您想设置的 `displayName`

当您传递以下的数据格式的数据, `data` 中的值就会被采纳作为 `Client` 的 `displayName`

```
{
    type:"setDisplayName",
    data:"<String类型的文本>"
}
```

#### setDataProviderType

和上述的 `setDisplayName` 类似,通过以下的数据格式设置您这个 `Client` 对外提供的数据类型是哪些<br/>


```
{
    type:"setDataProviderType",
    data:"['network',"tomcatLog",......]"
}
```

#### setDataSubscibeType

通过以下的方式订阅自己感兴趣的数据类型,可以是多个,当你设置成功了之后才能收到对应类型的数据

```
{
    type:"setDataSubscibeType",
    data:"['network',"tomcatLog",......]"
}
```

#### getDataProviders

这个功能是获取某一种或者几种数据类型的提供者的 `Client` 的信息

发送格式为 

```
{
    type:"getDataProviders",
    data:"['network',"tomcatLog",......]"
}
```

返回的信息为：

```
{
    type:"returnDataProviders",
    data:"[
        {
            uid : "<Client唯一的标识>",
            displayName : "<Client显示的名称>"
        },
        {
            uid : "<Client唯一的标识>",
            displayName : "<Client显示的名称>"
        },
        ......
    ]"
}
```