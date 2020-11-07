package com.zjj.nio.bioex;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {

    @SneakyThrows
    public static void main(String[] args) {
        multiConnected();
    }

    private static void multiConnected() throws IOException {
        ServerSocket serverSocket = new ServerSocket(40000);
        System.out.println("new ServerSocket");
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("client: " + client.getRemoteSocketAddress());
            new Thread(() -> {
                try {
                    getReadLine(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(client.getRemoteSocketAddress() + " off");
            }).start();
        }
    }


    private static void singleConnected() throws IOException {
        ServerSocket serverSocket = new ServerSocket(40000);
        System.out.println("new ServerSocket");
        Socket client = serverSocket.accept();
        System.out.println("client: " + client.getRemoteSocketAddress());
        getReadLine(client);
        System.out.println(client.getRemoteSocketAddress() + " off");
    }

    private static void getReadLine(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while (true) {
            String readLine = reader.readLine();
            if (readLine == null || readLine.equals("q!")) {
                break;
            }
            System.out.println(client.getRemoteSocketAddress() + ": " + readLine);
        }
    }


}
