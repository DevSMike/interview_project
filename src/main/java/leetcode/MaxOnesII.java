package leetcode;

/*
Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

Input: nums = [1,0,1,1,0,1]
Output: 4
Explanation:
- If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
- If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
The max number of consecutive ones is 4.
 */

public class MaxOnesII {

    public static void main(String[] args) {
        int[] arr = {1,0,1,1,0,1};
        System.out.println(maxOnes(arr));
    }

    private static int maxOnes(int[] arr) {
        int r = 0;
        int l = 0;
        int zeroCounter = 0;
        int maxLen = 0;


        while (r < arr.length) {
            if (arr[r] == 0) {
                zeroCounter++;
            }

            while (zeroCounter > 1) {
                if (arr[l] == 0) {
                    zeroCounter--;
                }
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        return maxLen;
    }
}
