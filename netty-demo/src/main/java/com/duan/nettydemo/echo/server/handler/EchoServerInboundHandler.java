package com.duan.nettydemo.echo.server.handler;

import com.duan.nettydemo.Logger;
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
 * InBound 处理入站事件（服务器到用户的数据）
 * 服务器 handler：这个组件实现了服务器的业务逻辑，决定了连接创建后和接收到信息后该如何处理
 *
 * @author DuanJiaNing
 */
@ChannelHandler.Sharable // 标识这类的实例之间可以在 channel 里面共享
@Slf4j
public class EchoServerInboundHandler extends ChannelInboundHandlerAdapter {

    // 建立连接时回调
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Logger.info(log, "channelActive", "ctx = " + ctx);
    }

    // 每个信息入站都会调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;
        Logger.info(log, "channelRead", "channelRead in.toString(CharsetUtil.UTF_8) = " + in.toString(CharsetUtil.UTF_8));
        ctx.write(msg); // 将数据返给发送者，临时保存，并不发送，在 channelReadComplete#writeAndFlush 中才发送

    }

    // channelRead 处理的消息是当前批处理中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Logger.info(log, "channelReadComplete", null);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 冲刷数据到远程节点
                .addListener(ChannelFutureListener.CLOSE); // 数据发送后结束操作（这一次操作的 Future#isDone()）
    }

    // 读操作捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Logger.error(log, "exceptionCaught", cause.getMessage());
        cause.printStackTrace();
        ctx.close(); // 关闭通道
    }
}
