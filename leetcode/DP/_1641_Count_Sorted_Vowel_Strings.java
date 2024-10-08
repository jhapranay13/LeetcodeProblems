package leetcode.DP;

/**
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 *
 */

public class _1641_Count_Sorted_Vowel_Strings {
    //Bottom up
    public int countVowelStrings(int n) {
        int[][] memo = new int[n + 1][6];

        for (int i = 0; i <= 5; i++) {
            memo[0][i] = 1;
        }

        for (int vowels = 5; vowels >= 1; vowels--) {

            for (int N = 1; N <= n; N++) {
                int ans = 0;

                for (int i = vowels; i <= 5; i++) {
                    ans += memo[N - 1][i];
                }
                memo[N][vowels] = ans;
            }
        }
        return memo[n][1];
    }
    //=============================================================================================
    // Top down
    public int countVowelStrings1(int n) {
        int[][] memo = new int[n + 1][6];
        return recur(1, n, memo);
    }

    private int recur(int vowels, int n, int[][] memo) {

        if (n == 0) {
            return 1;
        }

        if (memo[n][vowels] > 0) {
            return memo[n][vowels];
        }
        int ans = 0;

        for (int i = vowels; i <= 5; i++) {
            ans += recur(i, n - 1, memo);
        }
        return memo[n][vowels] = ans;
    }
    //=============================================================================================
    // Recursion
    public int countVowelStrings2(int n) {
        return recur(1, n);
    }

    private int recur(int vowels, int n) {

        if (n == 0) {
            return 1;
        }
        int ans = 0;

        for (int i = vowels; i <= 5; i++) {
            ans += recur(i, n - 1);
        }
        return ans;
    }
}
