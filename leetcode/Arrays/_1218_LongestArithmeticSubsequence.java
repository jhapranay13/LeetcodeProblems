package leetcode.Arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an integer array arr and an integer difference, return the
 *         length of the longest subsequence in arr which is an arithmetic
 *         sequence such that the difference between adjacent elements in the
 *         subsequence equals difference.
 * 
 *         A subsequence is a sequence that can be derived from arr by deleting
 *         some or no elements without changing the order of the remaining
 *         elements.
 * 
 *         Example 1:
 * 
 *         Input: arr = [1,2,3,4], difference = 1 Output: 4 Explanation: The
 *         longest arithmetic subsequence is [1,2,3,4].
 * 
 *         Example 2:
 * 
 *         Input: arr = [1,3,5,7], difference = 1 Output: 1 Explanation: The
 *         longest arithmetic subsequence is any single element.
 * 
 *         Example 3:
 * 
 *         Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2 Output: 4
 *         Explanation: The longest arithmetic subsequence is [7,5,3,1].
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 105 -104 <= arr[i], difference <= 104
 *
 */

public class _1218_LongestArithmeticSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int longestSubsequence(int[] arr, int difference) {
		Map<Integer, Integer> diffMap = new HashMap<>();
		int ans = 0;

		for (int i = 0; i < arr.length; i++) {
			int prevNum = arr[i] - difference;
			int freq = diffMap.getOrDefault(prevNum, 0);
			diffMap.put(arr[i], ++freq);
			ans = Math.max(ans, freq);
		}
		return ans;
	}
}
