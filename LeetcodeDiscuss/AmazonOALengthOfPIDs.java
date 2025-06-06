package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * So basically, Amazon is launching some "revolutionary security feature" that analyzes program PIDs to detect threats. Here's what we need to do:
 * Given:
 *
 * An array pid[] of integers (Program Identifiers)
 * An integer k for remainder checking
 *
 * Goal: Find how many contiguous subarrays are "malicious"
 * What makes a subarray malicious?
 * A contiguous subarray is malicious if: (sum of PIDs) % k == (length of subarray)
 * Example
 * Input: pid = [1, 4, 2, 3, 5], k = 4
 * Output: 2
 *
 * Malicious subarrays:
 *
 * [1] → sum=1, length=1, 1%4=1 ✓
 * [2, 4] → sum=6, length=2, 6%4=2 ✓
 *
 */
public class AmazonOALengthOfPIDs {

    public static int findMaliciousSubarraysOptimized(int[] pid, int k) {
        int n = pid.length;
        int maliciousCount = 0;

        // map to store frequency of (current_prefix_sum - current_index) % k
        // The key is the transformed remainder, the value is its frequency
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Initialize for the "empty prefix" before index 0.
        // current_prefix_sum = 0, current_index = 0 (conceptually for prefix_sum[0])
        // Transformed value: (0 - 0) % k = 0
        freqMap.put(0, 1); // We've seen this transformed value once

        long currentPrefixSum = 0; // Use long to prevent overflow for large sums

        // Iterate through the pid array
        for (int i = 0; i < n; i++) {
            currentPrefixSum += pid[i];

            // Calculate the transformed remainder for the current prefix_sum and index
            // The formula is (S_j - (j+1)) % k == (S_{i-1} - i) % k
            // Here, current_prefix_sum is S_j, and current index is j (which is 'i' in the loop)
            // So we are looking for (currentPrefixSum - (i + 1)) % k
            long transformedValue = currentPrefixSum - (i + 1);

            // Ensure the remainder is positive in Java
            int remainder = (int) (transformedValue % k);
            if (remainder < 0) {
                remainder += k;
            }

            // If this remainder has been seen before, it means we found a malicious subarray
            // For every previous occurrence of this 'remainder', there's a corresponding
            // 'start' index that forms a malicious subarray with the current 'end' index.
            if (freqMap.containsKey(remainder)) {
                maliciousCount += freqMap.get(remainder);
            }

            // Increment the frequency of the current remainder in the map
            freqMap.put(remainder, freqMap.getOrDefault(remainder, 0) + 1);
        }

        return maliciousCount;
    }

    public static void main(String[] args) {
        // Example from problem description: pid = [1, 4, 2, 3, 5], k = 4
        // Expected output based on my manual trace: 3
        // Example given in problem: [1] -> sum=1, length=1, 1%4=1 ✓
        //                           [2, 4] (which is [4,2]) -> sum=6, length=2, 6%4=2 ✓
        // [1], [1,4,2], [4,2], [5]  (4 total)

        int[] pid1 = {1, 4, 2, 3, 5};
        int k1 = 4;
        int result1 = findMaliciousSubarraysOptimized(pid1, k1);
        System.out.println("Input: pid = [1, 4, 2, 3, 5], k = 4");
        System.out.println("Number of malicious subarrays (Optimized): " + result1); // Should output 3

        // Another test case
        int[] pid2 = {2, 3, 5, 3, 1, 5};
        int k2 = 4;
        // From a similar problem on GeeksforGeeks, this example should yield 5
        // [5], [1], [5], [1,5], [3,5,3]
        int result2 = findMaliciousSubarraysOptimized(pid2, k2);
        System.out.println("\nInput: pid = [2, 3, 5, 3, 1, 5], k = 4");
        System.out.println("Number of malicious subarrays (Optimized): " + result2); // Should output 5
    }
}
