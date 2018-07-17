package com.duan.nettydemo.echo.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created on 2018/7/17.
 * 处理数据
 *
 * @author DuanJiaNing
 */
@ChannelHandler.Sharable                                // 1
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", // 2 当被通知该 channel 是活动的时候就发送信息
                CharsetUtil.UTF_8));
    }

    // 数据后从服务器接收到调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        // 当服务器发送 5 个字节时并不保证所有的 5 个字节会立刻收到 - 即使是只有 5 个字节，channelRead0() 方法可被调用两次，
        // 第一次用一个ByteBuf（Netty的字节容器）装载3个字节和第二次一个 ByteBuf 装载 2 个字节。唯一要保证的是，该字节将按
        // 照它们发送的顺序分别被接收。 （注意，这是真实的，只有面向流的协议如TCP）。

        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));    //3
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}