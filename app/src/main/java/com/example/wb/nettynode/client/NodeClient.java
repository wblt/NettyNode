package com.example.wb.nettynode.client;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.example.wb.nettynode.NettyConstant;
import com.example.wb.nettynode.codec.NetDecoder;
import com.example.wb.nettynode.codec.NetEncoder;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * 计算节点客户端
 * 
 * 
 * @author xwood
 * @version 20161017
 *
 */
public class NodeClient {

	private Channel channel;
	private Bootstrap clientBootstrap;
	private NodeClientHandler nodeClientHandler;
	private ScheduledExecutorService executor = Executors
			.newScheduledThreadPool(1);
	// 配置信息
	/**
	 * 服务器开始
	 */
	public void connect() {
		System.out.println("连接服务器开始");
		EventLoopGroup eventLoopGroupWorker   = new NioEventLoopGroup();
		try{
			clientBootstrap = new Bootstrap();

			nodeClientHandler = new NodeClientHandler();

			clientBootstrap
	                .group(eventLoopGroupWorker)
	                .channel(NioSocketChannel.class)
	                // BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
	                // 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
	                //.option(ChannelOption.SO_BACKLOG, 1024)
	                // 地址是否可复用（UDP socket address绑定时用到）
	                // 作用是重用处于TIME_WAIT但是未完全关闭的socket地址
	                .option(ChannelOption.SO_REUSEADDR, true)
	                .option(ChannelOption.TCP_NODELAY, true)
	                // 长连接
	                .option(ChannelOption.SO_KEEPALIVE, true)
	                // 发送和接受的缓冲大小
	                .option(ChannelOption.SO_SNDBUF, 66535)
	                //
	                .option(ChannelOption.SO_RCVBUF, 66535)
	                // 
	                .handler(new ChannelInitializer<SocketChannel>() {
	                            @Override
	                            public void initChannel(SocketChannel ch) throws Exception {
                        		// 空闲超时时间的控制
                        		ch.pipeline().addLast(
                        			 "idleStateHandler",
                        			 new IdleStateHandler(10,
											 20,
											 30));
	                                ch.pipeline().addLast(
	                                		new NetDecoder(),
	                                        new NetEncoder(),
											nodeClientHandler
	                                        );
	                            }
	                });
			
				System.out.println("IP:"+NettyConstant.LOCALIP);
				System.out.println("PORT:"+NettyConstant.PORT);
				
				// 启动客户端	
				doConnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连操作
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
						try {
							connect();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("连接服务器结束");
		System.out.println("连接服务器结束");
	}
	
	/**
	 * 连接服务器
	 */
    protected void doConnect() throws InterruptedException {

        if (channel != null && channel.isActive()) {
            return;
        }
		// 发起异步连接操作
		ChannelFuture future = clientBootstrap.connect(
				new InetSocketAddress(NettyConstant.REMOTEIP, NettyConstant.PORT),
				new InetSocketAddress(NettyConstant.LOCALIP,
						NettyConstant.LOCAL_PORT)).sync();
		future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture futureListener) throws Exception {
				if (futureListener.isSuccess()) {
					// 保存好连接频道
					channel = futureListener.channel();
					System.out.println("连接服务器成功!");

					// 发送消息
					nodeClientHandler.sendLoginMsg(channel);
				} else {
					System.out.println("连接服务器失败! 过10秒后将会常识重新连接服务器");
					futureListener.channel().eventLoop().schedule(new Runnable() {
						@Override
						public void run() {
							try {
								doConnect();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}, 10, TimeUnit.SECONDS);
				}
			}
		});
		future.channel().closeFuture().sync();
    }

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new NodeClient().connect();
	}

}
