package pricticum_structures.sprint6.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/*
yandex contest: https://contest.yandex.ru/contest/25070/run-report/131227364/

Требовалось реализовать поиск всех остравов

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Осуществляется обход матрицы, если элемент равен началу острова (#) и еще не был посещен, то мы увеличиваем счетчик остравов,
запускаем поиск в глубину от текущих координат. Результат этого поиска - длина острова. Сравниваем ее с максимальной,
при необходимости обновляем.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению явяляется корректным, потому что будут просмотрены все точки начала островов. Если часть острова была
посещена ранее, то алгоримт ее пропустит => количество остравов считается корректно. Всевозможные перемещения заданы в
массиве DIRECTIONS, т.е. мы сможем посетить только те точки, которые прпоисаны в условии. Если координата выходит за границы
матрицы, то она не будет рассмотрена => длина острова также рассчитывается корректно.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Пусть N - размер входных данных, N = rows * cols;
Тогда сложность dfs() будет O(N), в худшем случае вся матрица - целый остров.
Тогда итоговая сложность алгоритма O(N^2), т.к. мы пробегаемся по всей матрице, на каждом шаге выполняя dfs();
= > O(N^2).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пусть M = rows * cols - размер входных данных.
Мы храним матрицу - эо O(M) памяти
В dfs() используется вспомогательная структура - стек. Он может хранить в худшем случае M элементов => сложность O(M);
 ==> O(M^2) памяти
 */

public class WaterWorld {
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        int rows;
        int cols;
        char[][] matrix;
        boolean[][] visited;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            rows = Integer.parseInt(tokenizer.nextToken());
            cols = Integer.parseInt(tokenizer.nextToken());

            matrix = new char[rows][cols];
            visited = new boolean[rows][cols];

            for (int i = 0; i < rows; i++) {
                String row = reader.readLine();
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = row.charAt(j);
                }
            }
        }

        int maxLen = 0;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '#' && !visited[i][j]) {
                    count++;
                    int size = dfs(i, j, matrix, visited, rows, cols);
                    maxLen = Math.max(maxLen, size);
                }
            }
        }

        System.out.print(count + " " + maxLen);
    }

    private static int dfs(int x, int y, char[][] matrix, boolean[][] visited, int rows, int cols) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        visited[x][y] = true;
        int size = 0;

        while (!stack.empty()) {
            int[] current = stack.pop();
            size++;

            for (int i = 0; i < DIRECTIONS.length - 1; i++) {
                int newX = current[0] + DIRECTIONS[i];
                int newY = current[1] + DIRECTIONS[i + 1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && matrix[newX][newY] == '#' && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    stack.push(new int[]{newX, newY});
                }
            }
        }
        return size;
    }
}
