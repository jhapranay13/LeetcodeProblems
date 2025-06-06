package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *Given a string categories find out the number of ways to
 * split the string into exactly two contiguous non empty substring such
 * that number of distinct shared characters occouring in both the substring
 * is greater than k.
 *
 */

public class GoolgeOASplitTheStringWithDistinctGreaterThanK {

    public static void main(String args[]) {
        //String s = "abcabc";
        String s = "abbcac";
        int k = 1;
        System.out.println(splitString(s, k));
    }

    private static int splitString(String s, int k) {
        int count = 0;
        Map<Character, Integer> suffixCountMap = new HashMap<>();
        Map<Character, Integer> prefixCountMap = new HashMap<>();

        for (char ch : s.toCharArray()) {
            suffixCountMap.put(ch, suffixCountMap.getOrDefault(ch, 0) + 1);
        }
        int sharedCount = 0;

        for (char ch : s.toCharArray()) {
            suffixCountMap.put(ch, suffixCountMap.get(ch) - 1);

            if (suffixCountMap.get(ch) == 0) {
                suffixCountMap.remove(ch);
                sharedCount--;
            }

            if (!prefixCountMap.containsKey(ch) && suffixCountMap.containsKey(ch)) {
                sharedCount++;
            }
            prefixCountMap.put(ch, prefixCountMap.getOrDefault(ch, 0) + 1);

            if (sharedCount > k) {
                count++;
            }
        }
        return count;
    }
}
