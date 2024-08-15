package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

Constraints:

1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 *
 */

public class _80_RemoveDuplicatesFromSortedArray2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int removeDuplicates(int[] nums) {
		int pos = 0;
		int count = 0;
		int currNum = nums[0];

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] == currNum) {
				count++;
			} else {
				currNum = nums[i];
				count = 1;
			}
			nums[pos] = currNum;

			if (count <= 2) {
				pos++;
			}
		}
		return pos;
	}
	//===================================================================================
	public int removeDuplicates1(int[] nums) {

		if( nums == null || nums.length == 0 ) {
			return 0;
		}
		int count = 1;
		int prev = nums[ 0 ];
		int i = 0;

		for( int j = 1; j < nums.length; j++ ) {

			if( nums[ i ] != nums[ j ] ) {
				count = 1;
				i++;
				nums[ i ] = nums[ j ];
				continue;
			}
			count++;

			if( count <= 2  ) {
				i++;
				nums[ i ] = nums[ j ];
			}
		}
		return i + 1;
	}
	//=============================================================================
	public int removeDuplicates3(int[] nums) {

		if( nums == null || nums.length == 0 ) {
			return 0;
		}
		int i = 0;
		int count = 1;

		for( int j = 0; j < nums.length; j++ ) {

			if( j > 0 && nums[ j ] == nums[ j - 1 ] ) {
				count++;
			} else {
				count = 1;
			}

			if( count <= 2 ) {
				nums[ i ] = nums[ j ];
				i++;
			}
		}
		return i;
	}
}
