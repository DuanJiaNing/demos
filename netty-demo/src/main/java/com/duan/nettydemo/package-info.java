/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
package com.duan.nettydemo;

/*

1 EventLoopGroup -> * EventLoop
1 BootStrap -> 1 Netty 容器 -> * Channel
1 Channel -> 1 EventLoop(单独的线程) ->handle * "Channel 事件"
1 Channel -> * ChannelHandler
1 Channel -> 1 ChannelPipeline

1. EventLoopGroup: 可承载多个 EventLoop，Bootstrap 引导启动一个 Netty 容器，一个 Netty 容器可处理多个连接，即对应到多个
   Channel
2. EventLoop: 一个 EventLoop 用于处理一个 Channel 的 I/O 操作。一个单一的 EventLoop 通常会处理多个 "Channel 事件"，应用程
   序不需要同步 Netty 的 I/O操作;所有 Channel 的 I/O 始终用相同的线程来执行。
3. ChannelHandler: 监听特定的 Channel 事件，如 ChannelInboundHandler 类型接收入站事件（包括接收到的数据），可处理应用程序
   逻辑，可以进行数据冲刷，这些 ChannelHandler 会在程序的 "引导" 阶段被添加 ChannelPipeline 中，并且被添加的顺序将决定处理数据的顺序。
4. Channel: 每个 Channel 都有自己的 ChannelPipeline，当 Channel 创建时自动创建。 ChannelInitializer 实现了 ChannelHandler
   的抽象 ChannelInitializer 子类通过 ServerBootstrap 回调 initChannel()  方法先注册自己。注册后再将自定义的 ChannelHandler
   安装到 pipeline 中。操作完成时 ChannelInitializer 将从 ChannelPipeline 中删除自身
5. ChannelFuture: Netty 所有的 I/O 操作都是异步。因为一个操作可能无法立即返回，我们需要有一种方法在以后确定它的结果。出于
   这个目的，Netty 提供了接口 ChannelFuture,它的 addListener 方法注册了一个 ChannelFutureListener ，当操作完成时，可以被
   通知（不管成功与否）


outbound 若数据是从用户应用程序到远程主机则是 "出站"
inbound 相反若数据从远程主机到用户应用程序则是 "入站"。
 */
