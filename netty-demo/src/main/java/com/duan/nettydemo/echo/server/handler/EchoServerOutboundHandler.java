package com.duan.nettydemo.echo.server.handler;

import com.duan.nettydemo.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
@Slf4j // 貌似服务端不会收到 out-bound 事件
public class EchoServerOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
        Logger.info(log, "connect", "service connected by " + remoteAddress.toString());
    }

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.bind(ctx, localAddress, promise);
        Logger.info(log, "connect", "service be bind to " + localAddress.toString());
    }
}
