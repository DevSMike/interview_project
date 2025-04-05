package leetcode;

import java.util.Stack;

public class Islands {

    private static final int[][] WAYS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        System.out.println("-----------START----------");

        int[][] picture1 = new int[][]{
                {2, 2, 2, 1},
                {1, 2, 2, 1},
                {1, 3, 1, 1},
                {1, 1, 1, 3}
        };

        System.out.println(strokesRequired(picture1)); // expected 4

        int[][] picture2 = new int[][]{
                {1, 1, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 3, 1}
        };

        System.out.println(strokesRequired(picture2)); // expected 5
        System.out.println("-----------FINISH----------");

    }


    private static int strokesRequired(int[][] arr) {
        //your code
        int counter = 0;
        int rows = arr.length;
        int cols = arr[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    dfs(arr, visited, i, j, arr[i][j]);
                    counter++;
                }
            }
        }

        return counter;

    }


    private static void dfs (int [][] arr, boolean[][] visited, int x, int y, int target) {
        Stack<int[]> stack = new Stack<>();
        int rows = arr.length;
        int cols = arr[0].length;

        stack.push(new int[]{x,y});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();

            for (int[] coordinates : WAYS) {
                int newX = current[0] + coordinates[0];
                int newY = current[1] + coordinates[1];

                if (newX < rows && newX >= 0 && newY < cols && newY >= 0 && !visited[newX][newY] && arr[newX][newY] == target) {
                    stack.push(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }
    }
}
