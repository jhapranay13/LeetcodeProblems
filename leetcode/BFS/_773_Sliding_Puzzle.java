package leetcode.BFS;

import java.util.*;

/**
 *
 * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Example 2:
 *
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Example 3:
 *
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 *
 *
 * Constraints:
 *
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * Each value board[i][j] is unique.
 *
 */

public class _773_Sliding_Puzzle {
    public int slidingPuzzle(int[][] board) {
        StringBuilder initState = new StringBuilder();
        String finalState = "123450";

        for (int r = 0; r < board.length; r++) {

            for (int c = 0; c < board[0].length; c++) {
                initState.append(board[r][c]);
            }
        }
        int ans = 0;
        Set<String> v = new HashSet<>();
        Deque<String> q = new LinkedList<>();
        q.offer(initState.toString());
        v.add(initState.toString());

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String currState = q.poll();

                if (currState.equals(finalState)) {
                    return ans;
                }
                List<String> nextStates = getNextState(currState);

                for (String next : nextStates) {

                    if (v.add(next)) {
                        q.offer(next);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    private List<String> getNextState(String currState) {
        List<String> res = new ArrayList<>();
        int zeroPos = 0;
        int index = 0;

        for (char ch : currState.toCharArray()) {

            if (ch == '0') {
                zeroPos = index;
                break;
            }
            index++;
        }
        //idx = c + r * board[0].length
        int r = zeroPos / 3;
        int c = zeroPos % 3;

        for (int[] dir : dirs) {
            StringBuilder holder = new StringBuilder(currState);
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr >= 2 || nc >= 3) {
                continue;
            }
            int idx = nc + nr * 3;
            char ch = holder.charAt(idx);
            holder.setCharAt(idx, '0');
            holder.setCharAt(zeroPos, ch);
            res.add(holder.toString());
        }
        return res;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
