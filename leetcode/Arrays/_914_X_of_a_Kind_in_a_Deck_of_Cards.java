package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an integer array deck where deck[i] represents the number written on the ith card.
 *
 * Partition the cards into one or more groups such that:
 *
 * Each group has exactly x cards where x > 1, and
 * All the cards in one group have the same integer written on them.
 * Return true if such partition is possible, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 *
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 *
 *
 * Constraints:
 *
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 *
 */

public class _914_X_of_a_Kind_in_a_Deck_of_Cards {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : deck) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        int n = deck.length;

        for (int i = 2; i <= n; i++) {
            boolean canMake = true;

            for (int key : freqMap.keySet()) {
                int freq = freqMap.get(key);

                if (freq % i != 0) {
                    canMake = false;
                    break;
                }
            }

            if (canMake) {
                return true;
            }
        }
        return false;
    }
}
