package com.zjj.dubbo;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class TcpTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(10270)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childHandler(new SimpleChannelInboundHandler<String>() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        Channel channel = ctx.channel();
                        System.out.println(channel + " 激活");
                        Bootstrap bootstrap = new Bootstrap();
                        bootstrap.group(worker)
                                .channel(NioSocketChannel.class)
                                .remoteAddress(new InetSocketAddress(10270))
                                .option(ChannelOption.SO_KEEPALIVE, true)
                                .option(ChannelOption.TCP_NODELAY, true)
                                .option(ChannelOption.SO_REUSEADDR, true)
                                .handler(new SimpleChannelInboundHandler<String>() {
                                    @Override
                                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                        System.out.println(ctx.channel() + " 激活");
                                    }

                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                        bootstrap.connect().sync();
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        System.out.println(msg);
                    }
                });
        Channel channel = serverBootstrap.bind().sync().channel();
        System.out.println(serverBootstrap + " 启动");
    }
}
