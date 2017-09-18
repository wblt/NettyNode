package com.example.wb.nettynode.client;

import java.text.SimpleDateFormat;


import com.google.protobuf.InvalidProtocolBufferException;
import com.example.wb.nettynode.codec.DefConf;
import com.example.wb.nettynode.codec.NetCmdDataHeartDP;
import com.example.wb.nettynode.codec.NetCommand;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;


public class NodeClientHandler extends SimpleChannelInboundHandler<NetCommand> {

	private Channel channelHandlerContext;

	public NodeClientHandler() {

	}
	/**
	 * channelOpen、channelBound和channelConnected被合并为channelActive。
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("发送消息 开始");
		System.out.println("channelActive");
		System.out.println("发送消息 结束");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
		this.channelHandlerContext = ctx.channel();
		NetCommand cmd = (NetCommand) msg;
		System.out.println("接受到消息111"+cmd.getCmdCode());
		System.out.println("接受到消息222"+cmd.toString());
		System.out.println("消息类型===> : {}"+ cmd.getCmdType());
		// 心跳类型
		if (DefConf.HEART_COMMAND == cmd.getCmdType()) {
			// 一个心跳包发送过来了
			handleHeartMsg(ctx,cmd);
		} else { // 其他命令类型
			handleData(ctx,cmd);
		}
	}

	/**
	 * 进入和出去的正常数据的处理
	 * 
	 * @param ctx
	 * @param
	 */
	protected void handleData(ChannelHandlerContext ctx, NetCommand msg) {
		System.out.println("handleData 处理到达数据");


	}
	
    /**
     * 处理心跳响应信息
     * 
     * 客户端的场合 不要再响应信息了,会造成死循环
     * 
     * @param context
     */
    private void handleHeartMsg(ChannelHandlerContext context,NetCommand msg) {
    	//log.debug("处理心跳响应信息");
    	// 客户端响应处理心跳响应
    	if (null != msg 
    			&& DefConf.HEART_COMMAND == msg.getCmdType() 
    			&& "CT00000001".equals(msg.getCmdCode())) {
    		
    		// 获得参数的数据
    		byte[] heartDtoByts = msg.getData();
    		NetCmdDataHeartDP.NetCmdDataHeartDto heartDtoParam = null;
    		try {
    			// 反序列化得到的命令参数 用户名和密码对象
    			heartDtoParam = NetCmdDataHeartDP.NetCmdDataHeartDto.parseFrom(heartDtoByts);
    			
    			String heartTime = heartDtoParam.getTime();
    			
    			//log.debug("处理心跳响应信息处理:"+context.channel().remoteAddress() + heartTime);
    			
    		} catch (InvalidProtocolBufferException e) {
    			System.out.println("E00000001001-execute 反序列失败：" + e.getMessage());

    		}
    	}
    	// 客户端不做任何响应心跳的处理
    	System.out.println("处理心跳响应信息完成"+context.channel().remoteAddress());
    }

    public void sendLoginMsg(final Channel channel) {
		channelHandlerContext = channel;
		if (channel == null) {
			System.out.println("----管道为空------");
			return;
		}
		NetCommand netCommand = new NetCommand();
		netCommand.setVersion(2);
		// 命令Id
		netCommand.setCmdId("00000002");
		// 命令类型(心跳)
		netCommand.setCmdType(DefConf.LOGIN_COMMAND);
		// 命令编号(CT00000002 客户端向服务器端发送心跳包)
		netCommand.setCmdCode("CT00000002");
		channel.writeAndFlush(netCommand);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					while (true) {
//						Thread.sleep(2000);
//						NetCommand netCommand = new NetCommand();
//						netCommand.setVersion(2);
//						// 命令Id
//						netCommand.setCmdId("00000002");
//						// 命令类型(心跳)
//						netCommand.setCmdType(DefConf.MQ_COMMAND);
//						// 命令编号(CT00000002 客户端向服务器端发送心跳包)
//						netCommand.setCmdCode("CT00000002");
//						channel.writeAndFlush(netCommand);
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();

	}
	/**
	 * 向客户端发送心跳包
	 * 
	 * @param context
	 */
    protected void sendPingMsg(ChannelHandlerContext context) {
    	//log.info("向服务器端发送心跳包");
    	// 准备发送命令
		NetCommand netCommand = new NetCommand();
		netCommand.setVersion(2);
		// 命令Id
		netCommand.setCmdId("00000002");
		// 命令类型(心跳)
		netCommand.setCmdType(DefConf.HEART_COMMAND);
		// 命令编号(CT00000002 客户端向服务器端发送心跳包)
		netCommand.setCmdCode("CT00000002");
		// 命令参数
		NetCmdDataHeartDP.NetCmdDataHeartDto.Builder netCmdDataHeartDtoBuilder 
			= NetCmdDataHeartDP.NetCmdDataHeartDto.newBuilder();
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// new Date()为获取当前系统时间
		String nowTime = df.format(System.currentTimeMillis());
		netCmdDataHeartDtoBuilder.setTime(nowTime);
		// 21 服务器 到 客户端  12 客户端 到 服务器
		netCmdDataHeartDtoBuilder.setDirection(12);
		
		NetCmdDataHeartDP.NetCmdDataHeartDto netCmdDataHeartDto 
			= netCmdDataHeartDtoBuilder.build();
		netCommand.setData(netCmdDataHeartDto.toByteArray());
		context.channel().writeAndFlush(netCommand);
    }
    
    
    /**
	 * 空闲事件处理
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		// IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
	}
	
	protected void handleReaderIdle(ChannelHandlerContext ctx) {
        //log.info("---READER_IDLE---");
        //log.info("---client " + ctx.channel().remoteAddress().toString() + " reader timeout, close it---");
        // 客户端连接超时,关闭与客户端的连接
        //ctx.close();
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
    	//log.info("---WRITER_IDLE---");
    }

    /**
     * 客户端处理空闲
     * 只要有空闲下来 就隔段时间发送心跳包
     * @param ctx
     */
    protected void handleAllIdle(ChannelHandlerContext ctx) {
    	//log.info("---ALL_IDLE---");
    	// 向服务器端发送心跳包

		// sendPingMsg(ctx);
    }

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, NetCommand netCommand) throws Exception {
		System.out.println("---messageReceived---");
	}
}
