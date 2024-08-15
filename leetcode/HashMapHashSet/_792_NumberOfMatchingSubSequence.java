package leetcode.HashMapHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 *
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 *
 */

public class _792_NumberOfMatchingSubSequence {

    /*
    Since the length of S is large, let's think about ways to iterate through S only once, instead of many times as in the brute force solution.

We can put words into buckets by starting character. If for example we have words = ['dog', 'cat', 'cop'], then we can group them 'c' : ('cat', 'cop'), 'd' : ('dog',). This groups words by what letter they are currently waiting for. Then, while iterating through letters of S, we will move our words through different buckets.

For example, if we have a string like S = 'dcaog':

heads = 'c' : ('cat', 'cop'), 'd' : ('dog',) at beginning;
heads = 'c' : ('cat', 'cop'), 'o' : ('og',) after S[0] = 'd';
heads = 'a' : ('at',), 'o' : ('og', 'op') after S[0] = 'c';
heads = 'o' : ('og', 'op'), 't': ('t',) after S[0] = 'a';
heads = 'g' : ('g',), 'p': ('p',), 't': ('t',) after S[0] = 'o';
heads = 'p': ('p',), 't': ('t',) after S[0] = 'g';
     */

    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<String>> holder = new HashMap<>();

        for (String word : words) {
            char ch = word.charAt(0);
            List<String> list = holder.getOrDefault(ch, new ArrayList<>());
            list.add(word);
            holder.put(ch, list);
        }
        int ans = 0;

        for (char ch : s.toCharArray()) {
            List<String> wordList = holder.remove(ch);

            if (wordList != null)
                for (int i = 0; i < wordList.size(); i++) {
                    String word = wordList.get(i);
                    String str = word.substring(1);

                    if (str.length() == 0 || str.equals("")) {
                        ans++;
                        continue;
                    }
                    char ch1 = str.charAt(0);
                    List<String> list = holder.getOrDefault(ch1, new ArrayList<>());
                    list.add(str);
                    holder.put(ch1, list);
                }
        }
        return ans;
    }
    //=============================================================================================
    //Another approach
    public int numMatchingSubseq1(String s, String[] words) {
        Map<String, List<String>> holderMap = new HashMap<>();

        for (String word : words) {
            String str = word.substring(0, 1);
            String remain = word.substring(1);
            List<String> list = holderMap.getOrDefault(str, new ArrayList<>());
            list.add(remain);
            holderMap.put(str, list);
        }
        int ans = 0;

        for (char ch : s.toCharArray()) {
            List<String> wordList = holderMap.remove(ch + "");

            if (wordList != null) {
                for (int i = 0; i < wordList.size(); i++) {
                    String word = wordList.get(i);

                    if (word.equals("") || word.length() == 0) {
                        ans++;
                        continue;
                    }
                    String str = word.substring(0, 1);
                    String nextWord = word.substring(1);

                    List<String> list = holderMap.getOrDefault(str, new ArrayList<>());
                    list.add(nextWord);
                    holderMap.put(str, list);
                }
            }
        }
        return ans;
    }
}
