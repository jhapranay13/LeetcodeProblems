package leetcode.ForBiginners.Greedy.HashMultiSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *				
 *				1 2 3   4 5 6 7   8 9 10
 *				-----   -------   ------
 *         cinema has n rows of seats, numbered from 1 to n and there are ten
 *         seats in each row, labelled from 1 to 10 as shown in the figure
 *         above.
 * 
 *         Given the array reservedSeats containing the numbers of seats already
 *         reserved, for example, reservedSeats[i] = [3,8] means the seat
 *         located in row 3 and labelled with 8 is already reserved.
 * 
 *         Return the maximum number of four-person groups you can assign on the
 *         cinema seats. A four-person group occupies four adjacent seats in one
 *         single row. Seats across an aisle (such as [3,3] and [3,4]) are not
 *         considered to be adjacent, but there is an exceptional case on which
 *         an aisle split a four-person group, in that case, the aisle split a
 *         four-person group in the middle, which means to have two people on
 *         each side.
 * 
 * 
 * 
 *         Example 1:
 * 						1 2 3    4 5 6 7   8 9 10
 *                    1   X X    -------   X
 *                    2   ---------- X 
 *                    3 X -------------------- X 
 * 
 *         Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 *         Output: 4 Explanation: The figure above shows the optimal allocation
 *         for four groups, where seats mark with blue are already reserved and
 *         contiguous seats mark with orange are for one group. 
 *         
 *         Example 2:
 * 
 *         Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]] Output: 2 Example
 *         3:
 * 
 *         Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]] Output: 4
 * 
 * 
 *         Constraints:
 * 
 *         1 <= n <= 10^9 1 <= reservedSeats.length <= min(10*n, 10^4)
 *         reservedSeats[i].length == 2 1 <= reservedSeats[i][0] <= n 1 <=
 *         reservedSeats[i][1] <= 10 All reservedSeats[i] are distinct.
 *
 */

public class _1386_CinemaSeatAllocation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int maxNumberOfFamilies(int n, int[][] A) {
        int res=2*n;
        Map<Integer,Set<Integer>>map=new HashMap<>();
        
        for(int p[]:A){
            int r=p[0],c=p[1];
            if(!map.containsKey(r))map.put(r,new HashSet<>());
            map.get(r).add(c);
        }
        res-=2*map.size();
        
        for (Integer r:map.keySet()){
            Set<Integer>set=map.get(r);
            int cnt=0; 
            int mid=0;
            
            if (!set.contains(3)&&!set.contains(4)&&!set.contains(5)&&!set.contains(2)){
                cnt++;
            }
            
            if (!set.contains(6)&&!set.contains(7)&&!set.contains(8)&&!set.contains(9)){
                cnt++;
            }
            
            if (!set.contains(4)&&!set.contains(5)&&!set.contains(6)&&!set.contains(7)){
                mid++;
            }
            res+=Math.max(cnt,mid);
        }
        return res;
    }
}
