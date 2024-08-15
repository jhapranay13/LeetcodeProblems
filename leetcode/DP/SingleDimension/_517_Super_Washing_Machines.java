package leetcode.DP.SingleDimension;

/**
 *
 *
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 *
 * For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
 *
 * Given an integer array machines representing the number of dresses in each washing machine from left to right on the line, return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: machines = [1,0,5]
 * Output: 3
 * Explanation:
 * 1st move:    1     0 <-- 5    =>    1     1     4
 * 2nd move:    1 <-- 1 <-- 4    =>    2     1     3
 * 3rd move:    2     1 <-- 3    =>    2     2     2
 * Example 2:
 *
 * Input: machines = [0,3,0]
 * Output: 2
 * Explanation:
 * 1st move:    0 <-- 3     0    =>    1     2     0
 * 2nd move:    1     2 --> 0    =>    1     1     1
 * Example 3:
 *
 * Input: machines = [0,2,0]
 * Output: -1
 * Explanation:
 * It's impossible to make all three washing machines have the same number of dresses.
 *
 *
 * Constraints:
 *
 * n == machines.length
 * 1 <= n <= 10^4
 * 0 <= machines[i] <= 10^5
 *
 *
 */

public class _517_Super_Washing_Machines {
    public int findMinMoves(int[] machines) {
        int sum = 0;

        for (int machine : machines) {
            sum += machine;
        }

        if (sum % machines.length != 0) {
            return -1;
        }
        int average = sum / machines.length;
        int[] leftToRight = new int[machines.length];

        for (int i = 1; i < machines.length; i++) {
            leftToRight[i] = leftToRight[i - 1] + machines[i - 1];
        }
        int[] rightToLeft = new int[machines.length];

        for (int i = machines.length - 2; i >= 0; i--) {
            rightToLeft[i] = rightToLeft[i + 1] + machines[i + 1];
        }
        int totalMoves = 0;
        int n = machines.length;
        // What is expected and what is not there and what is required from the other side
        for(int i = 0 ; i< n ; i++){
            int expectedLeftSum = average * i ;
            int expectedRightSum = average * (n-i-1);
            int leftPasses = 0 , rightPasses = 0 ;

            if(expectedLeftSum > leftToRight[i]) {
                leftPasses = expectedLeftSum - leftToRight[i];
            }

            if (expectedRightSum > rightToLeft[i]) {
                rightPasses = expectedRightSum - rightToLeft[i];
            }
            totalMoves = Math.max(totalMoves, leftPasses + rightPasses);
        }
        return totalMoves;
    }
}
