package leetcode.medium;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105

Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 *
 */

public class _189_RotateArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_189_RotateArray obj = new _189_RotateArray();
		obj.rotate1(new int[] {1,2,3,4,5,6, 7}, 3);
	}
	//reverse Approach
	public void rotate(int[] nums, int k) {
		k = k % nums.length;
		reverse(nums, 0, nums.length - 1);
		reverse(nums, k, nums.length - 1);
		reverse(nums, 0, k - 1);
	}

	private void reverse(int[] nums, int lo, int hi) {

		while (lo < hi) {
			int temp = nums[lo];
			nums[lo++] = nums[hi];
			nums[hi--] = temp;
		}
	}
	//=============================================================================
	//Cyclic movement approach
	public void rotate1(int[] nums, int k) {
        k = k % nums.length;
        int countOfElementsHit = 0;
        
        for (int i = 0 ; countOfElementsHit < nums.length; i++) {
            int start = i;
            int temp = nums[start];
            
            do {
                start +=k;
                start %= nums.length;
                int tempNum = nums[start];
                nums[start] = temp;
                temp = tempNum;
                countOfElementsHit++;
            } while(i != start);
        }
    }
	//=============================================================================
	//Another Cyclic with condition changed
	public void rotate2(int[] nums, int k) {
		int touched = 0;
		k = k % nums.length;

		if (k == 0) {
			return;
		}

		for (int anchor = 0; anchor < nums.length; anchor++) {
			int curr = anchor;
			int val = nums[curr];

			do {
				int pos = (curr + k) % nums.length;
				int temp = nums[pos];
				nums[pos] = val;
				val = temp;
				curr = pos;
				touched++;
			} while (curr != anchor);

			if (touched == nums.length) {
				break;
			}
		}
	}
}
