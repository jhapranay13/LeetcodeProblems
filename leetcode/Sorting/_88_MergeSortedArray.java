package leetcode.Sorting;

/**
 * 
 * @author Pranay Jha
 *
 *You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].

Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */

public class _88_MergeSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int index1 = m - 1;
		int index2 = n - 1;
		int ansIndex = m + n - 1;

		while (index1 >= 0 && index2 >= 0) {

			if(nums1[index1] > nums2[index2]) {
				nums1[ansIndex--] = nums1[index1--];
			} else {
				nums1[ansIndex--] = nums2[index2--];
			}
		}

		while (index1 >= 0) {
			nums1[ansIndex--] = nums1[index1--];
		}

		while (index2 >= 0) {
			nums1[ansIndex--] = nums2[index2--];
		}
	}
	//==================================================================================
	//Another Version
	public void merge1(int[] nums1, int m, int[] nums2, int n) {
		int ptr = nums1.length - 1;
		int s1 = m - 1;
		int s2 = n - 1;

		while( s1 >= 0 || s2 >= 0 ) {

			if( s1 >= 0 && s2 >= 0 ) {
				int n1 = nums1[ s1 ];
				int n2 = nums2[ s2 ];

				if( n1 < n2 ) {
					nums1[ ptr-- ] = n2;
					s2--;
				} else {
					nums1[ ptr-- ] = n1;
					s1--;
				}
				continue;
			}

			if( s1 >= 0 ) {
				nums1[ ptr-- ] = nums1[ s1-- ];
			}

			if( s2 >= 0 ) {
				nums1[ ptr-- ] = nums2[ s2-- ];
			}
		}
	}
}
