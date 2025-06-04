package leetcode.BackTrackingRecursion;

/**
 *
 * A happy string is a string that:
 *
 * consists only of letters of the set ['a', 'b', 'c'].
 * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
 * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
 *
 * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
 *
 * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 3
 * Output: "c"
 * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
 * Example 2:
 *
 * Input: n = 1, k = 4
 * Output: ""
 * Explanation: There are only 3 happy strings of length 1.
 * Example 3:
 *
 * Input: n = 3, k = 9
 * Output: "cab"
 * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10
 * 1 <= k <= 100
 *
 */
public class _1415_Kth_Lexicographical_String_Happy_Strings_Length_n {

    public String getHappyString(int n, int k) {
        double calculation = 3 * Math.pow(2.0, n - 1);

        if (calculation < k || n == 0) {
            return ans;
        }
        K = k;
        char[] arr = {'a', 'b', 'c'};
        recur(arr, n, new StringBuilder());
        return ans;
    }
    int K = 0;
    String ans = "";

    private void recur(char[] arr, int n, StringBuilder partial) {

        if (partial.length() == n) {
            K--;

            if (K == 0) {
                ans = partial.toString();
            }
            return;
        }

        for (char ch : arr) {

            if (!partial.isEmpty() && ch == partial.charAt(partial.length() - 1)) {
                continue;
            }
            partial.append(ch);
            recur(arr, n, partial);
            partial.deleteCharAt(partial.length() - 1);

            if (!ans.equals("")) {
                break;
            }
        }
    }
}
