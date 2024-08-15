package leetcode.DP.TwoDimension;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array nums, return the length of the longest
 *         strictly increasing subsequence.
 * 
 *         A subsequence is a sequence that can be derived from an array by
 *         deleting some or no elements without changing the order of the
 *         remaining elements. For example, [3,6,2,7] is a subsequence of the
 *         array [0,3,1,6,2,2,7].
 * 
 *         Example 1:
 * 
 *         Input: nums = [10,9,2,5,3,7,101,18] Output: 4 Explanation: The
 *         longest increasing subsequence is [2,3,7,101], therefore the length
 *         is 4. 
 *         
 *         Example 2:
 * 
 *         Input: nums = [0,1,0,3,2,3] Output: 4 
 *         
 *         Example 3:
 * 
 *         Input: nums = [7,7,7,7,7,7,7] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 2500 -104 <= nums[i] <= 104
 * 
 * 
 *         Follow up: Can you come up with an algorithm that runs in O(n log(n))
 *         time complexity?
 *
 */

public class _300_LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Solution
	public int lengthOfLIS(int[] nums) {
		int[] memo = new int[nums.length];
		int ans = 0;

		for (int index = 0; index < nums.length; index++) {
			ans = Math.max(ans, recur(nums, index, memo));
		}
		return ans;
	}
	//Cannot apply include exclude logic as whichever is max will come int hat memo
	//position and start giving wornf answerse. g [4,10,4,3,8,9] memo will be 
	//[0, 3, 3, 3, 2, 1]. So for 1st index that is wrong it should be 2. But include 
	//and exclude will take max of it and when it recurses back it adds to 4.So it
	//won't work for such problems
	private int recur(int[] nums, int index, int[] memo) {

		if (index == nums.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int ans = 1;

		for (int i = index + 1; i < nums.length; i++) {

			if (nums[i] > nums[index] ) {
				ans = Math.max(ans, 1 + recur(nums, i, memo));
			}
		}
		return memo[index] = ans;
	}
	//=============================================================================
	//Top Down Include Exclude Solution
	public int lengthOfLIS1(int[] nums) {

		if( nums == null || nums.length == 0 ) {
			return 0;
		}

		if( nums.length == 1 ) {
			return 1;
		}
		int[][] memo = new int[ nums.length + 1 ][ nums.length ];
		int lis = longestSubSeqTD( nums, -1, 0, memo );
		return lis;
	}

	private int longestSubSeqTD(int[] arr, int prev, int cur, int[][] memo) {

		if( cur >= arr.length ) {
			return 0;
		}

		if( memo[ prev + 1 ][ cur ] > 0 ) {
			return memo[ prev + 1 ][ cur ];
		}
		int included = 0;
		int excluded = 0;

		if( prev < 0 || arr[ prev ] < arr[ cur ] ) {
			included = 1 + longestSubSeqTD(arr, cur, cur + 1, memo );
		}
		excluded = longestSubSeqTD(arr, prev, cur + 1, memo );
		return  memo[ prev + 1 ][ cur ] = Math.max( included , excluded);
	}
	//===========================================================================
	//Another approach
	public int lengthOfLIS3(int[] nums) {
		int[] memo = new int[nums.length + 1];
		return recur(nums, -1, 0, memo);
	}

	private int recur(int[] nums, int prev, int index, int[] memo) {

		if (index == nums.length) {
			return 0;
		}

		if (memo[prev + 1] > 0) {
			return memo[index];
		}
		int ans = 0;

		if (prev == -1 || nums[prev] < nums[index]) {
			ans = 1 + recur(nums, index, index + 1, memo);
		}
		ans = Math.max(ans, recur(nums, prev, index + 1, memo));
		return memo[prev + 1] = ans;
	}
	//===========================================================================
	// Another Bottom up approach
	public int lengthOfLIS4(int[] nums) {
		int[][] memo = new int[nums.length + 2][nums.length + 1];

		for (int index = nums.length - 1; index >= 0; index--) {

			for (int prev = index - 1; prev >= -1; prev--) {
				int ans = 0;

				if (prev == -1 || nums[prev] < nums[index]) {
					ans = 1 + memo[index + 1][index + 1];
				}
				ans = Math.max(ans, memo[prev + 1][index + 1]);
				memo[prev + 1][index] = ans;
			}
		}
		return memo[0][0];
	}	//============================================================================
	//Bottom up approach
	public int lengthOfLIS2(int[] nums) {
        int[] memo = new int[nums.length];
        int max = 0;
        
        for (int index = 0; index < nums.length; index++) {
            int ans = 1;
            
            for (int i = 0; i < index; i++) {
            
                if (nums[i] < nums[index] ) {
                    ans = Math.max(ans, 1 + memo[i]);
                }
            }
            memo[index] = ans;
            max = Math.max(max, memo[index]);
        }
        return max;
    }
	//=============================================================================================
	// Binary Search
	/**
	 this algorithm does not always generate a valid subsequence of the input,
	 but the length of the subsequence will always equal the length of the longest
	 increasing subsequence. For example, with the input [3, 4, 5, 1], at the end we will
	 have sub = [1, 4, 5], which isn't a subsequence, but the length is still correct.
	 The length remains correct because the length only changes when a new element is larger
	 than any element in the subsequence. In that case, the element is appended to the subsequence
	 instead of replacing an existing element.
	 */
	public int lengthOfLIS5(int[] nums) {
		List<Integer> holder = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];

			if (holder.size() == 0 || holder.get(holder.size() - 1) < num) {
				holder.add(num);
			} else {
				int index = binarySearchJustGreaterThanEqualTo(holder, num);
				holder.set(index, num);
			}
		}
		return holder.size();
	}

	private int binarySearchJustGreaterThanEqualTo(List<Integer> holder, int target) {
		int lo = 0;
		int hi = holder.size() - 1;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (holder.get(pivot) < target) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return hi;
	}
	//=============================================================================================
	// Segment tree
	class SegmentTree {
		int n;
		int[] cache;

		public SegmentTree(int n) {
			this.n = n;
			this.cache = new int[4 * n];
		}

		public void update(int pos, int val) {

			if (pos >= n) {
				return;
			}
			update(0, n - 1, pos, 0, val);
		}

		public void update(int lo, int hi, int pos, int cacheIndex, int val) {

			if (lo > hi || pos < lo || pos > hi) {
				return;
			}

			if (lo == hi) {
				cache[cacheIndex] = val;
				return;
			}
			int pivot = lo + (hi - lo) / 2;

			if (pos <= pivot) {
				update(lo, pivot, pos, cacheIndex * 2 + 1, val);
			} else {
				update(pivot + 1, hi, pos, cacheIndex * 2 + 2, val);
			}
			cache[cacheIndex] = Math.max(cache[cacheIndex * 2 + 1], cache[cacheIndex * 2 + 2]);
		}

		public int get(int lo, int hi, int qlo, int qhi, int cacheIndex) {

			if (lo > qhi || hi < qlo) {
				return 0;
			}

			if (lo >= qlo && hi <= qhi) {
				return cache[cacheIndex];
			}
			int pivot = lo + (hi - lo) / 2;
			int left = get(lo, pivot, qlo, qhi, cacheIndex * 2 + 1);
			int right = get(pivot + 1, hi, qlo, qhi, cacheIndex * 2 + 2);
			return cache[cacheIndex] = Math.max(left, right);
		}
	}

	public int lengthOfLIS6(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int num : nums) {
			max = Math.max(num, max);
			min = Math.min(num, min);
		}
		int size = max - min + 1;
		SegmentTree st = new SegmentTree(size);
		int ans = 0;

		for(int num : nums) {
			int pos = num - min;
			int currVal = st.get(0, size - 1, 0, pos - 1, 0) + 1;
			st.update(pos, currVal);
			ans = Math.max(ans, currVal);
		}
		return ans;
	}
}
