package LeetcodeDiscuss;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Find i and j such that sum of Contiguous array is maximised.
 *
 * a[i] = a[j] where j > i
 * Maximise (a[i] + a[i+1] + . . . . + a[j])
 *
 */
public class GoogleOAMaximumSumBetweenSameValues {

    public static void main(String ...args) {
        //int[] arr = {3, 1, 5, -2, 3, 6, 7, -1, 3, 0};
        //int[] arr = {-2, 0, 4, 6, 2, -2, -1, 3, 7, 4};
        //int[] arr = {1, 2, 3, 4, 5, 1};
        //int[] arr = {7, -1, -8, -5, -2, -6, 5, 3, 7, -2};
        //int[] arr= {-6, 0, -1, -2, -7, 0, -9, -2, -4, -6};
        //int[] arr = {1, 2, 3, 4};
        //int[] arr = {3, 1, 4, 3, 2, 3};
        //int[] arr = {9, 0, 0, 0, 9};
        //int[] arr = {2, 2, 2, 2};
        int[] arr = {7, 7};
        System.out.println(Arrays.toString(maxSum(arr)));
    }

    private static int[] maxSum(int[] arr) {
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new java.util.ArrayList<>()).add(i);
        }
        int[] presum = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                presum[i] = arr[i];
            } else {
                presum[i] = presum[i - 1] + arr[i];
            }
        }
        // Modified Kadane's algorithm
        int gloabalMax = Integer.MIN_VALUE;
        int globalStartIndex = -1;
        int globalEndIndex = -1;

        for (Map.Entry<Integer, List<Integer>> keySet : valueToIndices.entrySet()) {
            List<Integer> indices = keySet.getValue();
            int value = keySet.getKey();

            if (indices.size() < 2) {
                continue; // We need at least two indices to form a valid sum
            }
            int firstIndex = indices.get(0);
            int currentSum = Integer.MIN_VALUE;

            for (int i = 1; i < indices.size(); i++) {
                int secondIndex = indices.get(i);
                int prevIndex = indices.get(i - 1);
                int tempSum = presum[secondIndex] - (prevIndex > 0 ? presum[prevIndex - 1] : 0);
                // Subtracting value coz it will be counted twice in the sum

                if (currentSum != Integer.MIN_VALUE && tempSum > currentSum + tempSum - value) {
                    currentSum = tempSum; // Reset current sum if the new segment is better
                    firstIndex = prevIndex; // Update first index to the previous index
                } else {

                    if (currentSum == Integer.MIN_VALUE) {
                        currentSum = tempSum; // Initialize current sum if it was not set
                    } else {
                        currentSum += tempSum - value; // Add the value at second index to current sum
                    }
                }

                if (currentSum > gloabalMax) {
                    gloabalMax = currentSum;
                    globalStartIndex = firstIndex;
                    globalEndIndex = secondIndex;
                }
            }
        }
        return new int[]{gloabalMax, globalStartIndex, globalEndIndex};
    }
}
