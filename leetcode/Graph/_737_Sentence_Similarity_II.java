package leetcode.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *
 * They have the same length (i.e., the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
 * Example 2:
 *
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: true
 * Explanation: "leetcode" --> "platform" --> "anime" --> "manga" --> "onepiece".
 * Since "leetcode is similar to "onepiece" and the first two words are the same, the two sentences are similar.
 * Example 3:
 *
 * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"], similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
 * Output: false
 * Explanation: "leetcode" is not similar to "onepiece".
 *
 *
 * Constraints:
 *
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
 * 0 <= similarPairs.length <= 2000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of English letters.
 *
 */

public class _737_Sentence_Similarity_II {
    // UnionFind Parent
    Map<String, String> parent = new HashMap<>();

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

        if (sentence1.length != sentence2.length) {
            return false;
        }
        for (List<String> pair : similarPairs) {
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            if (!parent.containsKey(word1)) {
                parent.put(word1, word1);
            }

            if (!parent.containsKey(word2)) {
                parent.put(word2, word2);
            }
            union(word1, word2);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String word1 = sentence1[i];
            String word2 = sentence2[i];

            if (!word1.equals(word2)) {
                String p1 = find(word1);
                String p2 = find(word2);

                if (p1 == null || p2 == null || !p1.equals(p2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(String s1, String s2) {
        String p1 = find(s1);
        String p2 = find(s2);

        if (p1 != p2) {
            parent.put(p2, p1);
        }
    }

    // Find method of union Find
    public String find(String s) {

        if (s == null) {
            return null;
        }

        if (parent.getOrDefault(s, "").equals(s)) {
            return s;
        }
        return find(parent.get(s));
    }
}
