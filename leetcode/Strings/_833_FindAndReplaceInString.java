package leetcode.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a 0-indexed string s that you must perform k
 *         replacement operations on. The replacement operations are given as
 *         three 0-indexed parallel arrays, indices, sources, and targets, all
 *         of length k.
 * 
 *         To complete the ith replacement operation:
 * 
 *         Check if the substring sources[i] occurs at index indices[i] in the
 *         original string s. If it does not occur, do nothing. Otherwise if it
 *         does occur, replace that substring with targets[i]. For example, if s
 *         = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee",
 *         then the result of this replacement will be "eeecd".
 * 
 *         All replacement operations must occur simultaneously, meaning the
 *         replacement operations should not affect the indexing of each other.
 *         The testcases will be generated such that the replacements will not
 *         overlap.
 * 
 *         For example, a testcase with s = "abc", indices = [0, 1], and sources
 *         = ["ab","bc"] will not be generated because the "ab" and "bc"
 *         replacements overlap. Return the resulting string after performing
 *         all replacement operations on s.
 * 
 *         A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * 
 *         Example 1: abcd / \ a cd \ | eeebffff
 * 
 *         Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets =
 *         ["eee", "ffff"] Output: "eeebffff" Explanation: "a" occurs at index 0
 *         in s, so we replace it with "eee". "cd" occurs at index 2 in s, so we
 *         replace it with "ffff".
 * 
 *         Example 2: abcd / \ a cd != ec \ eeecd
 * 
 *         Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets =
 *         ["eee","ffff"] Output: "eeecd" Explanation: "ab" occurs at index 0 in
 *         s, so we replace it with "eee". "ec" does not occur at index 2 in s,
 *         so we do nothing.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= s.length <= 1000 k == indices.length == sources.length ==
 *         targets.length 1 <= k <= 100 0 <= indexes[i] < s.length 1 <=
 *         sources[i].length, targets[i].length <= 50 s consists of only
 *         lowercase English letters. sources[i] and targets[i] consist of only
 *         lowercase English letters.
 *
 */

public class _833_FindAndReplaceInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
		Map<Integer, String[]> indexSourceTargetMap = new HashMap<>();

		for (int i = 0; i < indices.length; i++) {
			indexSourceTargetMap.put(indices[i], new String[] { sources[i], targets[i] });
		}
		StringBuilder ans = new StringBuilder();

		for (int index = 0; index < s.length(); index++) {

			if (indexSourceTargetMap.containsKey(index)) {
				String[] srcTarget = indexSourceTargetMap.get(index);
				String source = srcTarget[0];
				String target = srcTarget[1];
				boolean flag = true;
				int currIndex = index;

				for (int sourceIndex = 0; sourceIndex < source.length(); sourceIndex++) {
					if (s.charAt(currIndex) != source.charAt(sourceIndex)) {
						flag = false;
						break;
					}
					currIndex++;
				}
				if (flag) {
					ans.append(target);
					index = currIndex - 1;
				} else {
					ans.append(s.charAt(index));
				}
			} else {
				ans.append(s.charAt(index));
			}
		}
		return ans.toString();
	}
	//=============================================================================================
	//Another way
	public String findReplaceString1(String s, int[] indices, String[] sources, String[] targets) {
		Map<Integer, String[]> indexSourceTargetMap = new HashMap<>();

		for (int i = 0; i < indices.length; i++) {
			indexSourceTargetMap.put(indices[i], new String[] {sources[i], targets[i]});
		}
		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {

			if (indexSourceTargetMap.containsKey(i)) {
				String[] srcTarget = indexSourceTargetMap.get(i);
				String src = srcTarget[0];
				String target = srcTarget[1];
				String toMatch = s.substring(i, i + src.length());

				if (toMatch.equals(src)) {
					ans.append(target);
					i += toMatch.length() - 1;
					continue;
				}
			}
			ans.append(s.charAt(i));
		}
		return ans.toString();
	}
}
