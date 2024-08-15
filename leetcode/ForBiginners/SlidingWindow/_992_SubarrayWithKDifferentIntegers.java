package leetcode.ForBiginners.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array nums of positive integers, call a (contiguous, not
 *         necessarily distinct) subarray of nums good if the number of
 *         different integers in that subarray is exactly k.
 * 
 *         (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 * 
 *         Return the number of good subarrays of nums.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,1,2,3], k = 2 Output: 7 Explanation: Subarrays
 *         formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3],
 *         [1,2,1], [2,1,2], [1,2,1,2]. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,1,3,4], k = 3 Output: 3 Explanation: Subarrays
 *         formed with exactly 3 different integers: [1,2,1,3], [2,1,3],
 *         [1,3,4].
 * 
 * 
 *         Note:
 * 
 *         1 <= nums.length <= 20000 1 <= nums[i] <= nums.length 1 <= k <=
 *         nums.length
 *
 */

public class _992_SubarrayWithKDifferentIntegers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int subarraysWithKDistinct(int[] nums, int k) {
        return numOfKDiffInteger(nums, k) - numOfKDiffInteger(nums, k -1);
    }
    
    public int numOfKDiffInteger(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        int ans = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        while (fast < nums.length) {
            freqMap.put(nums[fast], freqMap.getOrDefault(nums[fast], 0) + 1);
            
            while (freqMap.size() > k) {
                int freq = freqMap.get(nums[slow]);
                
                if (freq == 1) {
                    freqMap.remove(nums[slow]);
                } else {
                    freqMap.put(nums[slow], --freq);
                }
                slow++;
            }
            ans += fast - slow + 1;
            fast++;
        }
        return ans;
    }
}
