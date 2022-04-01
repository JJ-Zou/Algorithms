package com.zjj.nowcoder.ali20220304;
/**
 * 1 2   4   6   8 9 10 11 12 13
 * 1     5   6 7 8 9 10 11 12 13
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 * 1 2 3 4 5 6 7 8 9 10 11 12 13
 */
//46*45=2070
//4*(46-7)+3*(46-10)+3*(46-14)+4*(46-17)+3*(46-20)+3*(46-23)+3*(46-26)
//+3*(46-30)+4*(46-34)+4*(46-38)+4*(46-42)=827
//
// 4*19+4*23+4*26+4*30+4*32+4*36+3*38+4*41+2*45+4*45+2*45+3*45+4*45=1617
/*
3 5 7 15  -5
2 3 5 10
*/
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final String[] str1 = reader.readLine().trim().split(" ");
            final String[] str2 = reader.readLine().trim().split(" ");
            int a1 = Integer.parseInt(str1[0]);
            int b1 = Integer.parseInt(str1[1]);
            int c1 = Integer.parseInt(str1[2]);
            int a2 = Integer.parseInt(str2[0]);
            int b2 = Integer.parseInt(str2[1]);
            int c2 = Integer.parseInt(str2[2]);
            int[] arr = new int[13];
            for (int i = 0; i < 13; i++) {
                arr[i] = 4;
            }
            arr[a1 - 1]--;
            arr[b1 - 1]--;
            arr[c1 - 1]--;
            arr[a2 - 1]--;
            arr[b2 - 1]--;
            arr[c2 - 1]--;
            double total = 45d * 46;
            int num = 0;
            int min = (a2 + b2 + c2) - (a1 + b1 + c1);
            if(min > 12) {
                writer.write("1.000");
                return;
            }
            if(min < -12) {
                writer.write("0.000");
                return;
            }
            if(min >= 0) {
                int left = 0;
                for (int i = 0; i < min; i++) {
                    left += arr[i];
                }
                for (int i = 0; i < 13 - min; i++) {
                    left += arr[i + min];
                    num += arr[i] * (46 - left);
                }
                double res = num / total;
                writer.write(String.format("%.4f", res));
                return;
            }
            int right = -1;
            for (int i = 12; i > 13 + min; i--) {
                right += arr[i];
            }
            for (int i = 12; i >= 0; i--) {
                if(i + min + 1 >= 0) {
                    right += arr[i + min + 1];
                }
                num += arr[i] * right;
            }
            double res = num / total;
            writer.write(String.format("%.4f", res));
        }
    }
}
