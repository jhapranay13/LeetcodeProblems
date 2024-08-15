package leetcode.BackTrackingRecursion;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * A word's generalized abbreviation can be constructed by taking any number of non-overlapping and non-adjacent
 * substrings
 *  and replacing them with their respective lengths.
 *
 * For example, "abcde" can be abbreviated into:
 * "a3e" ("bcd" turned into "3")
 * "1bcd1" ("a" and "e" both turned into "1")
 * "5" ("abcde" turned into "5")
 * "abcde" (no substrings replaced)
 * However, these abbreviations are invalid:
 * "23" ("ab" turned into "2" and "cde" turned into "3") is invalid as the substrings chosen are adjacent.
 * "22de" ("ab" turned into "2" and "bc" turned into "2") is invalid as the substring chosen overlap.
 * Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "word"
 * Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
 * Example 2:
 *
 * Input: word = "a"
 * Output: ["1","a"]
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 15
 * word consists of only lowercase English letters.
 *
 */

public class _320_Generalized_Abbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();
        recur(word, ans, new StringBuilder(), 0, 0);
        return ans;
    }

    private void recur(String word, List<String> ans,
                       StringBuilder holder, int index, int count) {

        if (index == word.length()) {
            int length = addCount(holder, count);
            ans.add(holder.toString());
            deleteCount(holder, length);
            return;
        }
        recur(word, ans, holder, index + 1, count + 1);
        int length = addCount(holder, count);
        holder.append(word.charAt(index));
        recur(word, ans, holder, index + 1, 0);
        holder.deleteCharAt(holder.length() - 1);
        deleteCount(holder, length);
    }

    private int addCount(StringBuilder holder, int count) {
        int length = 0;

        if (count > 0) {
            int cnt = count;

            while (cnt > 0) {
                cnt /= 10;
                length++;
            }
            holder.append(count);
        }
        return length;
    }

    private void deleteCount(StringBuilder holder, int length) {

        while (length-- > 0) {
            holder.deleteCharAt(holder.length() - 1);
        }
    }
}
