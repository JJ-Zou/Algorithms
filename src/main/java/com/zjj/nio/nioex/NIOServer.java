package com.zjj.nio.nioex;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    @SneakyThrows
    public static void main(String[] args) {
        singleThreadNIOServer();
    }

    private static void singleThreadNIOServer() throws IOException {
        Set<SocketChannel> clients = new HashSet<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(40000));
        serverSocketChannel.configureBlocking(false);
        System.out.println("server: " + serverSocketChannel.getLocalAddress());
        while (true) {
            SocketChannel clientChannel = serverSocketChannel.accept();
            if (clientChannel != null) {
                clientChannel.configureBlocking(false);
                System.out.println(clientChannel.getRemoteAddress());
                clients.add(clientChannel);
            }
            Iterator<SocketChannel> iterator = clients.iterator();
            while (iterator.hasNext()) {
                SocketChannel client = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 100);
                int read = client.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.limit()];
                    byteBuffer.get(bytes);
                    byteBuffer.clear();
                    String msg = new String(bytes);
                    if (msg.equals("q!")) {
                        iterator.remove();
                        System.out.println(client.getRemoteAddress() + " off");
                        client.close();
                    } else {
                        System.out.println(client.getRemoteAddress() + ": " + msg);
                    }
                } else if (read == -1) {
                    iterator.remove();
                    System.out.println(client.getRemoteAddress() + " off");
                    client.close();
                }
            }
        }
    }
}
