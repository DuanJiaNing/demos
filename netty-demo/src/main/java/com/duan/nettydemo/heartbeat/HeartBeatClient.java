package com.duan.nettydemo.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.channels.SocketChannel;

/**
 * Created on 2018/7/5.
 *
 * @author DuanJiaNing
 */
@Component
@Slf4j
public class HeartBeatClient {

    private EventLoopGroup loopGroup = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private int nettyPort;

    @Value("${netty.server.host}")
    private String host;

    private SocketChannel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new CustomerHandleInitializer());

        ChannelFuture future = bootstrap.connect(host, nettyPort).sync();
        if (future.isSuccess()) {
            log.info("netty client start success");
        }
        channel = ((SocketChannel) future.channel());
    }

    public class CustomerHandleInitializer extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline()
                    // 10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                    .addLast(new IdleStateHandler(0, 10, 0))
                    /*.addLast(new HeartBeatEncode())*/
                    .addLast(new EchoClientHandler());
        }
    }

}
