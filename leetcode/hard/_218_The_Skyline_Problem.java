package leetcode.hard;

import java.util.*;

/**
 *A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 *
 *
 * Example 1:
 *
 *      ^
 *      |     *______
 *      |     |     |
 *      |     |   __*____
 *      |     |  |      |
 *      |   *_|__|__    |      *_______
 *      |   |      |    |     |     __|*__
 *      |   |      |    |     |    |     |
 *      |   |      |    |     |    |     |
 *      |   |      |    |     |    |     |
 *      |   |      |    |     |    |     |
 *      |   |      |    |     |    |     |
 *      |   |      |    *     |    |     *
 *      -------------------------------------------------------->
 *
 *
 *
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * Example 2:
 *
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 *
 * Constraints:
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 *
 *
 */

public class _218_The_Skyline_Problem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return y - x;
            }
        });
        List<int[]> verticalLines = new ArrayList<>();

        for (int[] building : buildings) {
            verticalLines.add(new int[] {building[0], -building[2]});
            verticalLines.add(new int[] {building[1], building[2]});
        }
        Map<Integer, Integer> excludeMap = new HashMap<>();

        Collections.sort(verticalLines, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {

                if (x[0] == y[0]) {
                    return x[1] - y[1];
                }
                return x[0] - y[0];
            }
        });
        int prevHeight = 0;
        pq.add(0);
        List<List<Integer>> ans = new ArrayList<>();

        for (int[] building : verticalLines) {
            int x = building[0];
            int h = building[1];

            if (h < 0) {
                pq.offer(-h);
            } else {
                excludeMap.put(h, excludeMap.getOrDefault(h, 0) + 1);
            }

            while (!pq.isEmpty()) {

                if (excludeMap.containsKey(pq.peek())) {

                    if (excludeMap.get(pq.peek()) == 1) {
                        excludeMap.remove(pq.peek());
                    } else {
                        excludeMap.put(pq.peek(), excludeMap.get(pq.peek()) - 1);
                    }
                    pq.poll();
                } else {
                    break;
                }
            }
            int curr = pq.peek();

            if (curr != prevHeight) {
                List<Integer> np = Arrays.asList(x, curr);
                ans.add(np);
                prevHeight = curr;
            }
        }
        return ans;
    }
    //=============================================================================================
    //TreeMap Solution
    public List<List<Integer>> getSkyline1(int[][] buildings) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        List<int[]> heightList = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int[] building : buildings) {
            heightList.add(new int[] {building[0], -building[2]});
            heightList.add(new int[] {building[1], building[2]});
        }
        Collections.sort(heightList, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int[] verticalLine : heightList) {
            int x = verticalLine[0];
            int h = verticalLine[1];

            if (h < 0) {
                Integer ceilingHeight = map.ceilingKey(-h);

                if (ceilingHeight == null || ceilingHeight < -h) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(x);
                    temp.add(-h);
                    ans.add(temp);
                }
                map.put(-h, map.getOrDefault(-h, 0) + 1);
            } else {

                if (map.containsKey(h)) {

                    if (map.get(h) == 1) {
                        map.remove(h);
                        Integer ceilingHeight = map.ceilingKey(h);

                        if (ceilingHeight != null) {
                            continue;
                        }
                        Integer floorHeight = map.floorKey(h);

                        if (floorHeight != null) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(x);
                            temp.add(floorHeight);
                            ans.add(temp);
                        } else {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(x);
                            temp.add(0);
                            ans.add(temp);
                        }
                    } else {
                        map.put(h, map.get(h) - 1);
                    }
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // Segment tree Solution
    class SegmentTree {
        Map<Long, Integer> cache;
        long n;

        public SegmentTree(long n) {
            this.n = n * 4;
            this.cache = new HashMap<>();
        }

        public void update(long index, int val) {
            Map<Long, Integer> temp = cache;
            if (index < 0 || index > n - 1) {
                return;
            }
            update(0, n - 1, index, val, 0);
        }

        private void update(long lo, long hi, long index, int val, long cacheIndex) {
            Map<Long, Integer> temp = cache;

            if (lo == index && hi == index) {
                cache.put(cacheIndex, val);
                return;
            }

            if (index < lo || index > hi) {
                return;
            }
            long pivot = lo + (hi - lo) / 2;
            update(lo, pivot, index, val, 2 * cacheIndex + 1);
            update(pivot + 1, hi, index, val, 2 * cacheIndex + 2);
            cache.put(cacheIndex, Math.max(cache.getOrDefault(2 * cacheIndex + 1, 0),
                    cache.getOrDefault(2 * cacheIndex + 2, 0)));
        }

        public int getMax(long lo, long hi) {
            Map<Long, Integer> temp = cache;

            if (lo < 0 || hi > n - 1 || lo > hi) {
                return 0;
            }
            return getMax(0, n - 1, lo, hi, 0);
        }

        private int getMax(long lo, long hi, long qlo, long qhi, long cacheIndex) {
            Map<Long, Integer> temp = cache;

            if (qlo > hi || qhi < lo) {
                return 0;
            }

            if (qlo <= lo && qhi >= hi) {
                return cache.getOrDefault(cacheIndex, 0);
            }
            long pivot = (long)lo + (long)(hi - lo) / 2;
            int left = getMax(lo, pivot, qlo, qhi, 2 * cacheIndex + 1);
            int right = getMax(pivot + 1, hi, qlo, qhi, 2 * cacheIndex + 2);
            return Math.max(left, right);
        }
    }

    public List<List<Integer>> getSkyline3(int[][] buildings) {
        List<int[]> holder = new ArrayList<>();

        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            holder.add(new int[] {building[0], i, -building[2]});
            holder.add(new int[] {building[1], i, building[2]});
        }
        Collections.sort(holder, (a, b) -> a[0] == b[0] ? a[2] - b[2] : a[0] - b[0]);
        List<List<Integer>> ans = new ArrayList<>();
        SegmentTree sgt = new SegmentTree(buildings.length + 1);
        Map<Integer, Integer> reverseIndexMap = new HashMap<>();

        for (int i = 0; i < holder.size(); i++) {
            int curr[] = holder.get(i);

            if (curr[2] < 0) {
                int tempMax = sgt.getMax(0, i);
                sgt.update(i, -curr[2]);

                if (tempMax < -curr[2]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(curr[0]);
                    temp.add(-curr[2]);
                    ans.add(temp);
                }
                reverseIndexMap.put(curr[1], i);
            } else {
                int indx = reverseIndexMap.get(curr[1]);
                sgt.update(indx, 0);
                int tempMax = sgt.getMax(0, i);

                if (tempMax < curr[2]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(curr[0]);
                    temp.add(tempMax);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    //=============================================================================================
    // Plain PriorityQueue Solution
    public List<List<Integer>> getSkyline4(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        // Step 1: Create events
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Step 2: Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Step 3: Max heap for heights
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0);

        int prevMax = 0;
        List<List<Integer>> result = new ArrayList<>();

        // Step 4: Sweep line
        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                maxHeap.add(-h); // start building
            } else {
                maxHeap.remove(h); // end building
            }

            int currMax = maxHeap.peek();
            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
    //=============================================================================================
    // Better PriorityQueue Solution
    public List<List<Integer>> getSkyline5(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        // Create events
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Max heap
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        // Height count map
        Map<Integer, Integer> count = new HashMap<>();

        pq.offer(0);
        count.put(0, 1);

        int prevMax = 0;
        List<List<Integer>> res = new ArrayList<>();

        // Sweep line
        for (int[] e : events) {
            int x = e[0];
            int h = e[1];

            if (h < 0) {
                // Start building
                int height = -h;
                pq.offer(height);
                count.put(height, count.getOrDefault(height, 0) + 1);
            } else {
                // End building
                count.put(h, count.get(h) - 1);
            }

            // Remove inactive heights
            while (!pq.isEmpty() && count.get(pq.peek()) == 0) {
                pq.poll();
            }

            int currMax = pq.peek();
            if (currMax != prevMax) {
                res.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return res;
    }
    //=============================================================================================
    // Better TreeMap Solution
    public List<List<Integer>> getSkyline6(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        // Step 1: Create events
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]}); // start
            events.add(new int[]{b[1], b[2]});  // end
        }

        // Step 2: Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        // Step 3: TreeMap to store heights
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        heightMap.put(0, 1); // ground level

        int prevMax = 0;
        List<List<Integer>> res = new ArrayList<>();

        // Step 4: Sweep line
        for (int[] e : events) {
            int x = e[0];
            int h = e[1];

            if (h < 0) {
                // Start building
                int height = -h;
                heightMap.put(height, heightMap.getOrDefault(height, 0) + 1);
            } else {
                // End building
                int cnt = heightMap.get(h) - 1;
                if (cnt == 0) {
                    heightMap.remove(h);
                } else {
                    heightMap.put(h, cnt);
                }
            }

            int currMax = heightMap.lastKey();
            if (currMax != prevMax) {
                res.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return res;
    }
}
