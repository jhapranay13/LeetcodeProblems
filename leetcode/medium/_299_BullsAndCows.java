package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are playing the Bulls and Cows game with your friend.
 * 
 *         You write down a secret number and ask your friend to guess what the
 *         number is. When your friend makes a guess, you provide a hint with
 *         the following info:
 * 
 *         The number of "bulls", which are digits in the guess that are in the
 *         correct position. The number of "cows", which are digits in the guess
 *         that are in your secret number but are located in the wrong position.
 *         Specifically, the non-bull digits in the guess that could be
 *         rearranged such that they become bulls. Given the secret number
 *         secret and your friend's guess guess, return the hint for your
 *         friend's guess.
 * 
 *         The hint should be formatted as "xAyB", where x is the number of
 *         bulls and y is the number of cows. Note that both secret and guess
 *         may contain duplicate digits.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: secret = "1807", guess = "7810" Output: "1A3B" Explanation:
 *         Bulls are connected with a '|' and cows are underlined: "1807" |
 *         "7810" 
 *         Example 2:
 * 
 *         Input: secret = "1123", guess = "0111" Output: "1A1B" Explanation:
 *         Bulls are connected with a '|' and cows are underlined: "1123" "1123"
 *         | or | "0111" "0111" Note that only one of the two unmatched 1s is
 *         counted as a cow since the non-bull digits can only be rearranged to
 *         allow one 1 to be a bull. 
 *         
 *         Example 3:
 * 
 *         Input: secret = "1", guess = "0" Output: "0A0B" 
 *         
 *         Example 4:
 * 
 *         Input: secret = "1", guess = "1" Output: "1A0B"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= secret.length, guess.length <= 1000 secret.length ==
 *         guess.length secret and guess consist of digits only.
 *
 */

public class _299_BullsAndCows {
	public static void main(String args[]) {

	}
	//=============================================================================
	public String getHint(String secret, String guess) {
		Map<Character, Set<Integer>> secretIndexMap = new HashMap<>();

		for (int i = 0; i < secret.length(); i++) {
			Set<Integer> indexSet = secretIndexMap.getOrDefault(secret.charAt(i), 
					new HashSet<>());
			indexSet.add(i);
			secretIndexMap.put(secret.charAt(i), indexSet);
		}
		int bullCount = 0;
		int cowCount = 0;

		for (int i = 0; i < guess.length(); i++) {
			char ch = guess.charAt(i);

			if (secretIndexMap.containsKey(ch)) {
				Set<Integer> indexSet = secretIndexMap.get(ch);

				if (indexSet.contains(i)) {
					bullCount++;
					indexSet.remove(i);

					if (indexSet.isEmpty()) {
						secretIndexMap.remove(ch);
					}
				}
			}
		}

		for (int i = 0; i < guess.length(); i++) {
			char ch = guess.charAt(i);
			char sch = secret.charAt(i);

			if(ch == sch) {
				continue;
			}

			if (secretIndexMap.containsKey(ch)) {
				cowCount++;
				Set<Integer> indexSet = secretIndexMap.get(ch);

				for (int index : indexSet) {
					indexSet.remove(index);
					break;
				}

				if (indexSet.isEmpty()) {
					secretIndexMap.remove(ch);
				}
			}
		}
		return bullCount + "A" + cowCount + "B";
	}
	//============================================================================
	public String getHint1(String secret, String guess) {
		Map<Character, Integer> secretfreqMap = new HashMap<>();

		for (int i = 0; i < secret.length(); i++) {
			int freq = secretfreqMap.getOrDefault(secret.charAt(i), 0);;
			secretfreqMap.put(secret.charAt(i), ++freq);
		}
		int bullCount = 0;
		int cowCount = 0;

		for (int i = 0; i < guess.length(); i++) {
			char gch = guess.charAt(i);
			char sch = secret.charAt(i);

			if (sch == gch) {
				int freq = secretfreqMap.get(sch);
				bullCount++;

				if (freq == 1) {
					secretfreqMap.remove(sch);
				} else {
					secretfreqMap.put(sch, --freq);
				}
			}
		}

		for (int i = 0; i < guess.length(); i++) {
			char ch = guess.charAt(i);
			char sch = secret.charAt(i);

			if (ch == sch) {
				continue;
			}

			if (secretfreqMap.containsKey(ch)) {
				cowCount++;
				int freq = secretfreqMap.get(ch);

				if (freq == 1) {
					secretfreqMap.remove(ch);
				} else {
					secretfreqMap.put(ch, --freq);
				}
			}
		}
		return bullCount + "A" + cowCount + "B";
	}
	//============================================================================
	public String getHint2(String s, String g) {
		Map< Character, Set< Integer > > sMap = new HashMap<>();
		Map< Character, Set< Integer > > gMap = new HashMap<>();

		for( int i = 0 ; i < s.length(); i++ ) {
			char ch = s.charAt( i );
			Set< Integer > index = sMap.getOrDefault( ch, new HashSet<>() );
			index.add( i );
			sMap.put( ch, index );
		}

		for( int i = 0 ; i < g.length(); i++ ) {
			char ch = g.charAt( i );
			Set< Integer > index = gMap.getOrDefault( ch, new HashSet<>() );
			index.add( i );
			gMap.put( ch, index );
		}
		int bulls = 0;
		int cows = 0;

		for( Character key : gMap.keySet() ) {
			Set< Integer > sSet = sMap.get( key );

			if( sSet == null ) {
				continue;
			}
			Set< Integer > gSet = gMap.get( key );
			Iterator<Integer> gIter = gSet.iterator();

			while( gIter.hasNext() ) {
				int i = gIter.next();

				if( sSet.contains( i ) ) {
					bulls++;
					sSet.remove( i );
					gIter.remove();
				}
			}

			if( sSet.size() <= gSet.size() ) {
				cows += sSet.size();
			} else if( sSet.size() > gSet.size() ) {
				cows += gSet.size(); 
			}
		}
		return bulls + "A" + cows + "B";
	}
}
