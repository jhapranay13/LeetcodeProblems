package leetcode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _140_WordBreak2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ======================================================================
	// Recursive approach
	public List<String> wordBreak(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		List<String> ans = recur(s, dict, 0);
		return ans;
	}

	private List<String> recur(String s, Set<String> dict, int index) {
		List<String> ans = new ArrayList<>();

		if (index == s.length()) {
			ans.add("");
			return ans;
		}

		for (int i = index + 1; i <= s.length(); i++) {
			String str = s.substring(index, i);

			if (dict.contains(str)) {
				List<String> temp = recur(s, dict, i);

				for (String cStr : temp) {
					String line = str + " " + cStr;
					ans.add(line.trim());
				}
			}
		}
		return ans;
	}

	// ======================================================================
	// Top down HashMap Approach
	public List<String> wordBreak1(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		Map<Integer, List<String>> memo = new HashMap<>();
		List<String> ans = recur(s, dict, 0, memo);
		return ans;
	}

	private List<String> recur(String s, Set<String> dict, int index, Map<Integer, List<String>> memo) {
		List<String> ans = new ArrayList<>();

		if (index == s.length()) {
			ans.add("");
			return ans;
		}

		if (memo.containsKey(index)) {
			return memo.get(index);
		}

		for (int i = index + 1; i <= s.length(); i++) {
			String str = s.substring(index, i);

			if (dict.contains(str)) {
				List<String> temp = recur(s, dict, i, memo);

				for (String cStr : temp) {
					String line = str + " " + cStr;
					ans.add(line.trim());
				}
			}
		}
		memo.put(index, ans);
		return ans;
	}

	// ========================================================================
	// Bottom up Approach
	public List<String> wordBreak2(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		Map<Integer, List<String>> memo = new HashMap<>();
		List<String> holder = new ArrayList<>();
		holder.add("");
		memo.put(s.length(), holder);

		for (int index = s.length() - 1; index >= 0; index--) {
			List<String> ans = new ArrayList<>();

			for (int i = index + 1; i <= s.length(); i++) {
				String str = s.substring(index, i);

				if (dict.contains(str)) {
					List<String> temp = memo.get(i);

					for (String cStr : temp) {
						String line = str + " " + cStr;
						ans.add(line.trim());
					}
				}
			}
			memo.put(index, ans);
		}
		return memo.get(0);
	}
}
