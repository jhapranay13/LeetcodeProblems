package leetcode.TrickyOneOfAKind;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array nums and you have to return a new
 *         counts array. The counts array has the property where counts[i] is
 *         the number of smaller elements to the right of nums[i].
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [5,2,6,1] Output: [2,1,1,0] Explanation: To the right
 *         of 5 there are 2 smaller elements (2 and 1). To the right of 2 there
 *         is only 1 smaller element (1). To the right of 6 there is 1 smaller
 *         element (1). To the right of 1 there is 0 smaller element. Example 2:
 * 
 *         Input: nums = [-1] Output: [0] Example 3:
 * 
 *         Input: nums = [-1,-1] Output: [0,0]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 105 -104 <= nums[i] <= 104
 *
 */

public class _315_CountOfSmallerNumbersAfterSelf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// MergeSort Version
	public List<Integer> countSmaller1(int[] nums) {
		int[] indices = new int[nums.length];

		for (int i = 0; i < indices.length; i++) {
			indices[i] = i;
		}
		int[] ans = new int[nums.length];
		mergeSort(nums, ans, indices, 0, nums.length - 1);
		List<Integer> ret = new ArrayList<>();

		for (int a : ans) {
			ret.add(a);
		}
		return ret;
	}

	private void mergeSort(int[] nums, int[] ans, int[] indices, int lo, int hi) {
		if (lo < hi) {
			int pivot = lo + (hi - lo) / 2;
			mergeSort(nums, ans, indices, lo, pivot);
			mergeSort(nums, ans, indices, pivot + 1, hi);
			merge(nums, ans, indices, lo, pivot, hi);
		}
	}

	private void merge(int[] nums, int[] ans, int[] indices, int lo, int pivot, int hi) {
		int i = lo;
		int j = pivot + 1;
		int rightToLeftCount = 0;
		int[] helper = new int[hi - lo + 1];
		int index = 0;

		while (i <= pivot && j <= hi) {
			// you add result on the left indexes so that we have precise count
			// when the left index is moving.
			if (nums[indices[i]] <= nums[indices[j]]) {
				helper[index++] = indices[i];
				ans[indices[i]] += rightToLeftCount;
				i++;
			} else {
				rightToLeftCount++;
				helper[index++] = indices[j];
				j++;
			}
		}

		while (i <= pivot) {
			ans[indices[i]] += rightToLeftCount;
			helper[index++] = indices[i++];
		}

		while (j <= hi) {
			helper[index++] = indices[j++];
		}
		index = 0;

		for (int k = lo; k <= hi; k++) {
			indices[k] = helper[index++];
		}
	}

	// ======================================================================
	// Previously Passed Now TLE
	public List<Integer> countSmaller(int[] nums) {
		int[] temp = new int[nums.length];
		List<Integer> ans = new ArrayList<>();

		for (int i = nums.length - 1; i >= 0; i--) {
			int index = insertionSort(temp, nums, i);
			ans.add(0, index);
		}
		return ans;
	}

	public int insertionSort(int[] temp, int[] nums, int i) {
		int tempIndex = nums.length - 1 - i;
		temp[tempIndex] = nums[i];

		if (tempIndex == 0) {
			return tempIndex;
		}
		int index = tempIndex;

		for (int j = tempIndex; j > 0; j--) {

			if (temp[j] > temp[j - 1]) {
				break;
			}
			int tmp = temp[j - 1];
			temp[j - 1] = temp[j];
			temp[j] = tmp;
			index = j - 1;

		}
		return index;
	}
	//=============================================================================================
	//Merge Sort for Merge for better understanding of how to sort the indexes.
	private void merge1(int[] nums, int[] indexes, int[] ans, int lo, int pivot, int hi) {
		int[] helper = new int[hi -lo + 1];
		int x = lo;
		int y = pivot + 1;
		int index = 0;
		int rightToLeftCount = 0;

		while (x <= pivot && y <= hi) {
			int index1 = indexes[x];
			int index2 = indexes[y];

			if (nums[index1] <= nums[index2]) {
				helper[index++] = index1;
				x++;
				ans[index1] += rightToLeftCount;
			} else  {
				helper[index++] = index2;
				y++;
				rightToLeftCount++;
			}
		}

		while (x <= pivot) {
			int index1 = indexes[x++];
			helper[index++] = index1;
			ans[index1] += rightToLeftCount;
		}

		while (y <= hi) {
			int index2 = indexes[y++];
			helper[index++] = index2;
		}
		index = 0;

		for (int i = lo; i <= hi; i++) {
			indexes[i] = helper[index++];
		}
	}
	//=============================================================================================
	//Segment Tree
	class SegmentTree {
		int n;
		int cache[];

		public SegmentTree(int n) {
			this.n = n;
			int calc = (int) Math.ceil(Math.log(n) / Math.log(2));
			int size = 2 * (int) (Math.pow(2, calc)  - 1);
			this.cache = new int[size];
		}

		public int get(int lo, int hi) {

			if (lo < 0 || hi > n - 1 || lo > hi) {
				return -1;
			}
			return get(0, n -1, lo, hi, 0);
		}

		private int get(int lo, int hi, int qlo, int qhi, int cacheIndex) {

			if (lo > qhi || hi < qlo) {
				return 0;
			}

			if (lo >= qlo && hi <= qhi) {
				return cache[cacheIndex];
			}
			int pivot = lo + (hi - lo) / 2;
			int left = get(lo, pivot, qlo, qhi, cacheIndex * 2 + 1);
			int right = get(pivot + 1, hi, qlo, qhi, cacheIndex * 2 + 2);
			return left + right;
		}

		public void update(int val, int index) {
			update(0, n - 1, 1, index, 0);
		}

		private void update(int lo, int hi, int val, int index, int cacheIndex) {

			if (lo > index || hi < index) {
				return;
			}

			if (lo == hi) {
				cache[cacheIndex] += val;
			}
			if (lo != hi) {
				int pivot = lo + (hi - lo) / 2;
				update(lo, pivot, val, index, cacheIndex * 2 + 1);
				update(pivot + 1, hi, val, index, cacheIndex * 2 + 2);
				cache[cacheIndex] = cache[cacheIndex * 2 + 1] + cache[cacheIndex * 2 + 2];
			}
		}
	}

	public List<Integer> countSmaller4(int[] nums) {
		int max = Integer.MIN_VALUE;

		for (int num : nums) {
			max = Math.max(max, Math.abs(num));
		}
		max *= 2;
		max += 2;
		max++;
		int addVal = max / 2 + 1;

		SegmentTree sgt = new SegmentTree(max);
		List<Integer> ans = new ArrayList<>();

		for (int i = nums.length - 1; i >= 0; i--) {
			int num = nums[i];
			int res = sgt.get(0, num - 1 + addVal);
			ans.add(0, res);
			sgt.update(1, num + addVal);
		}
		return ans;
	}
}
