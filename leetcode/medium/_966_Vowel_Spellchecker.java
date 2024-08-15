package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * Example 2:
 *
 * Input: wordlist = ["yellow"], queries = ["YellOw"]
 * Output: ["yellow"]
 *
 *
 * Constraints:
 *
 * 1 <= wordlist.length, queries.length <= 5000
 * 1 <= wordlist[i].length, queries[i].length <= 7
 * wordlist[i] and queries[i] consist only of only English letters.
 *
 */

public class _966_Vowel_Spellchecker {
    Set<Character> vowels = new HashSet<>();
    Set<String> original = new HashSet<>();
    Map<String, String> capCheck = new HashMap<>();
    Map<String, String> vowelCheck = new HashMap<>();

    public String[] spellchecker(String[] wordlist, String[] queries) {
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for (String word : wordlist) {
            original.add(word);
            String lowCase = word.toLowerCase();

            if (!capCheck.containsKey(lowCase)) {
                capCheck.put(lowCase, word);
            }
            addVowel(lowCase, word);
        }
        return queriesSolution(queries);
    }

    private String[] queriesSolution(String[] queries) {
        String[] ans = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            if (original.contains(query)) {
                ans[i] = query;
            } else if (capCheck.containsKey(query.toLowerCase())) {
                ans[i] = capCheck.getOrDefault(query.toLowerCase(), "");
            } else {
                String vowelStr = getVowelStr(query.toLowerCase());
                ans[i] = vowelCheck.getOrDefault(vowelStr, "");
            }
        }
        return ans;
    }

    private void addVowel(String lword, String orignWord) {
        String vowelWord = getVowelStr(lword);

        if (!vowelCheck.containsKey(vowelWord)) {
            vowelCheck.put(vowelWord, orignWord);
        }
    }

    private String getVowelStr(String word) {
        StringBuilder holder = new StringBuilder();

        for (char ch : word.toCharArray()) {

            if (vowels.contains(ch)) {
                holder.append("*");
            } else {
                holder.append(ch);
            }
        }
        return holder.toString();
    }
}
