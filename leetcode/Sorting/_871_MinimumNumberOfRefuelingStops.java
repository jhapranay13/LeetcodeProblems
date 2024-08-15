package leetcode.Sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 *
 * There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.
 *
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 *
 * Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.
 *
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 1, startFuel = 1, stations = []
 * Output: 0
 * Explanation: We can reach the target without refueling.
 * Example 2:
 *
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can not reach the target (or even the first gas station).
 * Example 3:
 *
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation: We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 *
 *
 * Constraints:
 *
 * 1 <= target, startFuel <= 109
 * 0 <= stations.length <= 500
 * 0 <= positioni <= positioni+1 < target
 * 1 <= fueli < 109
 */

public class _871_MinimumNumberOfRefuelingStops {

    //==============================================================================================
    //Top Down Approach
    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        //Since stops can be equal to stations length
        int memo[][] = new int[stations.length][stations.length + 1];

        for (int stops = 0; stops <= stations.length; stops++) {
            int maxDistanceInStops = 0;

            maxDistanceInStops = Math.max(maxDistanceInStops, recur(stations, startFuel,
                    stations.length - 1, stops, memo));

            if (maxDistanceInStops >= target) {
                return stops;
            }
        }
        return -1;
    }

    private int recur(int[][] stations, int fuel, int index, int stops, int[][] memo) {

        if (index < 0) {
            return fuel;
        }

        if (memo[index][stops] > 0) {
            return memo[index][stops];
        }
        int maxDistance = recur(stations, fuel, index - 1, stops, memo);
        //The condition that this position can be reached. If yes skip and check again
        if (stations[index][0] <= maxDistance) {

            if (stops > 0) {
                int withStops = recur(stations, fuel, index - 1, stops - 1, memo);
                //can this point be reached with stops
                if (withStops >= stations[index][0]) {
                    maxDistance = Math.max(maxDistance, withStops + stations[index][1]);
                }
            }
        }
        return memo[index][stops] = maxDistance;
    }
    //==============================================================================================
    //Bottom up Approach
    public int minRefuelStops1(int target, int startFuel, int[][] stations) {

        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        //Since stops can be equal to stations length
        int memo[][] = new int[stations.length][stations.length + 1];

        for (int stops = 0; stops <= stations.length; stops++) {
            int maxDistanceInStops = 0;

            for (int index = 0; index < stations.length; index++) {

                if (index == 0) {
                    memo[index][stops] = startFuel;
                }
                int maxDistance = 0;

                if (index == 0) {
                    maxDistance = startFuel;
                } else {
                    maxDistance = memo[index - 1][stops];;
                }
                //The condition that this position can be reached. If yes skip and check again
                if (stations[index][0] <= maxDistance) {

                    if (stops > 0) {
                        int withOutStops = 0;

                        if (index == 0) {
                            withOutStops = maxDistance;
                        } else {
                            withOutStops = memo[index - 1][stops - 1];
                        }
                        //can this point be reached without stops
                        if (withOutStops >= stations[index][0]) {
                            maxDistance = Math.max(maxDistance, withOutStops + stations[index][1]);
                        }
                    }
                }
                memo[index][stops] = maxDistance;
                maxDistanceInStops = Math.max(maxDistanceInStops, memo[index][stops]);
            }

            if (maxDistanceInStops >= target) {
                return stops;
            }
        }
        return -1;
    }
    //=============================================================================================
    //Bit more detailed with explanation
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {

        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        int maxStops = stations.length;
        int ans = -1;
        int[][] memo = new int[stations.length + 1][stations.length];
        //NumberOf Stops can be stations of length

        for (int stop = 0; stop <= maxStops; stop++) {
            int distanceTravelled = recur1(startFuel, stations, stop, stations.length - 1, memo);

            if (distanceTravelled >= target) {
                ans = stop;
                break;
            }
        }
        return ans;
    }

    private int recur1(int fuel, int[][] stations, int stop, int index, int[][] memo) {
        //returning fuel as this is the maxDistance we can travel from zero;
        if (index < 0) {
            return fuel;
        }

        if (memo[stop][index] > 0) {
            return memo[stop][index];
        }
        int maxDistance = recur1(fuel, stations, stop, index - 1, memo);
        int fuelAtThisPoint = stations[index][1];
        int distanceOfPoint = stations[index][0];
        //The condition that this position can be reached. If yes skip and check again
        if (distanceOfPoint <= maxDistance) {

            if (stop > 0) {
                int withStops = recur1(fuel, stations, stop - 1, index - 1, memo);
                //can this point be reached with stops
                if (withStops >= distanceOfPoint) {
                    maxDistance = Math.max(maxDistance, withStops + fuelAtThisPoint);
                }
            }
        }
        return memo[stop][index] = maxDistance;
    }
    //============================================================================================
    //Greedy Priority Queue
    public int minRefuelStops4(int target, int startFuel, int[][] stations) {
        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return y[1] - x[1];
            }
        });
        int index = 0;
        int distanceCanBeTravelled = startFuel;
        int stop = 0;

        while (distanceCanBeTravelled < target ) {

            while (index < stations.length && stations[index][0] <= distanceCanBeTravelled) {
                pq.offer(new int[] {stations[index][0], stations[index++][1]});
            }

            if (pq.isEmpty()) {
                return -1;
            }
            stop++;
            //It's true that PQ can poll a previous gas stop too but we have to make sure that fuel
            //collected is Maximum with Minimum Stops. So it is ok in this question that we can consider
            //previous stops whose distance is less than current distance as well with current stop as well
            int travelInfo[] = pq.poll();
            distanceCanBeTravelled += travelInfo[1];
        }
        return stop;
    }
    //=============================================================================================
    //Top Down but Memory Limit Exceeded
    public int minRefuelStops5(int target, int startFuel, int[][] stations) {

        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        int ans = Integer.MAX_VALUE;
        int[][] memo = new int[target + 1][stations.length];

        for (int index = 0; index < stations.length; index++) {
            ans = Math.min(ans, recur5(stations, target, startFuel, index, memo));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    //The logic is basically adding up all the fuel that can be collected by reaching different stations.
    //As much fuel we can collect the more distance we can travel
    private int recur5(int[][] stations, int target, int fuel, int index, int[][] memo) {

        if (fuel >= target) {
            return 0;
        }
        int currDistance = 0;
        int currFuel = 0;

        if (index < stations.length) {
            currDistance = stations[index][0];
            currFuel = stations[index][1];
        }

        if (fuel < currDistance) {
            return Integer.MAX_VALUE;
        }

        if (fuel + currFuel >= target) {
            return 1;
        }

        if (memo[fuel][index] > 0) {
            return memo[fuel][index];
        }
        int ret = Integer.MAX_VALUE;

        for (int i = index + 1; i < stations.length; i++) {
            int temp = recur5(stations, target, fuel + currFuel, i, memo);

            if (temp != Integer.MAX_VALUE) {
                ret = Math.min(ret, 1 + temp);
            }
        }
        return memo[fuel][index] = ret;
    }
    //=============================================================================================
    //Simple approach choose or don't choose TLE
    public int minRefuelStops6(int target, int startFuel, int[][] stations) {

        if (target <= startFuel) {
            return 0;
        }

        if (stations.length == 0) {
            return -1;
        }
        int ans = Integer.MAX_VALUE;
        Map<String, Integer> memo = new HashMap<>();
        ans = recur6(stations, target, startFuel, 0, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    //The logic is basically adding up all the fuel that can be collected by reaching different stations.
    //As much fuel we can collect the more distance we can travel
    private int recur6(int[][] stations, int target, int fuel, int index, Map<String, Integer> memo) {

        if (fuel >= target) {
            return 0;
        }

        if (index == stations.length) {
            return Integer.MAX_VALUE;
        }
        int[] stationInfo = stations[index];

        if (fuel < stationInfo[0]) {
            return Integer.MAX_VALUE;
        }
        String key = fuel + ", " + index;
        Integer val = memo.get(key);

        if (val != null) {
            return val;
        }
        int stop = recur6(stations, target, fuel + stationInfo[1], index + 1, memo);

        if (stop != Integer.MAX_VALUE) {
            stop++;
        }
        int withoutStop = recur6(stations, target, fuel, index + 1, memo);
        memo.put(key, Math.min(stop, withoutStop));
        return Math.min(stop, withoutStop);
    }
}
