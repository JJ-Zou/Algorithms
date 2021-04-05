package com.zjj.io.block;

import java.io.*;
import java.nio.CharBuffer;

public class BlockIO {
    public static void main(String[] args) throws IOException {
        CharBuffer buffer = CharBuffer.allocate(1024);
        InputStreamReader reader = new InputStreamReader(System.in);
        int read = reader.read(buffer);
        if (read > 0) {
            System.out.println(buffer.array());
        }
    }
}
