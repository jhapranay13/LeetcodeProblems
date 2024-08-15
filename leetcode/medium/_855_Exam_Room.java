package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 *
 *
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.
 *
 * Design a class that simulates the mentioned exam room.
 *
 * Implement the ExamRoom class:
 *
 * ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 * int seat() Returns the label of the seat at which the next student will set.
 * void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.
 *
 *
 * Example 1:
 *
 * Input
 * ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 * [[10], [], [], [], [], [4], []]
 * Output
 * [null, 0, 9, 4, 2, null, 5]
 *
 * Explanation
 * ExamRoom examRoom = new ExamRoom(10);
 * examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
 * examRoom.seat(); // return 9, the student sits at the last seat number 9.
 * examRoom.seat(); // return 4, the student sits at the last seat number 4.
 * examRoom.seat(); // return 2, the student sits at the last seat number 2.
 * examRoom.leave(4);
 * examRoom.seat(); // return 5, the student sits at the last seat number 5.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^9
 * It is guaranteed that there is a student sitting at seat p.
 * At most 104 calls will be made to seat and leave.
 *
 *
 */

public class _855_Exam_Room {
    class ExamRoom {
        TreeSet<Integer> holder;
        int n;

        public ExamRoom(int n) {
            this.holder = new TreeSet<>();
            this.n = n;
        }

        public int seat() {

            if (holder.size() == 0) {
                holder.add(0);
                return 0;
            }
            int prev = -1;
            int distance = -1;
            int seat = -1;

            for (int key : holder) {

                if (prev == -1) {
                    distance = key;

                    if (key != 0) {
                        seat = 0;
                    }
                } else if (key - prev != -1) {
                    int newDist = (key - prev) / 2;

                    if (newDist > distance) {
                        seat = (key + prev) / 2;
                        distance = newDist;
                    }
                }
                prev = key;
            }

            if (!holder.contains(n - 1) && n - 1 - prev > distance) {
                seat = n - 1;
            }
            holder.add(seat);
            return seat;
        }

        public void leave(int p) {
            holder.remove(p);
        }
    }

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
    //=============================================================================================
    //ArrayList Implementation
    class ExamRoom1 {
        List<Integer> holder;
        int n;

        public ExamRoom1(int n) {
            this.holder = new ArrayList<>();
            this.n = n;
        }

        public int seat() {
            List<Integer> temp = holder;
            if (holder.size() == 0) {
                holder.add(0);
                return 0;
            }
            int seat = -1;
            int prev = -1;
            int radius = 0;

            for (int i = 0; i < holder.size(); i++) {

                if (i == 0 && holder.get(0) != 0){
                    seat = 0;
                    radius = holder.get(0);
                } else {
                    int tempRadius = (holder.get(i) - prev) / 2;
                    int pos = prev + tempRadius;

                    if (tempRadius > radius) {
                        radius = tempRadius;
                        seat = pos;
                    }
                }
                prev = holder.get(i);
            }

            if (holder.get(holder.size() - 1) != n - 1 && (n  - holder.get(holder.size() - 1) - 1) > radius) {
                seat = n - 1;
            }
            boolean isAdded = false;

            for (int i = 0; i <= holder.size() - 1; i++) {

                if (holder.get(i) > seat) {
                    holder.add(i, seat);
                    isAdded = true;
                    break;
                }
            }

            if (!isAdded) {
                holder.add(holder.size(), seat);
            }
            return seat;
        }

        public void leave(int p) {

            for (int i = 0; i < holder.size(); i++) {

                if (holder.get(i) == p) {
                    holder.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Your ExamRoom object will be instantiated and called as such:
     * ExamRoom obj = new ExamRoom(n);
     * int param_1 = obj.seat();
     * obj.leave(p);
     */
    //=============================================================================================
    // MLE in array
    class ExamRoom2 {
        int[] holder;
        int n;
        int size;

        public ExamRoom2(int n) {
            this.holder = new int[n];
            this.n = n;
            Arrays.fill(this.holder, -1);
            size = 0;
        }

        public int seat() {

            if (size == 0) {
                holder[0] = 0;
                size++;
                return 0;
            }

            if (size == 1 && holder[0] != -1) {
                holder[n - 1] = n - 1;
                size++;
                return n - 1;
            }
            int seat = -1;
            int prev = -1;
            int radius = 0;

            for (int i = 0; i < n; i++) {

                if (holder[i] != -1) {

                    if (prev == -1 ){
                        seat = 0;
                        radius = i;
                        prev = i;
                    } else {
                        int tempRadius = (i - prev) / 2;
                        int pos = prev + tempRadius;

                        if (tempRadius > radius) {
                            radius = tempRadius;
                            seat = pos;
                        }
                        prev = i;
                    }
                }
            }

            if (holder[n - 1] == -1 && (n  - prev - 1) > radius) {
                seat = n - 1;
            }
            holder[seat] = seat;
            size++;
            return seat;
        }

        public void leave(int p) {
            size--;
            holder[p] = -1;
        }
    }

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
}
