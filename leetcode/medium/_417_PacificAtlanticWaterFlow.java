package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         There is an m x n rectangular island that borders both the Pacific
 *         Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left
 *         and top edges, and the Atlantic Ocean touches the island's right and
 *         bottom edges.
 * 
 *         The island is partitioned into a grid of square cells. You are given
 *         an m x n integer matrix heights where heights[r][c] represents the
 *         height above sea level of the cell at coordinate (r, c).
 * 
 *         The island receives a lot of rain, and the rain water can flow to
 *         neighboring cells directly north, south, east, and west if the
 *         neighboring cell's height is less than or equal to the current cell's
 *         height. Water can flow from any cell adjacent to an ocean into the
 *         ocean.
 * 
 *         Return a 2D list of grid coordinates result where result[i] = [ri,
 *         ci] denotes that rain water can flow from cell (ri, ci) to both the
 *         Pacific and Atlantic oceans.
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: heights =
 *         [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]] Output:
 *         [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]] Example 2:
 * 
 *         Input: heights = [[2,1],[1,2]] Output: [[0,0],[0,1],[1,0],[1,1]]
 * 
 * 
 *         Constraints:
 * 
 *         m == heights.length n == heights[r].length 1 <= m, n <= 200 0 <=
 *         heights[r][c] <= 105
 *
 */

public class _417_PacificAtlanticWaterFlow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
        Boolean[][] atlanticFlag = new Boolean[heights.length][ heights[0].length];
        Boolean[][] pacificFlag = new Boolean[heights.length][ heights[0].length];
        //Using Pruning Concept. Can be done using normal DFS or BFS too
        for (int r = 0; r < heights.length; r++) {
            if (r == 0) {
                for (int c = 0; c < heights[0].length; c++) {
                    if (pacificFlag[r][c] == null) {
                        dfs(heights, pacificFlag, r, c);
                    }
                }
                continue;
            }
            
            if (pacificFlag[r][0] == null) {
                dfs(heights, pacificFlag, r, 0);
            }
        }
        
        for (int r = 0; r < heights.length; r++) {
            if (r == heights.length - 1) {
                for (int c = 0; c < heights[0].length; c++) {
                    if (atlanticFlag[r][c] == null) {
                        dfs(heights, atlanticFlag, r, c);
                    }
                }
                continue;
            }
            
            if (atlanticFlag[r][heights[0].length - 1] == null) {
                dfs(heights, atlanticFlag, r, heights[0].length - 1);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        
        for (int r = 0 ; r < heights.length; r++) {
            for (int c = 0; c < heights[0].length; c ++) {
                if (atlanticFlag[r][c] != null && pacificFlag[r][c] != null &&
                   atlanticFlag[r][c] && pacificFlag[r][c]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(r);
                    temp.add(c);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }
    private int[][] dirs = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0}
    };
    
    private void dfs(int[][] heights, Boolean[][] flag, int row, int col) {
        flag[row][col] = true;
        
        for (int[] dir : dirs) {
            int nr = row + dir[0];
            int nc = col + dir[1];
            
            if (nr < 0 || nc >= heights[0].length || nr >= heights.length || nc < 0 ||
               flag[nr][nc] != null || heights[nr][nc] < heights[row][col]) {
                continue;
            }
            dfs(heights, flag, nr, nc);
        }
    }
    //============================================================================
    //Normal DFS. Slower than above approach
    public List<List<Integer>> pacificAtlantic1(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        
        if( matrix == null || matrix.length == 0 ) {
            return ans;
        }
        Boolean[][] ma = new Boolean[ matrix.length ][ matrix[ 0 ].length ];
        Boolean[][] mp = new Boolean[ matrix.length ][ matrix[ 0 ].length ];
        
        for( int r = 0; r < matrix.length; r++ ) {
            
            for( int c = 0; c < matrix[ 0 ].length; c++ ) {
                boolean[][] v = new boolean[ matrix.length ][ matrix[ 0 ].length ];
                dfs1( matrix, 0, 0, r, c, mp, v );
                v = new boolean[ matrix.length ][ matrix[ 0 ].length ];
                dfs1( matrix, matrix.length - 1, matrix[ 0 ].length - 1, r, c, ma, v );
                
                if( mp[ r ][ c ] != null && ma[ r ][ c ] != null &&
                    mp[ r ][ c ] && ma[ r ][ c ] ) {
                    List< Integer > p = new ArrayList<>();
                    p.add( r );
                    p.add( c );
                    ans.add( p );
                }
            }
        }
        return ans;
    }
    
    private boolean dfs1( int[][] matrix, int re, int ce, int r, int c, 
                        Boolean[][] memo, boolean[][] v ) {
        
        if( re == r || ce == c ) {
            return memo[ r ][ c ] = true;
        }
        v[ r ][ c ] = true;
        boolean ans = false;
        
        if( memo[ r ][ c ] != null && memo[ r ][ c ] != false ) {
            return memo[ r ][ c ];
        }
        
        for( int[] dir : dirs ) {
            int nr = r + dir[ 0 ];
            int nc = c + dir[ 1 ];
            
            if( nr < 0 || nc < 0 || nr >= matrix.length || nc >= matrix[ 0 ].length ||
              v[ nr ][ nc ] ) {
                continue;
            }
            
            if( matrix[ r ][ c ] < matrix[ nr ][ nc ] ) {
                continue;
            }
            boolean temp = dfs1( matrix, re, ce, nr, nc, memo, v );
            ans = ans ? ans : temp;
        }
        return memo[ r ][ c ] = ans;
    }
}
