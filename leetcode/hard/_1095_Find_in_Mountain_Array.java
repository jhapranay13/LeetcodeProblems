package leetcode.hard;

/**
 *
 * (This problem is an interactive problem.)
 *
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
 *
 * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 *
 *
 * Example 1:
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 *
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 *
 *
 * Constraints:
 *
 * 3 <= mountain_arr.length() <= 10^4
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 *
 */
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */


public class _1095_Find_in_Mountain_Array {

    interface MountainArray {
      public int get(int index);
      public int length();
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length();
        int lo = 0;
        int hi = length - 1;
        int peakIndex = 0;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (mountainArr.get(pivot) > mountainArr.get(pivot + 1)) {
                hi = pivot;
                peakIndex = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        int leftIndex = bsLeft(0, peakIndex, mountainArr, target);
        int rightIndex = bsRight(peakIndex, length - 1, mountainArr, target);

        if (leftIndex != -1) {
            return leftIndex;
        }
        return rightIndex;
    }

    private int bsLeft(int lo, int hi, MountainArray mountainArr, int target) {

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int val = mountainArr.get(pivot);

            if (val == target) {
                return pivot;
            }

            if (val < target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return -1;
    }

    private int bsRight(int lo, int hi, MountainArray mountainArr, int target) {

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            int val = mountainArr.get(pivot);

            if (val == target) {
                return pivot;
            }

            if (val > target) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return -1;
    }

}
