package old;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// using Collectors.groupingBy
public class MainGroupingBy {


    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Alice", 15),
                new Person("Bob", 17),
                new Person("Tim", 15),
                new Person("Dari", 17));
        Map<Integer, List<Person>> personByGroup = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        personByGroup.forEach((age, group) -> {
            System.out.println("Age: " + age + " Group: " + group);
        });
    }

    @Data
    @AllArgsConstructor
    private static class Person {
        private final String name;
        private final Integer age;
    }
}


