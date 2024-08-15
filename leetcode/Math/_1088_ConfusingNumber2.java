package leetcode.Math;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Pranay Jha
 *
 *         A confusing number is a number that when rotated 180 degrees becomes
 *         a different number with each digit valid.
 * 
 *         We can rotate digits of a number by 180 degrees to form new digits.
 * 
 *         When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9,
 *         8, and 6 respectively. When 2, 3, 4, 5, and 7 are rotated 180
 *         degrees, they become invalid. Note that after rotating a number, we
 *         can ignore leading zeros.
 * 
 *         For example, after rotating 8000, we have 0008 which is considered as
 *         just 8. Given an integer n, return the number of confusing numbers in
 *         the inclusive range [1, n].
 * 
 *         Example 1:
 * 
 *         Input: n = 20 Output: 6 Explanation: The confusing numbers are
 *         [6,9,10,16,18,19]. 6 converts to 9. 9 converts to 6. 10 converts to
 *         01 which is just 1. 16 converts to 91. 18 converts to 81. 19 converts
 *         to 61. 
 *         
 *         Example 2:
 * 
 *         Input: n = 100 Output: 19 Explanation: The confusing numbers are
 *         [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 109
 *
 */

public class _1088_ConfusingNumber2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int confusingNumberII(int N) {
        Map< Long, Long > transform = new HashMap<>();
        transform.put( 1L, 1L );
        transform.put( 0L, 0L );
        transform.put( 6L, 9L );
        transform.put( 8L, 8L );
        transform.put( 9L, 6L );
        AtomicInteger count = new AtomicInteger( 0 );
        dfs( transform, count, 0, N );
        return count.intValue();
    }
    
    private void dfs( Map< Long, Long > transform, AtomicInteger count, long curr, int N ) {
        
        if( curr > N ) {
            return;
        }
        
        if( isValid( transform, curr ) ) {
            count.getAndIncrement();
        }
        
        for( long key : transform.keySet() ) {
            long next = ( curr * 10 + key );
            
            if( next <= curr ) {
                continue;
            }
            dfs( transform, count, next, N );
        }
    }
    
    private boolean isValid( Map< Long, Long > transform, long curr ) {
        
        if( transform.containsKey( curr ) && ( curr == 9 || curr == 6 ) ) {
            return true;
        } else if( transform.containsKey( curr ) ) {
            return false;
        }
        long reverse = 0;
        long run = curr;
        
        while( run != 0 ) {
            long temp = run % 10;
            
            if( !transform.containsKey( temp % 10 ) ) {
				return false;
			}
            reverse *= 10;
            reverse += transform.get( temp );
            run = run / 10;
        }
        return reverse < curr || reverse > curr;
    }
}
