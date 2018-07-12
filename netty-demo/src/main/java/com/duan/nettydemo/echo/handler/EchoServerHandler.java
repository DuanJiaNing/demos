package com.duan.nettydemo.echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2018/7/12.
 * InBound 处理入站事件
 *
 * @author DuanJiaNing
 */
@ChannelHandler.Sharable // 标识这类的实例之间可以在 channel 里面共享
@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 建立连接时回调
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ctx = " + ctx);
    }

    // 每个信息入站都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;
        log.info("in.toString(CharsetUtil.UTF_8) = " + in.toString(CharsetUtil.UTF_8));
        ctx.write(msg); // 将数据返给发送者

    }

    // channelRead 处理的消息是当前批处理中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 冲刷数据到远程节点
                .addListener(ChannelFutureListener.CLOSE); // 数据发送后结束操作（这一次操作的 Future#isDone()）
    }

    // 读操作捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); // 关闭通道
    }
}
