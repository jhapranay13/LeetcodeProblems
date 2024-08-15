package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * Example 2:
 *
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 * Example 3:
 *
 * Input: n = 5
 * Output: 68
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 10^4
 *
 */

public class _1220_Count_Vowels_Permutation {
    /* Rules
        a -> e
        e -> a
          -> i
        i -> a
          -> e
          -> o
          -> u
        o -> i
          -> u
        u -> a
    */
    public int countVowelPermutation(int n) {
        Map<Character, List<Character>> holder = new HashMap<>();
        List<Character> list = new ArrayList<>();
        list.add('e');
        holder.put('a', list);
        list = new ArrayList<>();
        list.add('a');
        list.add('i');
        holder.put('e', list);
        list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('o');
        list.add('u');
        holder.put('i', list);
        list = new ArrayList<>();
        list.add('i');
        list.add('u');
        holder.put('o', list);
        list = new ArrayList<>();
        list.add('a');
        holder.put('u', list);
        long ans = 0;
        Long[][] memo= new Long[26][n + 1];

        for (char key : holder.keySet()) {
            ans += recur(key, holder, n, memo);
            ans %= mod;
        }
        return (int)ans;
    }

    private int mod = 1000000007;

    private long recur(char curr, Map<Character, List<Character>> holder, int n, Long[][] memo) {

        if (memo[curr - 'a'][n] != null) {
            return memo[curr - 'a'][n];
        }
        if (n == 1) {
            return 1;
        }
        List<Character> list = holder.get(curr);
        long ans = 0;

        for (char next : list) {
            long temp = recur(next, holder, n - 1, memo);

            if (temp != 0) {
                ans += temp;
                ans %= mod;
            }
        }
        return memo[curr - 'a'][n] = ans;
    }
}
