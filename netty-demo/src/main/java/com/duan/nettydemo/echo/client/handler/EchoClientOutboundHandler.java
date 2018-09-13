package com.duan.nettydemo.echo.client.handler;

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
@Slf4j
public class EchoClientOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
        Logger.info(log, "connect", "client connect to " + remoteAddress.toString());

    }
}
