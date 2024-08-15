package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an m x n board of characters and a list of strings words,
 *         return all words on the board.
 * 
 *         Each word must be constructed from letters of sequentially adjacent
 *         cells, where adjacent cells are horizontally or vertically
 *         neighboring. The same letter cell may not be used more than once in a
 *         word.
 * 
 * 
 * 
 *         Example 1:
 * 					----------
 * 					["o","a" |,"a","n"],
 * 					 ------- |---------
 * 					["e",|"t"|,"a","e"|],
 * 						 --------------				
 * 					["i",|"h"|,"k","r"],
 * 						 -----
 * 					["i","f","l","v"]
 * 
 *         Input: board =
 *         [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 *         words = ["oath","pea","eat","rain"] Output: ["eat","oath"] Example 2:
 * 
 * 
 *         Input: board = [["a","b"],["c","d"]], words = ["abcb"] Output: []
 * 
 * 
 *         Constraints:
 * 
 *         m == board.length n == board[i].length 1 <= m, n <= 12 board[i][j] is
 *         a lowercase English letter. 1 <= words.length <= 3 * 104 1 <=
 *         words[i].length <= 10 words[i] consists of lowercase English letters.
 *         All the strings of words are unique.
 *
 */

public class _212_WordSearch2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		Map<Character, Node> children = new HashMap<>();
		boolean eof = false;
	}

	class Trie {
		Node root;

		public void insert(String s) {

			if (root == null) {
				root = new Node();
			}
			Node curr = root;

			for (Character ch : s.toCharArray()) {
				Map<Character, Node> children = curr.children;

				if (!children.containsKey(ch)) {
					Node temp = new Node();
					children.put(ch, temp);
				}
				curr = children.get(ch);
			}
			curr.eof = true;
		}
	}

	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();

		for (String w : words) {
			trie.insert(w);
		}
		List<String> ans = new ArrayList<>();
		int[][] v = new int[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {

			for (int j = 0; j < board[0].length; j++) {
				Node node = trie.root;
				StringBuilder p = new StringBuilder();
				dfs(board, node, v, i, j, ans, p);
			}
		}
		return ans;
	}

	private void dfs(char[][] board, Node node, int[][] v, int i, int j, List<String> ans, StringBuilder p) {

		if (node == null) {
			return;
		}
		char ch = board[i][j];
		Map<Character, Node> children = node.children;
		v[i][j] = 1;
		p.append(ch);

		if (children.containsKey(ch)) {
			Node nextNode = children.get(ch);

			if (nextNode.eof) {
				ans.add(p.toString());
				nextNode.eof = false;
			}
			for (int[] dir : dirs) {
				int nr = i + dir[0];
				int nc = j + dir[1];

				if (nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length || v[nr][nc] == 1) {
					continue;
				}
				dfs(board, nextNode, v, nr, nc, ans, p);
			}
		}
		p.deleteCharAt(p.length() - 1);
		v[i][j] = 0;
	}

	private int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
}
