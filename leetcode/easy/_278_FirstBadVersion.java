package leetcode.easy;

/**
 * 
 * @author Pranay Jha
 *
 *         You are a product manager and currently leading a team to develop a
 *         new product. Unfortunately, the latest version of your product fails
 *         the quality check. Since each version is developed based on the
 *         previous version, all the versions after a bad version are also bad.
 * 
 *         Suppose you have n versions [1, 2, ..., n] and you want to find out
 *         the first bad one, which causes all the following ones to be bad.
 * 
 *         You are given an API bool isBadVersion(version) which returns whether
 *         version is bad. Implement a function to find the first bad version.
 *         You should minimize the number of calls to the API.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: n = 5, bad = 4 Output: 4 Explanation: call isBadVersion(3) ->
 *         false call isBadVersion(5) -> true call isBadVersion(4) -> true Then
 *         4 is the first bad version. 
 *         
 *         Example 2:
 * 
 *         Input: n = 1, bad = 1 Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= bad <= n <= 231 - 1
 *
 */
/* The isBadVersion API is defined in the parent class VersionControl.
boolean isBadVersion(int version); */

public class _278_FirstBadVersion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int firstBadVersion(int n) {
        int lo = 1;
        int hi = n;
        
        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            
            if (isBadVersion(pivot)) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return hi;
    }

	private boolean isBadVersion(int pivot) {
		/* The isBadVersion API is defined in the parent class VersionControl.
		boolean isBadVersion(int version); */
		return false;
	}

    //=====================================================================================
    //Another Approach
    public int firstBadVersion1(int n) {
        int lo = 1;
        int hi = n;
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isBadVersion(pivot)) {
                ans = pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
}
