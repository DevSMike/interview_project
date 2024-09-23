package yandex_coderun;

/*
В парке города Питсбурга есть чудесная аллея, состоящая из N посаженных в один ряд деревьев,
каждое одного из K сортов. В связи с тем, что Питсбург принимает открытый чемпионат Байтландии
по программированию, было решено построить огромную арену для проведения соревнований.
 Так, согласно этому плану вся аллея подлежала вырубке. Однако министерство деревьев и кустов
 воспротивилось этому решению, и потребовало оставить некоторые из деревьев в покое.
 Согласно новому плану строительства все деревья, которые не будут вырублены,
  должны образовывать один непрерывный отрезок, являющийся подотрезком исходного.
   Каждого из K видов деревьев требуется сохранить хотя бы по одному экземпляру.
   На вас возложена задача найти отрезок наименьшей длины, удовлетворяющий указанным ограничениям.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// --> найти минимальный отрезок , который содержит все виды кустов
public class BeautyAboveAllElse {
    public static void main(String[] args) throws IOException {
        int n, k;
        List<Integer> nAndKString;
        List<Integer> arr;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            nAndKString = getArray(reader);
            n = nAndKString.get(0);
            k = nAndKString.get(1);
            arr = getArray(reader);
        }

        int j = 0;
        int minLen = Integer.MAX_VALUE;
        int startPosition = 0;
        int endPosition = 0;
        Map<Integer, Integer> uniqueMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            uniqueMap.put(arr.get(i), uniqueMap.getOrDefault(arr.get(i), 0) + 1);
            while (uniqueMap.size() == k && i >= j) {
                if (i - j + 1 < minLen) {
                    minLen = i - j + 1;
                    startPosition = j + 1;
                    endPosition = i + 1;
                }
                uniqueMap.put(arr.get(j), uniqueMap.get(arr.get(j)) - 1);
                if (uniqueMap.get(arr.get(j)) == 0) {
                    uniqueMap.remove(arr.get(j));
                }
                j++;
            }
        }
        System.out.println(startPosition + " " + endPosition);
    }

    private static List<Integer> getArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
