package com.duan.nettydemo.echo.client;

import com.duan.nettydemo.Logger;
import com.duan.nettydemo.echo.Const;
import com.duan.nettydemo.echo.client.handler.EchoClientInboundHandler;
import com.duan.nettydemo.echo.client.handler.EchoClientOutboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Created on 2018/7/17.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class EchoClient {

    public static void main(String[] args) throws Exception {
        new EchoClient().start();
    }

    private void start() throws Exception {

        // 分配给处理该事件的 Handler，这包括创建新的连接和处理入站和出站数据
        // 一个单一的 EventLoop 通常会处理多个 Channel 事件。
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    // 1 个 EventLoopGroup
                    // 1. 所有连接到服务器的 Channel
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(Const.Client.host, Const.Client.port)) // 连接到远程主机端口
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new EchoClientInboundHandler())
                                    .addLast(new EchoClientOutboundHandler());
                        }
                    });

            ChannelFuture future = b.connect().sync(); // 连接到远程 等待连接完成
            Logger.info(log, "start", "client started and lister on " + future.channel().localAddress());

            waitUnitQuit(future);

        } finally {
            group.shutdownGracefully().sync(); // 关闭线程池和释放所有资源
        }
    }

    private void waitUnitQuit(ChannelFuture future) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("quit")) {
                future.channel().closeFuture().sync(); // 阻塞直到 Channel 关闭
                break;
            }
        }
    }
}
