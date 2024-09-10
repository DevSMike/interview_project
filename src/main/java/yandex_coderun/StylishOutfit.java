package yandex_coderun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Глеб обожает шоппинг. Как-то раз он загорелся идеей подобрать себе майку и штаны так,
чтобы выглядеть в них максимально стильно. В понимании Глеба стильность одежды тем больше,
 чем меньше разница в цвете элементов его одежды.

 В наличии имеется N (1 <= N <= 100_000) маек и M (1 <= M <= 100_000) штанов
 про каждый элемент известен его цвет (целое число от 1 до 10_000_000).
 Помогите Глебу выбрать одну майку и одни штаны так, чтобы разница в их цвете была как можно меньше.
 */
public class StylishOutfit {

    public static void main(String[] args) {

        int n; // кол во маек
        List<Long> tShortColorArr;
        int m; // кол во штанов
        List<Long> pantsColorArr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = readNumber(reader);
            tShortColorArr = readList(reader);
            m = readNumber(reader);
            pantsColorArr = readList(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        int j = 0;
        long minDif = Long.MAX_VALUE;
        List<Long> result = new ArrayList<>();
        while (i < pantsColorArr.size() && j < tShortColorArr.size()) {
            if (Math.abs(tShortColorArr.get(j) - pantsColorArr.get(i)) < minDif) {
                minDif = Math.abs(tShortColorArr.get(j) - pantsColorArr.get(i));
                result.clear();
                result.add(tShortColorArr.get(j));
                result.add(pantsColorArr.get(i));
            }
            if (pantsColorArr.get(i).equals(tShortColorArr.get(j))) {
                break;
            } else if (pantsColorArr.get(i) > tShortColorArr.get(j)) {
                ++j;
            } else {
                ++i;
            }
        }
        result.forEach(x->System.out.print(x + " "));
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private static Integer readNumber(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine().strip());
    }
}
