package yandex_coderun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
В центре города Че есть пешеходная улица - одно из самых популярных мест для прогулок жителей города.
По этой улице очень приятно гулять, ведь вдоль улицы расположено n забавных памятников.

Девочке Маше из города Че нравятся два мальчика из её школы, и она никак не может сделать выбор между ними.
 Чтобы принять окончательное решение, она решила назначить обоим мальчикам свидание в одно и то же время.
  Маша хочет выбрать два памятника на пешеходной улице, около которых мальчики будут её ждать.
  При этом она хочет выбрать такие памятники, чтобы мальчики не увидели друг друга.
  Маша знает, что из-за тумана мальчики увидят друг друга только в том случае, если они будут на расстоянии не более r метров.

Маша заинтересовалась, а сколько способов есть выбрать два различных памятника для организации свиданий?
 */
public class TheCityChe {

    public static void main(String[] args) throws IOException {

        List<Long> nAndRList;
        Long n;
        Long r;
        List<Long> monumentsDistToStreetList;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            nAndRList = readList(reader);
            n = nAndRList.get(0);
            r = nAndRList.get(1);
            monumentsDistToStreetList = readList(reader);
        }
        int i = 0;
        int j = 1;
        int counter = 0;
        while (i < n - 1 && j < n) {
            if (monumentsDistToStreetList.get(j) - monumentsDistToStreetList.get(i) <= r) {
                j++;
            } else {
                counter += n - j;
                i++;
            }
        }
        System.out.println(counter);
    }

    private static List<Long> readList(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        List<Long> result = new ArrayList<>();
        int countTokens = tokenizer.countTokens();
        while (countTokens > 0) {
            result.add(Long.parseLong(tokenizer.nextToken()));
            countTokens--;
        }
        return result;
    }

}
