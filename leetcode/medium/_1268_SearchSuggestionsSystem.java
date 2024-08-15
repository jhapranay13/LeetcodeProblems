package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of strings products and a string searchWord. We want
 *         to design a system that suggests at most three product names from
 *         products after each character of searchWord is typed. Suggested
 *         products should have common prefix with the searchWord. If there are
 *         more than three products with a common prefix return the three
 *         lexicographically minimums products.
 * 
 *         Return list of lists of the suggested products after each character
 *         of searchWord is typed.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
 *         searchWord = "mouse" Output: [ ["mobile","moneypot","monitor"],
 *         ["mobile","moneypot","monitor"], ["mouse","mousepad"],
 *         ["mouse","mousepad"], ["mouse","mousepad"] ] Explanation: products
 *         sorted lexicographically =
 *         ["mobile","moneypot","monitor","mouse","mousepad"] After typing m and
 *         mo all products match and we show user
 *         ["mobile","moneypot","monitor"] After typing mou, mous and mouse the
 *         system suggests ["mouse","mousepad"]
 * 
 *         Example 2:
 * 
 *         Input: products = ["havana"], searchWord = "havana" Output:
 *         [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * 
 * 
 *         Example 3:
 * 
 *         Input: products = ["bags","baggage","banner","box","cloths"],
 *         searchWord = "bags" Output:
 *         [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * 
 *         Example 4:
 * 
 *         Input: products = ["havana"], searchWord = "tatiana" Output:
 *         [[],[],[],[],[],[],[]]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= products.length <= 1000 There are no repeated elements in
 *         products. 1 <= Sum products[i].length <= 2 * 10^4 All characters of
 *         products[i] are lower-case English letters. 1 <= searchWord.length <=
 *         1000 All characters of searchWord are lower-case English letters.
 *
 */

public class _1268_SearchSuggestionsSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		Map<Character, Node> map = new HashMap<>();
		boolean eow = false;
		PriorityQueue<String> pq = new PriorityQueue<>();
	}

	class Tries {
		Node root = new Node();

		public void add(String str) {
			Node curr = root;

			for (char ch : str.toCharArray()) {
				Node next = null;

				if (curr.map.containsKey(ch)) {
					next = curr.map.get(ch);
				} else {
					next = new Node();
					curr.map.put(ch, next);
				}
				next.pq.offer(str);
				curr = next;
			}
			curr.eow = true;
		}

		public List<List<String>> search(String word) {
			Node curr = root;
			List<List<String>> ans = new ArrayList<>();

			for (char ch : word.toCharArray()) {
				Node next = null;

				if (curr != null && curr.map.containsKey(ch)) {
					next = curr.map.get(ch);
				}
				List<String> holder = new ArrayList<>();
				int count = 3;

				while (count-- > 0) {
					if (next != null && !next.pq.isEmpty()) {
						holder.add(next.pq.poll());
					}
				}
				ans.add(holder);
				curr = next;
			}
			return ans;
		}
	}

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Tries tries = new Tries();

		for (String product : products) {
			tries.add(product);
		}
		return tries.search(searchWord);
	}
}
