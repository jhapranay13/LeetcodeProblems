package leetcode.hard;

import java.util.*;

/**
 *
 * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in s1 exactly k times so that the resulting string equals s2.
 *
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "ba"
 * Output: 1
 * Explanation: The two string are 1-similar because we can use one swap to change s1 to s2: "ab" --> "ba".
 * Example 2:
 *
 * Input: s1 = "abc", s2 = "bca"
 * Output: 2
 * Explanation: The two strings are 2-similar because we can use two swaps to change s1 to s2: "abc" --> "bac" --> "bca".
 *
 *
 * Constraints:
 *
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
 * s2 is an anagram of s1.
 *
 */

public class _854_K_Similar_Strings {
    // DP HashMap Approach
    // Making s1 equal to s2
    public int kSimilarity(String s1, String s2) {
        Map<String, Integer> memo = new HashMap<>();
        char[] arr = s1.toCharArray();
        return recur(arr, s2, 0, memo);
    }

    private int recur(char[] arr, String s2, int index, Map<String, Integer> memo) {
        String arrStr = new String(arr);

        if (arrStr.equals(s2)) {
            return 0;
        }

        if (memo.containsKey(arrStr)) {
            return memo.get(arrStr);
        }
        int idx = index;
        // If both are equal proceed
        while (idx < s2.length() && arr[idx] == s2.charAt(idx)) {
            idx++;
        }
        int ans = Integer.MAX_VALUE;

        for (int i = idx + 1; i < s2.length(); i++) {

            if (arr[i] == s2.charAt(idx)) {
                swap(arr, i, idx);
                int temp = recur(arr, s2, idx + 1, memo);

                if (temp < Integer.MAX_VALUE) {
                    ans = Math.min(temp + 1, ans);
                }
                swap(arr, i, idx);
            }
        }
        memo.put(arrStr, ans);
        return ans;
    }

    private void swap(char[] arr, int x, int y) {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    //=============================================================================================
    // BFS approach
    public int kSimilarity1(String s1, String s2) {

        if (s1.equals(s2)) {
            return 0;
        }
        Set<String> v = new HashSet<>();
        Deque<String> q = new LinkedList<>();
        q.offer(s1);
        v.add(s1);
        int ans = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String str = q.poll();

                if (str.equals(s2)) {
                    return ans;
                }
                int index = 0;
                // Skipping all the chars that are same
                while (str.charAt(index) == s2.charAt(index)) {
                    index++;
                }

                for (int i = index + 1; i < s2.length(); i++) {

                    if (str.charAt(i) == s2.charAt(index)) {
                        String nextStr = swapAndGet(str, i, index);

                        if (v.add(nextStr)) {
                            q.offer(nextStr);
                        }
                    }
                }
            }
            ans++;
        }
        return ans;
    }

    private String swapAndGet(String str, int x, int y) {
        char[] arr = str.toCharArray();
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
        return new String(arr);
    }
}
