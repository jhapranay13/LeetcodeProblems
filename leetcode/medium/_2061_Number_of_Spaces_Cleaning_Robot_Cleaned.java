package leetcode.medium;

/**
 *
 * A room is represented by a 0-indexed 2D binary matrix room where a 0 represents an empty space and a 1 represents a space with an object. The top left corner of the room will be empty in all test cases.
 *
 * A cleaning robot starts at the top left corner of the room and is facing right. The robot will continue heading straight until it reaches the edge of the room or it hits an object, after which it will turn 90 degrees clockwise and repeat this process. The starting space and all spaces that the robot visits are cleaned by it.
 *
 * Return the number of clean spaces in the room if the robot runs indefinetely.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: room = [[0,0,0],[1,1,0],[0,0,0]]
 * Output: 7
 * Explanation:
 * The robot cleans the spaces at (0, 0), (0, 1), and (0, 2).
 * The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces down.
 * The robot cleans the spaces at (1, 2), and (2, 2).
 * The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces left.
 * The robot cleans the spaces at (2, 1), and (2, 0).
 * The robot has cleaned all 7 empty spaces, so return 7.
 * Example 2:
 *
 *
 * Input: room = [[0,1,0],[1,0,0],[0,0,0]]
 * Output: 1
 * Explanation:
 * The robot cleans the space at (0, 0).
 * The robot hits an object, so it turns 90 degrees clockwise and now faces down.
 * The robot hits an object, so it turns 90 degrees clockwise and now faces left.
 * The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces up.
 * The robot is at the edge of the room, so it turns 90 degrees clockwise and now faces right.
 * The robot is back at its starting position.
 * The robot has cleaned 1 space, so return 1.
 *
 *
 * Constraints:
 *
 * m == room.length
 * n == room[r].length
 * 1 <= m, n <= 300
 * room[r][c] is either 0 or 1.
 * room[0][0] == 0
 *
 */

public class _2061_Number_of_Spaces_Cleaning_Robot_Cleaned {
    public int numberOfCleanRooms(int[][] room) {
        int[][] v = new int[room.length * room[0].length][4];
        recur(room, v, 0, 0, 0);
        return ans;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int ans = 0;

    private boolean recur(int[][] room, int[][] v, int row, int col, int direction) {
        int idx = row * room[0].length + col;

        if (row < 0 || col < 0 || row > room.length - 1 || col > room[0].length - 1 || room[row][col] == 1) {
            //Change Direction
            return false;
        }

        if (v[idx][direction] == 1) {
            return true;
        }
        // So that we don't count it again
        if (room[row][col] == 0) {
            ans++;
            room[row][col] = -1;
        }
        v[idx][direction] = 1;

        for (int i = direction; i < direction + 4; i++) {
            int nextDirection = i % 4;
            int nr = row + dirs[nextDirection][0];
            int nc = col + dirs[nextDirection][1];
            // If true no more move possible as we entered the loop so no more rooms can be cleaned
            if(recur(room, v, nr, nc, nextDirection)){
                break;
            }
        }
        return true;
    }
}
