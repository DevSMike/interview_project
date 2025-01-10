package yandex_coderun;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Дан сортированный по возрастанию массив интов.
Необходимо собрать упорядоченный по вазрастанию массив из квадратов элементов

 пример: [-3, 2, 4] -> [4, 9, 16]
 */
public class SortSquareList {

    public static void main(String[] args) throws IOException {

       List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            arr = Arrays.stream(reader.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        int n = arr.size();
        int right = n - 1;
        int left = 0;
        int index = n - 1;

        int[] result = new int[n];
        while (left <= right) {
            if (Math.abs(arr.get(left)) > Math.abs(arr.get(right))) {
                result[index] = arr.get(left) * arr.get(left);
                left++;
            } else {
                result[index] = arr.get(right) * arr.get(right);
                right--;
            }
            index--;
        }
        for (int elem : result) {
            System.out.print(elem + " ");
        }
    }
}
