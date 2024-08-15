package leetcode.ForBiginners.BinarySearch.SimpleBinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers citations where citations[i] is the
 *         number of citations a researcher received for their ith paper and
 *         citations is sorted in an ascending order, return compute the
 *         researcher's h-index.
 * 
 *         According to the definition of h-index on Wikipedia: A scientist has
 *         an index h if h of their n papers have at least h citations each, and
 *         the other n - h papers have no more than h citations each.
 * 
 *         If there are several possible values for h, the maximum one is taken
 *         as the h-index.
 * 
 *         You must write an algorithm that runs in logarithmic time.
 * 
 *         Example 1:
 * 
 *         Input: citations = [0,1,3,5,6] Output: 3 Explanation: [0,1,3,5,6]
 *         means the researcher has 5 papers in total and each of them had
 *         received 0, 1, 3, 5, 6 citations respectively. Since the researcher
 *         has 3 papers with at least 3 citations each and the remaining two
 *         with no more than 3 citations each, their h-index is 3. 
 *         
 *         Example 2:
 * 
 *         Input: citations = [1,2,100] Output: 2
 * 
 * 
 *         Constraints:
 * 
 *         n == citations.length 1 <= n <= 105 0 <= citations[i] <= 1000
 *         citations is sorted in ascending order.
 *
 */

public class _275_HIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int hIndex(int[] citations) {
        int lo = 0;
        int hi = citations.length - 1;
        int length = citations.length;
        
        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (citations[pivot] == length - pivot) {
                return length - pivot;
            } else if (citations[pivot] < length - pivot) {
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return length - lo;
    }
    //=============================================================================================
    //Another Approach
    public int hIndex1(int[] citations) {

        if (citations.length == 1) {
            return citations[0] == 0 ? 0 : 1;
        }
        int lo = 0;
        int hi = citations.length - 1;

        if (citations[hi] == 0) {
            return 0;
        }
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (citations[pivot] >= citations.length - pivot) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return citations.length - hi;
    }
    //===============================================================================================
    //Another Approach
    public int hIndex2(int[] citations) {
        int lo = 0;
        int hi = citations.length - 1;
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (citations[pivot] >= citations.length - pivot) {
                ans = citations.length - pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
}
