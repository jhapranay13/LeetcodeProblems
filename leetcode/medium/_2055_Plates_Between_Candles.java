package leetcode.medium;

import java.util.Arrays;

/**
 *
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * ex-1
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 * Output: [2,3]
 * Explanation:
 * - queries[0] has two plates between candles.
 * - queries[1] has three plates between candles.
 *
 * Example 2:
 *
 * ex-2
 * Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * Output: [9,0,0,0,0]
 * Explanation:
 * - queries[0] has nine plates between candles.
 * - The other queries have zero plates between candles.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 10^5
 * s consists of '*' and '|' characters.
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 */

public class _2055_Plates_Between_Candles {
    //Binary Search Approach
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] presum = new int[s.length()];
        presum[0] = s.charAt(0) == '*' ? 1 : 0;

        for (int i = 1; i < s.length(); i++) {
            presum[i] = presum[i - 1];

            if (s.charAt(i) == '*') {
                presum[i] += 1;
            }
        }
        int[] ans = new int[queries.length];
        int index = 0;

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            if (s.charAt(start) == '*') {
                start = binarySearchNext(s, presum, start, end);
            }

            if (s.charAt(end) == '*') {
                end = binarySearchPrev(s, presum, query[0], end);
            }

            if (start < 0 || end < 0 || start >= end) {
                index++;
                continue;
            }
            ans[index++] = start == 0 ? presum[end] : presum[end] - presum[start - 1];
        }
        return ans;
    }
    //Idea behind binary search is that since each plate can be cosidered as 1 count.
    //The movement in binary search movement depends on number of plates and the difference
    //between two indexes. Number of plates can be calculated by using presum.
    private int binarySearchNext(String s, int[] presum, int start, int end) {
        int ans = -1;

        while (start < end) {
            int pivot = start + (end - start) / 2;
            int diff = start == 0 ? presum[pivot] : presum[pivot] - presum[start - 1];

            if (diff == pivot - start + 1) {
                start = pivot + 1;
            } else {
                end = pivot;
                ans = end;
            }
        }
        return ans;
    }

    private int binarySearchPrev(String s, int[] presum, int start, int end) {
        int ans = end;

        while (start < end) {
            int pivot = start + (end - start) / 2;
            int diff = pivot == 0 ? presum[end] : presum[end] - presum[pivot - 1];

            if (diff == end - pivot + 1) {
                end = pivot;
                ans = end;
            } else {
                start = pivot + 1;
            }
        }
        return ans - 1;
    }
    //=============================================================================================

    public int[] platesBetweenCandles1(String s, int[][] queries) {
        int[] presum = new int[s.length()];
        int index = 0;

        for (char ch : s.toCharArray()) {

            if (index == 0) {

                if (ch == '|') {
                    presum[index] = 0;
                } else {
                    presum[index] = 1;
                }
            } else {

                if (ch == '|') {
                    presum[index] = presum[index - 1];
                } else {
                    presum[index] = presum[index - 1] + 1;
                }
            }
            index++;
        }
        int[] left = new int[s.length()];
        Arrays.fill(left, -1);
        //checking the left closest plate
        for (int i = 0; i < s.length(); i++) {

            if (i == 0) {
                left[i] = -1;
            } else {

                if (s.charAt(i) == '|') {
                    left[i] = i;
                } else {
                    left[i] = left[i - 1];
                }
            }
        }
        int[] right = new int[s.length()];
        Arrays.fill(right, -1);
        //checking the right closest plate
        for (int i = s.length() - 1; i >= 0; i--) {

            if (i == s.length() - 1) {
                right[i] = -1;
            } else {

                if (s.charAt(i) == '|') {
                    right[i] = i;
                } else {
                    right[i] = right[i + 1];
                }
            }
        }
        int[] ans = new int[queries.length];
        index = 0;

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            if (s.charAt(start) == '*') {
                start = right[start];
            }

            if (s.charAt(end) == '*') {
                end = left[end];
            }

            if (start == -1 || end == -1 || start > end) {
                index++;
                continue;
            }
            int temp = 0;

            if (start == 0) {
                ans[index] = presum[end];
            } else {
                ans[index] = presum[end] - presum[start - 1];
            }
            index++;
        }
        return ans;
    }
}
