package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.
 *
 * Return all possible states of the string currentState after one valid move. You may return the answer in any order. If there is no valid move, return an empty list [].
 *
 *
 *
 * Example 1:
 *
 * Input: currentState = "++++"
 * Output: ["--++","+--+","++--"]
 * Example 2:
 *
 * Input: currentState = "+"
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= currentState.length <= 500
 * currentState[i] is either '+' or '-'.
 *
 */

public class _293_Flip_Game {
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();

        if (currentState.length() >= 2) {

            for (int i = 1; i < currentState.length(); i++) {
                StringBuilder holder = new StringBuilder(currentState);

                if (holder.charAt(i) == '+' && holder.charAt(i - 1) == '+') {
                    holder.setCharAt(i, '-');
                    holder.setCharAt(i - 1, '-');
                    ans.add(holder.toString());
                }
            }
        }
        return ans;
    }
}
