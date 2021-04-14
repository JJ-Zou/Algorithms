package com.zjj.nowcoder.hw414.ex1;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine().trim());
            int[] v = new int[n];
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                v[i] = Integer.parseInt(reader.readLine().trim());
            }
            StringJoiner joiner = new StringJoiner(",");
            int period = 0;
            int cur = 0;
            boolean inAeb = false;
            boolean inAebUpload = false;
            int aebPeriod = 0;
            int startAeb = 0;
            int endAeb = 0;
            joiner.add(String.valueOf(v[cur++]));
            period++;
            while (cur < n) {
                if (v[cur - 1] - v[cur] >= 9) {
                    if (!inAeb) {
                        inAeb = true;
                        aebPeriod = 1;
                        startAeb = cur;
                    } else {
                        if (aebPeriod >= 4) {
                            period = 0;
                        }
                        aebPeriod++;
                    }
                } else {
                    if (inAeb) {
                        inAeb = false;
                        if (aebPeriod > 3) {
                            period = 0;
                            inAebUpload = true;
                            endAeb = cur;
                            for (int i = startAeb - 4; i < endAeb + 4; i++) {
                                if (i >= 0 && i < n && !set.contains(i)) {
                                    set.add(i);
                                    joiner.add(String.valueOf(v[i]));
                                }
                            }
                        }
                        aebPeriod = 0;
                    } else {
                        if (inAebUpload) {
                            if (cur - endAeb < 4) {
                                period = 0;
                            } else {
                                inAebUpload = false;
                            }
                        }
                    }
                }
                if (period == 60 && !inAeb) {
                    period = 0;
                    joiner.add(String.valueOf(v[cur++]));
                }
                cur++;
                period++;
            }
            writer.write(joiner.toString());
        }
    }
}
