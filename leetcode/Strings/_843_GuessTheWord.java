package leetcode.Strings;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         This is an interactive problem.
 * 
 *         You are given an array of unique strings wordlist where wordlist[i]
 *         is 6 letters long, and one word in this list is chosen as secret.
 * 
 *         You may call Master.guess(word) to guess a word. The guessed word
 *         should have type string and must be from the original list with 6
 *         lowercase letters.
 * 
 *         This function returns an integer type, representing the number of
 *         exact matches (value and position) of your guess to the secret word.
 *         Also, if your guess is not in the given wordlist, it will return -1
 *         instead.
 * 
 *         For each test case, you have exactly 10 guesses to guess the word. At
 *         the end of any number of calls, if you have made 10 or fewer calls to
 *         Master.guess and at least one of these guesses was secret, then you
 *         pass the test case.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: secret = "acckzz", wordlist =
 *         ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10 Output: You
 *         guessed the secret word correctly. Explanation:
 *         master.guess("aaaaaa") returns -1, because "aaaaaa" is not in
 *         wordlist. master.guess("acckzz") returns 6, because "acckzz" is
 *         secret and has all 6 matches. master.guess("ccbazz") returns 3,
 *         because "ccbazz" has 3 matches. master.guess("eiowzz") returns 2,
 *         because "eiowzz" has 2 matches. master.guess("abcczz") returns 4,
 *         because "abcczz" has 4 matches. We made 5 calls to master.guess and
 *         one of them was the secret, so we pass the test case.
 * 
 *         Example 2:
 * 
 *         Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses
 *         = 10 Output: You guessed the secret word correctly.
 * 
 * 
 *         Constraints:
 * 
 *         1 <= wordlist.length <= 100 wordlist[i].length == 6 wordlist[i]
 *         consist of lowercase English letters. All the strings of wordlist are
 *         unique. secret exists in wordlist. numguesses == 10
 *
 */

public class _843_GuessTheWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * // This is the Master's API interface. // You should not implement it, or
	 * speculate about its implementation interface Master { public int guess(String
	 * word) {} }
	 */

	interface Master {
		public default int guess(String word) {
			return 0;
		}
	}

	//=============================================================================================
	// Latest One that works
	public void findSecretWord6(String[] wordlist, Master master) {

		Map<String,Integer> map = new HashMap();

		for(int i = 0; i < wordlist.length - 1; i++){

			for(int j = i+1; j < wordlist.length; j++){

				String word1 = wordlist[i];
				String word2 = wordlist[j];

				if(!map.containsKey(word1)) map.put(word1,0);
				if(!map.containsKey(word2)) map.put(word2,0);

				if(comparee(word1,word2) > 0){
					map.put(word1,map.get(word1) + 1);
					map.put(word2,map.get(word1) + 1);
				}
			}
		}

		Arrays.sort(wordlist,(w1,w2)->map.get(w2) - map.get(w1));

		for(int i = 0; i < wordlist.length; i++){

			String word = wordlist[i];

			if(word.length() == 0) continue;
			int count = master.guess(word);
			if(count == 6) break;
			clean(word,i+1,count,wordlist);
		}
	}

	public void clean(String word, int start , int count,String[] wordlist){

		for(int i = start; i < wordlist.length; i++){

			String currWord = wordlist[i];
			if(currWord.length() == 0) continue;
			if(comparee(word,currWord) != count) wordlist[i] = "";
		}
	}

	public int comparee(String w1, String w2){

		int count = 0;

		for(int i = 0; i < w1.length(); i++){

			if(w1.charAt(i) == w2.charAt(i)) count++;
		}

		return count;
	}

	/*
	 * Answer - We pick a word at random from our array of words. From there we pass
	 * the guessed word to our master.guess method and capture how many characters
	 * matched with the answer word. We take that piece of information and use it
	 * this way. Lets say our guess returns 0 matches. Then we want to go find all
	 * the words that have 0 matches with us, that's because if we find a word that
	 * matches with any of our characters it can't possibly be the word as those
	 * characters in that index don't match the answer word. So any word that has 0
	 * matches should be added to a list of potential words. Lets say our guess
	 * returns 3 matches. That means there's 3 characters in our word of 6
	 * characters that matched the answer word. So we want to find other words
	 * that'll potentially have those 3 characters too! The problem is we don't know
	 * what the 3 characters are which means we'll have to consider all the words
	 * that have exactly 3 characters that are the same and in the same index. So
	 * those words become candidates. We then only test the guess on potential
	 * words, so update our wordlist to point to a new array of just those words.
	 * Continue to ask the question so long as we haven't found the match or we ran
	 * out of tries (10).
	 * 
	 * question: What are we trying to solve? answer: Given an array of words, we
	 * want to call guess on the Master object passing in a string of 6 letters. We
	 * want to keep guessing until we find the secrete word.
	 * 
	 * question: What are our constraints? 1 <= numberOfWords <= 100 all words have
	 * a length of 6 all lower case letters all words are unique secrete exist in
	 * the wordlist allowed 10 guesses
	 */

	public void findSecretWord(String[] wordlist, Master master) {
		for (int i = 0; i < 10; i++) {
			String guessWord = wordlist[i % wordlist.length];
			int matches = master.guess(guessWord);
			if (matches == 6) {
				return;
			}

			List<String> candidates = getMatches(guessWord, matches, wordlist);
			wordlist = candidates.toArray(new String[0]);
		}
	}

	// The thing is, we don't want to include the word that we just tried. How do we
	// exclude it?
	// The way it's excluded is that when we compare it's going to hit 6 chars as a
	// match so if it was the answer it would've return before this
	// Else the match count < 6, so it'll always be omitted.
	private List<String> getMatches(String testOn, int matchCount, String[] words) {
		List<String> candidates = new ArrayList<>();
		for (String word : words) {
			int count = 0;
			for (int i = 0; i < word.length(); i++) {
				if (testOn.charAt(i) == word.charAt(i)) {
					count++;
				}
			}

			if (count == matchCount) {
				candidates.add(word);
			}
		}

		return candidates;
	}

	// =====================================================================
	// Old Solution Failing in new test case
	public void findSecretWord1(String[] wordlist, Master master) {
		List<String> wlist = new ArrayList<>();

		for (String word : wordlist) {
			wlist.add(word);
		}
		int counter = 10;
		Set<String> v = new HashSet<>();

		while (counter-- > 0) {
			int minMatch = Integer.MAX_VALUE;
			String word = null;

			for (String s1 : wlist) {

				for (String s2 : wlist) {
					int temp = match(s1, s2);

					if (temp < minMatch) {
						word = s2;
						minMatch = temp;
					}
				}
			}
			int guessCount = master.guess(word);

			if (guessCount == word.length()) {
				break;
			}
			v.add(word);
			List<String> tlist = new LinkedList<>();

			for (String str : wlist) {

				if (v.contains(str)) {
					continue;
				}

				if (match(word, str) == guessCount) {
					tlist.add(str);
				}
			}
			wlist = tlist;
		}
	}

	private int match(String s1, String s2) {
		int count = 0;
		int index = 0;

		while (index < s1.length()) {

			if (s1.charAt(index) == s2.charAt(index++)) {
				count++;
			}
		}
		return count;
	}
	//=============================================================================================
	//New understanding and logic
	public void findSecretWord3(String[] wordlist, Master master) {
		List<String> candidates = new ArrayList<>();

		for (String word : wordlist) {
			candidates.add(word);
		}
		Set<String> v = new HashSet<>();

		for (int i = 0; i < 10; i++) {
			String mostUniqueWord = bestGuess(candidates);
			int guess = master.guess(mostUniqueWord);
			v.add(mostUniqueWord);

			if (mostUniqueWord.length() == guess) {
				return;
			}
			candidates = getCandidates(candidates, guess, mostUniqueWord);
		}
	}

	private List<String> getCandidates(List<String> candidates, int score, String word) {
		List<String> ans = new ArrayList<>();

		for (String str: candidates) {

			if (getMatch(str, word) == score) {
				ans.add(str);
			}
		}
		return ans;
	}

	private int getMatch(String w1, String w2) {
		int matchCount = 0;

		for (int i = 0; i < w1.length(); i++) {

			if (w1.charAt(i) == w2.charAt(i)) {
				matchCount++;
			}
		}
		return matchCount;
	}

	private String bestGuess(List<String> words) {
		List<Map<Character, Integer>> allLetterFreqs = new ArrayList<>(6);
		for (int letter = 0; letter < 6; letter++) {
			allLetterFreqs.add(getLetterFreqs(words, letter));
		}

		String bestGuessWord = "";
		int bestGuessScore = 0;
		for (String word : words) {
			int score = score(allLetterFreqs, word);
			if (score > bestGuessScore) {
				bestGuessWord = word;
				bestGuessScore = score;
			}
		}
		return bestGuessWord;
	}
	//the score is such that it has the most common alphabets in a particular position so next time many will
	//get eliminated when we try again thus narrowing down our search list
	private int score(List<Map<Character, Integer>> allLetterFreqs, String word) {
		int score = 0;
		for (int i = 0; i < 6; i++) {
			char letter = word.charAt(i);
			int matches = allLetterFreqs.get(i).get(letter);
			score += matches;
		}
		return score;
	}

	private Map<Character, Integer> getLetterFreqs(List<String> words, int index) {
		Map<Character, Integer> characterFreq = new HashMap<>();

		for (String word : words) {
			characterFreq.merge(word.charAt(index), 1, Integer::sum);
		}

		return characterFreq;
	}
}
