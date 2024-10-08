package leetcode.Sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, return true if any value appears at
 *         least twice in the array, and return false if every element is
 *         distinct.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2,3,1] Output: true 
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2,3,4] Output: false 
 *         
 *         Example 3:
 * 
 *         Input: nums = [1,1,1,3,3,4,3,2,4,2] Output: true
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 -109 <= nums[i] <= 109
 *
 */

public class _217_ContainsDuplicate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//===============================================================================
	//Using Sorting 
	public boolean containsDuplicate(int[] nums) {
		Arrays.sort(nums);

		for(int i = 1; i < nums.length; i++) {

			if (nums[i] == nums[i - 1]) {
				return true;
			}
		}
		return false;
	}
	//===============================================================================
	//Using Set
	public boolean containsDuplicate1(int[] nums) {
		Set<Integer> cache = new HashSet<>();

		for (int num : nums) {

			if (!cache.add(num)) {
				return true;
			}
		}
		return false;
	}
}
