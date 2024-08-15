package leetcode.medium;

import java.util.Arrays;

/**
 *
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points
 * construct a square.
 *
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 *
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 *
 *
 * Example 1:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: true
 * Example 2:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * Output: false
 * Example 3:
 *
 * Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * Output: true
 *
 *
 * Constraints:
 *
 * p1.length == p2.length == p3.length == p4.length == 2
 * -104 <= xi, yi <= 104
 *
 */

public class _593_ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // Make sure all points aren't be the same, by making sure two of them aren't same.
        if (p1[0] == p2[0] && p1[1] == p2[1]) {
            return false;
        }
        int[][] points = {p1, p2, p3, p4};
        //by sorting the two points in the middle and two points in the middle can be connected. The max point is the farthest point. Checking by exchanging two middl element
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        boolean flag = check(points[0], points[1], points[2], points[3]);
        flag |= check(points[0], points[2], points[1], points[3]);
        return flag;
    }

    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        double distance1And2 = distSquared(p1, p2);
        double distance1And3 = distSquared(p1, p3);
        double distance3And4 = distSquared(p3, p4);
        double distance2And4 = distSquared(p2, p4);
        double diagonal1And4 = distSquared(p1, p4);
        double diagonal2And3 = distSquared(p2, p3);

        return distance1And2 == distance1And3 && distance3And4 == distance2And4 && distance1And3 == distance2And4 && diagonal1And4 == diagonal2And3;
    }

    private double distSquared(int[] p1, int[] p2) {
        int dx = Math.abs(p1[0] - p2[0]);
        int dy = Math.abs(p1[1] - p2[1]);
        return Math.pow(dx, 2) + Math.pow(dy, 2);
    }
}
