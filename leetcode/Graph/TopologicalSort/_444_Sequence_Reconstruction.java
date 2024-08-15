package leetcode.Graph.TopologicalSort;

import java.util.*;

/**
 *
 * You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.
 *
 * Check if nums is the shortest possible and the only supersequence. The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. There could be multiple valid supersequences for the given array sequences.
 *
 * For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
 * While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest.
 * Return true if nums is the only shortest supersequence for sequences, or false otherwise.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
 * Output: false
 * Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
 * The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
 * The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
 * Since nums is not the only shortest supersequence, we return false.
 * Example 2:
 *
 * Input: nums = [1,2,3], sequences = [[1,2]]
 * Output: false
 * Explanation: The shortest possible supersequence is [1,2].
 * The sequence [1,2] is a subsequence of it: [1,2].
 * Since nums is not the shortest supersequence, we return false.
 * Example 3:
 *
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * Output: true
 * Explanation: The shortest possible supersequence is [1,2,3].
 * The sequence [1,2] is a subsequence of it: [1,2,3].
 * The sequence [1,3] is a subsequence of it: [1,2,3].
 * The sequence [2,3] is a subsequence of it: [1,2,3].
 * Since nums is the only shortest supersequence, we return true.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 104
 * nums is a permutation of all the integers in the range [1, n].
 * 1 <= sequences.length <= 10^4
 * 1 <= sequences[i].length <= 10^4
 * 1 <= sum(sequences[i].length) <= 10^5
 * 1 <= sequences[i][j] <= n
 * All the arrays of sequences are unique.
 * sequences[i] is a subsequence of nums.
 *
 */

public class _444_Sequence_Reconstruction {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        int[] incoming = new int[nums.length + 1];

        for (List<Integer> sequence : sequences) {

            for (int i = 0; i < sequence.size(); i++) {

                if (!adjacency.containsKey(sequence.get(i))) {
                    adjacency.put(sequence.get(i), new ArrayList<>());
                }
            }

            for (int i = 1; i < sequence.size(); i++) {
                adjacency.get(sequence.get(i - 1)).add(sequence.get(i));
                incoming[sequence.get(i)]++;
            }
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 1; i <= nums.length; i++) {

            if (incoming[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> topHolder = new ArrayList<>();

        while (!q.isEmpty()) {

            if (q.size() > 1) {
                return false;
            }
            int curr = q.poll();
            topHolder.add(curr);
            List<Integer> adj = adjacency.getOrDefault(curr, new ArrayList<>());

            for (Integer child : adj) {
                incoming[child]--;

                if (incoming[child] == 0) {
                    q.offer(child);
                }
            }
        }

        if (topHolder.size() != nums.length) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {

            if (topHolder.get(i) != nums[i]) {
                return false;
            }
        }
        return true;
    }
}
