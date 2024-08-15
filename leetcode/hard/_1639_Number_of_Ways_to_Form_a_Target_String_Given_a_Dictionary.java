package leetcode.hard;

/**
 *
 *
 * You are given a list of strings of the same length words and a string target.
 *
 * Your task is to form target using the given words under the following rules:
 *
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
 * Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 *
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 * Example 2:
 *
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * All strings in words have the same length.
 * 1 <= target.length <= 1000
 * words[i] and target contain only lowercase English letters.
 *
 *
 */

public class _1639_Number_of_Ways_to_Form_a_Target_String_Given_a_Dictionary {
    public int numWays(String[] words, String target) {
        int[][] charCountForIndex = new int[words[0].length()][26];
        // Getting Character count for every position
        for (int i = 0; i < words[0].length(); i++) {

            for (int j = 0; j < words.length; j++) {
                int index = words[j].charAt(i) - 'a';
                charCountForIndex[i][index]++;
            }
        }
        Long[][] memo = new Long[words[0].length()][target.length()];
        return (int)recur(target, charCountForIndex, 0, 0, memo);
    }
    private int mod = (int)1e9 + 7;

    private long recur(String target, int[][] charCountForIndex, int posIndex, int targetIndex,
                       Long[][] memo) {

        if (targetIndex == target.length()) {
            return 1;
        }

        if (posIndex == charCountForIndex.length) {
            return 0;
        }

        if (memo[posIndex][targetIndex] != null) {
            return memo[posIndex][targetIndex];
        }
        char ch = target.charAt(targetIndex);
        //ignoring current
        long ans = recur(target, charCountForIndex, posIndex + 1, targetIndex, memo);
        int indx = ch - 'a';

        if (charCountForIndex[posIndex][indx] != 0) {
            ans += (charCountForIndex[posIndex][indx] *
                    recur(target, charCountForIndex, posIndex + 1, targetIndex + 1, memo)) % mod;
            ans %= mod;
        }
        return memo[posIndex][targetIndex] = ans;
    }
}
