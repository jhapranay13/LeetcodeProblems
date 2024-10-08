package leetcode.Graph.AccountMerge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a list of accounts where each element accounts[i] is a list of
 *         strings, where the first element accounts[i][0] is a name, and the
 *         rest of the elements are emails representing emails of the account.
 * 
 *         Now, we would like to merge these accounts. Two accounts definitely
 *         belong to the same person if there is some common email to both
 *         accounts. Note that even if two accounts have the same name, they may
 *         belong to different people as people could have the same name. A
 *         person can have any number of accounts initially, but all of their
 *         accounts definitely have the same name.
 * 
 *         After merging the accounts, return the accounts in the following
 *         format: the first element of each account is the name, and the rest
 *         of the elements are emails in sorted order. The accounts themselves
 *         can be returned in any order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: accounts =
 *         [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *         Output:
 *         [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *         Explanation: The first and third John's are the same person as they
 *         have the common email "johnsmith@mail.com". The second John and Mary
 *         are different people as none of their email addresses are used by
 *         other accounts. We could return these lists in any order, for example
 *         the answer [['Mary', 'mary@mail.com'], ['John',
 *         'johnnybravo@mail.com'], ['John', 'john00@mail.com',
 *         'john_newyork@mail.com', 'johnsmith@mail.com']] would still be
 *         accepted. 
 *         
 *         Example 2:
 * 
 *         Input: accounts =
 *         [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 *         Output:
 *         [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= accounts.length <= 1000 2 <= accounts[i].length <= 10 1 <=
 *         accounts[i][j] <= 30 accounts[i][0] consists of English letters.
 *         accounts[i][j] (for j > 0) is a valid email.
 *
 */

public class _721_AccountsMerge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class UnionFind {
		int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size];

			for (int i = 0; i < size; i++) {
				this.parent[i] = i;
			}
		}

		public int find(int node) {
			if (this.parent[node] != node) {
				return find(this.parent[node]);
			}
			return node;
		}

		public void union(int node1, int node2) {
			int parent1 = find(node1);
			int parent2 = find(node2);

			if (parent1 != parent2) {
				this.parent[parent2] = parent1;
			}
		}
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> emailToNameMap = new HashMap<>();
		Map<String, Integer> emailToIndexMap = new HashMap<>();
		int index = 0;

		for (List<String> account : accounts) {
			String name = null;

			for (String str : account) {
				if (name == null) {
					name = str;
					continue;
				}
				emailToNameMap.put(str, name);

				if (!emailToIndexMap.containsKey(str)) {
					emailToIndexMap.put(str, index++);
				}
			}
		}
		UnionFind uf = new UnionFind(index);

		for (List<String> account : accounts) {
			String name = null;
			String prev = null;

			for (String str : account) {
				if (name == null) {
					name = str;
					continue;
				}

				if (prev == null) {
					prev = str;
					continue;
				}
				uf.union(emailToIndexMap.get(prev), emailToIndexMap.get(str));
			}
		}
		Map<Integer, List<String>> parentToEmailMap = new HashMap<>();

		for (String email : emailToIndexMap.keySet()) {
			int indx = emailToIndexMap.get(email);
			int parent = uf.find(indx);
			List<String> list = parentToEmailMap.getOrDefault(parent, new ArrayList<>());
			list.add(email);
			parentToEmailMap.put(parent, list);
		}
		List<List<String>> ans = new ArrayList<>();

		for (Map.Entry<Integer,List<String>> entry : parentToEmailMap.entrySet()) {
			List<String> list = entry.getValue();
			Collections.sort(list);
			String name = emailToNameMap.get(list.get(0));
			list.add(0, name);
			ans.add(list);
		}
		return ans;
	}
}
