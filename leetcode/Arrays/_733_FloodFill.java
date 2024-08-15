package leetcode.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @author Pranay Jha
 *
 *
 *         An image is represented by an m x n integer grid image where
 *         image[i][j] represents the pixel value of the image.
 * 
 *         You are also given three integers sr, sc, and newColor. You should
 *         perform a flood fill on the image starting from the pixel
 *         image[sr][sc].
 * 
 *         To perform a flood fill, consider the starting pixel, plus any pixels
 *         connected 4-directionally to the starting pixel of the same color as
 *         the starting pixel, plus any pixels connected 4-directionally to
 *         those pixels (also with the same color), and so on. Replace the color
 *         of all of the aforementioned pixels with newColor.
 * 
 *         Return the modified image after performing the flood fill.
 * 
 *         Example 1:
 * 
 *         Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor =
 *         2 Output: [[2,2,2],[2,2,0],[2,0,1]] Explanation: From the center of
 *         the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all
 *         pixels connected by a path of the same color as the starting pixel
 *         (i.e., the blue pixels) are colored with the new color. Note the
 *         bottom corner is not colored 2, because it is not 4-directionally
 *         connected to the starting pixel. Example 2:
 * 
 *         Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 *         Output: [[2,2,2],[2,2,2]]
 * 
 *         Constraints:
 * 
 *         m == image.length n == image[i].length 1 <= m, n <= 50 0 <=
 *         image[i][j], newColor < 216 0 <= sr < m 0 <= sc < n
 */

public class _733_FloodFill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) {
			return image;
		}
		int oldColor = image[sr][sc];
		recur(image, sr, sc, newColor, oldColor);
		return image;
	}

	private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	private void recur(int[][] image, int r, int c, int color, int oldColor) {

		if (r < 0 || c < 0 || r == image.length || c == image[0].length || image[r][c] != oldColor) {
			return;
		}
		image[r][c] = color;

		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			recur(image, nr, nc, color, oldColor);
		}
	}
	//=============================================================================
	//Slightly different
	public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[ sr ][ sc ];
        
        if( currColor == newColor ) {
            return image;
        }
        dfs( image, sr, sc, newColor );
        return image;
    }
    
    
    private void dfs( int[][] image, int r, int c, int newColor ) {
        int currColor = image[ r ][ c ];
        image[ r ][ c ] = newColor;
        
        for( int[] dir : dirs ) {
            int nr = r + dir[ 0 ];
            int nc = c + dir[ 1 ];
            
            if( nr < 0 || nc < 0 || nr == image.length || nc == image[ 0 ].length ||
              image[ nr ][ nc ] != currColor ) {
                continue;
            }
            dfs( image, nr, nc, newColor );
        }
    }
	//=============================================================================================
	//BFS approach
	public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
		Deque<int[]> q = new LinkedList<>();
		q.offer(new int[] {sr, sc});
		boolean v[][] = new boolean[image.length][image[0].length];
		v[sr][sc] = true;
		int val = image[sr][sc];

		while (!q.isEmpty()) {
			int[] pos = q.poll();
			image[pos[0]][pos[1]] = newColor;

			for (int[] dir : dirs) {
				int nr = pos[0] + dir[0];
				int nc = pos[1] + dir[1];

				if (nr >= 0 && nc >= 0 && nr < image.length && nc < image[0].length &&
						image[nr][nc] == val && !v[nr][nc]) {
					q.offer(new int[] {nr, nc});
					v[nr][nc] = true;
				}
			}
		}
		return image;
	}
}
