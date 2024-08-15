package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Alex and Lee play a game with piles of stones. There are an even
 *         number of piles arranged in a row, and each pile has a positive
 *         integer number of stones piles[i].
 * 
 *         The objective of the game is to end with the most stones. The total
 *         number of stones is odd, so there are no ties.
 * 
 *         Alex and Lee take turns, with Alex starting first. Each turn, a
 *         player takes the entire pile of stones from either the beginning or
 *         the end of the row. This continues until there are no more piles
 *         left, at which point the person with the most stones wins.
 * 
 *         Assuming Alex and Lee play optimally, return True if and only if Alex
 *         wins the game.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: piles = [5,3,4,5] Output: true Explanation: Alex starts first,
 *         and can only take the first 5 or the last 5. Say he takes the first
 *         5, so that the row becomes [3, 4, 5]. If Lee takes 3, then the board
 *         is [4, 5], and Alex takes 5 to win with 10 points. If Lee takes the
 *         last 5, then the board is [3, 4], and Alex takes 4 to win with 9
 *         points. This demonstrated that taking the first 5 was a winning move
 *         for Alex, so we return true.
 * 
 * 
 *         Constraints:
 * 
 *         2 <= piles.length <= 500 piles.length is even. 1 <= piles[i] <= 500
 *         sum(piles) is odd.
 *
 */

public class _877_StoneGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=====================================================================
	//Top Down
	public boolean stoneGame(int[] piles) {
        int[][] memo = new int[piles.length][piles.length];
        return recur(piles, 0, piles.length - 1, memo) >= 0;
    }
    //The entire logic is that if A and B are opponents and we can caluclate the difference 
    //between the points of A and B we can know who won if A has more pints he wins.
    //If B has more points B will win. By subtraacting and adding since two negative becomes 
    //positive. We can get to the precise answer.
    public int recur(int[] piles, int lo, int hi, int[][] memo) {
        if (lo == hi) {
            return piles[lo];
        }
        
        if(memo[lo][hi] > 0) {
            return memo[lo][hi];
        }
        int x = piles[lo] - recur(piles, lo + 1, hi, memo);
        int y = piles[hi] - recur(piles, lo, hi - 1, memo);
        return memo[lo][hi] = Math.max(x, y);
    }
	//=====================================================================
	//Bottom up
	public boolean stoneGame4(int[] piles) {
        int[][] memo = new int[piles.length + 1][piles.length + 1];
        
        for (int i = 0; i < piles.length; i++) {
            memo[i][i] = piles[i];
        }
        
        for (int lo = piles.length - 1; lo >= 0; lo--) {
            for (int hi = 0; hi < piles.length; hi++) {
                
                if (hi <= lo) {
                    continue;
                }
                int x = piles[lo] - memo[lo + 1][hi];
                int y = piles[hi] - memo[lo][hi - 1];
                memo[lo][hi] = Math.max(x, y);        
            }
        }
        return memo[0][piles.length - 1] >= 0;
    }
	// ======================================================================
	// Different Memo using state
	public boolean stoneGame5(int[] piles) {
		int[][][] memo = new int[piles.length][piles.length][2];
		return recur5(piles, 0, piles.length - 1, 0, memo) >= 0;
	}

	// The entire logic is that if A and B are opponents and we can caluclate the
	// difference
	// between the points of A and B we can know who won if A has more pints he
	// wins.
	// If B has more points B will win. By subtraacting and adding since two
	// negative becomes
	// positive. We can get to the precise answer.
	public int recur5(int[] piles, int lo, int hi, int state, int memo[][][]) {
		if (lo == hi) {
			return piles[lo];
		}

		if (memo[lo][hi][state] > 0) {
			return memo[lo][hi][state];
		}
		int alexSum = 0;
		int leeSum = Integer.MAX_VALUE;

		if (state == 0) {
			int alexX = piles[lo] - recur5(piles, lo + 1, hi, state ^ 1, memo);
			int alexY = piles[hi] - recur5(piles, lo, hi - 1, state ^ 1, memo);
			return memo[lo][hi][state] = Math.max(alexX, alexY);
		} else {
			int leeX = piles[lo] - recur5(piles, lo + 1, hi, state ^ 1, memo);
			int leeY = piles[hi] - recur5(piles, lo, hi - 1, state ^ 1, memo);
			return memo[lo][hi][state] = Math.min(leeX, leeY);
		}
	}

	// =======================================================================
	// HashMap memo
	public boolean stoneGame6(int[] piles) {
		Map<String, Integer> memo = new HashMap<>();
		return recur6(piles, 0, piles.length - 1, 0, memo) >= 0;
	}

	// Logic is that when alex choses then Lee will chode the next one and vice
	// versa. So if we minimize what //lee choses we can see if alex wins or not by
	// subtracting the value being returned. If Alex value is above
	// zero he wins
	public int recur6(int[] piles, int lo, int hi, int state, Map<String, Integer> memo) {
		if (lo == hi) {
			return piles[lo];
		}
		String key = lo + " " + hi + " " + state;

		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		int alexSum = 0;
		int leeSum = Integer.MAX_VALUE;

		if (state == 0) {
			int alexX = piles[lo] - recur6(piles, lo + 1, hi, state ^ 1, memo);
			int alexY = piles[hi] - recur6(piles, lo, hi - 1, state ^ 1, memo);
			memo.put(key, Math.max(alexX, alexY));
			return Math.max(alexX, alexY);
		} else {
			int leeX = piles[lo] - recur6(piles, lo + 1, hi, state ^ 1, memo);
			int leeY = piles[hi] - recur6(piles, lo, hi - 1, state ^ 1, memo);
			memo.put(key, Math.min(leeX, leeY));
			// Math.max also works as lee should also play optimally
			return Math.min(leeX, leeY);
		}
	}
}
