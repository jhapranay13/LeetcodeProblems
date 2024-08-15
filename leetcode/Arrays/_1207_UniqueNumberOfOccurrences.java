package leetcode.Arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers arr, write a function that returns true if
 *         and only if the number of occurrences of each value in the array is
 *         unique.
 * 
 *         Example 1:
 * 
 *         Input: arr = [1,2,2,1,1,3] Output: true Explanation: The value 1 has
 *         3 occurrences, 2 has 2 and 3 has 1. No two values have the same
 *         number of occurrences.
 * 
 *         Example 2:
 * 
 *         Input: arr = [1,2] Output: false
 * 
 *         Example 3:
 * 
 *         Input: arr = [-3,0,1,-3,1,1,1,-3,10,0] Output: true
 * 
 * 
 *         Constraints:
 * 
 *         1 <= arr.length <= 1000 -1000 <= arr[i] <= 1000
 *
 */

public class _1207_UniqueNumberOfOccurrences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> freqMap = new HashMap<>();

		for (int val : arr) {
			freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
		}
		Set<Integer> occurence = new HashSet<>();

		for (int key : freqMap.keySet()) {
			int freq = freqMap.get(key);

			if (!occurence.add(freq)) {
				return false;
			}
		}
		return true;
	}
}
