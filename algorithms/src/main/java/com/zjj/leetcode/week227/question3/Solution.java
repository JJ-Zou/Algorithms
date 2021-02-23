package com.zjj.leetcode.week227.question3;

/**
 * unsolved
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().largestMerge("uuurruuuruuuuuuuuruuuuu",
                "urrrurrrrrrrruurrrurrrurrrrruu"));
    }

    public String largestMerge(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2;
        }
        if (word2 == null || word2.length() == 0) {
            return word1;
        }
        StringBuilder sb = new StringBuilder();
        int cur1 = 0;
        int cur2 = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        while (cur1 < len1 && cur2 < len2) {
            if ((word1.substring(cur1)).compareTo(word2.substring(cur2)) < 0) {
                sb.append(word2.charAt(cur2));
                cur2++;
            } else {
                sb.append(word1.charAt(cur1));
                cur1++;
            }
        }
        if (cur1 == len1) {
            sb.append(word2.substring(cur2));
        }
        if (cur2 == len2) {
            sb.append(word1.substring(cur1));
        }
        return sb.toString();
    }

    public String largestMerge0(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2;
        }
        if (word2 == null || word2.length() == 0) {
            return word1;
        }
        StringBuilder sb = new StringBuilder();
        int cur1 = 0;
        int cur2 = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        while (cur1 < len1 && cur2 < len2) {
            if (word1.charAt(cur1) > word2.charAt(cur2)) {
                sb.append(word1.charAt(cur1));
                cur1++;
            } else if (word1.charAt(cur1) < word2.charAt(cur2)) {
                sb.append(word2.charAt(cur2));
                cur2++;
            } else {
                //ccecba ccd
                int c1 = cur1;
                int c2 = cur2;
                c1++;
                c2++;
                while (c1 < len1 && c2 < len2 && word1.charAt(c1) == word2.charAt(c2) && word1.charAt(c1) > word1.charAt(c1 - 1)) {
                    c1++;
                    c2++;
                }
                if (c1 == len1 && c2 == len2) {
                    sb.append(word1.substring(cur1)).append(word2.substring(cur2));
                    return sb.toString();
                }
                if (c1 == len1) {
                    if (word2.charAt(c2) < word2.charAt(c2 - 1)) {
                        sb.append(word1.substring(cur1)).append(word2.substring(cur2));
                        return sb.toString();
                    }
                    sb.append(word2, cur2, c2 + 1);
                    cur2 = c2 + 1;
                } else if (c2 == len2) {
                    if (word1.charAt(c1) < word1.charAt(c1 - 1)) {
                        sb.append(word2.substring(cur2)).append(word1.substring(cur1));
                        return sb.toString();
                    }
                    sb.append(word1, cur1, c1 + 1);
                    cur1 = c1 + 1;
                } else if (word1.charAt(c1) < word1.charAt(c1 - 1) && word2.charAt(c2) < word2.charAt(c2 - 1)) {
                    sb.append(word1.substring(cur1, c1)).append(word2.substring(cur2, c2));
                    cur1 = c1;
                    cur2 = c2;
                } else if (word1.charAt(c1) < word1.charAt(c1 - 1)) {
                    sb.append(word2.substring(cur2, c2 + 1));
                    cur2 = c2 + 1;
                } else if (word2.charAt(c2) < word2.charAt(c2 - 1)) {
                    sb.append(word1.substring(cur1, c1 + 1));
                    cur1 = c1 + 1;
                } else if (word1.charAt(c1) > word2.charAt(c2)) {
                    sb.append(word1.substring(cur1, c1 + 1));
                    cur1 = c1 + 1;
                } else {
                    sb.append(word2.substring(cur2, c2 + 1));
                    cur2 = c2 + 1;
                }
            }
        }
        if (cur1 == len1) {
            sb.append(word2.substring(cur2));
        }
        if (cur2 == len2) {
            sb.append(word1.substring(cur1));
        }
        return sb.toString();
    }
}
