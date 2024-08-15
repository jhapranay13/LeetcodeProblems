package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.
 *
 * Return true if the starting player can guarantee a win, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: currentState = "++++"
 * Output: true
 * Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Example 2:
 *
 * Input: currentState = "+"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= currentState.length <= 60
 * currentState[i] is either '+' or '-'.
 *
 *
 * Follow up: Derive your algorithm's runtime complexity.
 *
 */

public class _294_Flip_Game_II {
    public boolean canWin(String currentState) {
        Map<String, Boolean> memo = new HashMap<>();
        return recur(currentState, memo);
    }

    private boolean recur(String state, Map<String, Boolean> memo) {

        if (state == null || state.length() < 2) {
            return false;
        }

        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        boolean ans = false;

        for (int i = 0; i < state.length(); i++) {

            if (state.startsWith("++", i)) {
                String nextState = state.substring(0, i) + "--" + state.substring(i + 2);
                // If next person losses current can win
                if (!recur(nextState, memo)) {
                    ans = true;
                    break;
                }
            }
        }
        memo.put(state, ans);
        return ans;
    }
}
