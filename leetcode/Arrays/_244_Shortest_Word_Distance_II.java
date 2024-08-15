package leetcode.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 *
 * Constraints:
 *
 * 1 <= wordsDict.length <= 3 * 10^4
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 * At most 5000 calls will be made to shortest.
 *
 */

public class _244_Shortest_Word_Distance_II {
    class WordDistance {
        Map<String, List<Integer>> posMap;

        public WordDistance(String[] wordsDict) {
            this.posMap = new HashMap<>();

            for (int i = 0; i < wordsDict.length; i++) {
                List<Integer> list = posMap.getOrDefault(wordsDict[i], new ArrayList<>());
                list.add(i);
                posMap.put(wordsDict[i], list);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> pos1 = posMap.get(word1);
            List<Integer> pos2 = posMap.get(word2);
            int index1 = 0;
            int index2 = 0;
            int ans = Integer.MAX_VALUE;

            while (index1 < pos1.size() && index2 < pos2.size()) {
                ans = Math.min(ans, Math.abs(pos1.get(index1) - pos2.get(index2)));

                if (pos1.get(index1) < pos2.get(index2)) {
                    index1++;
                } else {
                    index2++;
                }
            }
            return ans;
        }
    }

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */
}
