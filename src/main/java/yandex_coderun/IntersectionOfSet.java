package yandex_coderun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/*
Даны два списка чисел, которые могут содержать до 10000 чисел каждый.
Выведите все числа, которые входят как в первый,
так и во второй список в порядке возрастания.
 */
public class IntersectionOfSet {

    public static void main(String[] args) {

        List<Integer> arr1;
        List<Integer> arr2;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            arr1 = readList(reader);
            arr2 = readList(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
     1 2 6 4 5 7 --> 1 2 4 5 6 7
     10 2 3 4 8 --> 2 3 4 8 10
         */
        Collections.sort(arr1);
        Collections.sort(arr2);

        int j = 0;
        int i = 0;
        while (i < arr1.size() && j < arr2.size()) {
            if (Objects.equals(arr1.get(i), arr2.get(j))) {
                System.out.print(arr1.get(i) + " ");
                ++i;
                ++j;
            } else if (arr1.get(i) > arr2.get(j)) {
                ++j;
            } else {
                ++i;
            }

        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
