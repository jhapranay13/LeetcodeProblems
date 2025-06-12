package LeetcodeDiscuss;


import java.util.*;

/**
 *
 * Given list of words, count the number of buddy pairs (need only count not the string itself)
 * A pair of strings is a buddy pair if all characters of string a and b follow the same gap
 *
 * e.g. "abc" is buddy pair for "def"
 */
public class GoogleOABuddyPair {

    public static void main(String[] args) {
        System.out.println("Test 1: " + countBuddyPairs(Arrays.asList("abc", "bcd", "cde", "def"))); // 6
        System.out.println("Test 2: " + countBuddyPairs(Arrays.asList("abc", "xyz", "acd", "dfg", "pqrs"))); // 2
        System.out.println("Test 3: " + countBuddyPairs(Arrays.asList("abc", "abd", "ace", "adz"))); // 0
        System.out.println("Test 4: " + countBuddyPairs(Arrays.asList("a", "b", "c", "abc", "def"))); // 1
        System.out.println("Test 5: " + countBuddyPairs(new ArrayList<>())); // 0
        System.out.println("Test 6: " + countBuddyPairs(Arrays.asList("abc", "bcd", "cde", "def", "efg", "fgh"))); // 15
        System.out.println("Test 7: " + countBuddyPairs(Arrays.asList("abc", "ABC", "bcd", "BCD"))); // Case-sensitive
    }

    private static int countBuddyPairs(List<String> list) {
        int ans = 0;
        Map<String, Integer> freqMap = new HashMap<>();

        for (String word : list) {
            StringBuilder keyBuilder = new StringBuilder();

            for (int i = 1; i < word.length(); i++) {
                int gap = word.charAt(i) - word.charAt(i - 1);
                keyBuilder.append(gap).append(",");
            }
            String key = keyBuilder.toString();
            freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
        }

        for (int count : freqMap.values()) {
            if (count > 1) {
                ans += (count * (count - 1)) / 2; // Combination of pairs
            }
        }
        return ans;
    }
}
