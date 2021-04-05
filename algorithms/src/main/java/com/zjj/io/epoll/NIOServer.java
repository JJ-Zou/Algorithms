package com.zjj.io.epoll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) {
        try (ServerSocketChannel socketChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            socketChannel.bind(new InetSocketAddress(20010), 1024);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int select = selector.select();
                System.out.println(select);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isAcceptable()) {
                        SocketChannel channel = ((ServerSocketChannel) selectionKey.channel()).accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = ((SocketChannel) selectionKey.channel()).read(buffer);
                        if (read > 0) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, read));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
