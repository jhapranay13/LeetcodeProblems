package leetcode.BinarySearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an n x n matrix where each of the rows and columns are sorted
 *         in ascending order, return the kth smallest element in the matrix.
 * 
 *         Note that it is the kth smallest element in the sorted order, not the
 *         kth distinct element.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8 Output: 13
 *         Explanation: The elements in the matrix are
 *         [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13 Example
 *         2:
 * 
 *         Input: matrix = [[-5]], k = 1 Output: -5
 * 
 * 
 *         Constraints:
 * 
 *         n == matrix.length n == matrix[i].length 1 <= n <= 300 -109 <=
 *         matrix[i][j] <= 109 All the rows and columns of matrix are guaranteed
 *         to be sorted in non-decreasing order. 1 <= k <= n^2
 *
 */

public class _378_KthSmallestElementInASortedMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//======================================================================
	//Binary Search Implementation
	public int kthSmallest(int[][] matrix, int k) {
        int length = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[length - 1][length - 1];
        
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            //Smallest and Largest Value to triangulate.
            int[] smallLargeVal = new int[] {lo, hi};
            int count = countValueLessThanOrEqual(matrix, pivot, smallLargeVal);
            
            if (count == k) {
                return smallLargeVal[0];
            } else if (count < k) {
                lo = smallLargeVal[1];
            } else {
                hi = smallLargeVal[0];
            }
        }
        return lo;
    }
    
    private int countValueLessThanOrEqual(int[][] matrix, int pivot, int[] smallLargeVal) {
        int col = 0;
        int row = matrix.length - 1;
        int count = 0;
        
        while (col < matrix.length && row >= 0) {
            
            if (matrix[row][col] > pivot) {
                smallLargeVal[1] = Math.min(smallLargeVal[1], matrix[row][col]);
                row--;
            } else {
                smallLargeVal[0] = Math.max(smallLargeVal[0], matrix[row][col]);
                col++;
                count += row + 1;
            }
        }
        return count;
    }
	
	// ======================================================================
	// Heap Implementation. Similar to Merging K sorted Arrays 
	public int kthSmallest1(int[][] matrix, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] x, int[] y) {
				return x[0] - y[0];
			}
		});

		for (int i = 0; i < matrix.length; i++) {
			pq.offer(new int[] { matrix[i][0], i, 0 });
		}
		int length = matrix[0].length;
		int[] node = null;

		while (k-- > 0) {
			node = pq.poll();

			if (node[2] < length - 1) {
				pq.offer(new int[] { matrix[node[1]][node[2] + 1], node[1], node[2] + 1 });
			}
		}
		return node[0];
	}
    //=====================================================================
    //Binary serach different implemnetation
    public int kthSmallest3(int[][] matrix, int k) {
        int lo = matrix[0][0];
        int hi = matrix[matrix.length - 1][matrix[0].length - 1];
        int count = 0;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            count = getCount(matrix, pivot);

            if (count < k) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return hi;
    }
    //Get count for less than or equal to
    private int getCount(int[][] matrix, int pivot) {
        int r = matrix.length - 1;
        int c = 0;
        int count = 0;

        while( r >= 0 && c < matrix[0].length) {
            int val = matrix[r][c];

            if (val <= pivot) {
                c++;
                count += r + 1;
            } else {
                r--;
            }
        }
        return count;
    }
}
