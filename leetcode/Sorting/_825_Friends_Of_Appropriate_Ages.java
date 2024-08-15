package leetcode.Sorting;

import java.util.Arrays;

/**
 *
 * There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
 *
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 *
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * Otherwise, x will send a friend request to y.
 *
 * Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
 *
 * Return the total number of friend requests made.
 *
 *
 *
 * Example 1:
 *
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: ages = [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 *
 * Constraints:
 *
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 *
 */

public class _825_Friends_Of_Appropriate_Ages {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0;
        int sameCount = 0;

        for (int i = 0; i < ages.length; i++) {
            double calculation = 0.5 * ages[i] + 7;
            int index = getIndex(ages, calculation, i);

            if (i != 0 && ages[i] == ages[i - 1]) {
                sameCount++;
            } else {
                sameCount = 0;
            }

            if (index != -1) {
                //acounting for previous index so if it is same add that too in the result
                //as previos index can send request to current index as well.
                ans += i - index + sameCount;
            }
        }
        return ans;
    }

    private int getIndex(int ages[], double target, int hi) {
        int ans = -1;
        int lo = 0;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (ages[pivot] <= target) {
                lo = pivot + 1;
            } else {
                hi = pivot;
                ans = pivot;
            }
        }
        return ans;
    }
}
