package leetcode.Presum;

/**
 *
 *
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 * Example 2:
 *
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 *
 */

public class _1442_Count_Triplets_That_Can_Form_Two_Arrays_of_Equal_XOR {
    public int countTriplets(int[] arr) {
        int presum[] = new int[arr.length + 1];
        presum[0] = 0;
        System.arraycopy(arr, 0, presum, 1, arr.length);

        for (int i = 1; i < presum.length; i++) {
            presum[i] ^= presum[i - 1];
        }
        int ans = 0;

        for (int start = 0; start < presum.length; start++) {

            for (int end = start + 1; end < presum.length; end++) {

                if (presum[start] == presum[end]) {
                    // If the prefix XOR values at indices start and end are equal,
                    // it means the XOR of elements between start and end (excluding
                    // start and end) is 0.
                    ans += end - start - 1;
                }
            }
        }
        return ans;
    }
}
