package old;

import old.personAPI.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// sort map like value : key for each value
public class SortedListMain {

    public static void main(String[] args) {
        List<Product> persons = List.of(
                new Product("4", "P1"),
                new Product("3", "P2"),
                new Product("1", "P3"),
                new Product("2", "P4"));

        System.out.println(persons.stream().sorted((p1, p2) -> {
            int idOne = Integer.parseInt(p1.getId());
            int idTwo = Integer.parseInt(p2.getId());
            return Integer.compare(idOne, idTwo);
        }).collect(Collectors.toList()));

        // continue

        Map<Integer, List<Long>> inputMap = new HashMap<>();
        inputMap.put(1, List.of(0L, 1L, 2L));
        inputMap.put(2, List.of(3L, 4L));

        Map<Long, Integer> outputMap = inputMap.entrySet().stream()
                .flatMap(entry -> {
                    var key = entry.getKey();
                    var value = entry.getValue();
                    return value.stream().map(num -> Map.entry(num, key));
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("OutMap: " + outputMap);
    }
}
