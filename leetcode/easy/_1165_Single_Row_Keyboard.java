package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * There is a special keyboard with all keys in a single row.
 *
 * Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25). Initially, your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.
 *
 * You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
 *
 *
 *
 * Example 1:
 *
 * Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
 * Output: 4
 * Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
 * Total time = 2 + 1 + 1 = 4.
 * Example 2:
 *
 * Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
 * Output: 73
 *
 *
 * Constraints:
 *
 * keyboard.length == 26
 * keyboard contains each English lowercase letter exactly once in some order.
 * 1 <= word.length <= 104
 * word[i] is an English lowercase letter.
 *
 */

public class _1165_Single_Row_Keyboard {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> posMap = new HashMap<>();
        int pos = 0;

        for (char ch : keyboard.toCharArray()) {
            posMap.put(ch, pos++);
        }
        int currPos = 0;
        char currChar = keyboard.charAt(0);
        int ans = 0;

        for (char ch : word.toCharArray()) {
            int nextPos = currPos;

            if (ch != currChar) {
                nextPos = posMap.get(ch);
            }
            ans += Math.abs(nextPos - currPos);
            currPos = nextPos;
            currChar = ch;
        }
        return ans;
    }
}
