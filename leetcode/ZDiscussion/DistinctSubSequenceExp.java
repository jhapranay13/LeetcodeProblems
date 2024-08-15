package leetcode.ZDiscussion;

public class DistinctSubSequenceExp {
	//Distict subsequence . Exp
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aaabab";
		print(s, 0, "", '\u0000');
	}

	private static void print(String s, int index, String sub, char last) {
		if (index == s.length()) {
			System.out.println(sub);
			return;
		}
		print(s, index + 1, sub + s.charAt(index), s.charAt(index));
		
		if (s.charAt(index) != last) 
			print(s, index + 1, sub, last);
	}
}
