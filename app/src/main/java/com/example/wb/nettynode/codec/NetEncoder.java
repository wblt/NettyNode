package com.example.wb.nettynode.codec;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.example.wb.nettynode.codec.BBBNetUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NetEncoder extends MessageToByteEncoder<NetCommand> {
	
//	public final static Logger log = LoggerFactory.getLogger(NetEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NetCommand msg, ByteBuf out) throws Exception {
			
		try {
			
			byte[] outData = msg.encodeCmd();

			out.writeBytes(outData);

        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("encode exception, " + BBBNetUtil.parseChannelRemoteAddr(ctx.channel())+ e);
            if (null != msg) {
				System.out.println(msg.toString());
            }
            BBBNetUtil.closeChannel(ctx.channel());
        }
	}
}
