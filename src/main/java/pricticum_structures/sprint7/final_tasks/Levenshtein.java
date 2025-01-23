package pricticum_structures.sprint7.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
yandex contest: https://contest.yandex.ru/contest/25597/run-report/132142361/

Требовалось реализовать алгоритм Левенштейна - поиска минимального количества операций для преобразования
одной строки в другую

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Пусть N = длина первой строки, M - длина второй строки.
Создается матрица dp[][] размерности N x M. Первая строка заполняется длинной этой строки, первый столбец заполняется
длинной этого столбца - потому что если длина другой строки 0, то минимальное число операций - это длина противоположной
строки. Цикл начинается от 1. Для каждой ячейки матрицы смотрим, какая операция дешевле: удаление/вставка/ замена.
Реализуем рекуррентную формулу алгоритма Вагнера-Фишера с помощью динамического программирования.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению является корректным, потому что для каждой маленькой подзадачи (для всех комбинаций длин двух строк)
будут посчитаны в ячейке dp[i][j] количество оптимальных операций. Итоговое количество оптимальных операций будет
храниться в ячейке dp[N][M]

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм совершит обход по всей матрице => Временная сложность = O(N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм хранит в памяти всю матрицу => Пространственная сложность = O(N * M)
 */
public class Levenshtein {

    public static void main(String[] args) throws IOException {
        String str1;
        String str2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str1 = reader.readLine();
            str2 = reader.readLine();
        }
        int result = levenshtein(str1, str2);
        System.out.println(result);
    }

    private static int levenshtein(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int j = 0; j <= n; j++) {
            dp[j][0] = j;
        }

        for (int i = 0; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[n][m];
    }
}
