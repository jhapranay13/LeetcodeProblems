package leetcode.SlidingWindow;

/**
 *
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation: There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 * Example 2:
 *
 * Input: data = [0,0,0,1,0]
 * Output: 0
 * Explanation: Since there is only one 1 in the array, no swaps are needed.
 * Example 3:
 *
 * Input: data = [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 *
 *
 * Constraints:
 *
 * 1 <= data.length <= 105
 * data[i] is either 0 or 1.
 *
 */

public class _1151_MinimumSwapstoGroupAll1sTogether {

    public int minSwaps(int[] data) {
        int oneCount = 0;

        for (int num : data) {

            if (num == 1) {
                oneCount++;
            }
        }
        int fast = 0;
        int slow = 0;
        int currOneCount = 0;
        int ans = Integer.MAX_VALUE;

        while (fast < data.length) {
            int val = data[fast++];

            if (val == 1) {
                currOneCount++;
            }

            if (fast - slow == oneCount) {
                ans = Math.min(ans, oneCount - currOneCount);
                int slowVal = data[slow++];

                if (slowVal == 1) {
                    currOneCount--;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
