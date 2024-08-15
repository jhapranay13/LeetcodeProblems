package leetcode.Sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * Once a task is started, the CPU will process the entire task without stopping.
 * The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 * Example 2:
 *
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 *
 * Constraints:
 *
 * tasks.length == n
 * 1 <= n <= 105
 * 1 <= enqueueTimei, processingTimei <= 109
 *
 */

public class _1834_SingleThreadedCPU {
    public int[] getOrder(int[][] tasks) {
        PriorityQueue<int[]> taskAvailabilityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {

                if (x[0] == y[0]) {
                    return x[1] - y[1];
                }
                return x[0] - y[0];
            }
        });
        int index = 0;

        for (int[] task : tasks) {
            taskAvailabilityQueue.offer(new int[] {task[0], task[1], index++});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if (x[1] == y[1]) {
                    return x[2] - y[2];
                }
                return x[1] - y[1];
            }
        });
        int[] ans = new int[tasks.length];
        int timeTaken = 0;
        index = 0;

        while (!taskAvailabilityQueue.isEmpty()) {

            while (!taskAvailabilityQueue.isEmpty() && taskAvailabilityQueue.peek()[0] <= timeTaken) {
                pq.offer(taskAvailabilityQueue.poll());
            }

            if (pq.isEmpty()) {
                int currTime = taskAvailabilityQueue.peek()[0];

                while (!taskAvailabilityQueue.isEmpty() && taskAvailabilityQueue.peek()[0] <= currTime) {
                    pq.offer(taskAvailabilityQueue.poll());
                }
                timeTaken = currTime;
            }
            int[] currTask = pq.poll();
            ans[index++] = currTask[2];
            timeTaken += currTask[1];
        }

        while (!pq.isEmpty()) {
            int[] currTask = pq.poll();
            ans[index++] = currTask[2];
        }
        return ans;
    }
    //============================================================================================
    //Different priority queue solution
    public int[] getOrder1(int[][] tasks) {
        PriorityQueue<int[]> availablity = new PriorityQueue<>((a, b) -> {

            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        PriorityQueue<int[]> processing = new PriorityQueue<>((a, b) -> {

            if (a[1] == b[1]) {
                return a[2] - b[2];
            }
            return a[1] - b[1];
        });
        int index = 0;

        for (int[] task : tasks) {
            availablity.offer(new int[] {task[0], task[1], index++});
        }
        int[] ans = new int[tasks.length];
        int currTime = -1;
        index = 0;

        while (!availablity.isEmpty()) {

            while(!availablity.isEmpty() && availablity.peek()[0] <= currTime) {
                processing.offer(availablity.poll());
            }

            if (!processing.isEmpty()) {
                int[] currTask = processing.poll();
                int tempTime = currTask[1];
                currTime += tempTime;
                ans[index++] = currTask[2];
            } else {

                if (!availablity.isEmpty()) {
                    currTime = availablity.peek()[0];
                }
            }
        }

        while (!processing.isEmpty()) {
            ans[index++] = processing.poll()[2];
        }
        return ans;
    }
    //=============================================================================================
    //different solution using sort
    public int[] getOrder2(int[][] tasks) {
        int[][] sortedtasks = new int[tasks.length][3];

        for (int i = 0; i < tasks.length; i++) {
            sortedtasks[i] = new int[] {tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(sortedtasks, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> processing = new PriorityQueue<>((a, b) -> {

            if (a[1] == b[1]) {
                return a[2] - b[2];
            }
            return a[1] - b[1];
        });
        int index = 0;
        int[] ans = new int[tasks.length];
        int ansIndex = 0;
        int currTime = -1;

        while (index < sortedtasks.length || !processing.isEmpty()) {

            if (index < sortedtasks.length && processing.isEmpty() &&
                    currTime < sortedtasks[index][0]) {
                currTime = sortedtasks[index][0];
            }

            while (index < sortedtasks.length && sortedtasks[index][0] <= currTime) {
                processing.offer(sortedtasks[index++]);
            }
            int[] currVal = processing.poll();
            ans[ansIndex++] = currVal[2];
            currTime += currVal[1];
        }
        return ans;
    }

}
