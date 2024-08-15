package leetcode.TreeSetTreeMap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning, you are standing at index 0, which is equal to '0'. You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "011010", minJump = 2, maxJump = 3
 * Output: true
 * Explanation:
 * In the first step, move from index 0 to index 3.
 * In the second step, move from index 3 to index 5.
 * Example 2:
 *
 * Input: s = "01101110", minJump = 2, maxJump = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 *
 */

public class _1871_Jump_Game_VII {
    //Queue based
    public boolean canReach(String s, int minJump, int maxJump) {
        Deque<Integer> q = new LinkedList<>();
        q.offerLast(0);

        for (int i = 1; i < s.length(); i++) {
            // removing the old renge
            while (!q.isEmpty() && q.peekFirst() < i - maxJump) {
                q.pollFirst();
            }
            // adding the range
            if (s.charAt(i) == '0' && !q.isEmpty() && q.peekFirst() <= i - minJump ) {
                q.addLast(i);
            }
        }
        return !q.isEmpty() && q.peekLast() == s.length() - 1;
    }
    //=============================================================================================
    // TreeSet Based
    public boolean canReach1(String s, int minJump, int maxJump) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i) == '0') {
                Integer low = set.floor(i - minJump);

                if (low != null && low >= i - maxJump) {
                    set.add(i);
                }
            }
        }
        return !set.isEmpty() && set.last() == s.length() - 1;
    }
}
