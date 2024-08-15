package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Pranay Jha
 *
 *         A company is planning to interview 2n people. Given the array costs
 *         where costs[i] = [aCosti, bCosti], the cost of flying the ith person
 *         to city a is aCosti, and the cost of flying the ith person to city b
 *         is bCosti.
 * 
 *         Return the minimum cost to fly every person to a city such that
 *         exactly n people arrive in each city.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: costs = [[10,20],[30,200],[400,50],[30,20]] Output: 110
 *         Explanation: The first person goes to city A for a cost of 10. The
 *         second person goes to city A for a cost of 30. The third person goes
 *         to city B for a cost of 50. The fourth person goes to city B for a
 *         cost of 20.
 * 
 *         The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the
 *         people interviewing in each city.
 * 
 *         Example 2:
 * 
 *         Input: costs =
 *         [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]] Output:
 *         1859
 * 
 *         Example 3:
 * 
 *         Input: costs =
 *         [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 *         Output: 3086
 * 
 * 
 *         Constraints:
 * 
 *         2 * n == costs.length 2 <= costs.length <= 100 costs.length is even.
 *         1 <= aCosti, bCosti <= 1000
 *
 */

public class _1029_TwoCityScheduling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Sort Solution
	public int twoCitySchedCost(int[][] costs) {
        Arrays.sort( costs, new Comparator< int[] >() {
            
            public int compare( int[] i1, int[] i2 ) {
                return ( i1[ 0 ] - i1[ 1 ] ) - ( i2[ 0 ] - i2[ 1 ] );
            }
        } );
        int n = costs.length;
        int i = 0;
        int sum = 0;
        
        while( i < n / 2 ) {
            sum += costs[ i++ ][ 0 ];
        }
        
        while( i < n ) {
            sum+= costs[ i++ ][ 1 ];
        }
        return sum;
    }
	//=============================================================================
	//DP top down solution
	public int twoCitySchedCost1(int[][] costs) {
        int cost = dfs( costs, costs.length / 2, costs.length / 2, 0 );
        return cost;
    }
    
    public int dfs( int[][] costs, int lim1, int lim2, int idx ) {
        
        if( idx == costs.length ) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int costA = costs[ idx ][ 0 ];
        int costB = costs[ idx ][ 1 ];
        
        if( lim1 == 0 ) {
            min = costB + dfs( costs, lim1, lim2 - 1, idx + 1 );
        } else if( lim2 == 0 ) {
            min = costA + dfs( costs, lim1 - 1, lim2, idx + 1 );
        } else {
            costA += dfs( costs, lim1 - 1, lim2, idx + 1 );
            costB += dfs( costs, lim1, lim2 - 1, idx + 1 );
            min = Math.min( costA, costB );
        }
        return min;
    }
    //=============================================================================================
    //Another Approach Top Down
    public int twoCitySchedCost2(int[][] costs) {
        int[][][] memo = new int[costs.length / 2 + 1][costs.length / 2 + 1][costs.length];
        return recur(costs, costs.length / 2, costs.length / 2, 0, memo);
    }

    private int recur(int[][] costs, int len1, int len2, int index, int[][][] memo) {

        if (index == costs.length) {
            return 0;
        }

        if (memo[len1][len2][index] > 0) {
            return memo[len1][len2][index];
        }
        int cost1 = Integer.MAX_VALUE / 10;

        if (len1 != 0) {
            cost1 = costs[index][0] + recur(costs, len1 - 1, len2, index + 1, memo);
        }
        int cost2 = Integer.MAX_VALUE / 10;

        if (len2 != 0) {
            cost2 = costs[index][1] + recur(costs, len1, len2 - 1, index + 1, memo);
        }
        return memo[len1][len2][index] = Math.min(cost1, cost2);
    }
}
