package leetcode.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an m x n binary matrix grid. An island is a group of
 *         1's (representing land) connected 4-directionally (horizontal or
 *         vertical.) You may assume all four edges of the grid are surrounded
 *         by water.
 * 
 *         An island is considered to be the same as another if and only if one
 *         island can be translated (and not rotated or reflected) to equal the
 *         other.
 * 
 *         Return the number of distinct islands.
 * 
 * 
 * 
 *         Example 1:
 * 						[1,1,0,0,0],
 * 						[1,1,0,0,0],
 * 						[0,0,0,1,1],
 * 						[0,0,0,1,1]
 * 
 *         Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 *         Output: 1 
 *         
 *         Example 2:
 * 
 * 
 *         Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 *         Output: 3
 * 
 * 
 *         Constraints:
 * 
 *         m == grid.length n == grid[i].length 1 <= m, n <= 50 grid[i][j] is
 *         either 0 or 1.
 *
 */

public class _694_NumberOfDistinctIsland {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int numDistinctIslands(int[][] grid) {
		Set<String> island = new HashSet<>();
		boolean[][] visited = new boolean[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				StringBuilder key = new StringBuilder();

				if (!visited[i][j] && grid[i][j] == 1) {
					dfs(grid, visited, key, i, j, i, j);
					island.add(key.toString());
				}
			}
		}
		return island.size();
	}
	private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	private void dfs(int[][] grid, boolean[][] visited, StringBuilder key, 
			int r, int c, int br, int bc) {
		if (visited[r][c]) {
			return;
		}

		if(key.length() > 0) {
			key.append(",");
		}
		key.append((br - r) + "," + (bc - c));
		visited[r][c] = true;

		for (int[] dir : dirs) {
			int nr = dir[0] + r;
			int nc = dir[1] + c;

			if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
					grid[nr][nc] == 0) {
				continue;
			}
			dfs(grid, visited, key, nr, nc, br, bc);
		}
	}
	//=======================================================================================
	//Slightly different approach
	public int numDistinctIslands1(int[][] grid) {

		int [][] visited = new int[ grid.length ][ grid[ 0 ].length ];
		Set< String > paths = new HashSet<>();

		for( int i = 0; i < grid.length; i++ ) {

			for( int j = 0; j < grid[ 0 ].length; j++ ) {

				if( visited[ i ][ j ] == 1 ) {
					continue;
				}

				if( grid[ i ][ j ] == 1 ) {
					String path = dfs( visited, grid, i, j, i, j );
					paths.add( path.trim() );
				}
			}
		}
		return paths.size();
	}

	public String dfs( int[][] visited, int[][] grid, int row, int col, int minRow, int minCol ) {

		if( visited[ row ][ col ] == 1 ) {
			return "";
		}

		int[] rowOffset = { -1, 0, 1, 0 };
		int[] colOffset = { 0, -1, 0, 1 };

		visited[ row ][ col ] = 1;
		String path = ( row - minRow )  + "," + ( col - minCol );

		for( int i = 0; i < rowOffset.length; i++ ) {
			int newRow = row + rowOffset[ i ];
			int newCol = col + colOffset[ i ];

			if(  newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[ 0 ].length ) {

				if( grid[ newRow ][ newCol ] == 1 ) {
					path += "|" + dfs( visited, grid, newRow, newCol, minRow, minCol );
				}
			}
		}
		return path;
	}
}
