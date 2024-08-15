package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 *
 * Input: nums = [1,2,2,3,1,4,2]
 * Output: 6
 * Explanation:
 * The degree is 3 because the element 2 is repeated 3 times.
 * So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 *
 *
 * Constraints:
 *
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 *
 */

public class _697_Degree_of_an_Array {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freqMapNum = new HashMap<>();
        Map<Integer, Integer> firstOccouranceNum = new HashMap<>();
        Map<Integer, Integer> lastOccouranceNum = new HashMap<>();
        int maxFreq = 0;

        for (int i = 0; i < nums.length; i++) {
            int freq = freqMapNum.getOrDefault(nums[i], 0);
            freq++;
            freqMapNum.put(nums[i], freq);
            maxFreq = Math.max(maxFreq, freq);

            if (!firstOccouranceNum.containsKey(nums[i])) {
                firstOccouranceNum.put(nums[i], i);
            }
            lastOccouranceNum.put(nums[i], i);
        }
        int ans = Integer.MAX_VALUE;

        for (int num : nums) {
            int freq = freqMapNum.get(num);

            if (freq == maxFreq) {
                int firstOccourance = firstOccouranceNum.get(num);
                int lastOccourance = lastOccouranceNum.get(num);
                ans = Math.min(ans, lastOccourance - firstOccourance + 1);
            }
        }
        return ans;
    }
    //=============================================================================================
    // Sliding window
    public int findShortestSubArray1(int[] nums) {
        Map<Integer, Integer> freqMapNum = new HashMap<>();
        int maxFreq = 0;

        for (int num : nums) {
            int freq = freqMapNum.getOrDefault(num, 0);
            freq++;
            maxFreq = Math.max(maxFreq, freq);
            freqMapNum.put(num, freq);
        }

        int currMaxFreq = 0;
        int fast = 0;
        int slow = 0;
        int ans = nums.length;
        Map<Integer, Integer> freqNum = new HashMap<>();

        while (fast < nums.length) {
            int num = nums[fast];
            int freq = freqNum.getOrDefault(num, 0);
            freq++;
            currMaxFreq = Math.max(currMaxFreq, freq);
            freqNum.put(num, freq);

            while (slow <= fast && currMaxFreq == maxFreq) {
                ans = Math.min(ans, fast - slow + 1);
                int slowNum = nums[slow++];
                int slowFreq = freqNum.get(slowNum);

                if (slowFreq == 1) {
                    freqNum.remove(slowNum);
                } else {
                    freqNum.put(slowNum, slowFreq - 1);
                }

                if (slowFreq == currMaxFreq) {
                    currMaxFreq--;
                    break;
                }
            }
            fast++;
        }
        return ans;
    }

}
