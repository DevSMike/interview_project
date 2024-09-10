package old;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //  int[] nums = {1, 4, 5, 2, 3, 9, 8, 11, 0}; //"0-5, 8-9, 11"
        int[] nums = {1, 4};
        // 0, 1,2,3,4,5,8,9, 11
        System.out.println(compress(nums));
    }

    // 1, 4
    public static String compress(int[] arr) {
        Arrays.sort(arr);
        StringBuilder result = new StringBuilder();
        int startElem = arr[0];
        int endElem = -1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == 1) {
                endElem = arr[i + 1];
            } else if (endElem == -1) {
                result.append(startElem).append(",");
                startElem = arr[i + 1];
            } else {
                result.append(startElem).append("-").append(endElem).append(",");
                startElem = arr[i + 1];
            }
        }
        if (startElem > endElem) {
            result.append(startElem);
        } else {
            result.append(startElem).append("-").append(endElem);
        }
        return result.toString();
    }
}
