package com.zjj.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AIOServer {
    public static void main(String[] args) {
        try (AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open()) {
            socketChannel.bind(new InetSocketAddress(20010));
            socketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    socketChannel.accept(attachment, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.err.println("error: " + exc.getMessage());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
