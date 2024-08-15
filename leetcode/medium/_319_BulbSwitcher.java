package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n bulbs that are initially off. You first turn on all the
 *         bulbs, then you turn off every second bulb.
 * 
 *         On the third round, you toggle every third bulb (turning on if it's
 *         off or turning off if it's on). For the ith round, you toggle every i
 *         bulb. For the nth round, you only toggle the last bulb.
 * 
 *         Return the number of bulbs that are on after n rounds.
 * 
 *         Example 1:
 * 
 *         Input: n = 3 Output: 1 Explanation: At first, the three bulbs are
 *         [off, off, off]. After the first round, the three bulbs are [on, on,
 *         on]. After the second round, the three bulbs are [on, off, on]. After
 *         the third round, the three bulbs are [on, off, off]. So you should
 *         return 1 because there is only one bulb is on.
 * 
 *         Example 2:
 * 
 *         Input: n = 0 Output: 0
 * 
 *         Example 3:
 * 
 *         Input: n = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         0 <= n <= 109
 *
 */

public class _319_BulbSwitcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Original Logic.
	/**
	 * 
	 * Logic : 1st round Bulb is ON, then each switch will OFF then ON. so for any
	 * number -> 1st round : ON 2nd round : OFF 3rd round : ON 4th round : OFF and
	 * so on .... If we can calculate how many times each bulb is switched we can
	 * tell it was ON/OFF at the last. from above we can see if we switch bulb EVEN
	 * times we get it OFF at last, if switch ODD times we get it ON at the last.**
	 * Now take few examples to understand better- N=1 -> bulb switched 1 time (for
	 * N=1) -> OFF at last
	 * 
	 * N=2 -> bulb switched 2 times (for N=1, 2) -> OFF at last
	 * 
	 * N=3 -> bulb switched 2 times (for N=1, 3) -> OFF at last
	 * 
	 * N=4 -> bulb switched 3 times (for N=1, 2, 4) -> ON at last
	 * 
	 * N=5 -> bulb switched 2 times (for N=1, 5) -> OFF at last
	 * 
	 * N=6 -> bulb switched 4 times (for N=1, 2, 3, 6) -> OFF at last
	 * 
	 * N=7 -> bulb switched 2 times (for N=1, 7) -> OFF at last
	 * 
	 * N=8 -> bulb switched 8* times (for N=1, 2, 4, 8) -> OFF at last (how we got 8
	 * switches - add all switches of N=1 (1 times at N=1), N=2 (2 times at N=1,2),
	 * N=4 (3 times at N=1,2,4) , N=8 (2 time at N=1,8))
	 * 
	 * N=9 -> bulb switched 3 times (for N=1, 3, 9) -> ON at last
	 * 
	 * As you can see from above we got a conclusion only bulbs which are perfect
	 * square will be left ON at the last. (4, 9, 16, 25....) rest all bulbs will be
	 * OFF at the last.
	 * 
	 * so we just need to fine how may perfact squares are present for any given N.
	 * if N=20 -> 2, 4, 9, 16 (total 4) if N=30 -> 2, 4, 9, 16, 25 (total 5)
	 * 
	 * so we can convert calculating how many perfact sqs to -> simple solution
	 * 
	 * Ans: round(sqrt(N));
	 * 
	 */
	public int bulbSwitch(int n) {
		int ans = 0;

		for (int i = 1; i * i <= n; i++) {
			ans++;
		}
		return ans;
	}

	// ======================================================================
	// Brute Force TLE.
	public int bulbSwitch1(int n) {
		int[] b = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			for (int j = i; j <= n; j += i) {
				b[j] = b[j] == 0 ? 1 : 0;
			}
		}
		int count = 0;

		for (int i = 1; i <= n; i++) {

			if (b[i] == 1) {
				count++;
			}
		}
		return count;
	}
}
