package leetcode.medium;

/**
 *
 * You are standing at position 0 on an infinite number line. There is a destination at position target.
 *
 * You can make some number of moves numMoves so that:
 *
 * On each move, you can either go left or right.
 * During the ith move (starting from i == 1 to i == numMoves), you take i steps in the chosen direction.
 * Given the integer target, return the minimum number of moves required (i.e., the minimum numMoves) to reach the destination.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the 1st move, we step from 0 to 1 (1 step).
 * On the 2nd move, we step from 1 to -1 (2 steps).
 * On the 3rd move, we step from -1 to 2 (3 steps).
 * Example 2:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the 1st move, we step from 0 to 1 (1 step).
 * On the 2nd move, we step from 1 to 3 (2 steps).
 *
 *
 * Constraints:
 *
 * -10^9 <= target <= 10^9
 * target != 0
 *
 */

public class _754_Reach_a_Number {

    // Step 1:
// If the target=-5.
// 0+-1+-2+-3+1=-5 steps=5
// OR if the target=5
// 0+1+2+3-1=5 steps=5
// In both the casses the steps are same thus to reduce the complexity we can Math.abs(target)::

// step 2:

// At each "i" (increasing by 1) add the value to a variable such that "sum<target"

// step 3:

    // Now if the "sum" == "target" then "sum-target%2" should be "0" and thus total steps is the answer.
// If the "sum-target" is not even then we increase the value of "sum" :
// For ex: target=5:
// sum=0+1+2+3="6">"5" (exitst the first loop) step=3:
// sum-target%2==0 (NO)
// increasing sum = 0+1+2+3+4 = "10-5%2==0" (NO) step=4:
// increasing sum = 0+1+2+3+4+5 = "15-5%2==0" (YES) step=5:
// Reason For (sum-target)%2==0
// 0+1+2+3+4+5, we can change the "+ve" to "-ve" from right to left and get the value like:
// 0-1+2+3-4-5 = 5
    public int reachNumber(int target) {
        int step=0;
        int sum=0;
        int i=1;
        target=Math.abs(target);

        while(sum<target){
            sum=sum+i;
            step++;
            i++;
        }

        if(sum==target)
            return step;

        while((sum-target)%2!=0){
            sum=sum+i;
            step++;
            i++;
        }
        return step;
    }
}
