package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * we need to find the maximum sum in a subarray with atmost k distinct elements .
 * the array has both positive and negative values in the array?
 *
 */

public class MaxSubArraySumWithKDistinct {

    public static void main(String ...args) {
        int[] arr = {1, 2, 1, 2, 3};
        int k = 2;
        System.out.println(maxSubArraySumWithKDistinct(arr, k));
        System.out.println(maxSubArraySumWithKDistinct(new int[]{1, 2, 1, 2, 3, -2, 4, -1}, 2)); // 6
        System.out.println(maxSubArraySumWithKDistinct(new int[]{4, 5, 1, 2, 3}, 3));           // 10
        System.out.println(maxSubArraySumWithKDistinct(new int[]{-5, -1, -2, -3}, 2));          // -3
        System.out.println(maxSubArraySumWithKDistinct(new int[]{1, 1, 1, 1}, 5));              // 4
        System.out.println(maxSubArraySumWithKDistinct(new int[]{1, 2, 2, 3, 3, 3, 1, 1}, 1));   // 9
        System.out.println(maxSubArraySumWithKDistinct(new int[]{}, 2));                        // Integer.MIN_VALUE
        System.out.println(maxSubArraySumWithKDistinct(new int[]{7}, 1));                       // 7

    }

    private static int maxSubArraySumWithKDistinct(int[] arr, int k) {
        int ans = Integer.MIN_VALUE;
        Map<Integer, Integer> freqMap = new HashMap<>();
        int slow = 0;
        int fast = 0;
        int sum = 0;

        while(fast < arr.length) {
            int fastVal = arr[fast];
            sum += fastVal;
            freqMap.put(fastVal, freqMap.getOrDefault(fastVal, 0) + 1);

            while (slow < fast && freqMap.size() > k) {
                int slowVal = arr[slow];
                sum -= slowVal;
                freqMap.put(slowVal, freqMap.get(slowVal) - 1);

                if (freqMap.get(slowVal) == 0) {
                    freqMap.remove(slowVal);
                }
                slow++;
            }
            ans = Math.max(ans, sum);
            fast++;
        }
        return ans;
    }
}
