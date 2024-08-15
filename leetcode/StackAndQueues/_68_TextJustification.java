package leetcode.StackAndQueues;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of strings words and a width maxWidth, format the text
 *         such that each line has exactly maxWidth characters and is fully
 *         (left and right) justified.
 * 
 *         You should pack your words in a greedy approach; that is, pack as
 *         many words as you can in each line. Pad extra spaces ' ' when
 *         necessary so that each line has exactly maxWidth characters.
 * 
 *         Extra spaces between words should be distributed as evenly as
 *         possible. If the number of spaces on a line does not divide evenly
 *         between words, the empty slots on the left will be assigned more
 *         spaces than the slots on the right.
 * 
 *         For the last line of text, it should be left-justified and no extra
 *         space is inserted between words.
 * 
 *         Note:
 * 
 *         A word is defined as a character sequence consisting of non-space
 *         characters only. Each word's length is guaranteed to be greater than
 *         0 and not exceed maxWidth. The input array words contains at least
 *         one word.
 * 
 * 
 *         Example 1:
 * 
 *         Input: words = ["This", "is", "an", "example", "of", "text",
 *         "justification."], maxWidth = 16 Output: [ "This is an", "example of
 *         text", "justification. " ]
 * 
 *         Example 2:
 * 
 *         Input: words = ["What","must","be","acknowledgment","shall","be"],
 *         maxWidth = 16 Output: [ "What must be", "acknowledgment ", "shall be
 *         " ] Explanation: Note that the last line is "shall be " instead of
 *         "shall be", because the last line must be left-justified instead of
 *         fully-justified. Note that the second line is also left-justified
 *         becase it contains only one word.
 * 
 *         Example 3:
 * 
 *         Input: words =
 *         ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"],
 *         maxWidth = 20 Output: [ "Science is what we", "understand well",
 *         "enough to explain to", "a computer. Art is", "everything else we",
 *         "do " ]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= words.length <= 300 1 <= words[i].length <= 20 words[i] consists
 *         of only English letters and symbols. 1 <= maxWidth <= 100
 *         words[i].length <= maxWidth
 *
 *
 */

public class _68_TextJustification {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> ans = new ArrayList<>();

		if (words == null || words.length == 0) {
			return ans;
		}
		Deque<String> q = new LinkedList<>();
		int charCount = 0;

		for (String w : words) {

			if (q.size() + w.length() + charCount <= maxWidth) {
				q.offer(w);
				charCount += w.length();
			} else {
				String line = getLine(q, maxWidth, charCount);
				// System.out.println( line );
				charCount = w.length();
				ans.add(line);
				q.offer(w);
			}
		}

		if (!q.isEmpty()) {
			String line = getLastLine(q, maxWidth, charCount);
			ans.add(line);
		}
		return ans;
	}

	private String getLastLine(Deque<String> q, int maxWidth, int charCount) {
		StringBuilder line = new StringBuilder();
		int space = maxWidth - charCount;

		while (!q.isEmpty()) {
			line.append(q.poll());

			if (space != 0) {
				line.append(" ");
				space--;
			}
		}

		while (space > 0) {
			line.append(" ");
			space--;
		}
		return line.toString();
	}

	private String getLine(Deque<String> q, int maxWidth, int charCount) {
		int spaces = maxWidth - charCount;
		int[] spaceCount = new int[q.size() == 1 ? 1 : q.size() - 1];
		int index = 0;

		while (spaces > 0) {

			if (index >= spaceCount.length) {
				index = 0;
			}
			spaceCount[index]++;
			index++;
			spaces--;
		}
		StringBuilder line = new StringBuilder();
		index = 0;

		while (!q.isEmpty()) {
			String w = q.poll();
			line.append(w);

			if (index < spaceCount.length) {
				int cnt = spaceCount[index++];

				while (cnt > 0) {
					line.append(" ");
					cnt--;
				}
			}
		}
		return line.toString();
	}
	//========================================================================
	//Another approach
	public List<String> fullJustify1(String[] words, int maxWidth) {
        Deque<String> q = new LinkedList<>();
        int charCount = 0;
        int spaceCount = 0;
        List<String> ans = new ArrayList<>();
        
        for (String word : words) {
            
            if (charCount + q.size() + word.length() <= maxWidth) {
                q.offer(word);
                charCount += word.length();
            } else {
                getLine(maxWidth - charCount, q, ans);
                charCount = word.length();
                q.offer(word);
            }
        }
        
        if (!q.isEmpty()) {
            getLastLine(q, maxWidth - charCount, ans);
        }
        return ans;
    }
    
    private void getLine(int totalSpace, Deque<String> q, List<String> ans) {
        StringBuilder line = new StringBuilder();
        int spaceSlot = q.size() - 1 == 0 ? 1 : q.size() - 1;
        int minSpace = totalSpace / spaceSlot;
        int remainderSpace = totalSpace % spaceSlot;
        StringBuilder space = new StringBuilder();
        boolean single = q.size() == 1;
        
        while (minSpace > 0) {
            space.append(" ");
            minSpace--;
        }
        
        while (!q.isEmpty()) {
            line.append(q.poll());
            
            if (!q.isEmpty()) {
                line.append(space.toString());
            }
            
            if (remainderSpace > 0) {
                line.append(" ");
                remainderSpace--;
            }
        }
        
        if (single) {
            line.append(space.toString());
        }
        ans.add(line.toString());
    }
    
    private void getLastLine(Deque<String> q, int totalSpaces, List<String> ans) {
        StringBuilder line = new StringBuilder();
        
        while (!q.isEmpty()) {
            
            if (line.length() > 0) {
                line.append(" ");
                totalSpaces--;
            }
            line.append(q.poll());
        }
        
        while (totalSpaces > 0) {
            line.append(" ");
            totalSpaces--;
        }
        ans.add(line.toString());
    }
}
