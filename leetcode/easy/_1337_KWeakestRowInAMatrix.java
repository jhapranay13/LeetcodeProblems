package leetcode.easy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n binary matrix mat of 1's (representing
 *         soldiers) and 0's (representing civilians). The soldiers are
 *         positioned in front of the civilians. That is, all the 1's will
 *         appear to the left of all the 0's in each row.
 * 
 *         A row i is weaker than a row j if one of the following is true:
 * 
 *         The number of soldiers in row i is less than the number of soldiers
 *         in row j. Both rows have the same number of soldiers and i < j.
 *         Return the indices of the k weakest rows in the matrix ordered from
 *         weakest to strongest.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: mat = [[1,1,0,0,0], [1,1,1,1,0], [1,0,0,0,0], [1,1,0,0,0],
 *         [1,1,1,1,1]], k = 3 Output: [2,0,3] Explanation: The number of
 *         soldiers in each row is: - Row 0: 2 - Row 1: 4 - Row 2: 1 - Row 3: 2
 *         - Row 4: 5 The rows ordered from weakest to strongest are
 *         [2,0,3,1,4]. 
 *         Example 2:
 * 
 *         Input: mat = [[1,0,0,0], [1,1,1,1], [1,0,0,0], [1,0,0,0]], k = 2
 *         Output: [0,2] Explanation: The number of soldiers in each row is: -
 *         Row 0: 1 - Row 1: 4 - Row 2: 1 - Row 3: 1 The rows ordered from
 *         weakest to strongest are [0,2,3,1].
 * 
 * 
 *         Constraints:
 * 
 *         m == mat.length n == mat[i].length 2 <= n, m <= 100 1 <= k <= m
 *         matrix[i][j] is either 0 or 1.
 *
 */

public class _1337_KWeakestRowInAMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[] kWeakestRows(int[][] mat, int k) {
        Map<Integer, Integer> zeroPosMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (zeroPosMap.get(x) == zeroPosMap.get(y)) {
                    return x - y;
                }
                return zeroPosMap.get(x) - zeroPosMap.get(y);
            }
        });
        int index = 0;
        
        for (int[] m : mat) {
            //If there are no zeroes binary search will still return last index
        	//So checking for that condition.
            if (m[m.length - 1] == 1) {
                zeroPosMap.put(index, m.length);
                pq.offer(index++);   
                continue;
            }
            int indexOfZero = binarySearch(m);										
            zeroPosMap.put(index, indexOfZero);
            pq.offer(index++);
        }
        int ans[] = new int[k];
        index = 0;
        
        while (k-- > 0) {
            ans[index++] = pq.poll();    
        }
        return ans;
    }
    
    private int binarySearch(int[] m) {
        int lo = 0;
        int hi = m.length - 1;
        
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (m[pivot] == 1) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return hi;
    }
}
