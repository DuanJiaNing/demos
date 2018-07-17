package com.duan.nettydemo.echo.server;

import com.duan.nettydemo.echo.Const;
import com.duan.nettydemo.echo.server.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created on 2018/7/12.
 * 引导服务器
 * 监听和接收进来的连接请求
 * 配置 Channel 来通知一个关于入站消息的 EchoServerHandler 实例
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
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            // Bootstrap 引导服务器并随后绑定
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(Const.Server.port)) // 本地 InetSocketAddress 给服务器绑定
                    // 新的连接被接受，新的 Channel 将创建
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ChannelInitializer 会将 EchoServerHandler 添加到 pipeline 中
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture future = b.bind().sync(); //  绑定服务器
            log.info("server started and lister on " + future.channel().localAddress());
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
