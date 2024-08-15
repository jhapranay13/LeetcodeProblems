package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Imagine you have a special keyboard with the following keys:
 *
 * A: Print one 'A' on the screen.
 * Ctrl-A: Select the whole screen.
 * Ctrl-C: Copy selection to buffer.
 * Ctrl-V: Print buffer on screen appending it after what has already been printed.
 * Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the keys.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: We can at most get 3 A's on screen by pressing the following key sequence:
 * A, A, A
 * Example 2:
 *
 * Input: n = 7
 * Output: 9
 * Explanation: We can at most get 9 A's on screen by pressing following key sequence:
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 *
 */

public class _651_4_Keys_Keyboard {
    public int maxA(int n) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(n, 0, 0, memo);
    }

    private int recur(int keyStroke, int currLength, int copyLength, Map<String, Integer> memo) {

        if (keyStroke == 3) {
            //Press 3 times A or select Copy Paste
            return Math.max(3 + currLength, Math.max(currLength * 2, copyLength * 3 + currLength));
        }

        if (keyStroke == 2) {
            return Math.max(keyStroke + currLength, Math.max(currLength + copyLength + 1, currLength + copyLength * 2));
        }

        if (keyStroke == 1) {
            return Math.max(currLength + keyStroke, currLength + copyLength);
        }
        String key = keyStroke + "|" + currLength + "|" + copyLength;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int printA = 0;
        int ctrlV = 0;
        int ctrlACtrlC = 0;

        // If copyLength is less than 2 then printing can make sense or else ctrv makes more sense
        if (copyLength < 2) {
            printA = recur(keyStroke - 1, currLength + 1, copyLength, memo);
        }
        // pressing ctrl c makes sense only when keystroke is greater than 2
        if (keyStroke > 2) {
            ctrlACtrlC = recur(keyStroke - 2, currLength, currLength, memo);
        }
        // if copyLength is greater than equal to 2 then ctrv makes more sense than pressing A
        if (copyLength >= 2) {
            ctrlV = recur(keyStroke - 1, currLength + copyLength, copyLength, memo);
        }
        int ans = Math.max(printA, Math.max(ctrlACtrlC, ctrlV));;
        memo.put(key, ans);
        return ans;
    }
    //=============================================================================================
    //Another Approach
    public int maxA1(int n) {
        Map<String, Integer> memo = new HashMap<>();
        return recur(n, 0, memo);
    }

    private int recur(int keyStroke, int currLength, Map<String, Integer> memo) {

        if (keyStroke == 3) {
            //Press 3 times A or select Copy Paste
            return Math.max(3 + currLength, currLength * 2);
        }

        if (keyStroke <= 2) {
            return currLength + keyStroke;
        }

        String key = keyStroke + "|" + currLength;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ctrlACtrlCctrlV = 0;
        int printA = recur(keyStroke - 1, currLength + 1, memo);
        int multiplier = 2;

        if (keyStroke > 3 && currLength >= 2) {

            for (int ks = keyStroke - 3; ks >= 0; ks--) {
                int temp = recur(ks, currLength * multiplier, memo);
                ctrlACtrlCctrlV = Math.max(temp, ctrlACtrlCctrlV);
                multiplier++;
            }
        }

        int ans = Math.max(printA, ctrlACtrlCctrlV);
        memo.put(key, ans);
        return ans;
    }
}
