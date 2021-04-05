package com.zjj.io.epoll;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {
    public static void main(String[] args) {
        try (SocketChannel channel = SocketChannel.open();
             Selector selector = Selector.open()) {
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_CONNECT);
            channel.connect(new InetSocketAddress("39.105.65.104", 20010));
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectedKey : selectionKeys) {
                    if (selectedKey.isConnectable() && channel.finishConnect()) {
                        channel.register(selector, SelectionKey.OP_WRITE);
                    }
                    if (selectedKey.isWritable()) {
                        Scanner reader = new Scanner(System.in);
                        byte[] line = reader.nextLine().getBytes();
                        ByteBuffer buffer = ByteBuffer.allocate(line.length);
                        buffer.put(line);
                        buffer.flip();
                        channel.write(buffer);
                        System.out.println("send " + new String(line));
                    }
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
