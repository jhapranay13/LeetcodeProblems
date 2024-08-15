package leetcode.Arrays;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * Design a Snake game that is played on a device with screen size height x width. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0, 0) with a length of 1 unit.
 *
 * You are given an array food where food[i] = (ri, ci) is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game's score both increase by 1.
 *
 * Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.
 *
 * When a piece of food appears on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies after moving (i.e. a snake of length 4 cannot run into itself).
 *
 * Implement the SnakeGame class:
 *
 * SnakeGame(int width, int height, int[][] food) Initializes the object with a screen of size height x width and the positions of the food.
 * int move(String direction) Returns the score of the game after applying one direction move by the snake. If the game is over, return -1.
 *
 *
 * Example 1:
 *
 *              |    S -----    |         |
 *              |_______|__I____|_________|
 *              |       |       |          |
 *              |_______|_______|__________|
 *              |    f  |       |          |
 *              |_______|_______|__________|
 *
 * Input
 * ["SnakeGame", "move", "move", "move", "move", "move", "move"]
 * [[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
 * Output
 * [null, 0, 0, 1, 1, 2, -1]
 *
 * Explanation
 * SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
 * snakeGame.move("R"); // return 0
 * snakeGame.move("D"); // return 0
 * snakeGame.move("R"); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
 * snakeGame.move("U"); // return 1
 * snakeGame.move("L"); // return 2, snake eats the second food. No more food appears.
 * snakeGame.move("U"); // return -1, game over because snake collides with border
 *
 *
 * Constraints:
 *
 * 1 <= width, height <= 104
 * 1 <= food.length <= 50
 * food[i].length == 2
 * 0 <= ri < height
 * 0 <= ci < width
 * direction.length == 1
 * direction is 'U', 'D', 'L', or 'R'.
 * At most 104 calls will be made to move.
 *
 */

public class _353_DesignSnakeGame {

    class SnakeGame {
        Deque<int[]> body;
        Set<String> bodyset;
        int row;
        int col;
        int width;
        int height;
        int score;
        int foodindex;
        int[][] food;

        public SnakeGame(int width, int height, int[][] food) {
            this.body = new LinkedList<>();
            body.offer(new int[] {0, 0});
            this.bodyset = new HashSet<>();
            bodyset.add(0 + ", " + 0);
            this.row = 0;
            this.col = 0;
            this.width = width;
            this.height = height;
            this.score = 0;
            this.foodindex = 0;
            this.food = food;
        }

        public int move(String direction) {

            switch (direction) {
                case "U": {
                    row--;
                } break;
                case "D": {
                    row++;
                } break;
                case "L": {
                    col--;
                } break;
                case "R": {
                    col++;
                } break;
            }

            if (row < 0 || col < 0 || row >= height || col >= width) {
                return -1;
            }

            if (!body.isEmpty()) {
                int[] lastpos = body.peekLast();
                String key = row + ", " + col;
                //Checking to see if snake is not touching itself
                if (bodyset.contains(key) && !(row == lastpos[0] && col == lastpos[1])) {
                    return -1;
                }
            }

            return getScore();
        }

        private int getScore() {
            String key = row + ", " + col;
            int[] foodval = {-1, -1};

            if (foodindex < food.length) {
                foodval = food[foodindex];
            }

            if (foodval[0] == row && foodval[1] == col) {
                body.offerFirst(new int[] {row, col});
                bodyset.add(key);
                score++;
                foodindex++;
            } else {
                int[] temp = body.pollLast();
                String tkey = temp[0] + ", " + temp[1];
                bodyset.remove(tkey);
                body.offerFirst(new int[] {row, col});
                bodyset.add(key);
            }
            return score;
        }
    }

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
}
