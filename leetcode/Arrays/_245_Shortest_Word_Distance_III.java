package leetcode.Arrays;

/**
 *
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between the occurrence of these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 *
 *
 *
 * Example 1:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 * Example 2:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= wordsDict.length <= 105
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 *
 */

public class _245_Shortest_Word_Distance_III {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int ans = wordsDict.length;
        int index1 = -1;
        int index2 = -1;

        for (int i = 0; i < wordsDict.length; i++) {

            if (wordsDict[i].equals(word1)) {
                index1 = i;

                if (index2 != -1) {

                    if (word1.equals(word2) && index2 != index1) {
                        ans = Math.min(ans, Math.abs(index1 - index2));
                    } else if (!word1.equals(word2)) {
                        ans = Math.min(ans, Math.abs(index1 - index2));
                    }
                }
            }

            if (wordsDict[i].equals(word2)) {
                index2 = i;

                if (index1 != -1) {

                    if (word1.equals(word2) && index2 != index1) {
                        ans = Math.min(ans, Math.abs(index1 - index2));
                    } else if (!word1.equals(word2)) {
                        ans = Math.min(ans, Math.abs(index1 - index2));
                    }
                }
            }
        }
        return ans;
    }
}
