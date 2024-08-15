package leetcode.Graph.TopologicalSort;

import java.util.*;

/**
 * There is a new alien language that uses the English alphabet. However, the order among the letters
 * is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in
 * words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically
 * increasing order by the new language's rules. If there is no solution, return "". If there are
 * multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ,
 * the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters
 * are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 *
 *
 *
 *
 *
 */

public class _269_AlienDictonary {

    //=============================================================================================
    //DFS Toplogical sort
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adjMap = new HashMap<>();

        for (String word : words) {

            for (char ch : word.toCharArray()) {
                adjMap.put(ch, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            // if 2 words are equal no ordering can be discovered
            if (word1.equals(word2)) {
                continue;
            }
            //If word1 starts with word2. Wrong test case
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            int w1 = 0;
            int w2 = 0;
            //The first difference is all we care about in lexicographical order as words are arranged that way
            while (w1 < word1.length() && w2 < word2.length()) {

                if (word1.charAt(w1) == word2.charAt(w2)) {
                    w1++;
                    w2++;
                } else {
                    Set<Character> set = adjMap.getOrDefault(word1.charAt(w1), new HashSet<>());
                    set.add(word2.charAt(w2));
                    adjMap.put(word1.charAt(w1), set);
                    break;
                }
            }
        }
        //Normal topological sort DFS
        StringBuilder ans = new StringBuilder();
        Set<Character> visited = new HashSet<>();

        for (char key : adjMap.keySet()) {

            if (!visited.contains(key)) {
                Set<Character> cycle = new HashSet<>();

                if (!recur(visited, cycle, adjMap, key, ans)) {
                    return "";
                }
            }
        }
        return ans.toString();
    }

    private boolean recur(Set<Character> visited, Set<Character> cycle, Map<Character,
            Set<Character>> adjMap, char ch, StringBuilder ans) {
        if (cycle.contains(ch)) {
            return false;
        }
        cycle.add(ch);

        if (visited.contains(ch)) {
            return true;
        }
        visited.add(ch);

        Set<Character> list = adjMap.get(ch);

        for (char next : list) {

            if (!recur(visited, cycle, adjMap, next, ans)) {
                return false;
            }
        }
        cycle.remove(ch);
        ans.insert(0, ch);
        return true;
    }
    //=============================================================================================
    //Topological sort BFS
    public String alienOrder1(String[] words) {
        Map<Character, Set<Character>> adjMap = new HashMap<>();
        Map<Character, Integer> incoming = new HashMap<>();
        for (String word : words) {

            for (char ch : word.toCharArray()) {
                adjMap.put(ch, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            // if 2 words are equla no ordering
            if (word1.equals(word2)) {
                continue;
            }
            //If word1 starts with word2. Wrong test case
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            int w1 = 0;
            int w2 = 0;
            //The first difference is all we care about in lexicographical order as words are arranged that way
            while (w1 < word1.length() && w2 < word2.length()) {

                if (word1.charAt(w1) == word2.charAt(w2)) {
                    w1++;
                    w2++;
                } else {
                    Set<Character> set = adjMap.getOrDefault(word1.charAt(w1), new HashSet<>());
                    set.add(word2.charAt(w2));
                    adjMap.put(word1.charAt(w1), set);
                    break;
                }
            }
        }

        for (char ch : adjMap.keySet()) {

            if (!incoming.containsKey(ch)) {
                incoming.put(ch, 0);
            }
            Set<Character> set = adjMap.get(ch);

            for (char c : set) {
                incoming.put(c, incoming.getOrDefault(c, 0) + 1);
            }
        }
        //Normal topological Khans Algorithm
        StringBuilder ans = new StringBuilder();
        Deque<Character> q = new LinkedList<>();

        for (Character key : adjMap.keySet()) {

            if (incoming.getOrDefault(key, 0) == 0) {
                q.offer(key);
            }
        }
        int count = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                count++;
                char val = q.poll();
                ans.append(val);
                Set<Character> set = adjMap.get(val);

                for (char ch : set) {
                    int freq = incoming.getOrDefault(ch, -1);
                    freq--;
                    incoming.put(ch, freq);

                    if (freq == 0) {
                        q.add(ch);
                    }
                }
            }
        }

        if (count != adjMap.size()) {
            return "";
        }
        return ans.toString();
    }
}
