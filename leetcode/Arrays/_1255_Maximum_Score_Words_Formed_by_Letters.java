package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 *
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
 *
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 * Example 2:
 *
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * Word "xxxz" only get a score of 25.
 * Example 3:
 *
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 14
 * 1 <= words[i].length <= 15
 * 1 <= letters.length <= 100
 * letters[i].length == 1
 * score.length == 26
 * 0 <= score[i] <= 10
 * words[i], letters[i] contains only lower case English letters.
 *
 */

public class _1255_Maximum_Score_Words_Formed_by_Letters {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : letters) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return recur(words, freqMap, score, 0);
    }

    private int recur(String[] words, Map<Character, Integer> freqMap, int[] score, int index) {

        if (index == words.length) {
            return 0;
        }
        String word = words[index];
        Map<Character, Integer> charFreq = new HashMap<>();

        for (char ch : word.toCharArray()) {
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }
        int include = 0;
        int exclude = 0;
        boolean isValid = true;

        for (char key : charFreq.keySet()) {

            if (!freqMap.containsKey(key) || freqMap.get(key) < charFreq.get(key)) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            int currScore = 0;

            for (char key : charFreq.keySet()) {
                int temp = score[key - 'a'];
                currScore += temp * charFreq.get(key);

                if (freqMap.get(key) == charFreq.get(key)) {
                    freqMap.remove(key);
                } else {
                    freqMap.put(key, freqMap.get(key) - charFreq.get(key));
                }
            }
            include = currScore + recur(words, freqMap, score, index + 1);

            for (char key : charFreq.keySet()) {
                freqMap.put(key, freqMap.getOrDefault(key, 0) + charFreq.get(key));
            }
        }
        exclude = recur(words, freqMap, score, index + 1);
        return Math.max(include, exclude);
    }
}
