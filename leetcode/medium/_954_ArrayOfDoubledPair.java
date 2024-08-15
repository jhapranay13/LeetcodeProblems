package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: arr = [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 *
 *
 * Constraints:
 *
 * 2 <= arr.length <= 3 * 104
 * arr.length is even.
 * -105 <= arr[i] <= 105
 *
 */

public class _954_ArrayOfDoubledPair {

    public boolean canReorderDoubled(int[] arr) {
        Integer [] b = new Integer[arr.length];

        for (int i= 0; i < arr.length; i++) {
            b[i] = arr[i];
        }
        Arrays.sort(b, new Comparator<Integer>() {
            //Using absolute coz in case of array such as [4,-2,2,-4]
            // -4 will be taken up first and will give wrong answers.
            public int compare(Integer i1, Integer i2) {

                return Math.abs(i1) - Math.abs(i2);
            }
        });
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        for (int num : b) {

            if (freqMap.containsKey(num)) {

                if (num == 0 && freqMap.get(num) <= 1) {
                    return false;
                }

                if (!freqMap.containsKey(num * 2)) {
                    return false;
                }

                if (freqMap.get(num) == 1) {
                    freqMap.remove(num);
                } else {
                    freqMap.put(num, freqMap.get(num) - 1);
                }

                if (freqMap.get(num * 2) == 1) {
                    freqMap.remove(num * 2);
                } else {
                    freqMap.put(num * 2, freqMap.get(num * 2) - 1);
                }
            }
        }
        return freqMap.isEmpty();
    }
}
