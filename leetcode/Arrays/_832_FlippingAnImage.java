package leetcode.Arrays;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an n x n binary matrix image, flip the image horizontally, then
 *         invert it, and return the resulting image.
 * 
 *         To flip an image horizontally means that each row of the image is
 *         reversed.
 * 
 *         For example, flipping [1,1,0] horizontally results in [0,1,1]. To
 *         invert an image means that each 0 is replaced by 1, and each 1 is
 *         replaced by 0.
 * 
 *         For example, inverting [0,1,1] results in [1,0,0].
 * 
 * 
 *         Example 1:
 * 
 *         Input: image = [[1,1,0],[1,0,1],[0,0,0]] Output:
 *         [[1,0,0],[0,1,0],[1,1,1]] Explanation: First reverse each row:
 *         [[0,1,1],[1,0,1],[0,0,0]]. Then, invert the image:
 *         [[1,0,0],[0,1,0],[1,1,1]]
 * 
 *         Example 2:
 * 
 *         Input: image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]] Output:
 *         [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]] Explanation: First reverse
 *         each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]. Then invert the
 *         image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 
 * 
 *         Constraints:
 * 
 *         n == image.length n == image[i].length 1 <= n <= 20 images[i][j] is
 *         either 0 or 1.
 *
 */

public class _832_FlippingAnImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int[][] flipAndInvertImage(int[][] image) {

		for (int[] img : image) {
			int lo = 0;
			int hi = img.length - 1;

			while (lo <= hi) {
				int temp = img[lo];
				img[lo] = img[hi];
				img[hi] = temp;
				// can also be done using ternary opertor
				if (lo != hi) {
					img[lo++] ^= 1; // Xor to flip from one to zero or zero to one
				}
				img[hi--] ^= 1;
			}
		}
		return image;
	}

	// =============================================================================
	// using ternary operator
	public int[][] flipAndInvertImage1(int[][] A) {

		for (int[] r : A) {
			int lo = 0;
			int hi = r.length - 1;

			while (lo <= hi) {
				int lval = r[lo];
				int hval = r[hi];

				if (lo != hi) {
					r[lo] = hval;
					r[hi] = lval;

					r[lo] = r[lo] == 0 ? 1 : 0;
				}
				r[hi] = r[hi] == 0 ? 1 : 0;
				lo++;
				hi--;
			}
		}
		return A;
	}
}
