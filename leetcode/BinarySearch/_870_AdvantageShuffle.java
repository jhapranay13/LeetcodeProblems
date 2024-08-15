package leetcode.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two arrays nums1 and nums2 of equal size, the advantage of
 *         nums1 with respect to nums2 is the number of indices i for which
 *         nums1[i] > nums2[i].
 * 
 *         Return any permutation of nums1 that maximizes its advantage with
 *         respect to nums2.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums1 = [2,7,11,15], nums2 = [1,10,4,11] Output: [2,11,7,15]
 * 
 *         Example 2:
 * 
 *         Input: nums1 = [12,24,8,32], nums2 = [13,25,32,11] Output:
 *         [24,32,8,12]
 * 
 * 
 *         Note:
 * 
 *         1 <= nums1.length = nums2.length <= 10000 0 <= nums1[i] <= 109 0 <=
 *         nums2[i] <= 109
 *
 */

public class _870_AdvantageShuffle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Binary search implementation
	public int[] advantageCount(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		List<Integer> list = new ArrayList<>();

		for (int num : nums1) {
			list.add(num);
		}
		int[] ans = new int[nums2.length];

		for (int i = 0; i < nums2.length; i++) {
			int num = nums2[i];
			int index = binarySearchJustGreaterThan(list, num);
			ans[i] = list.remove(index);
		}
		return ans;
	}

	private int binarySearchJustGreaterThan(List<Integer> list, int num) {
		int lo = 0;
		int hi = list.size();

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;
			int pivotVal = list.get(pivot);

			if (pivotVal <= num) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return hi == list.size() ? 0 : hi;
	}

	// ==============================================================================
	// Different Binary Search
	public int[] advantageCount1(int[] A, int[] B) {
		Arrays.sort(A);
		List<Integer> al = new ArrayList<>();

		for (int ia : A) {
			al.add(ia);
		}
		int[] ans = new int[A.length];
		int index = 0;

		for (int b : B) {
			int val = bsg(b, al);
			ans[index++] = val;
		}
		return ans;
	}

	private int bsg(int b, List<Integer> list) {
		int lo = 0;
		int hi = list.size() - 1;

		while (lo <= hi) {

			if (hi - lo <= 1) {
				int currh = list.get(hi);
				int currl = list.get(lo);

				if (currl > b) {
					return list.remove(lo);
				} else if (currh > b) {
					return list.remove(hi);
				}
				break;
			}
			int p = lo + (hi - lo) / 2;
			int curr = list.get(p);

			if (curr <= b) {
				lo = p + 1;
			} else {
				hi = p;
			}
		}
		return list.remove(0);
	}
	//=============================================================================================
	//Another Binary Search Approach
	public int[] advantageCount3(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		List<Integer> holder = new ArrayList<>();

		for (int num : nums1) {
			holder.add(num);
		}
		int[] ans = new int[nums1.length];

		for (int i = 0; i < nums2.length; i++) {
			int num = nums2[i];
			int index = binarySearchJustGreaterThan3(holder, num);
			ans[i] = holder.remove(index);
		}
		return ans;
	}

	private int binarySearchJustGreaterThan3(List<Integer> holder, int num) {
		int ans = 0;
		int lo = 0;
		int hi = holder.size() - 1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (holder.get(pivot) > num) {
				ans = pivot;
				hi = pivot - 1;
			} else {
				lo = pivot + 1;
			}
		}
		return ans;
	}
}
