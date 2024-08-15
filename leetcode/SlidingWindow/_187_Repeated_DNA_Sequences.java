package leetcode.SlidingWindow;

import java.util.*;

/**
 *
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is either 'A', 'C', 'G', or 'T'.
 *
 */
// Rabin Karp
public class _187_Repeated_DNA_Sequences {
    public List<String> findRepeatedDnaSequences(String s) {

        if (s.length() <= 10) {
            return new ArrayList<>();
        }
        double prime = 11;
        Set<Double> v = new HashSet<>();
        double hash = 0;
        Set<String> ans = new HashSet<>();
        double maxPower = 1;
        Map<Character, Integer> valHolder = new HashMap<>();
        valHolder.put('A', 1);
        valHolder.put('C', 2);
        valHolder.put('G', 3);
        valHolder.put('T', 4);


        for (int i = 9; i >= 0; i--) {
            double val = (double)valHolder.get(s.charAt(i));
            hash += val * maxPower;
            maxPower *= prime;
        }
        maxPower /= prime;
        int slow = 0;
        int fast = 9;

        while (fast < s.length()) {

            if (10 == fast - slow + 1) {

                if (!v.add(hash)) {
                    ans.add(s.substring(slow, fast + 1));
                }
                double val = (double)valHolder.get(s.charAt(slow++));
                double removeVal = val * maxPower;
                hash -= removeVal;
            }
            hash *= prime;

            if (++fast < s.length()) {
                hash += (double)valHolder.get(s.charAt(fast));
            }
        }
        return new ArrayList<>(ans);
    }
}
