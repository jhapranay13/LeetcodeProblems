package leetcode.BinarySearch;

/**
 * 
 * @author Pranay Jha
 *
 *         In an election, the i-th vote was cast for persons[i] at time
 *         times[i].
 * 
 *         Now, we would like to implement the following query function:
 *         TopVotedCandidate.q(int t) will return the number of the person that
 *         was leading the election at time t.
 * 
 *         Votes cast at time t will count towards our query. In the case of a
 *         tie, the most recent vote (among tied candidates) wins.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: ["TopVotedCandidate","q","q","q","q","q","q"],
 *         [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 *         Output: [null,0,1,1,0,0,1] Explanation: At time 3, the votes are [0],
 *         and 0 is leading. At time 12, the votes are [0,1,1], and 1 is
 *         leading. At time 25, the votes are [0,1,1,0,0,1], and 1 is leading
 *         (as ties go to the most recent vote.) This continues for 3 more
 *         queries at time 15, 24, and 8.
 * 
 * 
 *         Note:
 * 
 *         1 <= persons.length = times.length <= 5000 0 <= persons[i] <=
 *         persons.length times is a strictly increasing array with all elements
 *         in [0, 10^9]. TopVotedCandidate.q is called at most 10000 times per
 *         test case. TopVotedCandidate.q(int t) is always called with t >=
 *         times[0].
 *
 */

public class _911_OnlineElection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TopVotedCandidate {
		int[] persons;
		int[] times;
		int[] leading;

		public TopVotedCandidate(int[] persons, int[] times) {
			this.persons = persons;
			this.times = times;
			this.leading = new int[persons.length];
			int[] count = new int[persons.length];
			int maxVotes = 0;
			int maxPerson = -1;

			for (int i = 0; i < persons.length; i++) {
				int person = persons[i];
				count[person]++;

				if (count[person] >= maxVotes) {
					maxVotes = count[person];
					maxPerson = person;
					leading[i] = maxPerson;
				} else {
					leading[i] = maxPerson;
				}
			}
		}

		public int q(int t) {
			return binarySearchLessThan(t);
		}

		public int binarySearchLessThan(int t) {
			int lo = 0;
			int hi = times.length - 1;
			int ans = 0;

			while (lo <= hi) {
				int pivot = lo + (hi - lo) / 2;

				if (times[pivot] <= t) {
					ans = pivot;
					lo = pivot + 1;
				} else {
					hi = pivot - 1;
				}
			}
			return leading[ans];
		}
	}

	/**
	 * Your TopVotedCandidate object will be instantiated and called as such:
	 * TopVotedCandidate obj = new TopVotedCandidate(persons, times); int param_1 =
	 * obj.q(t);
	 */
}
