package pricticum_structures.sprint7.final_tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/*
yandex contest: https://contest.yandex.ru/contest/25597/run-report/132096696/

Требовалось реализовать алгоритм поиска возможности деления суммы на две части, чтобы сумма в них была одинакова.

-- ПРИНЦИП РАБОТЫ АЛГОРИТМА --
Пусть N - размер массива с очками. Пусть sum - сумма очков. Если сумма нечетная - сразу False.
Мы считаем половину суммы: target = sum / 2. Создаем массив boolean dp[] размерности target + 1. dp[0] = true, потому
что сумму ноль можно разделить по условию задачи. Для каждого очка из массива проверяем, что можно получить нужную
сумму target.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Такой подход к решению является корректным, потому что будут проверены все подзадачи нахождения полусуммы. В итоге будет
массив boolean, где dp[target] будет ответом на задачу.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
N - размерность массива. M - полусумма массива. Временная сложность алгоритма: O (N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
N - размерность массива. M - полусумма массива. Пространственная сложность алгоритма: O (N + M)
 */
public class CommonSums {
    public static void main(String[] args) throws IOException {
        int n;
        int[] points;
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            points = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                points[i] = Integer.parseInt(tokenizer.nextToken());
                sum += points[i];
            }
        }

        if (sum % 2 != 0) {
            System.out.println("False");
            return;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int point : points) {
            for (int j = target; j >= point; j--) {
                dp[j] = dp[j] || dp[j - point];
            }
        }

        if (dp[target]) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
