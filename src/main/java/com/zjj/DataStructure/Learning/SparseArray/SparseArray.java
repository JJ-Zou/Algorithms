package com.zjj.DataStructure.Learning.SparseArray;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SparseArray {
    private static final int LENGTH = 10;
    private static final int AMOUNT = 8;
    private int[][] chessArray = new int[LENGTH][LENGTH];
    private int[][] sparseArray;

    SparseArray() {
        this(AMOUNT);
    }

    SparseArray(int amount) {
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            chessArray[random.nextInt(LENGTH)][random.nextInt(LENGTH)]
                    = 1 + random.nextInt(LENGTH - 1);
        }
    }

    SparseArray(String filename) {
        try (InputStream inputStream
                     = this.getClass().getClassLoader().getResourceAsStream(filename);
             Scanner scanner = new Scanner(inputStream)) {
            System.out.println(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SparseArray sparse = new SparseArray();
        System.out.println(sparse);
        sparse.toSparseArray();
        int[][] sparseArray = sparse.getSparseArray();
        System.out.println(Arrays.deepToString(sparseArray));
        sparse.saveToDesk("sparseArray" + System.currentTimeMillis());
    }

    public int[][] getSparseArray() {
        return sparseArray;
    }

    public void toSparseArray() {
        int amount = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (chessArray[i][j] != 0) {
                    amount++;
                }
            }
        }
        sparseArray = new int[amount + 1][3];
        sparseArray[0][0] = LENGTH;
        sparseArray[0][1] = LENGTH;
        sparseArray[0][2] = amount;
        int count = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
    }

    public void saveToDesk(String filename) {
        if (getSparseArray() == null) {
            toSparseArray();
        }
        File file = new File(this.getClass()
                .getResource("/").getPath() + filename);
        try (OutputStream outputStream
                     = new FileOutputStream(file);
             OutputStreamWriter writer
                     = new OutputStreamWriter(outputStream, "UTF-8")) {
            String string = Arrays.deepToString(sparseArray);
            char[] chars = string.toCharArray();
            writer.write(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                stringBuilder.append(chessArray[i][j]);
                if (j != LENGTH - 1) {
                    stringBuilder.append(", ");
                }
            }
            if (i != LENGTH - 1) {
                stringBuilder.append("\n\t\t");
            }
        }
        return "chessArray= {" +
                "\n\t\t" + stringBuilder.toString() +
                "\n\t\t" + '}';
    }
}
