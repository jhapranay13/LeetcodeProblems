package LeetcodeDiscuss;

/**
 *
 *A string is special if there are no matching adjacent characters. Given a string s of length n, generate a special string of length n that is lexicographically greater than s. if multiple such special strings are possible, then return the lexicographically smallest string among them.
 * Note:
 * Special string: A string is special if there are no two adjacent characters that are the same
 * Lexicographical order: this is a generalization of the way words are alphabetically ordered in dictionaries. For example, "abc" is lexicographical smaller than "abd" because 'c' comes before ' d' in the alphabet.
 * A string a is lexicographical smaller than a sting b if and only if one of the following holds:
 *
 * a is a prefix of b, but a is not equal to b. For example, "abc" is smaller than "abd" because ' c' comes before 'd'.
 * Important considerations:
 * if the character is 'z', is the last character in the alphabet and cannot be increased further. The sting should not wrap around to 'a' after 'z'
 * the output string must not have any adjacent characters that are the same.
 * Some examples:
 * Input: abccde Expected: abcdab
 * Input: abbd Expected: abca
 * Input: aab Expected: aba
 * Input: zzab Expected: -1
 * Input: zz Expected: -1
 */
public class AmazonOASpecialString {

    public static void main(String[] args) {
        System.out.println(getSpecialString("abccde")); // Expected: abcdab
        System.out.println(getSpecialString("abbd")); // Expected: abca
        System.out.println(getSpecialString("aab")); // Expected: aba
        System.out.println(getSpecialString("zzab")); // Expected: -1
        System.out.println(getSpecialString("zz")); // Expected: -1
    }

    private static String getSpecialString(String str) {
        String ans = "-1";
        StringBuilder holder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (i > 0 && str.charAt(i) == str.charAt(i - 1)) {
                // If the current character is the same as the previous one, we need to change it
                char nextChar = (char) (str.charAt(i) + 1);

                // If nextChar is 'z', we cannot use it
                if (nextChar > 'z') {
                    return ans; // Cannot create a valid string
                }
                holder.append(nextChar);
                generateSpecialString(holder, str, i + 1);
                break;
            } else {
                holder.append(str.charAt(i));
            }
        }
        return holder.toString();
    }

    private static void generateSpecialString(StringBuilder holder, String str, int i) {

        for ( ; i < str.length(); i++) {
            char prevChar = holder.charAt(holder.length() - 1);

            for (char  ch = 'a'; ch <= 'z'; ch++) {

                if (ch == prevChar) {
                    continue; // Skip if the character is the same as the previous one or the same as the character in the original string
                }
                holder.append(ch);
                break;
            }
        }
    }
}
