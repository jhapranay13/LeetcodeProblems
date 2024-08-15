package leetcode.BackTrackingRecursion;

/**
 * 
 * @author Pranay Jha
 *
 *Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 
Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 
Follow up: Could you use search pruning to make your solution faster with a larger board?
 *
 */


public class _79_WordSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

public boolean exist(char[][] board, String word) {
        
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        char sChar = word.charAt(0);
        
        for (int i = 0; i < board.length; i++) {
            
            for (int j = 0; j < board[0].length; j++) {
                char bChar = board[i][j];
                
                if (sChar == bChar) {
                    int[][] visited = new int[board.length][board[0].length];
                    //Set<String> visited
                    if (recur(board, visited, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    private boolean recur(char[][] board, int[][] visited, String word, int index,
                         int row, int col) {
        //String key "row," + "col"
        if (row > board.length - 1 || col > board[0].length - 1 || row < 0 || col < 0 ||
           visited[row][col] == 1) {
        	//set visited.contains key check can also be used
            return false;
        }
        
        if (index == word.length() - 1) {
            return word.charAt(index) == board[row][col];
        }
        
        if (board[row][col] != word.charAt(index)) {
            return false;
        }
        visited[row][col] = 1;
        //visited.add(key)
        
        for (int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            
            if (recur(board, visited, word, index + 1, nr, nc)) {
                return true;
            }
        }
        visited[row][col] = 0;
        //visited.remove(key);
        return false;
    }
}
