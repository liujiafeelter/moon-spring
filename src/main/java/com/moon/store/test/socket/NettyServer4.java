package com.moon.store.test.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.UnsupportedEncodingException;

public class NettyServer4 {

    public static void main(String[] args)throws Exception {
        NettyServer4 server = new NettyServer4();
        server.start();
    }

    public void start() throws Exception{
        EventLoopGroup parentGroup = new NioEventLoopGroup();//负责处理TCP/IP连接
        EventLoopGroup childGroup = new NioEventLoopGroup();//负责处理Channel（通道）的I/O事件

        ServerBootstrap server = new ServerBootstrap();
        server.group(parentGroup, childGroup);
        server.channel(NioServerSocketChannel.class);
        server.localAddress(8080);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyServerHandler());
            }
        });
        ChannelFuture f = server.bind().sync();
        f.channel().closeFuture().sync();
    }

}
class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 功能：读取服务器发送过来的信息
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        String rev = this.getMessage(buf);
        System.out.println("客户端收到服务器数据:" + rev);
    }

    /**
     * @param buf
     * @return
     */
    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


}