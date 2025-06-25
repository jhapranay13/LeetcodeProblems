package LeetcodeDiscuss;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * Find the K-th greatest element for every subarray ranging from size K to N.
 */
public class SalesforceInterviewDSAKthGreatest {
    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 5, 4};
        int k1 = 2;
        // Subarrays (size K to N):
        // [3,2] (size 2): 2nd greatest is 2
        // [3,2,1] (size 3): 2nd greatest is 2
        // [3,2,1,5] (size 4): 2nd greatest is 3
        // [3,2,1,5,4] (size 5): 2nd greatest is 4
        // [2,1] (size 2): 2nd greatest is 1
        // [2,1,5] (size 3): 2nd greatest is 2
        // [2,1,5,4] (size 4): 2nd greatest is 4
        // [1,5] (size 2): 2nd greatest is 1
        // [1,5,4] (size 3): 2nd greatest is 4
        // [5,4] (size 2): 2nd greatest is 4
        List<Integer> res1 = findKthGreatestTwoHeaps(arr1, k1);
        System.out.println("Array: " + Arrays.toString(arr1) + ", K: " + k1);
        System.out.println("K-th greatest elements: " + res1); // Expected: [2, 2, 3, 4, 1, 2, 4, 1, 4, 4]

        int[] arr2 = {7, 1, 5, 3, 6, 4};
        int k2 = 3;
        List<Integer> res2 = findKthGreatestTwoHeaps(arr2, k2);
        System.out.println("\nArray: " + Arrays.toString(arr2) + ", K: " + k2);
        System.out.println("K-th greatest elements: " + res2);
        // Manual verification for a few:
        // [7,1,5] (size 3): sorted [1,5,7]. 3rd greatest is 1. (1st smallest) -> minHeap: [1,5,7] -> peek is 1
        // [7,1,5,3] (size 4): sorted [1,3,5,7]. 3rd greatest is 3. -> minHeap: [3,5,7] -> peek is 3
        // [1,5,3] (size 3): sorted [1,3,5]. 3rd greatest is 1. -> minHeap: [1,3,5] -> peek is 1
    }

    private static List<Integer> findKthGreatestTwoHeaps(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

            for (int j = i; j < arr.length; j++) {
                minHeap.offer(arr[j]);

                // Maintain the size of the minHeap to be at most k
                if (minHeap.size() > k) {
                    maxHeap.offer(minHeap.poll());
                }

                // If we have at least k elements, we can find the k-th greatest
                if (j - i + 1 >= k) {
                    ans.add(minHeap.peek());
                }
            }
        }
        return ans;
    }
}
