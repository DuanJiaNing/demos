package com.duan.nettydemo.heartbeat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/5.
 *
 * @author DuanJiaNing
 */
@Slf4j
@Component
public class EchoClientHander extends SimpleChannelInboundHandler<ByteBuf> {

//    @Autowired
//    private CustomProtocol heartBeat;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent state = ((IdleStateEvent) evt);
            if (state.state() == IdleState.WRITER_IDLE) {
                log.error("no message send last 10 seconds");

                // 向服务端发送消息
                /*ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);*/

            }

        }

        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        log.info("receive message " + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
