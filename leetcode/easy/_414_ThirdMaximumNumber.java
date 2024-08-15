package leetcode.easy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given integer array nums, return the third maximum number in this
 *         array. If the third maximum does not exist, return the maximum
 *         number.
 * 
 *         Example 1:
 * 
 *         Input: nums = [3,2,1] Output: 1 Explanation: The third maximum is 1.
 *         
 *         Example 2:
 * 
 *         Input: nums = [1,2] Output: 2 Explanation: The third maximum does not
 *         exist, so the maximum (2) is returned instead. 
 *         
 *         Example 3:
 * 
 *         Input: nums = [2,2,3,1] Output: 1 Explanation: Note that the third
 *         maximum here means the third maximum distinct number. Both numbers
 *         with value 2 are both considered as second maximum.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -231 <= nums[i] <= 231 - 1
 * 
 * 
 *         Follow up: Can you find an O(n) solution?
 *
 */

public class _414_ThirdMaximumNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	public int thirdMax(int[] nums) {    

		Set<Integer> seenMaximums = new HashSet<>();

		for (int i = 0; i < 3; i++) {
			Integer curMaximum = maxIgnoringSeenMaximums(nums, seenMaximums);
			if (curMaximum == null) {
				return Collections.max(seenMaximums);
			}
			seenMaximums.add(curMaximum);
		}

		return Collections.min(seenMaximums);
	}


	private Integer maxIgnoringSeenMaximums(int[] nums, Set<Integer> seenMaximums) {

		Integer maximum = null;
		for (int num : nums) {
			if (seenMaximums.contains(num)) {
				continue;
			}
			if (maximum == null || num > maximum) {
				maximum = num;
			}
		}
		return maximum;
	}
	//=============================================================================
	public int thirdMax1(int[] nums) {

		// Put the input integers into a HashSet.
		Set<Integer> setNums = new HashSet<>();
		for (int num : nums) setNums.add(num);

		// Find the maximum.
		int maximum = Collections.max(setNums);

		// Check whether or not this is a case where we
		// need to return the *maximum*.
		if (setNums.size() < 3) {
			return maximum;
		}

		// Otherwise, continue on to finding the third maximum.
		setNums.remove(maximum);
		int secondMaximum = Collections.max(setNums);
		setNums.remove(secondMaximum);
		return Collections.max(setNums);
	}
	//==============================================================================
	public int thirdMax2(int[] nums) {

		if( nums.length < 3 ) {
			int max = Integer.MIN_VALUE;

			for( int i : nums ) {
				max = Math.max( i, max );
			}
			return max;
		}
		Set< Long > v = new HashSet<>();
		long ans = Long.MIN_VALUE;

		for( int i = 0; i < 3; i++ ){
			long max = Long.MIN_VALUE;

			for( long j : nums ) {

				if( !v.contains( j ) ) {
					max = Math.max( max, j );
				}
			}
			v.add( max );
			ans = max;
		}

		if( ans == Long.MIN_VALUE ) {

			for( long i : v ) {
				ans = Math.max( i, ans );
			}
		}
		return (int)ans;
	}
}
