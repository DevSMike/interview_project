package leetcode;

/*

Места в кинотеатре расположены в один ряд.
Чтобы сидеть максимально далеко от остальных зрителей в ряду,
 пришедший зритель выбирает место, где расстояние от него до ближайшего
 к нему зрителя будет наибольшим. Гарантируется, что в ряду всегда есть
 свободное место и уже сидит хотя бы один зритель.
 Напишите функцию, которая по заданному ряду мест (массиву из нулей и единиц)
 вернет расстояние от выбранного места до ближайшего зрителя.

// Input: [1, 0, 0, 0, 1]
// Output: 2

// Input: [1, 0, 1, 0, 1, 0, 0, 0, 1]
// Output: 2

// Input: [1, 0, 1, 0]
// Output: 1

 */
public class Cinema {
    public static int maxDistance(int[] seats) {
        int max = 0;
        int prev = -1;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (prev == -1) {
                    max = i;
                } else {
                    max = Math.max(max, (i - prev) / 2);
                }
                prev = i;
            }
        }

        max = Math.max(max, seats.length - 1 - prev);
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 0, 1};
        System.out.println(maxDistance(arr));
    }
}
