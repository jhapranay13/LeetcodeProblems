package LeetcodeDiscuss;


/**
 *
 * There are n cars on x axis. i-th Car's initial position is denoted by location[i] and maximum speed by speed[i].
 * cars can travel in both directions(left and right).
 * At the end, all cars need to meet at the same location,
 * what would be the minimum time required at which all cars can meet.
 *
 * Test case
 * cars : 3
 * position: [1, 3, 7]
 * speed: [2, 1, 1]
 *
 * Answer: 2
 * if these cars meet at 5. each car will take 2 units of time.
 *
 */

public class UberOAMinimumTimeCarsWillMeetAtAPoint {

    public static void main(String args[]) {
        /*int[] position = {1, 3, 7};
        int[] speed = {2, 1, 1};*/

        /*int[] position = {0, 100};
        int[] speed = {10, 1};*/

        /*int[] position = {0, 100, 200};
        int[] speed = {10, 10, 10};*/

        int[] position = {0, 10, 20, 30};
        int[] speed = {1, 2, 3, 4};

        System.out.println(minimumTime(position, speed));
    }

    private static int minimumTime(int[] position, int[] speed) {
        int lo = 0;
        int hi = 1000000; // Assuming a large enough upper limit for the search space
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (canMeet(position, speed, pivot)) {
                hi = pivot - 1;
                ans = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }

    private static boolean canMeet(int[] position, int[] speed, int time) {
        int[][] range = new int[position.length][2];

        for (int i = 0 ; i < position.length; i++) {
            int left = position[i] - speed[i] * time;
            int right = position[i] + speed[i] * time;
            range[i][0] = left;
            range[i][1] = right;
        }
        int start = Integer.MIN_VALUE;
        int end = Integer.MAX_VALUE;
        // Compressing the range
        for (int i = 0; i < position.length; i++) {
            start = Math.max(start, range[i][0]);
            end = Math.min(end, range[i][1]);
        }
        // Found common range for cars to meet
        System.out.println("Start: " + start + ", End: " + end + ", Time: " + time);
        return start <= end;
    }
}
