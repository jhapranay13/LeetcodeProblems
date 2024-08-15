package leetcode.medium;

/**
 *
 * This is an interactive problem.
 *
 * You have a sorted array of unique elements and an unknown size. You do not have an access to the array but you can use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 *
 * returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * returns 231 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 *
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in secret and its index is 4.
 * Example 2:
 *
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in secret so return -1.
 *
 *
 * Constraints:
 *
 * 1 <= secret.length <= 10^4
 * -104 <= secret[i], target <= 10^4
 * secret is sorted in a strictly increasing order.
 *
 */
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

public class _702_Search_in_a_Sorted_Array_of_Unknown_Size {
    interface ArrayReader {
      public int get(int index);
  }

    // Find the boundry and then do the binary search on the boundry
    public int search(ArrayReader reader, int target) {

        if (reader.get(0) == target) {
            return 0;
        }
        int lo = 0;
        int hi = 1;
        // finding the boundry
        while (reader.get(hi) < target) {
            lo = hi;
            hi <<= 1; // increasing hi by power of 2
        }

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int val = reader.get(pivot);

            if (val < target) {
                lo = pivot + 1;
            } else if (val > target) {
                hi = pivot - 1;
            } else {
                return pivot;
            }
        }
        return -1;
    }
}
