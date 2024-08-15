package leetcode.BinarySearch;

/**
 *
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers. However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 * Example 2:
 *
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 * Example 3:
 *
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 *
 *
 * Constraints:
 *
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 *
 */

public class _1850_Minimum_Adjacent_Swaps_to_Reach_the_Kth_Smallest_Number {
    public int getMinSwaps(String num, int k) {
        int[] nums = new int[num.length()];
        int[] original = new int[num.length()];
        int index = 0;

        for (char ch : num.toCharArray()) {
            nums[index] = ch - '0';
            original[index++] = ch - '0';
        }

        while (k-- > 0) {
            nextPermutation(nums);
        }
        int ans = 0;

        for (int i = 0; i < num.length(); i++) {
            // Meaning swap happened
            if (nums[i] != original[i]) {
                int anchor = i;

                while (nums[anchor] != original[i]) {
                    anchor++;
                }
                while (anchor > i) {
                    swap(nums, anchor, anchor - 1);
                    ans++;
                    anchor--;
                }
            }
        }
        return ans;
    }
    // next greater permutation
    private void nextPermutation(int[] arr) {
        int index = arr.length - 2;

        while (index >= 0 && arr[index] >= arr[index + 1]) {
            index--;
        }
        int justGreaterThanEqualToIndex = binarySearchJustGreaterThan(arr, index + 1, arr[index]);
        swap(arr, index, justGreaterThanEqualToIndex);
        reverse(arr, index + 1);
    }

    private int binarySearchJustGreaterThan(int[] arr, int index, int target) {
        int lo = index;
        int hi = arr.length - 1;
        int ans = index;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if(arr[pivot] > target) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void reverse(int[] arr, int index) {
        int eIndex = arr.length - 1;

        while (index < eIndex) {
            swap(arr, index++, eIndex--);
        }
    }
}
