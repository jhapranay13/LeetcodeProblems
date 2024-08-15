package leetcode.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         You are given a string s and array queries where queries[i] = [lefti,
 *         righti, ki]. We may rearrange the substring s[lefti...righti] for
 *         each query and then choose up to ki of them to replace with any
 *         lowercase English letter.
 * 
 *         If the substring is possible to be a palindrome string after the
 *         operations above, the result of the query is true. Otherwise, the
 *         result is false.
 * 
 *         Return a boolean array answer where answer[i] is the result of the
 *         ith query queries[i].
 * 
 *         Note that each letter is counted individually for replacement, so if,
 *         for example s[lefti...righti] = "aaa", and ki = 2, we can only
 *         replace two of the letters. Also, note that no query modifies the
 *         initial string s.
 * 
 * 
 * 
 *         Example :
 * 
 *         Input: s = "abcda", queries =
 *         [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]] Output:
 *         [true,false,false,true,true] Explanation: queries[0]: substring =
 *         "d", is palidrome. queries[1]: substring = "bc", is not palidrome.
 *         queries[2]: substring = "abcd", is not palidrome after replacing only
 *         1 character. queries[3]: substring = "abcd", could be changed to
 *         "abba" which is palidrome. Also this can be changed to "baab" first
 *         rearrange it "bacd" then replace "cd" with "ab". queries[4]:
 *         substring = "abcda", could be changed to "abcba" which is palidrome.
 * 
 *         Example 2:
 * 
 *         Input: s = "lyb", queries = [[0,1,0],[2,2,1]] Output: [false,true]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length, queries.length <= 105 0 <= lefti <= righti < s.length
 *         0 <= ki <= s.length s consists of lowercase English letters.
 *
 */

public class _1177_CanMakePalindromeFromSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =====================================================================
	// PreSum Approach. Better Performance new Usage of PreSum
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		List<Boolean> ans = new ArrayList<>();
		int[][] preSum = new int[s.length() + 1][26];

		for (int i = 1; i <= s.length(); i++) {
			char ch = s.charAt(i - 1);

			for (int j = 0; j < 26; j++) {

				if (ch - 'a' == j) {
					preSum[i][j]++;
				}
				preSum[i][j] += preSum[i - 1][j];
			}
		}

		for (int[] query : queries) {
			int oddCount = 0;
			int lo = query[0];
			int hi = query[1];
			int change = query[2];

			for (int i = 0; i < 26; i++) {
				if ((preSum[hi + 1][i] - preSum[lo][i]) % 2 == 1) {
					oddCount++;
				}
			}
			ans.add(oddCount / 2 <= change);
		}
		return ans;
	}

	// ======================================================================
	// Binary Search Approach. Just Passes. Sometimes TLE
	public List<Boolean> canMakePaliQueries1(String s, int[][] queries) {
		List<Boolean> ans = new ArrayList<>();
		Map<Integer, List<Integer>> characterIndexMap = new HashMap<>();
		int index = 0;

		for (char ch : s.toCharArray()) {
			List<Integer> indexList = characterIndexMap.getOrDefault(ch - 'a', new ArrayList<>());
			indexList.add(index++);
			characterIndexMap.put(ch - 'a', indexList);
		}

		for (int[] query : queries) {
			int numOfOdd = 0;
			// Can also use for (key = 0; key < 26; key++) coz characters are 26
			for (int key : characterIndexMap.keySet()) {
				int left = binarySearchGreaterThanEqual(characterIndexMap.get(key), query[0]);
				int right = binarySearchLessThanEqual(characterIndexMap.get(key), query[1]);
				// if even 0 will be added if odd 1 will be added

				if (left >= 0 && right >= 0 && left <= right) {
					numOfOdd += (right - left + 1) % 2;
				}
			}

			if (numOfOdd / 2 <= query[2]) {
				ans.add(true);
			} else {
				ans.add(false);
			}
		}
		return ans;
	}

	private int binarySearchGreaterThanEqual(List<Integer> indexList, int target) {
		if (indexList == null) {
			return -1;
		}
		int hi = indexList.size() - 1;
		int lo = 0;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (indexList.get(pivot) < target) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}

		if (indexList.get(hi) >= target) {
			return hi;
		}
		return -1;
	}

	private int binarySearchLessThanEqual(List<Integer> indexList, int target) {
		if (indexList == null) {
			return -1;
		}
		int hi = indexList.size() - 1;
		int lo = 0;
		int ans = -1;

		while (lo <= hi) {
			int pivot = lo + (hi - lo) / 2;

			if (indexList.get(pivot) <= target) {
				ans = pivot;
				lo = pivot + 1;
			} else {
				hi = pivot - 1;
			}
		}

		if (ans >= 0 && indexList.get(ans) <= target) {
			return ans;
		}
		return ans;
	}
	//=====================================================================
	//Brute Force Approach
	public List<Boolean> canMakePaliQueries2(String str, int[][] queries) {
		List< Boolean > result = new ArrayList<Boolean>();

		if( str == null ) {

			for( int i = 0; i < queries.length; i++ ) {
				result.add( false );
			}
			return result;
		}
		
		for( int[] query : queries ) {
			int [] freqHolder = new int[ 26 ];
			int lo = query[ 0 ];
			int hi = query[ 1 ];
			int difference = query[ 2 ];
			
			for( int i = lo; i <= hi; i++ ) {
				char key = str.charAt( i );
				int pos = key - 'a';
				freqHolder[ pos ] = freqHolder[ pos ] == 0 ? 1 : 0;
			}
			int sum = 0;
			
			for( int i = 0; i < freqHolder.length; i++ ) {
				sum += freqHolder[ i ];
			}
			
			if( sum == 0 ) {
				result.add( true );
			} else if( sum - difference <= difference + 1 ) {
				result.add( true );
			} else {
				result.add( false );
			}
		}
		
		return result;
	}
}
