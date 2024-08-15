package leetcode.DP.TwoDimension;

/**
 * 
 * @author Pranay Jha
 *
 *         You want to schedule a list of jobs in d days. Jobs are dependent
 *         (i.e To work on the i-th job, you have to finish all the jobs j where
 *         0 <= j < i).
 * 
 *         You have to finish at least one task every day. The difficulty of a
 *         job schedule is the sum of difficulties of each day of the d days.
 *         The difficulty of a day is the maximum difficulty of a job done in
 *         that day.
 * 
 *         Given an array of integers jobDifficulty and an integer d. The
 *         difficulty of the i-th job is jobDifficulty[i].
 * 
 *         Return the minimum difficulty of a job schedule. If you cannot find a
 *         schedule for the jobs return -1.
 * 
 * 
 * 
 *         Example 1:
 * 						| 6 |  |   |
 *                      | 5 |  |   |
 *                      | 4 |  |   |
 *                      | 3 |  |   |
 *                      |_2_|  |_1_|  
 * 
 * 						Day 1   Day 2
 * 
 *         Input: jobDifficulty = [6,5,4,3,2,1], d = 2 Output: 7 Explanation:
 *         First day you can finish the first 5 jobs, total difficulty = 6.
 *         Second day you can finish the last job, total difficulty = 1. The
 *         difficulty of the schedule = 6 + 1 = 7 
 *         
 *         
 *         Example 2:
 * 
 *         Input: jobDifficulty = [9,9,9], d = 4 Output: -1 Explanation: If you
 *         finish a job per day you will still have a free day. you cannot find
 *         a schedule for the given jobs. 
 *         
 *         Example 3:
 * 
 *         Input: jobDifficulty = [1,1,1], d = 3 Output: 3 Explanation: The
 *         schedule is one job per day. total difficulty will be 3. 
 *         
 *         Example 4:
 * 
 *         Input: jobDifficulty = [7,1,7,1,7,1], d = 3 Output: 15 
 *         
 *         Example 5:
 * 
 *         Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6 Output:
 *         843
 * 
 * 
 *         Constraints:
 * 
 *         1 <= jobDifficulty.length <= 300 0 <= jobDifficulty[i] <= 1000 1 <= d
 *         <= 10
 *
 */

public class _1335_MinimumDisfficultyOfAJobSchedule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =====================================================================
	// Top Down approach
	public int minDifficulty(int[] jobDifficulty, int d) {
		int[][] memo = new int[jobDifficulty.length][d];
		int ans = rec(jobDifficulty, d, 0, 0, memo);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	private int rec(int[] jd, int d, int i, int rd, int[][] memo) {

		if (d == rd && i == jd.length) {
			return 0;
		}

		if (d == rd || i == jd.length) {
			return Integer.MAX_VALUE;
		}

		if (memo[i][rd] > 0) {
			return memo[i][rd];
		}
		int max = Integer.MIN_VALUE;
		int sum = Integer.MAX_VALUE;

		for (int j = i; j < jd.length; j++) {
			int currVal = jd[j];
			max = Math.max(max, currVal);
			int rest = rec(jd, d, j + 1, rd + 1, memo);

			if (rest == Integer.MAX_VALUE) {
				continue;
			}
			sum = Math.min(sum, max + rest);
		}
		return memo[i][rd] = sum;
	}
	//=============================================================================================
	//Bottom up solution
	public int minDifficulty1(int[] jd, int d) {

		if (jd.length < d) {
			return -1;
		}
		int[][] memo = new int[ jd.length + 1 ][ d + 1 ];

		for (int i = jd.length; i >= 0; i--) {

			for (int rd = d; rd >= 0; rd--) {
				if( d == rd && i == jd.length ) {
					memo[i][rd] = 0;
					continue;
				}

				if( d == rd || i == jd.length ) {
					memo[i][rd] = Integer.MAX_VALUE;
					continue;
				}
				int max = Integer.MIN_VALUE;
				int sum = Integer.MAX_VALUE;

				for( int j = i; j < jd.length; j++ ) {
					int currVal = jd[ j ];
					max = Math.max( max, currVal );
					int rest = memo[j + 1][rd + 1];

					if( rest == Integer.MAX_VALUE ) {
						continue;
					}
					sum = Math.min( sum, max + rest );
				}
				memo[ i ][ rd ] = sum;
			}
		}
		int ans = memo[0][0];
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}
}
