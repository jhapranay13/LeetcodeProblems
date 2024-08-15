package leetcode.Graph;

import java.util.PriorityQueue;

/**
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.
 *
 * Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".
 *
 * If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [0,1]
 * Output: "lul"
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
 * Example 2:
 *
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [3,0]
 * Output: "impossible"
 * Explanation: The ball cannot reach the hole.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
 * Output: "dldr"
 *
 *
 * Constraints:
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * ball.length == 2
 * hole.length == 2
 * 0 <= ballrow, holerow <= m
 * 0 <= ballcol, holecol <= n
 * Both the ball and the hole exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 *
 */

public class _499_The_Maze_III {
    // Dijkstra
    int[][] dirs = new int[][] {{1,0},{0,-1},{0,1},{-1,0}};
    String[] stDir = new String[] {"d","l","r","u"};

    class Node {
        int r;
        int c;
        String path;
        int distance;

        public Node (int r, int c, String path, int distance) {
            this.r = r;
            this.c = c;
            this.path = path;
            this.distance = distance;
        }

        public String toString() {
            return "r >>" + r + " c >>" + c + " path >>" + path + " distance >>" + distance;
        }
    }

    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) ->
                a.distance == b.distance ? a.path.compareTo(b.path) : a.distance - b.distance);
        Node node = new Node(ball[0], ball[1], "", 0);
        Node[][] nodeDistance = new Node[maze.length][maze[0].length];
        nodeDistance[node.r][node.c] = node;
        pq.offer(node);

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.r == hole[0] && curr.c == hole[1]) {
                return curr.path;
            }

            for (int i = 0; i < dirs.length; i++) {
                int nr = curr.r;
                int nc = curr.c;
                int[] dir = dirs[i];
                int step = 0;

                while (nr + dir[0] >= 0 && nc + dir[1] >= 0 && nr + dir[0] < maze.length &&
                        nc + dir[1] < maze[0].length && maze[nr + dir[0]][nc + dir[1]] != 1) {
                    nr += dir[0];
                    nc += dir[1];
                    step++;

                    if (nr == hole[0] && nc == hole[1]) {
                        break;
                    }
                }

                if (nodeDistance[nr][nc] == null ||
                        nodeDistance[nr][nc].distance > curr.distance + step ||
                        (nodeDistance[nr][nc].distance == curr.distance + step &&
                                (curr.path + stDir[i]).compareTo(nodeDistance[nr][nc].path) < 0)) {

                    Node next = new Node(nr, nc, curr.path + stDir[i], curr.distance + step);
                    nodeDistance[nr][nc] = next;
                    pq.offer(next);
                }
            }
        }
        return "impossible";
    }
}
