package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums and an integer k, return true if there
 *         are two distinct indices i and j in the array such that nums[i] ==
 *         nums[j] and abs(i - j) <= k.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,1], k = 3 Output: true 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,0,1,1], k = 1 Output: true
 *         
 *         Example 3:
 * 
 *         Input: nums = [1,2,3,1,2,3], k = 2 Output: false
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 -109 <= nums[i] <= 109 0 <= k <= 105
 *
 */
public class _219_ContainsDuplicate2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            
            if (valueIndexMap.containsKey(nums[i])) {
                int index = valueIndexMap.get(nums[i]);
                
                if (Math.abs(index - i) <= k) {
                    return true;
                }
            }
            //If it does not exist put in map. If it exist and distance is k override it
            //as it will be too far for others
            valueIndexMap.put(nums[i], i);
        }
        return false;
    }
}
