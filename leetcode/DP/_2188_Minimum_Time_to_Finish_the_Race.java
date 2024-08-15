package leetcode.DP;

import java.util.Arrays;

/**
 *
 * You are given a 0-indexed 2D integer array tires where tires[i] = [fi, ri] indicates that the ith tire can finish its xth successive lap in fi * ri(x-1) seconds.
 *
 * For example, if fi = 3 and ri = 2, then the tire would finish its 1st lap in 3 seconds, its 2nd lap in 3 * 2 = 6 seconds, its 3rd lap in 3 * 22 = 12 seconds, etc.
 * You are also given an integer changeTime and an integer numLaps.
 *
 * The race consists of numLaps laps and you may start the race with any tire. You have an unlimited supply of each tire and after every lap, you may change to any given tire (including the current tire type) if you wait changeTime seconds.
 *
 * Return the minimum time to finish the race.
 *
 *
 *
 * Example 1:
 *
 * Input: tires = [[2,3],[3,4]], changeTime = 5, numLaps = 4
 * Output: 21
 * Explanation:
 * Lap 1: Start with tire 0 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Lap 3: Change tires to a new tire 0 for 5 seconds and then finish the lap in another 2 seconds.
 * Lap 4: Continue with tire 0 and finish the lap in 2 * 3 = 6 seconds.
 * Total time = 2 + 6 + 5 + 2 + 6 = 21 seconds.
 * The minimum time to complete the race is 21 seconds.
 * Example 2:
 *
 * Input: tires = [[1,10],[2,2],[3,4]], changeTime = 6, numLaps = 5
 * Output: 25
 * Explanation:
 * Lap 1: Start with tire 1 and finish the lap in 2 seconds.
 * Lap 2: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 3: Change tires to a new tire 1 for 6 seconds and then finish the lap in another 2 seconds.
 * Lap 4: Continue with tire 1 and finish the lap in 2 * 2 = 4 seconds.
 * Lap 5: Change tires to tire 0 for 6 seconds then finish the lap in another 1 second.
 * Total time = 2 + 4 + 6 + 2 + 4 + 6 + 1 = 25 seconds.
 * The minimum time to complete the race is 25 seconds.
 *
 *
 * Constraints:
 *
 * 1 <= tires.length <= 105
 * tires[i].length == 2
 * 1 <= fi, changeTime <= 105
 * 2 <= ri <= 105
 * 1 <= numLaps <= 1000
 *
 */

public class _2188_Minimum_Time_to_Finish_the_Race {
    int maxLap = 1;
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        long[] bestTimeMemo = new long[numLaps + 1];
        long[] memo = new long[numLaps + 1];
        Arrays.fill(bestTimeMemo, Long.MAX_VALUE);
        Arrays.fill(memo, Long.MAX_VALUE);

        findBest(tires, changeTime, numLaps, bestTimeMemo);
        long ans = recur(tires, numLaps, bestTimeMemo, memo, changeTime);
        return (int)ans;
    }

    private void findBest(int[][] tires, int changeTime, int numLaps, long[] bestTimeMemo) {

        for (int[] tire : tires) {
            int fi = tire[0];
            int ri = tire[1];
            int totalTime = fi;
            int lapTime = fi;
            int prevLapTime = fi;
            // since we are not considering cases where this fails prevLapTime + changeTime > lapTime
            // coz technically time taken should be best time with or without changing tire
            for (int lap = 1; lap <= numLaps && prevLapTime + changeTime > lapTime ; lap++) {
                bestTimeMemo[lap] = Math.min(bestTimeMemo[lap], totalTime);
                prevLapTime = lapTime;
                lapTime = fi * (int)Math.pow(ri, lap);
                totalTime += lapTime;
                maxLap = Math.max(maxLap, lap);//Getting the maximum lap that a tire can go without changing
            }
        }
    }

    private long recur(int[][] tires, int numLaps, long[] bestTimeMemo, long[] memo, int changeTime) {
        //Subtracting change already added in previous step
        if (numLaps == 0){
            return -changeTime;
        }

        if (memo[numLaps] != Long.MAX_VALUE) {
            return memo[numLaps];
        }
        long ans = Long.MAX_VALUE;

        for (int lap = 1; lap <= Math.min(maxLap, numLaps); lap++) {
            ans = Math.min(ans, bestTimeMemo[lap] + changeTime + recur(tires, numLaps - lap, bestTimeMemo, memo, changeTime));
        }

        return memo[numLaps] = ans;
    }
}
