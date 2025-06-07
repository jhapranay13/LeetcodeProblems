package LeetcodeDiscuss;

import java.util.*;

/**
 *
 * Problem: Robot Navigation with Circular Sensor Traps
 *
 * You are given a 2D rectangular room of dimensions w × h (width × height).
 * Inside the room are n security sensors, each located at a specific (x, y) coordinate
 * and having a circular detection radius r.
 *
 * The robot starts at the bottom-left corner (0, 0) and needs to reach the top-right
 * corner (w, h). The robot can move in any direction, including diagonally or along a
 * curved path, as long as it does not enter the detection zone (i.e., the circular area) of
 * any sensor.
 *
 * If the robot’s path touches or enters even one sensor’s detection zone,
 * the alarm is triggered and the path is invalid.
 *
 * Task
 * Implement a function:
 *
 * def pathExists(positions: List[List[int]], w: int, h: int) -> bool:
 * positions: A list of lists, where each element is [x, y, r], representing a sensor located at (x, y) with detection radius r.
 * w: Width of the room.
 * h: Height of the room.
 *
*/
public class GoogleOARobotNavigationWithSensor {

    public static void main(String ...args) {
        /*List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(2, 2, 1),  // Sensor near start, small radius
                Arrays.asList(7, 7, 1)   // Sensor near end, small radius
        );
        int w = 10;
        int h = 10;
        */
        /*
        List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(0, 0, 0) // Sensor directly at (0,0) with radius 0
        );
        int w = 10;
        int h = 10;
        */
        /*List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(0, 0, 1) // Sensor at (0,0) with radius 1, blocks start and part of walls
        );
        int w = 10;
        int h = 10;
        */
        /*List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(9, 9, 0) // Sensor directly at (w,h) with radius 0
        );
        int w = 10;
        int h = 10;
        */
        /*List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(5, 0, 1) // Sensor at bottom, touching x=5
        );
        int w = 10;
        int h = 10;
        */
       /* List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(9, 5, 1),
                Arrays.asList(5, 9, 1),
                Arrays.asList(8, 8, 3)
        );
        int w = 10;
        int h = 10;
        */

        List<List<Integer>> positions = Arrays.asList(
                Arrays.asList(4, 3, 3),
                Arrays.asList(5, 8, 1),
                Arrays.asList(8, 6, 2),
                Arrays.asList(8, 9, 1)
        );
        int w = 10;
        int h = 10;
        System.out.println(pathExists(positions, w, h));
    }

    private static boolean pathExists(List<List<Integer>> positions, int w, int h) {
        boolean loWallBlocked = false;
        boolean hiWallBlocked = false;
        boolean leftWallBlocked = false;
        boolean rightWallBlocked = false;
        int n = w * h;
        UnionFind uf = new UnionFind(n + 4); // +4 for walls
        // n == lo
        // n + 1 == hi
        // n + 2 == left
        // n + 3 == right

        for (int i = 0; i < positions.size(); i++) {
            List<Integer> sensor = positions.get(i);
            int x = sensor.get(0);
            int y = sensor.get(1);
            int r = sensor.get(2);
            int id = y + x * w;

            if (x - r <= 0) {
                leftWallBlocked = true;
                uf.union(id, n + 2); // Connect to left wall
            }

            if (x + r >= w - 1) {
                rightWallBlocked = true;
                uf.union(id, n + 3); // Connect to right wall
            }

            if (y - r <= 0) {
                loWallBlocked = true;
                uf.union(id, n); // Connect to lo wall
            }

            if (y + r >= h - 1) {
                hiWallBlocked = true;
                uf.union(id, n + 1); // Connect to hi wall
            }

            for (int j = i + 1; j < positions.size(); j++) {

                List<Integer> next = positions.get(j);
                int nextX = next.get(0);
                int nextY = next.get(1);
                int nextR = next.get(2);

                if (distance(x, y, nextX, nextY) <= r + nextR) {
                    uf.union(x * w + y, nextY + w * nextX);
                }
            }

            if ((loWallBlocked && hiWallBlocked && uf.find(n) == uf.find(n + 1)) ||
                    (leftWallBlocked && rightWallBlocked && uf.find(n + 2) == uf.find(n + 3)) ||
                    (loWallBlocked && leftWallBlocked && uf.find(n) == uf.find(n + 2)) ||
                    (hiWallBlocked && rightWallBlocked && uf.find(n + 1) == uf.find(n + 3))) {
                return false;
            }
        }
        return true;
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {

            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent[rootY] = rootX; // Union operation
            }
        }
    }
}
