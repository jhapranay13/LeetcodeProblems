package leetcode.BackTrackingRecursion;


/**
 *
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by connecting the dots in a specific sequence, forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both of the following are true:
 *
 * All the dots in the sequence are distinct.
 * If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot, the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots are allowed.
 * For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 * However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to dot 3 passes through the center of dot 2.
 * Here are some example valid and invalid unlock patterns:
 *
 *
 *
 * The 1st pattern [4,1,3,6] is invalid because the line connecting dots 1 and 3 pass through dot 2, but dot 2 did not previously appear in the sequence.
 * The 2nd pattern [4,1,9,2] is invalid because the line connecting dots 1 and 9 pass through dot 5, but dot 5 did not previously appear in the sequence.
 * The 3rd pattern [2,4,1,3,6] is valid because it follows the conditions. The line connecting dots 1 and 3 meets the condition because dot 2 previously appeared in the sequence.
 * The 4th pattern [6,5,4,1,9,2] is valid because it follows the conditions. The line connecting dots 1 and 9 meets the condition because dot 5 previously appeared in the sequence.
 * Given two integers m and n, return the number of unique and valid unlock patterns of the Android grid lock screen that consist of at least m keys and at most n keys.
 *
 * Two unlock patterns are considered unique if there is a dot in one sequence that is not in the other, or the order of the dots is different.
 *
 *      1 ----2---->3               1  2  3              1-->2----3              1  2  3
 *      ^           V               ^\  \                V /      V              ^\   \
 *      4     5     6              4  5\ \6              4    5   6            4<--5\<-\-6
 *                                      \\                                            \\
 *      7     8     9              7  8   9               7   8   9             7   8   9
 *
 *      Invalid                       Invalid               vslid                   valid
 * Example 1:
 *
 * Input: m = 1, n = 1
 * Output: 9
 * Example 2:
 *
 * Input: m = 1, n = 2
 * Output: 65
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 9
 *
 *
 */

public class _351_AndroidUnlockProblem {

    public int numberOfPatterns(int m, int n) {
        boolean[][] v = new boolean[3][3];
        int ans = 0;
        // 3 X 3 means 9 number combination is possible
        //Can also use something like jump tables using hashMap to check to see if jump is possible
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                ans += recur(m, n, v, r, c, 1);
            }
        }
        return ans;

    }
    //Observation if a pos lie in diagnal form oyher pos in 2 d array differnce between
    // it's x and y co ordinates will be same
    private int recur(int min, int max, boolean[][] v, int row, int col, int cnt) {
        if (cnt == max) {
            return 1;
        }
        int ans = cnt >= min ? 1 : 0;
        v[row][col] = true;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int dr = Math.abs(row - r);
                int dy = Math.abs(col - c);
                boolean jumpFlag = dr % 2 == 0 && dy % 2 == 0;
                //use dx and dy to check whether it gonna be a jump,
                //if so, whether the jump will cross an visited slot.
                if (!v[r][c]) {

                    if (!jumpFlag) {
                        ans += recur(min, max, v, r, c, cnt + 1);
                    } else if (jumpFlag){

                        if (v[(r + row) / 2][(c + col) / 2]) {
                            ans += recur(min, max, v, r, c, cnt + 1);
                        }
                    }
                }
            }
        }
        v[row][col] = false;
        return ans;
    }
}
