package leetcode.ForBiginners.DynamicProgramming.MatrixMultiplication;

/**
 * 
 * @author Pranay Jha
 *
 *         You have a convex n-sided polygon where each vertex has an integer
 *         value. You are given an integer array values where values[i] is the
 *         value of the ith vertex (i.e., clockwise order).
 * 
 *         You will triangulate the polygon into n - 2 triangles. For each
 *         triangle, the value of that triangle is the product of the values of
 *         its vertices, and the total score of the triangulation is the sum of
 *         these values over all n - 2 triangles in the triangulation.
 * 
 *         Return the smallest possible total score that you can achieve with
 *         some triangulation of the polygon.
 * 
 * 
 * 
 *         Example 1:
 * 							2
 * 						   / \
 * 						  1---3	
 * 								
 *         Input: values = [1,2,3] Output: 6 Explanation: The polygon is already
 *         triangulated, and the score of the only triangle is 6. 
 *         
 *         Example 2:
 * 						3-----7
 * 						|   / |
 * 						|  /  |
 * 						| /   |	
 * 						|/    |
 * 						5-----4
 * 
 *         Input: values = [3,7,4,5] Output: 144 Explanation: There are two
 *         triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 +
 *         3*4*7 = 144. The minimum score is 144. 
 *         
 *         Example 3:
 * 					
 *    					1-4
 *    				   /|\ \
 *                    / | \ \
 *                   3  |  \ 1
 *                   \  |  / /
 *                    \ | / /	
 *                     \|/ /
 *                      1-5
 *         Input: values = [1,3,1,4,1,5] Output: 13 Explanation: The minimum
 *         score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 * 
 * 
 *         Constraints:
 * 
 *         n == values.length 3 <= n <= 50 1 <= values[i] <= 100
 *
 */

public class _1039_MinimumScoreTriangulationOfPolygon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down approach
	public int minScoreTriangulation(int[] values) {
		int memo[][] = new int[values.length][values.length];
		int ans = recur(values, 0, values.length - 1, memo);
		return ans;
	}

	private int recur(int[] values, int lo, int hi, int[][] memo) {

		if (hi - lo < 2) {
			return 0;
		}

		if (hi - lo == 2) {
			return values[lo] * values[lo + 1] * values[hi];
		}

		if (memo[lo][hi] > 0) {
			return memo[lo][hi];
		}
		int ans = Integer.MAX_VALUE;

		for (int i = lo + 1; i < hi; i++) {
			int val = values[lo] * values[i] * values[hi];
			int left = recur(values, lo, i, memo);
			int right = recur(values, i, hi, memo);
			ans = Math.min(ans, val + left + right);
		}
		return memo[lo][hi] = ans;
	}
	//============================================================================
	//Bottom up approach
	public int minScoreTriangulation1(int[] values) {
        int memo[][] = new int[values.length + 1][values.length + 1];
        for (int h = 0; h <= values.length; h++) {
            for (int lo = 0; lo <= values.length - h; lo++) {
                int hi = lo + h - 1;
                
                if (hi - lo < 2) {
                    continue;
                }
                
                if (hi - lo == 2) {
                    memo[lo][hi] = values[lo] * values[lo + 1] * values[hi];
                    continue;
                }
                int ans = Integer.MAX_VALUE;
        
                for (int i = lo + 1; i < hi; i++) {
                    int val = values[lo] * values[i] * values[hi];
                    int left = memo[lo][i];
                    int right = memo[i][hi];
                    ans = Math.min(ans, val + left + right);
                }
                memo[lo][hi] = ans;
            }
        }
        return memo[0][values.length - 1];
    }
}
