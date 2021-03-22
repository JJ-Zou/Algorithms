package com.zjj.nowcoder.mettuan10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Solution3 {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int group = Integer.parseInt(reader.readLine());
            int tableNum = Integer.parseInt(reader.readLine());
            String tables = reader.readLine();
            int peopleNum = Integer.parseInt(reader.readLine());
            String peoples = reader.readLine();
            for(int i = 0; i < group; i++) {
                sitDown(tableNum, tables.toCharArray(), peoples.toCharArray(), writer);
            }
        } catch (Exception ignore) {

        }
    }

    private static void sitDown(int tableNum, char[] tables, char[] peoples, BufferedWriter writer) throws Exception {
        Deque<Integer> nonTable = new ArrayDeque<>();
        Queue<Integer> oneTable = new PriorityQueue<>();
        for (int i = 0; i < tableNum; i++) {
            if (tables[i] == '0') {
                nonTable.addLast(i + 1);
            } else if (tables[i] == '1') {
                oneTable.add(i + 1);
            }
        }
        for (char p : peoples) {
            if (p == 'M') {
                if (!oneTable.isEmpty()) {
                    writer.write(oneTable.poll());
                    writer.newLine();
                } else {
                    int seat = nonTable.pollFirst();
                    writer.write(seat);
                    writer.newLine();
                    oneTable.add(seat);
                }
            } else {
                if (!nonTable.isEmpty()) {
                    int seat = nonTable.pollFirst();
                    writer.write(seat);
                    writer.newLine();
                    oneTable.add(seat);
                } else {
                    writer.write(oneTable.poll());
                    writer.newLine();
                }
            }
        }
    }
}
