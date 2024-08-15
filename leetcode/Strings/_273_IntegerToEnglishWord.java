package leetcode.Strings;

/**
 * 
 * @author Pranay Jha
 *
 *         Convert a non-negative integer num to its English words
 *         representation.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: num = 123 Output: "One Hundred Twenty Three" Example 2:
 * 
 *         Input: num = 12345 Output: "Twelve Thousand Three Hundred Forty Five"
 *         Example 3:
 * 
 *         Input: num = 1234567 Output: "One Million Two Hundred Thirty Four
 *         Thousand Five Hundred Sixty Seven" Example 4:
 * 
 *         Input: num = 1234567891 Output: "One Billion Two Hundred Thirty Four
 *         Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * 
 * 
 *         Constraints:
 * 
 *         0 <= num <= 231 - 1
 *
 */

public class _273_IntegerToEnglishWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	String[] ones = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
			"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
	String[] over = { "Hundred", "Thousand", "Million", "Billion" };

	public String numberToWords(int num) {
		String number = getNum(num);
		return number;
	}

	public String getNum(int num) {
		StringBuilder result = new StringBuilder();

		if (num == 0 || num / 20 == 0) {
			return ones[num];
		}

		if (num < 100) {
			int temp = num / 10;
			result.append(tens[temp]);
			int temp2 = num % 10;

			if (temp2 != 0) {
				result.append(" " + ones[temp2]);
			}
			return result.toString();
		}

		if (num < 1000) {
			int temp = num / 100;
			result.append(ones[temp] + " " + over[0]);
			int temp2 = num % 100;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 10000) {
			int temp = num / 1000;
			result.append(ones[temp] + " " + over[1]);
			int temp2 = num % 1000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 100000) {
			int temp = num / 1000;
			result.append(getNum(temp) + " " + over[1]);
			int temp2 = num % 1000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 1000000) {
			int temp = num / 1000;
			result.append(getNum(temp) + " " + over[1]);
			int temp2 = num % 1000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 10000000) {
			int temp = num / 1000000;
			result.append(getNum(temp) + " " + over[2]);
			int temp2 = num % 1000000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 1000000000) {
			int temp = num / 1000000;
			result.append(getNum(temp) + " " + over[2]);
			int temp2 = num % 1000000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num < 1000000000) {
			int temp = num / 100000000;
			result.append(getNum(temp) + " " + over[3]);
			int temp2 = num % 100000000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}

		if (num / 1000000000 != 0) {
			int temp = num / 1000000000;
			result.append(getNum(temp) + " " + over[3]);
			int temp2 = num % 1000000000;

			if (temp2 != 0) {
				result.append(" " + getNum(temp2));
			}
			return result.toString();
		}
		return result.toString();
	}
	//=============================================================================================
	//Another approach
	String[] ones1 = { "" , "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Eleven","Twelve", "Thirteen", "Fourteen", "Fifteen","Sixteen", "Seventeen", "Eighteen",                       "Nineteen"};
	String[] tens1 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	String[] over1 = {"Hundred", "Thousand", "Million", "Billion"};
	public String numberToWords1(int num) {

		if (num == 0) {
			return "Zero";
		}
		String ans = recur(num);
		return ans.trim();
	}

	private String recur(int num) {
		String str = "";

		if (num < 20) {
			str = ones1[num];
			return str.trim();
		}

		if (num < 100) {
			int temp = num / 10;
			int temp2 = num % 10;
			str = tens1[temp] + " " + ones1[temp2];
			return str.trim();
		}

		if (num < 1000) {
			int temp = num / 100;
			int temp2 = num % 100;
			str = recur(temp) + " " + over1[0] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 10000) {
			int temp = num / 1000;
			int temp2 = num % 1000;
			str = recur(temp) + " " + over1[1] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 100000) {
			int temp = num / 1000;
			int temp2 =  num % 1000;
			str = recur(temp) + " " + over1[1] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 1000000) {
			int temp = num /1000;
			int temp2 = num % 1000;
			str = recur(temp) + " " + over1[1] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 10000000) {
			int temp = num /1000000;
			int temp2 = num % 1000000;
			str = recur(temp) + " " + over1[2] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 100000000) {
			int temp = num /1000000;
			int temp2 = num % 1000000;
			str = recur(temp) + " " + over1[2] + " " + recur(temp2);
			return str.trim();
		}

		if (num < 1000000000) {
			int temp = num /1000000;
			int temp2 = num % 1000000;
			str = recur(temp) + " " + over1[2] + " " + recur(temp2);
			return str.trim();
		}

		if (num / 1000000000 != 0) {
			int temp = num /1000000000;
			int temp2 = num % 1000000000;
			str = recur(temp) + " " + over1[3] + " " + recur(temp2);
			return str.trim();
		}
		return str.trim();
	}
}
