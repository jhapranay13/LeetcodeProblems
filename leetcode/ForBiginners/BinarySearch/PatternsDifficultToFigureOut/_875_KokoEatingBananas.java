package leetcode.ForBiginners.BinarySearch.PatternsDifficultToFigureOut;

/**
 * 
 * @author Pranay Jha
 *
 *         Koko loves to eat bananas. There are n piles of bananas, the ith pile
 *         has piles[i] bananas. The guards have gone and will come back in h
 *         hours.
 * 
 *         Koko can decide her bananas-per-hour eating speed of k. Each hour,
 *         she chooses some pile of bananas and eats k bananas from that pile.
 *         If the pile has less than k bananas, she eats all of them instead and
 *         will not eat any more bananas during this hour.
 * 
 *         Koko likes to eat slowly but still wants to finish eating all the
 *         bananas before the guards return.
 * 
 *         Return the minimum integer k such that she can eat all the bananas
 *         within h hours.
 * 
 *         Example 1:
 * 
 *         Input: piles = [3,6,7,11], h = 8 Output: 4 
 *         
 *         Example 2:
 * 
 *         Input: piles = [30,11,23,4,20], h = 5 Output: 30 
 *         
 *         Example 3:
 * 
 *         Input: piles = [30,11,23,4,20], h = 6 Output: 23
 * 
 * 
 *         Constraints:
 * 
 *         1 <= piles.length <= 104 piles.length <= h <= 109 1 <= piles[i] <=
 *         109
 *
 */

public class _875_KokoEatingBananas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Binary Search
	public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int hi = Integer.MIN_VALUE;
        
        for (int pile : piles) {
            lo = Math.min(lo, pile);
            hi = Math.max(hi, pile);
        }
        
        while (lo < hi) {
            int pivot = (lo + hi) / 2;
            
            if (!isPossible(piles, pivot, h)) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return lo;
    }
    
    private boolean isPossible(int[] piles, int pivot, int h) {
        int hoursTaken = 0;
        
        for (int pile : piles) {
            hoursTaken += (pile - 1) / pivot + 1;
        }
        return hoursTaken <= h;
    }
    //=============================================================================================
    //Another approach
    public int minEatingSpeed1(int[] piles, int h) {

        int ans = 0;
        int lo = 1;
        int hi = 0;

        for (int n : piles) {
            hi = Math.max(hi, n);
            lo = Math.min(lo, n);
        }

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (check(piles, h, pivot)) {
                ans = pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }

    private boolean check(int[] piles, int h, int pivot) {
        int timeTaken = 0;

        for (int i = 0; i < piles.length; i++) {
            timeTaken += piles[i] / pivot;

            if (piles[i] % pivot != 0) {
                timeTaken++;
            }

            if (timeTaken > h) {
                return false;
            }
        }
        return true;
    }
}
