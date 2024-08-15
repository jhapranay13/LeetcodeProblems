package leetcode.Arrays;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 *
 *
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where poeple[i] is the time that the ith person will arrive to see the flowers.
 *
 * Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
 *
 *
 *
 * Example 1:
 *
 *           F   F   F   F   F   F
 *                   F   F   F   F  F
 *                                        F  F   F   F
 *                       F   F   F  F  F  F  F   F   F   F
 *           1   2   3   4   5   6  7  8  9  10  11  12  13
 *               P   P              P            P
 *               0   1              2            3
 *
 * Input: flowers = [[1,6],[3,7],[9,12],[4,13]], poeple = [2,3,7,11]
 * Output: [1,2,2,2]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 * Example 2:
 *
 *
 * Input: flowers = [[1,10],[3,3]], poeple = [3,3,2]
 * Output: [2,2,1]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 *
 *
 */

public class _2251_Number_of_Flowers_in_Full_Bloom {
    // Line Sweep
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });
        // Flower end time status = 2 flower bloom time status = 0
        for (int[] flower : flowers) {
            pq.offer(new int[] {flower[0], 0});
            pq.offer(new int[] {flower[1], 2});
        }
        // People time status = 1
        int idx = 0;
        for (int peopleTime : people) {
            pq.offer(new int[] {peopleTime, 1, idx++});
        }
        int ans[] = new int[people.length];
        int index = 0;
        int sum = 0;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();

            if (data[1] == 1) {
                ans[data[2]] = sum;
            } else {

                if (data[1] == 0) {
                    sum++;
                } else {
                    sum--;
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // TreeMap implementation
    // Line Sweep
    public int[] fullBloomFlower1s(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> holder = new TreeMap<>();

        for (int[] flower : flowers) {
            holder.put(flower[0], holder.getOrDefault(flower[0], 0) + 1);
            holder.put(flower[1] + 1, holder.getOrDefault(flower[1] + 1, 0) - 1);
        }
        int sum = 0;


        for (int key : holder.keySet()) {
            int floor = holder.get(key);
            sum += floor;
            holder.put(key, sum);
        }
        int ans[] = new int[people.length];
        int index = 0;

        for (int timePeople : people) {
            Integer time = holder.floorKey(timePeople);

            if (time != null) {
                ans[index] = holder.get(time);
            }
            index++;
        }
        return ans;
    }
}
