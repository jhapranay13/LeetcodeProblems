package leetcode.medium;

import java.util.Arrays;

/**
 *
 *  Given an array of integers citations where citations[i] is the number of citations a
 *  researcher received for their ith paper, return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of
 * their n papers have at least h citations each, and the other n − h papers have no more than h
 * citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two
 * with no more than 3 citations each, their h-index is 3.
 *
 * Example 2:
 *
 * Input: citations = [1,3,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 *
 */

public class _274_HIndex {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = 0;
        int lo = 0;
        int hi = citations.length - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (citations[pivot] >= citations.length - pivot) {
                ans = citations.length - pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
}
