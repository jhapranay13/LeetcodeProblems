package leetcode.TwoPointer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array of strings queries and a string pattern, return a boolean array answer where answer[i] is true if queries[i] matches pattern, and false otherwise.
 *
 * A query word queries[i] matches pattern if you can insert lowercase English letters pattern so that it equals the query. You may insert each character at any position and you may not insert any characters.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * Explanation: "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
 * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
 * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
 * Example 2:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * Explanation: "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
 * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
 * Example 3:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * Output: [false,true,false,false,false]
 * Explanation: "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
 *
 *
 * Constraints:
 *
 * 1 <= pattern.length, queries.length <= 100
 * 1 <= queries[i].length <= 100
 * queries[i] and pattern consist of English letters.
 *
 */

public class _1023_Camelcase_Matching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();

        for (String query : queries) {
            ans.add(check(query, pattern));
        }
        return ans;
    }

    private boolean check(String query, String pattern) {
        int pIndex = 0;
        int capCount = 0;

        for (char ch : query.toCharArray()) {

            if (pIndex < pattern.length() && ch == pattern.charAt(pIndex)) {
                pIndex++;
            } else if (ch >= 'A' && ch <= 'Z') {
                capCount++;
            }
        }
        return capCount == 0 && pIndex == pattern.length();
    }
}
