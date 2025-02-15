package leetcode;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] result = new int[k];
        int idx = 0;

        List<Integer> list = map.entrySet().stream()
                .sorted((a,b)-> -1 * Integer.compare(a.getValue(), b.getValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        for (int key : list) {
            result[idx++] = key;
            if (idx == k) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;

        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
