package leetcode.medium;

/**
 *
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 * Any two characters may be swapped, even if they are not adjacent.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 *
 * Example 2:
 *
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 *
 * Example 3:
 *
 * Input: s = "1110"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 *
 */

public class _1864_Minimum_Number_of_Swaps_to_Make_the_Binary_String_Alternating {
    public int minSwaps(String s) {
        int zcount = 0;
        int ocount = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '1') {
                ocount++;
            } else {
                zcount++;
            }
        }
        int ans = -1;
        int diff = Math.abs(ocount - zcount);

        if (diff == 0 || diff == 1) {
            if (ocount > zcount) {
                ans = check(ocount, zcount, s, '1');
            } else if (zcount > ocount){
                ans = check(ocount, zcount, s, '0');
            } else {
                ans = Math.min(check(ocount, zcount, s, '1'), check(ocount, zcount, s, '0'));
            }
        }
        return ans;
    }

    private int check(int ocount, int zcount, String s, char start) {
        char primeChar = start;
        char altChar = start == '1' ? '0' : '1';
        int primeCount = start == '1' ? ocount : zcount;
        int altCount = start == '1' ? zcount : ocount;
        StringBuilder gen = new StringBuilder();
        //generating as per count
        while (primeCount > 0) {
            gen.append(primeChar);

            if (altCount > 0) {
                gen.append(altChar);
            }
            primeCount--;
            altCount--;
        }
        int diff = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) != gen.charAt(i)) {
                diff++;
            }
        }
        return diff / 2;// since we swap then we correct 2 difference. So divide by 2 for swap
    }
}
