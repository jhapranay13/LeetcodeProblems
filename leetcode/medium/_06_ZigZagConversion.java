package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);


Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:


Input: s = "A", numRows = 1
Output: "A"


Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
 */
public class _06_ZigZagConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String convert(String s, int numRows) {

		if (numRows == 1) {
			return s;
		}
		StringBuilder[] cache = new StringBuilder[numRows]; // ArrayList can also be used

		for (int i = 0; i < numRows; i++) {
			cache[i] = new StringBuilder();
		}
		int direction = 0;//can also be boolean
		int index = 0;

		for (char c : s.toCharArray()) {
			cache[index].append("" + c);

			if (direction == 0) {
				index++;
			}

			if (direction == 1) {
				index--;
			}

			if (index == numRows) {
				direction = 1;
				index = numRows - 2;
			}

			if (index == -1) {
				direction = 0;
				index = 1;
			}
		}
		StringBuilder ans = new StringBuilder();

		for (StringBuilder cacheVal : cache) {
			ans.append(cacheVal);
		}
		return ans.toString();
	}
}
