package leetcode;

import java.util.Arrays;

public class Merge {
//    给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
//
//
//
//    说明:
//
//    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
//    你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
//    示例:
//
//    输入:
//    nums1 = [1,2,3,0,0,0], m = 3
//    nums2 = [2,5,6],       n = 3
//
//    输出: [1,2,2,3,5,6]


    public static void main(String[] args) {
      int[] arr1 =  {1,2,3,0,0,0};
       int arr1Len = 3;
         int[] arr2 = {2,5,6};
        int arr2Len = 3;
        merge(arr1,arr1Len,arr2,arr2Len);

        System.out.println(Arrays.toString(arr1));

    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m+n];
        int p0 = 0;
        int p1 = 0;
        int p2 = 0;

        while(p1 < m && p2 <n){
            temp[p0++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }


        while(p1 < m){
            temp[p0++] = nums1[p1++];
        }

        while(p2 < n){
            temp[p0++] = nums2[p2++];
        }

        for(int k =0 ;k < temp.length;k++){
            nums1[k] = temp[k];
        }

        System.out.println(Arrays.toString(nums1));


    }

}
