package com.duan.nettydemo.echo.client.handler;

import com.duan.nettydemo.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2018/9/13.
 *
 * @author DuanJiaNing
 */
@Slf4j
public class EchoClientInboundFilterHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object in) throws Exception {
        Logger.info(log, "channelRead", "Client received in filter: " + in.toString());
        super.channelRead(ctx, in); // 使在 pipeline 继续传递
    }


}
