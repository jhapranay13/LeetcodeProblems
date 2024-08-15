package leetcode.DP;

/**
 *
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
 *
 * A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 * Example 2:
 *
 * Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * Output: "aaaaaaaa"
 *
 *
 * Constraints:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 *
 */

public class _1092_Shortest_Common_Supersequence {
    //=============================================================================================
    // Longest Common Subsequence Bottom up
    public String shortestCommonSupersequence(String str1, String str2) {
        String str = longestCommonSubSequence(str1, str2);
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        StringBuilder ans = new StringBuilder();

        while (index < str.length() && index1 <= str1.length() && index2 <= str2.length()) {

            while (str1.charAt(index1) != str.charAt(index)) {
                ans.append(str1.charAt(index1++));
            }

            while (str2.charAt(index2) != str.charAt(index)) {
                ans.append(str2.charAt(index2++));
            }
            ans.append(str.charAt(index));
            index1++;
            index2++;
            index++;
        }

        while (index1 < str1.length()) {
            ans.append(str1.charAt(index1++));
        }

        while (index2 < str2.length()) {
            ans.append(str2.charAt(index2++));
        }
        return ans.toString();
    }

    private String longestCommonSubSequence(String str1, String str2) {
        String[][] memo = new String[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            memo[i][str2.length()] = "";
        }

        for (int i = 0; i <= str2.length(); i++) {
            memo[str1.length()][i] = "";
        }

        for (int index1 = str1.length() - 1; index1 >= 0; index1--) {

            for (int index2 = str2.length() - 1; index2 >= 0; index2--) {
                String ans = "";

                if (str1.charAt(index1) == str2.charAt(index2)) {
                    ans += str1.charAt(index1);
                    ans += memo[index1 + 1][index2 + 1];
                } else {
                    String first = memo[index1 + 1][index2];
                    String second = memo[index1][index2 + 1];

                    if (first.length() < second.length()) {
                        ans = second;
                    } else {
                        ans = first;
                    }
                }
                memo[index1][index2] = ans;
            }
        }
        return memo[0][0];
    }
    //=============================================================================================
    // Longest Common Subsequence Top down
    public String shortestCommonSupersequence1(String str1, String str2) {
        String str = longestCommonSubSequence1(str1, str2);
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        StringBuilder ans = new StringBuilder();

        while (index < str.length() && index1 <= str1.length() && index2 <= str2.length()) {

            while (str1.charAt(index1) != str.charAt(index)) {
                ans.append(str1.charAt(index1++));
            }

            while (str2.charAt(index2) != str.charAt(index)) {
                ans.append(str2.charAt(index2++));
            }
            ans.append(str.charAt(index));
            index1++;
            index2++;
            index++;
        }

        while (index1 < str1.length()) {
            ans.append(str1.charAt(index1++));
        }

        while (index2 < str2.length()) {
            ans.append(str2.charAt(index2++));
        }
        return ans.toString();
    }

    private String longestCommonSubSequence1(String str1, String str2) {
        String[][] memo = new String[str1.length()][str2.length()];
        return lcs(str1, str2, 0, 0, memo);
    }

    private String lcs(String str1, String str2, int index1, int index2, String[][] memo) {

        if (index1 == str1.length() || str2.length() == index2) {
            return "";
        }

        if (memo[index1][index2] != null) {
            return memo[index1][index2];
        }
        String ans = "";

        if (str1.charAt(index1) == str2.charAt(index2)) {
            ans += str1.charAt(index1);
            ans += lcs(str1, str2, index1 + 1, index2 + 1, memo);
        } else {
            String first = lcs(str1, str2, index1 + 1, index2, memo);
            String second = lcs(str1, str2, index1, index2 + 1, memo);

            if (first.length() < second.length()) {
                ans = second;
            } else {
                ans = first;
            }
        }
        return memo[index1][index2] = ans;
    }

}
