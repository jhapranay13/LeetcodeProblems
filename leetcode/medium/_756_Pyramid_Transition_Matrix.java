package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each row of blocks contains one less block than the row beneath it and is centered on top.
 *
 * To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed. A triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of three-letter strings allowed, where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.
 *
 * For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block. Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
 * You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.
 *
 * Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every triangular pattern in the pyramid is in allowed, or false otherwise.
 *
 *
 *
 * Example 1:
 *                             c      E       A     F
 *                            B C    C D     C E   F F
 *
 *          A
 *         C E
 *        B c D
 *
 * Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
 * Output: true
 * Explanation: The allowed triangular patterns are shown on the right.
 * Starting from the bottom (level 3), we can build "CE" on level 2 and then build "A" on level 1.
 * There are three triangular patterns in the pyramid, which are "BCC", "CDE", and "CEA". All are allowed.
 * Example 2:
 *
 *          ?                   B     C       D       E      F
 *         E D                 A A   A A     B C     B B    D E
 *        B B C
 *       A A A A
 *
 * Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
 * Output: false
 * Explanation: The allowed triangular patterns are shown on the right.
 * Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.
 *
 *
 * Constraints:
 *
 * 2 <= bottom.length <= 6
 * 0 <= allowed.length <= 216
 * allowed[i].length == 3
 * The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
 * All the values of allowed are unique.
 *
 */

public class _756_Pyramid_Transition_Matrix {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> sequenceHolder = new HashMap<>();

        for (String str : allowed) {
            String key = str.substring(0, 2);
            String val = str.substring(2);

            if (!sequenceHolder.containsKey(key)) {
                sequenceHolder.put(key, new ArrayList<>());
            }
            sequenceHolder.get(key).add(val);
        }
        return recur(sequenceHolder, bottom, new StringBuilder(), 1, new HashMap<String, Boolean>());
    }

    private boolean recur(Map<String, List<String>> sequenceHolder, String currRow, StringBuilder nextRow,
                          int currIndex, HashMap<String, Boolean> memo) {

        if (currRow.length() == 1) {
            return true;
        }

        if (currIndex == currRow.length()) {
            return recur(sequenceHolder, nextRow.toString(), new StringBuilder(), 1, memo);
        }
        String memokey = currRow + "|" + nextRow + "|" + currIndex;
        String key = "" + currRow.charAt(currIndex - 1) + currRow.charAt(currIndex);

        if (memo.containsKey(memokey)) {
            return memo.get(memokey);
        }
        boolean ans = false;

        if (sequenceHolder.containsKey(key)) {

            for (String child : sequenceHolder.get(key)) {
                nextRow.append(child);

                if (recur(sequenceHolder, currRow, nextRow, currIndex + 1, memo)) {
                    ans = true;
                    break;
                }
                nextRow.deleteCharAt(nextRow.length() - 1);
            }
        }
        memo.put(memokey, ans);
        return ans;
    }
}
