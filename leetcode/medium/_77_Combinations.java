package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]


Constraints:

1 <= n <= 20
1 <= k <= n
 *
 */


public class _77_Combinations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> combine1(int n, int k) {
		List< List< Integer > > ans = new ArrayList<>();
		ArrayList< Integer > p = new ArrayList<>();

		if( k == 1 ) {

			for( int i = 1; i <= n; i++ ) {
				p.add( i );
				ans.add( (List)p.clone() );
				p.remove( p.size() - 1 );
			}
			return ans;
		}
		recur1( ans, p, 1, n, k );
		return ans;
	}

	private void recur1( List< List< Integer > > ans, ArrayList< Integer > p, int val,
			int n, int k ) {

		if( val > n ) {
			return;
		}
		p.add( val );

		if( p.size() == k ) {
			ans.add( ( List< Integer > )p.clone() );
		} else {
			recur1( ans, p, val + 1, n, k );
		}
		p.remove( p.size() - 1 );
		recur1( ans, p, val + 1, n, k );
	}
	//================================================================================================

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> partial = new ArrayList<>();
		recur(ans, partial, n, k, 1);
		return ans;
	}

	private void recur(List<List<Integer>> ans, ArrayList<Integer> partial, int n, int k,
			int curr ) {
		if (k == 0) {
			ans.add((ArrayList<Integer>)partial.clone());
			return;
		}

		if (curr > n) {
			return;
		}
		partial.add(curr);
		recur(ans, partial, n, k - 1, curr + 1);
		partial.remove(partial.size() - 1);
		recur(ans, partial, n, k, curr + 1);
	}
}
