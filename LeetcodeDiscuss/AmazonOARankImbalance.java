package LeetcodeDiscuss;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * "Given an array arr of integers, find the total number of 'imbalanced pairs'.
 * An 'imbalanced pair' (arr[j], arr[k]) is defined as a pair where arr[j] is a greater
 * element than arr[k], and arr[k] is the next smaller element to arr[j] in a
 * specific direction (either to its right or to its left), such that the difference
 * in their values (arr[j] - arr[k]) is not equal to the absolute difference in their
 * indices (|j - k|)."
 *
 */

public class AmazonOARankImbalance {

    private static int findTotalImbalance(int[] arr) {
        Deque<Integer> mq = new LinkedList<>();
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {

            while (!mq.isEmpty() && arr[mq.peek()] > arr[i] ) {
                int pop = mq.pop();
                ans += arr[pop] - arr[i] != i - pop ? 1 : 0;
            }
            mq.push(i);
        }
        mq.clear();

        for (int i = arr.length - 1; i >= 0; i--) {

            while (!mq.isEmpty() && arr[mq.peek()] > arr[i]) {
                int pop = mq.pop();
                ans += arr[i] - arr[pop] != pop - i ? 1 : 0;
            }
            mq.push(i);
        }
        return ans;
    }

    private static int findTotalImbalance1(int[] arr) {
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int max = arr[i];

            for (int j = i; j < arr.length; j++) {
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                int diff = max - min;
                int range = j - i;
                ans += diff > range ? 1 : 0;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        //int[] arr = {3, 1, 2};
        //int[] arr = {4, 1, 3, 2};
        //int[] arr = {3, 1, 2, 4};
        int[] arr = {5, 1, 3,4, 8, 9};

        System.out.println(findTotalImbalance1(arr));
        System.out.println(findTotalImbalance(arr));

    }
}
