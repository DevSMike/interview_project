package old;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStringReader {

    public static void main(String[] args) throws IOException {
        List<Integer> arr1;
        List<Integer> arr2;
        int n;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine().strip());
            arr1 = bufferedReadingIntegerArr(reader);
            arr2 = bufferedReadingIntegerArr(reader);
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            writer.write(String.valueOf(arr1.get(i)));
            writer.write(" ");
            writer.write(String.valueOf(arr2.get(i)));
            writer.write(" ");
        }
        writer.flush();
    }

    public static List<Integer> bufferedReadingIntegerArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
