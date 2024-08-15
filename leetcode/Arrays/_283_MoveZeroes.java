package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, move all 0's to the end of it while
 *         maintaining the relative order of the non-zero elements.
 * 
 *         Note that you must do this in-place without making a copy of the
 *         array.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [0,1,0,3,12] Output: [1,3,12,0,0] 
 *         
 *         Example 2:
 * 
 *         Input: nums = [0] Output: [0]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 104 -231 <= nums[i] <= 231 - 1
 * 
 * 
 *         Follow up: Could you minimize the total number of operations done?
 *
 */

public class _283_MoveZeroes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//=============================================================================
	public void moveZeroes(int[] nums) {
		int pointer = 0;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] == 0) {
				continue;
			}
			nums[pointer++] = nums[i];
		}

		while (pointer < nums.length) {
			nums[pointer++] = 0;
		}
	}
	//==============================================================================
	public void moveZeroes1(int[] nums) {
		int pos = 0;

		for( int i = 0; i < nums.length; i++ ) {

			if( nums[ i ] != 0 ) {
				int temp = nums[ i ];
				nums[ i ] = nums[ pos ];
				nums[ pos++ ] = temp;
			}
		}
	}
	//==============================================================================
	//Another approach
	public void moveZeroes2(int[] nums) {
		int anchor = -1;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] == 0) {
				if (anchor == -1 || nums[anchor] != 0) {
					anchor = i;
				} else{
					while (anchor < nums.length && nums[anchor] != 0) {
						anchor++;
					}
				}
			}

			if (nums[i] != 0 && anchor != -1 && anchor < i) {
				int temp = nums[i];
				nums[i] = 0;
				nums[anchor] = temp;
				anchor++;
			}
		}
	}
}
