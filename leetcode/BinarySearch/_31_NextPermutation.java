package leetcode.BinarySearch;

import java.util.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]

Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]

Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]

Example 4:

Input: nums = [1]
Output: [1]


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
 *
 */


public class _31_NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void nextPermutation(int[] nums) {
		boolean swapped = false;

		for (int i = nums.length - 1; i > 0; i--) {

			if (nums[i] > nums[i - 1]) {
				int justGreaterThanIndex = binarySearchJustGreaterThan(nums, i, nums[i -1]);
				int temp = nums[justGreaterThanIndex];
				nums[justGreaterThanIndex] = nums[i - 1];
				nums[i - 1] = temp;
				Arrays.sort(nums, i, nums.length);
				swapped = true;
				break;
			}
		}

		if (!swapped) {
			Arrays.sort(nums);
		}
	}

	//The array till start should be arranged in descending order.
	private int binarySearchJustGreaterThan(int nums[], int start, int target) {
		int lo = start;
		int hi = nums.length - 1;
		int ans = -1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (nums[pivot] > target) {
				ans = pivot;
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}

		//first occourance
		while (ans > 0 && nums[ans] == nums[ans - 1]) {
			ans--;
		}
		return ans;
	}
	//Time Complexity O(N)
	
	//======================================================================
	//Another way
	public void nextPermutation1(int[] nums) {
        boolean swapped = false;
        
        for (int i = nums.length - 1; i > 0; i--) {
            
            if (nums[i] > nums[i - 1]) {
                int index = binarySearchJustGreater(nums, i, nums[i - 1]);
                
                if (index > -1) {
                    swapped = true;
                    int temp = nums[index];
                    nums[index] = nums[i - 1];
                    nums[i - 1] = temp;
                    reverse(nums, i, nums.length - 1);
                    break;
                }
            }
        }
        
        if (!swapped) {
            reverse(nums, 0, nums.length - 1);
        }
    }
    
    private void reverse(int[] nums, int lo, int hi) {
        
        while (lo < hi) {
            int temp = nums[lo];
            nums[lo++] = nums[hi];
            nums[hi--] = temp;
        }
    }
    
    private int binarySearchJustGreater(int[] nums, int start, int target) {
        int lo = start;
        int hi = nums.length - 1;
        int ans = -1;
        
        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (nums[pivot] > target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        
        //Right Most 
        while (ans < nums.length - 1 && nums[ans] == nums[ans + 1]) {
            ans++;
        }
        return ans;
    }
}
