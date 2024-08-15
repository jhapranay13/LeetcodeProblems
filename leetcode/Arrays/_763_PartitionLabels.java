package leetcode.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a string s. We want to partition the string into as
 *         many parts as possible so that each letter appears in at most one
 *         part.
 * 
 *         Return a list of integers representing the size of these parts.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: s = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation:
 *         The partition is "ababcbaca", "defegde", "hijhklij". This is a
 *         partition so that each letter appears in at most one part. A
 *         partition like "ababcbacadefegde", "hijhklij" is incorrect, because
 *         it splits s into less parts. 
 *         
 *         Example 2:
 * 
 *         Input: s = "eccbbbbdec" Output: [10]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 500 s consists of lowercase English letters.
 *
 */

public class _763_PartitionLabels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastPosMap = new HashMap<>();
        int index = 0;
        
        for (char ch : s.toCharArray()) {
            lastPosMap.put(ch, index++);
        }
        int lo = 0;
        int hi = 0;
        int anchor = lastPosMap.get(s.charAt(hi));
        List<Integer> ans = new ArrayList<>();
        
        while (hi < s.length()) {
            
            if (anchor == hi) {
                ans.add(hi - lo + 1);
                hi++;
                lo = hi;
                anchor = hi < s.length() ? lastPosMap.get(s.charAt(hi)) : -1;
                continue;
            }
            char ch = s.charAt(hi);
            int lastPos = lastPosMap.get(ch);
            anchor = Math.max(anchor, lastPos);
            hi++;
        }
        
        if (lo < hi) {
            ans.add(hi - lo + 1);
        }
        return ans;
    }
	//=============================================================================
	//Another Version
	public List<Integer> partitionLabels1(String S) {
        int lo = 0;
        int hi = 0;
        List< Integer > ans = new ArrayList<>();
        
        for( int i = 0; i < S.length(); i++ ) {
            char ch = S.charAt( i );
            int temp = S.lastIndexOf( ch );
            hi = Math.max( hi, temp );
            
            if( i == hi ) {
                ans.add( hi - lo + 1 );
                lo = hi + 1;
            }
        } 
        return ans;
    }
}
