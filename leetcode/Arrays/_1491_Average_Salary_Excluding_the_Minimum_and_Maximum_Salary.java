package leetcode.Arrays;

/**
 *
 * You are given an array of unique integers salary where salary[i] is the salary of the ith employee.
 *
 * Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: salary = [4000,3000,1000,2000]
 * Output: 2500.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 4000 respectively.
 * Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
 * Example 2:
 *
 * Input: salary = [1000,2000,3000]
 * Output: 2000.00000
 * Explanation: Minimum salary and maximum salary are 1000 and 3000 respectively.
 * Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
 *
 *
 * Constraints:
 *
 * 3 <= salary.length <= 100
 * 1000 <= salary[i] <= 10^6
 * All the integers of salary are unique.
 *
 */

public class _1491_Average_Salary_Excluding_the_Minimum_and_Maximum_Salary {
    public double average(int[] salary) {
        double sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int sal : salary) {
            sum += sal;
            min = Math.min(min, sal);
            max = Math.max(max, sal);
        }
        sum -= min + max;
        double ans = 0d;

        if (salary.length - 2 > 0) {
            ans = (double)sum / (salary.length - 2);
        }
        return ans;
    }
}
