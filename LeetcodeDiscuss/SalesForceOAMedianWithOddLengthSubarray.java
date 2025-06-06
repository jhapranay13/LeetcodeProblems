package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * In Salesforce's annual hackathon, employees from a team are ranked based on their
 * efficiency scores. These scores are represented as an array. The team lead wants to
 * analyze subgroups of employees to identify groups with specific characteristics.
 *
 * Given:
 *
 * n employees in a team standing in a line,
 * An array efficiency representing each employee's efficiency and
 * An integer k (1 ≤k≤ n), representing the reference employee in the array.
 * Find the number of subarrays of odd lengths with a median equal to efficiency[k].
 *
 * Note:
 *
 * A subarray is a sequence of consecutive elements of the array.
 * The median of an array of odd length, say n, is the (n+1)/2th element of the array
 * if sorted in non-decreasing order. For example, the median of [2, 5, 4, 1, 1, 6] of length
 * 7 is 2, since upon sorting, the array becomes [1, 1, 1, 2, 4, 5, 6] and the
 * (7+1)/2 = 4th element is 2.
 *
 */

public class SalesForceOAMedianWithOddLengthSubarray {

    public static void main(String args[]) {
        /*int[] efficiency = {2, 5, 4, 1, 6};
        int k = 2; // Reference employee index*/

        /*int[] efficiency = {1 , 4, 5};
        int k = 1;*/

        int[] efficiency = {1, 5, 4, 3, 2};
        int k = 2;
        System.out.println(countOddLengthSubarraysWithMedian(efficiency, k));
    }

    private static int countOddLengthSubarraysWithMedian(int[] efficiency, int k) {
        int ans = 1; // since 1 is odd
        int pos = k;
        int sum = 0;
        int parity = 0;
        Map<String, Integer> leftSum = new HashMap<>();

        for (int i = pos - 1; i >= 0; i--) {

            if (efficiency[pos] > efficiency[i]) {
                sum--;
            } else if (efficiency[pos] < efficiency[i]) {
                sum++;
            }
            int currparity = parity % 2;
            parity++;
            leftSum.put(sum + " - " + currparity, leftSum.getOrDefault(sum + " - " + currparity, 0) + 1);
        }
        parity = 0;
        Map<String, Integer> rightSum = new HashMap<>();
        sum = 0;

        for (int i = pos +  1; i < efficiency.length; i++) {

            if (efficiency[pos] > efficiency[i]) {
                sum--;
            } else if (efficiency[pos] < efficiency[i]) {
                sum++;
            }
            int currparity = parity % 2;
            parity++;
            rightSum.put(sum + " - " + currparity, rightSum.getOrDefault(sum + " - " + currparity, 0) + 1);
        }
        // for length to be odd, we need to consider the left and right sums
        // length of left and right sum should either be even or odd. So same parity
        for (Map.Entry<String, Integer> entry : rightSum.entrySet()) {
            String[] keyParts = entry.getKey().split(" - ");
            int rightSumValue = Integer.parseInt(keyParts[0]);
            int rightParity = Integer.parseInt(keyParts[1]);
            int rightFrequency = entry.getValue();

            // Check if the right sum exists with the opposite parity
            String leftKey = -rightSumValue + " - " + rightParity;
            ans += entry.getValue() * leftSum.getOrDefault(leftKey, 0);
        }
        return ans;
    }
}
