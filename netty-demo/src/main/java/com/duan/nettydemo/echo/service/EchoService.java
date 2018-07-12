package com.duan.nettydemo.echo.service;

import com.duan.nettydemo.echo.handler.EchoServerHandler;
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
 *
 * @author DuanJiaNing
 */
@Slf4j
public class EchoService {

    private final int port;

    private EchoService(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            log.error("only need one arg!");
        }

        int port = Integer.parseInt(args[0]);
        new EchoService(port).start(); // 启动服务

    }

    private void start() throws Exception {

        // 使用 nio 进行传输
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 新的连接被接受，新的 Channel 将创建
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // ChannelInitializer 会将 EchoServerHandler 添加到 pipeline 中
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture future = b.bind().sync();
            log.info("server started and lister on " + future.channel().localAddress());
            future.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
