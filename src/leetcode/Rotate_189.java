package leetcode;

import java.util.Arrays;

public class Rotate_189 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        rotate(arr,2);
    }

    public static void rotate(int[] nums, int k) {
        if(nums.length == 1 || k == 0){
            return;
        }
        int n = nums.length;
        int[] newNums = new int[n];
        for(int i = 0 ; i < n ;i++){
            newNums[(i+k) % n] = nums[i];
        }
        System.arraycopy(newNums,0,nums,0,n);

    }
}
