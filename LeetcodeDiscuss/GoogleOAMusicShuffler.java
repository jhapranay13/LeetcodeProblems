package LeetcodeDiscuss;

import java.util.*;

/**
 *
 * Design a music playlist shuffler (music are represented as array of anything(can be integer, string, etc))
 *
 * It should always return a random music.
 * The returned music should not be returned again for the next K plays.
 * Randomness was expected similar to what rand() method provide in C++. I came up a solution using a Queue having time complexity of O(n) and space complexity of O(n).
 * The interviewer asked me to furhter optimize the same. I was able to come up a O(1) solution having O(K) space complexity.
 *
 */

public class GoogleOAMusicShuffler {

    public class MusicShuffler<T> {
        private final List<T> songs;
        private final int K;
        private final Set<T> recentSet = new HashSet<>();
        private final Queue<T> recentQueue = new LinkedList<>();
        private final Random rand = new Random();

        public MusicShuffler(List<T> songs, int K) {
            this.songs = songs;
            this.K = K;
        }

        public T next() {
            T candidate;
            int attempts = 0;

            // Max attempts = songs.size() to avoid infinite loop
            do {
                candidate = songs.get(rand.nextInt(songs.size()));
                attempts++;
            } while (recentSet.contains(candidate) && attempts < songs.size());

            // Update recent history
            if (recentQueue.size() == K) {
                T removed = recentQueue.poll();
                recentSet.remove(removed);
            }

            recentQueue.offer(candidate);
            recentSet.add(candidate);

            return candidate;
        }
    }

}
