package com.duan.nettydemo.echo.server;

import com.duan.nettydemo.Logger;
import com.duan.nettydemo.echo.Const;
import com.duan.nettydemo.echo.server.handler.EchoServerInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created on 2018/7/12.
 * 引导服务器
 * 监听和接收进来的连接请求
 * 配置 Channel 来通知一个关于入站消息的 EchoServerInboundHandler 实例
 *
 * @author DuanJiaNing
 */
@Slf4j
public class EchoService {

    public static void main(String[] args) throws Exception {
        new EchoService().start(); // 启动服务

    }

    private void start() throws Exception {

        // 使用 nio 进行传输，线程池，将事件分配给处理该事件的 Handler ，如接受新的连接和读/写数据。
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // Bootstrap 引导服务器并随后绑定
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 2 个 EventLoopGroup
                    // 1. 持有一个绑定了本地端口的 socket
                    // 2. 所有创建的 Channel，处理服务器所接收到的客户端进来的连接
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(Const.Server.port)) // 本地 InetSocketAddress 给服务器绑定，绑定本地端口
                    // 新的连接被接受，新的 Channel 将创建
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ChannelInitializer 会将 EchoServerInboundHandler 添加到 pipeline 中
                            ch.pipeline().addLast(new EchoServerInboundHandler());
                        }
                    });

            ChannelFuture future = b.bind().sync(); //  绑定服务器
            Logger.info(log, "start", "server started and lister on " + future.channel().localAddress());
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
