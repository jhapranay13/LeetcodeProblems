package leetcode.Greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled
 * array, return an empty array. The elements in original may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 * Example 2:
 *
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * Example 3:
 *
 * Input: changed = [1]
 * Output: []
 * Explanation: changed is not a doubled array.
 *
 *
 * Constraints:
 *
 * 1 <= changed.length <= 105
 * 0 <= changed[i] <= 105
 *
 */

public class _2007_FindOriginalArrayFromDoubledArray {

    public int[] findOriginalArray(int[] changed) {

        if (changed.length % 2 != 0) {
            return new int[0];
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] ans = new int[changed.length/ 2];
        int index = 0;

        for (int num : changed) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        Arrays.sort(changed);

        for (int i = 0; i < changed.length; i++) {

            if (!freqMap.containsKey(changed[i])) {
                continue;
            }
            int freqLow = freqMap.get(changed[i]);
            int freqDouble = freqMap.getOrDefault(changed[i] * 2, 0);

            if (changed[i] == 0) {

                if (freqLow < 2) {
                    return new int[0];
                }

                if (freqLow == 2) {
                    freqMap.remove(changed[i]);
                } else {
                    freqMap.put(changed[i], freqLow - 2);
                }
                ans[index++] = 0;
                continue;
            }

            if (freqDouble == 0) {
                return new int[0];
            }

            if (freqLow == 1) {
                freqMap.remove(changed[i]);
            } else {
                freqMap.put(changed[i], --freqLow);
            }

            if (freqDouble == 1) {
                freqMap.remove(changed[i] * 2);
            } else {
                freqMap.put(changed[i] * 2, --freqDouble);
            }
            ans[index++] = changed[i];
        }
        return ans;
    }
}
