package com.duan.nettydemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;

import java.nio.charset.Charset;

import static io.netty.buffer.Unpooled.copiedBuffer;

/**
 * Created on 2018/7/12.
 *
 * @author DuanJiaNing
 */
public class Test {

    public void connect() {

        Bootstrap b = new Bootstrap();

        // Configure the connect timeout option.
        b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        // 异步连接到远程对等节点。调用立即返回并提供 ChannelFuture
        ChannelFuture f = b.connect("127.0.0.1", 8013);

        // 操作完成后通知注册一个 ChannelFutureListener
        f.addListener((ChannelFutureListener) future -> {
            // 检查操作的状态
            if (future.isSuccess()) {
                ByteBuf buffer = copiedBuffer("Hello", Charset.defaultCharset());
                ChannelFuture wf = future.channel().writeAndFlush(buffer);
                // ...
            } else {
                Throwable cause = future.cause();
                cause.printStackTrace();
            }
        });

        f.awaitUninterruptibly();

        // Now we are sure the future is completed.
        assert f.isDone();

        if (f.isCancelled()) {
            // Connection attempt cancelled by user
        } else if (!f.isSuccess()) {
            f.cause().printStackTrace();
        } else {
            // Connection established successfully
        }

    }

}
