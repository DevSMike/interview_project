package yandex_coderun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

/*
Вася очень любит везде искать своё счастливое число 𝐾
Каждый день он ходит в школу по улице, вдоль которой припарковано N машин.
Он заинтересовался вопросом, сколько существует отрезков из подряд идущих машин таких,
что сумма их номеров равна K. Помогите Васе узнать ответ на его вопрос.
 */
public class SumOfNumbers {

    public static void main(String[] args) {

        List<Integer> nAndKList;
        int n;
        int k;
        List<Integer> carsNumbersList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            nAndKList = readList(reader);
            n = nAndKList.get(0);
            k = nAndKList.get(1);
            carsNumbersList = readList(reader);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        int j = 0;
        int currentSum = 0;
        int counter = 0;

        for (int i = 0; i < carsNumbersList.size(); i++) {
            currentSum += carsNumbersList.get(i);

            while (currentSum > k && j <= i) {
                currentSum -= carsNumbersList.get(j);
                j++;
            }
            if (currentSum == k) {
                ++counter;
            }
        }

        System.out.println(counter);

    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
