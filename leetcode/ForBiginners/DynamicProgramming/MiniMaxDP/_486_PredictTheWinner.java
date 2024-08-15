package leetcode.ForBiginners.DynamicProgramming.MiniMaxDP;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an integer array nums. Two players are playing a game
 *         with this array: player 1 and player 2.
 * 
 *         Player 1 and player 2 take turns, with player 1 starting first. Both
 *         players start the game with a score of 0. At each turn, the player
 *         takes one of the numbers from either end of the array (i.e., nums[0]
 *         or nums[nums.length - 1]) which reduces the size of the array by 1.
 *         The player adds the chosen number to their score. The game ends when
 *         there are no more elements in the array.
 * 
 *         Return true if Player 1 can win the game. If the scores of both
 *         players are equal, then player 1 is still the winner, and you should
 *         also return true. You may assume that both players are playing
 *         optimally.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,5,2] Output: false Explanation: Initially, player 1
 *         can choose between 1 and 2. If he chooses 2 (or 1), then player 2 can
 *         choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will
 *         be left with 1 (or 2). So, final score of player 1 is 1 + 2 = 3, and
 *         player 2 is 5. Hence, player 1 will never be the winner and you need
 *         to return false.
 * 
 *         Example 2:
 * 
 *         Input: nums = [1,5,233,7] Output: true Explanation: Player 1 first
 *         chooses 1. Then player 2 has to choose between 5 and 7. No matter
 *         which number player 2 choose, player 1 can choose 233. Finally,
 *         player 1 has more score (234) than player 2 (12), so you need to
 *         return True representing player1 can win.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 20 0 <= nums[i] <= 107
 *
 */

public class _486_PredictTheWinner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =======================================================================
	// Recursive Approach
	public boolean PredictTheWinner(int[] nums) {
		return recur(nums, 0, nums.length - 1, 1) >= 0;
	}

	public int recur(int[] nums, int lo, int hi, int turn) {
		if (lo == hi) {
			return turn * nums[lo];
		}
		// turn represent first or 2nd player. First player is added 2nd is substracted
		int x = turn * nums[lo] + recur(nums, lo + 1, hi, -turn);
		int y = turn * nums[hi] + recur(nums, lo, hi - 1, -turn);
		return turn * Math.max(turn * x, turn * y);
	}

	// ========================================================================
	// Recursive No need of turn
	public boolean PredictTheWinner1(int[] nums) {
		return recur1(nums, 0, nums.length - 1) >= 0;
	}

	public int recur1(int[] nums, int lo, int hi) {
		if (lo == hi) {
			return nums[lo];
		}
		int x = nums[lo] - recur1(nums, lo + 1, hi);
		int y = nums[hi] - recur1(nums, lo, hi - 1);
		return Math.max(x, y);
	}
	//=======================================================================
	//Top Down Approach
	public boolean PredictTheWinner2(int[] nums) {
        int memo[][] = new int[nums.length][nums.length];
        return recur2(nums, 0, nums.length - 1, memo) >= 0;
    }
    
    public int recur2(int[] nums, int lo, int hi, int[][] memo) {
        if (lo == hi) {
            return nums[lo];
        }
        
        if (memo[lo][hi] > 0) {
            return memo[lo][hi];
        }
        int x = nums[lo] - recur2(nums, lo + 1, hi, memo);
        int y = nums[hi] - recur2(nums, lo, hi - 1, memo);
        return memo[lo][hi] = Math.max(x, y);
    }
	
}
