package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given two images img1 and img2 both of size n x n,
 *         represented as binary, square matrices of the same size. (A binary
 *         matrix has only 0s and 1s as values.)
 * 
 *         We translate one image however we choose (sliding it left, right, up,
 *         or down any number of units), and place it on top of the other image.
 *         After, the overlap of this translation is the number of positions
 *         that have a 1 in both images.
 * 
 *         (Note also that a translation does not include any kind of rotation.)
 * 
 *         What is the largest possible overlap?
 * 
 * 
 * 
 *         Example 1:
 * 
 * 
 *         Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 =
 *         [[0,0,0],[0,1,1],[0,0,1]] Output: 3 Explanation: We slide img1 to
 *         right by 1 unit and down by 1 unit.
 * 
 *         The number of positions that have a 1 in both images is 3.
 * 
 *         Example 2:
 * 
 *         Input: img1 = [[1]], img2 = [[1]] Output: 1
 * 
 *         Example 3:
 * 
 *         Input: img1 = [[0]], img2 = [[0]] Output: 0
 * 
 * 
 *         Constraints:
 * 
 *         n == img1.length n == img1[i].length n == img2.length n ==
 *         img2[i].length 1 <= n <= 30 img1[i][j] is 0 or 1. img2[i][j] is 0 or
 *         1.
 *
 */

public class _835_ImageOverlap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int largestOverlap(int[][] img1, int[][] img2) {
		int ans = 0;

		for (int i = 0; i < img1.length; i++) {

			for (int j = 0; j < img1.length; j++) {

				ans = Math.max(ans, Math.max(overlap(img1, img2, i, j), overlap(img2, img1, i, j)));
			}
		}
		return ans;
	}
	// for two sheets placed up and down.
	// Thus we simulate it moving in opposite direction by replacing the
	// down sheet with up sheet and up sheet with down sheet. We Move top and right
	// and
	// bottom and right when we change the sheets order we get the opposite movement

	private int overlap(int[][] img1, int[][] img2, int i, int j) {
		int result1 = 0;
		int result2 = 0;

		for (int row = i; row < img1.length; row++) {

			for (int col = j; col < img1.length; col++) {
				result1 += img1[row][col] & img2[row - i][col - j];
				result2 += img1[row - i][col] & img2[row][col - j];
			}
		}
		return Math.max(result1, result2);
	}
	//=============================================================================================
	//Different approach
	public int largestOverlap1(int[][] img1, int[][] img2) {
		int ans = 0;
		//Shifiting calculation
		for (int r = 0; r < img1.length; r++) {

			for (int c = 0; c < img1[r].length; c++) {
				int temp = Math.max(overlap1(img1, img2, r, c), overlap1(img2, img1, r, c));
				ans = Math.max(ans, temp);
			}
		}
		return ans;
	}
	//Image1 is static image 2 is moving
	//logic is since we are shifting in the previous method calculate the range of image1 and image 2
	//that will be compared
	private int overlap1(int[][] img1, int[][] img2, int r, int c) {
		//int result Moving top and right
		int result1 = 0;

		//Shifting top and left and  top and right
		for (int row = img2.length - 1; row >= r; row--) {

			for (int col = img2[0].length - 1; col >= c; col--) {
				result1 += img1[row][col] & img2[row - r][col - c];
			}
		}
		//result Moving down and right
		int result2 = 0;
		//shifting bottom and right
		for (int row = img2.length - 1; row >= r; row--) {

			for (int col = img2[0].length - 1; col >= c; col--) {
				result2 += img1[row - r][col] & img2[row][col - c];
			}
		}
		return Math.max(result1, result2);
	}
	//=============================================================================================
	// Another way
	public int largestOverlap3(int[][] img1, int[][] img2) {
		int ans = 0;

		for (int r = 0; r < img1.length; r++) {

			for (int c = 0; c < img1[0].length; c++) {
				int result1 = slide(img1, img2, r, c);
				int result2 = slide(img2, img1, r, c);
				ans = Math.max(ans, Math.max(result1, result2));
			}
		}
		return ans;
	}

	private int slide(int[][] img1, int[][] img2, int r, int c) {
		int slideUpLeft = 0;
		int slideDownLeft = 0;

		for (int rw = 0; rw < img1.length - r; rw++) {

			for (int cl = 0; cl < img1[0].length - c; cl++) {
				slideUpLeft += img1[rw][cl] & img2[rw + r][cl + c];
			}
		}

		for (int rw = 0; rw < img1.length - r; rw++) {

			for (int cl = 0; cl < img1[0].length - c; cl++) {
				slideDownLeft += img1[rw + r][cl] & img2[rw][cl + c];
			}
		}

		return Math.max(slideUpLeft, slideDownLeft);
	}
}
