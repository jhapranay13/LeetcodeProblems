package LeetcodeDiscuss;


/**
 *
 * Given an array find the sum of all subarrays which follow a +-1 difference AP
 * i.e the difference of the AP can be either 1 or -1
 * Individual elements are considered AP in itself
 * Input array : [7,2,3,4]
 * Answer : 7 + 2 + 3 + 4 + [2,3] + [2,3,4] + [3,4] = 37
 *
 * step 1
 *  7
 *
 *  step 2
 *
 *  ans = 9    7 + 2
 *
 *  step 3
 *
 *  ans = 17   7 + 2 + 3 + (2 + 3)
 *             7 + 2 +
 *
 *  step 4
 *
 *  ans = 37   7 + 2 + 3 + 4 + (2 + 3) + (2 + 3 + 4) + (3 + 4)
 *
 *
 * 1
 * 2 + (1 + 2) = 2 * 2 + (1) = 2 * 2 + prev_sum
 * 3 + (2 + 3) + (1 + 2 + 3) = 3 * 3 + (2 * 2 + 1) = 3 * 3 + prev_sum
 * 4 + (3 + 4) + (2 + 3 + 4) + (1 + 2 + 3 + 4) = 4 * 4 +  (3*2 + (2 * 2 + 1)) = 4*4 + prev_sum
 *
 *Thus: sum of all the subarrays in an array when ith index is added:
 * sum = arr[i] * len_array + prev_sum
 */
public class APlusMinusAPDiffGoogle {

    public static void main(String[] args) {
        int arr[] = {7, 2, 3, 4};
        int ans = aPlusMinusAPDiff(arr);
        System.out.println("The answer is: " + ans);
    }

    private static int aPlusMinusAPDiff(int[] arr) {
        int ans = arr[0];
        int currSum = arr[0];
        int fast = 1;
        int slow = 0;

        while (fast < arr.length) {

            if (Math.abs(arr[fast] - arr[fast - 1]) != 1) {
                // if the difference is greater than 1 then we need to move slow pointer
                slow = fast;
                currSum = arr[fast];
            } else {
                // if the difference is 1 or -1 then we can add the current element to the sum
                currSum += arr[fast] * (fast - slow + 1);
            }
            ans += currSum;
            fast++;
        }
        return ans;
    }
}
