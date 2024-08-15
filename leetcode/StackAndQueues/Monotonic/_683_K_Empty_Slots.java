package leetcode.StackAndQueues.Monotonic;

import java.util.Stack;
import java.util.TreeSet;

/**
 *
 * You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn on exactly one bulb every day until all bulbs are on after n days.
 *
 * You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we will turn on the bulb at position x where i is 0-indexed and x is 1-indexed.
 *
 * Given an integer k, return the minimum day number such that there exists two turned on bulbs that have exactly k bulbs between them that are all turned off. If there isn't such day, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: bulbs = [1,3,2], k = 1
 * Output: 2
 * Explanation:
 * On the first day: bulbs[0] = 1, first bulb is turned on: [1,0,0]
 * On the second day: bulbs[1] = 3, third bulb is turned on: [1,0,1]
 * On the third day: bulbs[2] = 2, second bulb is turned on: [1,1,1]
 * We return 2 because on the second day, there were two on bulbs with one off bulb between them.
 * Example 2:
 *
 * Input: bulbs = [1,2,3], k = 1
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == bulbs.length
 * 1 <= n <= 2 * 10^4
 * 1 <= bulbs[i] <= n
 * bulbs is a permutation of numbers from 1 to n.
 * 0 <= k <= 2 * 10^4
 *
 *
 */

public class _683_K_Empty_Slots {
    public int kEmptySlots(int[] bulbs, int K) {
        int N = bulbs.length;

        int[] days = new int[N];
        for (int i = 0; i < N; i++) {
            days[bulbs[i]-1] = i + 1;
        }

        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int day = days[i];
            while (!stack.isEmpty() && days[stack.peek()] > day) {
                stack.pop();
            }
            if (!stack.isEmpty() && i - stack.peek() - 1 == K) {
                min = Math.min(min, day);
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = N-1; i >= 0; i--) {
            int day = days[i];
            while (!stack.isEmpty() && days[stack.peek()] > day) {
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek() - 1 - i == K) {
                min = Math.min(min, day);
            }
            stack.push(i);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
    //=============================================================================================
    public int kEmptySlots1(int[] bulbs, int k) {
        TreeSet<Integer> holder = new TreeSet<>();

        for (int bulb : bulbs) {
            int next = bulb + k + 1;
            int prev = bulb - k - 1;

            if ((holder.ceiling(bulb) != null && holder.ceiling(bulb) == next) ||                (holder.floor(bulb) != null && holder.floor(bulb) == prev)) {
                return holder.size() + 1;
            }
            holder.add(bulb);
        }
        return -1;
    }
}
