package leetcode.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.
 *
 * The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.
 *
 * Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: favorite = [2,2,1,2]
 * Output: 3
 * Explanation:
 * The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
 * All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
 * Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
 * The maximum number of employees that can be invited to the meeting is 3.
 * Example 2:
 *
 * Input: favorite = [1,2,0]
 * Output: 3
 * Explanation:
 * Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
 * The seating arrangement will be the same as that in the figure given in example 1:
 * - Employee 0 will sit between employees 2 and 1.
 * - Employee 1 will sit between employees 0 and 2.
 * - Employee 2 will sit between employees 1 and 0.
 * The maximum number of employees that can be invited to the meeting is 3.
 * Example 3:
 *
 *
 * Input: favorite = [3,0,1,4,1]
 * Output: 4
 * Explanation:
 * The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
 * Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
 * So the company leaves them out of the meeting.
 * The maximum number of employees that can be invited to the meeting is 4.
 *
 */

public class _2127_Maximum_Employees_to_Be_Invited_to_a_Meeting {
    // Since mentioned in the question that each will have a fovourite person
    // This means that graph is bound to have cycle
    public int maximumInvitations(int[] favorite) {
        int[] indegree = new int[favorite.length];

        for (int i = 0; i < favorite.length; i++) {
            indegree[favorite[i]]++;
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < favorite.length; i++) {

            if (indegree[i] == 0) {
                q.offer(i);
                indegree[i]--;
            }
        }
        int step = 0;
        int maxStepToReachNode[] = new int[favorite.length];
        // Checking for straight chain without any loops to elimnite them and get the length
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int curr = q.poll();
                int fav = favorite[curr];
                maxStepToReachNode[fav] = Math.max(maxStepToReachNode[fav], step + 1);
                indegree[fav]--;

                if (indegree[fav] == 0) {
                    q.add(fav);
                    indegree[fav]--;
                }
            }
            step++;
        }
        int ansFor2Loop = 0;
        int answerForBigLoop = 0;
        // Any Nodes who have indegree greater than -1 are loops
        // Also using indegree as visited
        for (int i = 0; i < favorite.length; i++) {

            if (indegree[i] < 0) {
                continue;
            }
            int curr = i;
            int length = 0;
            // Calcultaing length of the loop/cycle
            while (indegree[curr] > 0) {
                indegree[curr] = -1;
                curr = favorite[curr];
                length++;
            }
            // That means they can sit beside each other in circular table
            // maxStepToReachNode[favorite[i]] Since this can also have other singular path
            if (length == 2) {
                ansFor2Loop += length + maxStepToReachNode[i] + maxStepToReachNode[favorite[i]];
            } else {
                answerForBigLoop = Math.max(answerForBigLoop, length);
            }
        }
        return Math.max(answerForBigLoop, ansFor2Loop);
    }
}
