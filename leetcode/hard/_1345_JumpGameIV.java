package leetcode.hard;

import java.util.*;

/**
 *
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 *
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You do not need to jump.
 * Example 3:
 *
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 5 * 104
 * -108 <= arr[i] <= 108
 *
 */

public class _1345_JumpGameIV {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> valIndexMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = valIndexMap.getOrDefault(arr[i], new LinkedList<>());
            list.add(i);
            valIndexMap.put(arr[i], list);
        }
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        Set<Integer> v = new HashSet<>();
        v.add(0);

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] curr = q.poll();

                if (curr[0] == arr.length - 1) {
                    return curr[1];
                }
                List<Integer> list = valIndexMap.getOrDefault(arr[curr[0]], new ArrayList<>());

                for (int i = list.size() - 1; i >= 0; i--) {
                    int next = list.get(i);
                    list.remove(i);

                    if (v.add(next)) {
                        q.offer(new int[] {next, curr[1] + 1});
                    }
                }

                if (curr[0] > 0 && v.add(curr[0] - 1)) {
                    q.offer(new int[] { curr[0] - 1, curr[1] + 1});

                }

                if (curr[0] < arr.length - 1 && v.add(curr[0] + 1)) {
                    q.offer(new int[] { curr[0] + 1, curr[1] + 1});
                }
            }
        }
        return -1;
    }
}
