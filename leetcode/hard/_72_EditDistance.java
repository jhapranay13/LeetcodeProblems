package leetcode.hard;

/**
 * 
 * @author Pranay Jha
 *
 *         Given two strings word1 and word2, return the minimum number of
 *         operations required to convert word1 to word2.
 * 
 *         You have the following three operations permitted on a word:
 * 
 *         Insert a character Delete a character Replace a character
 * 
 * 
 *         Example 1:
 * 
 *         Input: word1 = "horse", word2 = "ros" Output: 3 Explanation: horse ->
 *         rorse (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros
 *         (remove 'e') Example 2:
 * 
 *         Input: word1 = "intention", word2 = "execution" Output: 5
 *         Explanation: intention -> inention (remove 't') inention -> enention
 *         (replace 'i' with 'e') enention -> exention (replace 'n' with 'x')
 *         exention -> exection (replace 'n' with 'c') exection -> execution
 *         (insert 'u')
 * 
 * 
 *         Constraints:
 * 
 *         0 <= word1.length, word2.length <= 500 word1 and word2 consist of
 *         lowercase English letters.
 *
 */

public class _72_EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// levenshtein distance Top Down Approach
	public int minDistance(String word1, String word2) {
		int[][] memo = new int[word1.length()][word2.length()];
		return recur(word1, word2, 0, 0, memo);
	}

	private int recur(String word1, String word2, int index1, int index2, int[][] memo) {
		if (word1.length() == index1) {
			return word2.length() - index2;
		}

		if (word2.length() == index2) {
			return word1.length() - index1;
		}

		if (memo[index1][index2] > 0) {
			return memo[index1][index2];
		}
		int ans = 0;

		if (word1.charAt(index1) == word2.charAt(index2)) {
			ans = recur(word1, word2, index1 + 1, index2 + 1, memo);
		} else {
			int insert = recur(word1, word2, index1 + 1, index2, memo);
			int delete = recur(word1, word2, index1, index2 + 1, memo);
			int update = recur(word1, word2, index1 + 1, index2 + 1, memo);

			ans = 1 + Math.min(insert, Math.min(update, delete));
		}
		return memo[index1][index2] = ans;
	}

	// ===========================================================================
	// Bottom up approach
	public int minDistance1(String word1, String word2) {
		int[][] memo = new int[word1.length() + 1][word2.length() + 1];

		for (int i = 0; i < word2.length(); i++) {
			memo[word1.length()][i] = word2.length() - i;
		}

		for (int i = 0; i < word1.length(); i++) {
			memo[i][word2.length()] = word1.length() - i;
		}

		for (int index1 = word1.length() - 1; index1 >= 0; index1--) {
			for (int index2 = word2.length() - 1; index2 >= 0; index2--) {
				int ans = 0;

				if (word1.charAt(index1) == word2.charAt(index2)) {
					ans = memo[index1 + 1][index2 + 1];
				} else {
					int insert = memo[index1 + 1][index2];
					int delete = memo[index1][index2 + 1];
					int update = memo[index1 + 1][index2 + 1];

					ans = 1 + Math.min(insert, Math.min(update, delete));
				}
				memo[index1][index2] = ans;
			}
		}
		return memo[0][0];
	}

	// =====================================================================
	// Another Approch Top down
	private int recur1(String word1, String word2, int x, int y, int[][] memo) {

		if (x == word1.length()) {
			return word2.length() - y;
		}

		if (y == word2.length()) {
			return word1.length() - x;
		}

		if (memo[x][y] > 0) {
			return memo[x][y];
		}
		int replace = Integer.MAX_VALUE;
		int insert = Integer.MAX_VALUE;
		int delete = Integer.MAX_VALUE;
		int equal = Integer.MAX_VALUE;

		if (word1.charAt(x) != word2.charAt(y)) {
			replace = 1 + recur(word1, word2, x + 1, y + 1, memo);
			insert = 1 + recur(word1, word2, x, y + 1, memo);
			delete = 1 + recur(word1, word2, x + 1, y, memo);
		} else {
			equal = recur(word1, word2, x + 1, y + 1, memo);
		}

		int ans = Math.min(equal, Math.min(replace, Math.min(insert, delete)));
		return memo[x][y] = ans;
	}
	//======================================================================
	//Bottom up
	public int minDistance2(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        
        for (int x = word1.length(); x >= 0; x--) {
            
            for (int y = word2.length(); y >= 0; y--) {
                
                if (x == word1.length()) {
                    memo[x][y] = word2.length() - y;
                    continue;
                }
        
                if (y == word2.length()) {
                    memo[x][y] = word1.length() - x;
                    continue;
                }
                
                int replace = Integer.MAX_VALUE;
                int insert = Integer.MAX_VALUE;
                int delete = Integer.MAX_VALUE;
                int equal = Integer.MAX_VALUE;
        
                if (word1.charAt(x) != word2.charAt(y)) {
                    replace = 1 + memo[x + 1][y + 1];
                    insert = 1 + memo[x][y + 1];
                    delete = 1 + memo[x + 1][y];
                } else {
                    equal = memo[x + 1][y + 1];
                }
        
                int ans = Math.min(equal, Math.min(replace, Math.min(insert, delete)));
                memo[x][y] = ans;
            }
        }
        return memo[0][0];
    }
}
