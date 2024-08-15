package leetcode.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * You are given an integer array rolls of length n and an integer k. You roll a k sided dice numbered from 1 to k, n times, where the result of the ith roll is rolls[i].
 *
 * Return the length of the shortest sequence of rolls that cannot be taken from rolls.
 *
 * A sequence of rolls of length len is the result of rolling a k sided dice len times.
 *
 * Note that the sequence taken does not have to be consecutive as long as it is in order.
 *
 *
 *
 * Example 1:
 *
 * Input: rolls = [4,2,1,2,3,3,2,4,1], k = 4
 * Output: 3
 * Explanation: Every sequence of rolls of length 1, [1], [2], [3], [4], can be taken from rolls.
 * Every sequence of rolls of length 2, [1, 1], [1, 2], ..., [4, 4], can be taken from rolls.
 * The sequence [1, 4, 2] cannot be taken from rolls, so we return 3.
 * Note that there are other sequences that cannot be taken from rolls.
 * Example 2:
 *
 * Input: rolls = [1,1,2,2], k = 2
 * Output: 2
 * Explanation: Every sequence of rolls of length 1, [1], [2], can be taken from rolls.
 * The sequence [2, 1] cannot be taken from rolls, so we return 2.
 * Note that there are other sequences that cannot be taken from rolls but [2, 1] is the shortest.
 * Example 3:
 *
 * Input: rolls = [1,1,3,2,2,2,3,3], k = 4
 * Output: 1
 * Explanation: The sequence [4] cannot be taken from rolls, so we return 1.
 * Note that there are other sequences that cannot be taken from rolls but [4] is the shortest.
 *
 *
 * Constraints:
 *
 * n == rolls.length
 * 1 <= n <= 10^5
 * 1 <= rolls[i] <= k <= 10^5
 *
 */

public class _2350_Shortest_Impossible_Sequence_of_Rolls {
    /**
     Basically dividing the array to see if all the number are avaialble in one section
     [4,2,1,2,3,3,2,4,1]
     |_______| |_____|

     So we see with 2 sections contains all the values so all the subsequence for
     length 2 is possible. but 3 is not possible for all the sequence
     */
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> valHolder = new HashSet<>();
        int sectionCount = 0;

        for (int roll : rolls) {
            valHolder.add(roll);

            if (valHolder.size() == k) {
                valHolder = new HashSet<>();
                sectionCount++;
            }
        }
        return sectionCount + 1;
    }
}
