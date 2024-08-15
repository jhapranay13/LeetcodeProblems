package leetcode.Graph.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * You are given a stream of n videos, each represented by a distinct number from 1 to n that you need to "upload" to a server. You need to implement a data structure that calculates the length of the longest uploaded prefix at various points in the upload process.
 *
 * We consider i to be an uploaded prefix if all videos in the range 1 to i (inclusive) have been uploaded to the server. The longest uploaded prefix is the maximum value of i that satisfies this definition.
 *
 * Implement the LUPrefix class:
 *
 * LUPrefix(int n) Initializes the object for a stream of n videos.
 * void upload(int video) Uploads video to the server.
 * int longest() Returns the length of the longest uploaded prefix defined above.
 *
 *
 * Example 1:
 *
 * Input
 * ["LUPrefix", "upload", "longest", "upload", "longest", "upload", "longest"]
 * [[4], [3], [], [1], [], [2], []]
 * Output
 * [null, null, 0, null, 1, null, 3]
 *
 * Explanation
 * LUPrefix server = new LUPrefix(4);   // Initialize a stream of 4 videos.
 * server.upload(3);                    // Upload video 3.
 * server.longest();                    // Since video 1 has not been uploaded yet, there is no prefix.
 *                                      // So, we return 0.
 * server.upload(1);                    // Upload video 1.
 * server.longest();                    // The prefix [1] is the longest uploaded prefix, so we return 1.
 * server.upload(2);                    // Upload video 2.
 * server.longest();                    // The prefix [1,2,3] is the longest uploaded prefix, so we return 3.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= video <= n
 * All values of video are distinct.
 * At most 2 * 105 calls in total will be made to upload and longest.
 * At least one call will be made to longest.
 *
 *
 */

public class _2424_Longest_Uploaded_Prefix {
    class LUPrefix {
        class UnionFind {
            int[] cache;
            int size[];

            public UnionFind(int size) {
                this.cache = new int[size];
                this.size = new int[size];

                for (int i = 0; i < size; i++) {
                    cache[i] = i;
                }
            }

            public int find(int x) {

                if (cache[x] != x) {
                    return cache[x] = find(cache[x]);
                }
                return x;
            }

            public void union(int x, int y) {

                if (size[x] == 0) {
                    size[x] = 1;
                }

                if (size[y] == 0) {
                    return;
                }
                int px = find(x);
                int py = find(y);

                if (px != py) {
                    cache[px] = py;
                    size[py] += size[px];
                }
            }
        }
        UnionFind uf;

        public LUPrefix(int n) {
            this.uf = new UnionFind(n + 2);
        }

        public void upload(int video) {
            uf.union(video, video - 1);
            uf.union(video, video + 1);
        }

        public int longest() {
            int parent = uf.find(1);
            return uf.size[parent];
        }
    }

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */
    //=============================================================================================
    // Hashset Solution
    class LUPrefix1 {
        Set<Integer> set;
        int maxSequence;

        public LUPrefix1(int n) {
            this.set = new HashSet<>();
            this.maxSequence = 0;
        }

        public void upload(int video) {
            set.add(video);

            while (set.contains(maxSequence + 1)) {
                maxSequence++;
            }
        }

        public int longest() {
            return maxSequence;
        }
    }

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */

}
