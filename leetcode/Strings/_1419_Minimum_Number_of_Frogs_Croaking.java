package leetcode.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * You are given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple "croak" are mixed.
 *
 * Return the minimum number of different frogs to finish all the croaks in the given string.
 *
 * A valid "croak" means a frog is printing five letters 'c', 'r', 'o', 'a', and 'k' sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of a valid "croak" return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1
 * Explanation: One frog yelling "croak" twice.
 * Example 2:
 *
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2
 * Explanation: The minimum number of frogs is two.
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 * Example 3:
 *
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from different frogs.
 *
 *
 * Constraints:
 *
 * 1 <= croakOfFrogs.length <= 10^5
 * croakOfFrogs is either 'c', 'r', 'o', 'a', or 'k'.
 *
 */

public class _1419_Minimum_Number_of_Frogs_Croaking {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int ans = 0;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            if (croakOfFrogs.charAt(i) == 'c') {
                c++;
            } else if (croakOfFrogs.charAt(i) == 'r') {
                r++;
            } else if (croakOfFrogs.charAt(i) == 'o') {
                o++;
            } else if (croakOfFrogs.charAt(i) == 'a') {
                a++;
            } else if (croakOfFrogs.charAt(i) == 'k') {
                k++;
            }

            if (r > c || o > r || a > o || k > a) {
                return -1;
            }

            if (k == 1) {
                ans = Math.max(ans, c);
                c--;
                r--;
                o--;
                a--;
                k--;
            }
        }
        if (c != 0 || r != 0 || a !=0 || o != 0 || k !=0 ) {
            return -1;
        }

        return ans;
    }
    //=============================================================================================
    // Another approach
    public int minNumberOfFrogs1(String croakOfFrogs) {
        Set<Character> holder = new HashSet<>();
        char[] chars = {'c', 'r', 'o', 'a', 'k'};
        int[] count = new int[26];
        int ans = 0;

        for (char ch : croakOfFrogs.toCharArray()) {
            count[ch - 'a']++;
            int minCount = Integer.MAX_VALUE;
            int maxCount = Integer.MIN_VALUE;

            for (int i = 0; i < chars.length; i++) {
                // Since it has to be in sequence so any character coming after
                // this cannot have greater count e.g r cannot have a count greater than c
                if (i + 1 < chars.length && count[chars[i] - 'a'] < count[chars[i + 1] - 'a']) {
                    return -1;
                }
                maxCount = Math.max(maxCount, count[chars[i] - 'a']);
                minCount = Math.min(minCount, count[chars[i] - 'a']);
            }
            //If there is an answer it will be the maxCount which is still unformed
            ans = Math.max(ans, maxCount);

            //subtracting all the mincount coz that is the minimum which can be formed
            for (int i = 0; i < chars.length; i++) {
                count[chars[i] - 'a'] -= minCount;
            }
        }

        for (int i = 0; i < 26; i++) {

            if (count[i] != 0) {
                return -1;
            }
        }
        return ans;
    }

}
