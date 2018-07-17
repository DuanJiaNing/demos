package com.duan.nettydemo.echo.client;

import com.duan.nettydemo.echo.Const;
import com.duan.nettydemo.echo.client.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created on 2018/7/17.
 *
 * @author DuanJiaNing
 */
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
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(Const.Client.host, Const.Client.port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync(); // 连接到远程;等待连接完成

            f.channel().closeFuture().sync(); // 阻塞直到 Channel 关闭
        } finally {
            group.shutdownGracefully().sync(); // 关闭线程池和释放所有资源
        }
    }
}
