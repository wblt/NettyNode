package com.example.wb.nettynode.codec;

import java.nio.ByteBuffer;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.example.wb.nettynode.codec.BBBNetUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NetDecoder extends LengthFieldBasedFrameDecoder {

    private static final int FRAME_MAX_LENGTH = 16777216;

    public NetDecoder() {
        super(FRAME_MAX_LENGTH, 0, 4, 0, 0);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    	ByteBuf frame = null;
        try {
            frame = (ByteBuf) super.decode(ctx, in);
            if (null == frame) {
                return null;
            }

            ByteBuffer byteBuffer = frame.nioBuffer();
            byte[] outData = new byte[byteBuffer.remaining()];
            byteBuffer.get(outData, 0, outData.length);

            NetCommand netCommand = new NetCommand();
            netCommand.decodeCmd(outData);
            
            return netCommand;
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("decode exception, " + BBBNetUtil.parseChannelRemoteAddr(ctx.channel())+e);
            BBBNetUtil.closeChannel(ctx.channel());
        } finally {
            if (null != frame) {
                frame.release();
            }
        }

        return null;
    }

}
