package leetcode.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * You are given a string s representing a list of words. Each letter in the word has one or more options.
 *
 * If there is one option, the letter is represented as is.
 * If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
 * For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].
 *
 * Return all words that can be formed in this manner, sorted in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: ["abcd"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50
 * s consists of curly brackets '{}', commas ',', and lowercase English letters.
 * s is guaranteed to be a valid input.
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 *
 */

public class _1087_Brace_Expansion {
    public String[] expand(String s) {
        List<String> holder = new ArrayList<>();
        recur(s, holder, new StringBuilder(), 0);
        Collections.sort(holder);
        String[] ans = new String[holder.size()];
        int index = 0;

        for (String word : holder) {
            ans[index++] = word;
        }
        return ans;
    }

    private void recur(String s, List<String> holder, StringBuilder word, int index) {

        if (index == s.length()) {
            holder.add(word.toString());
            return;
        }

        if (s.charAt(index) != '{') {
            word.append(s.charAt(index));
            recur(s, holder, word, index + 1);
            word.deleteCharAt(word.length() - 1);
        } else {
            int nextIndex = index + 1;
            List<Character> list = new ArrayList<>();

            while (s.charAt(nextIndex) != '}') {

                if (Character.isAlphabetic(s.charAt(nextIndex)))
                    list.add(s.charAt(nextIndex));
                nextIndex++;
            }

            for (char ch : list) {
                word.append(ch);
                recur(s, holder, word, nextIndex + 1);
                word.deleteCharAt(word.length() - 1);
            }
        }
    }
}
