package core;

import java.util.*;

public class LeetCode698 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2,2,2,2};
        boolean b = canPartitionKSubsets(nums, 4);
        System.out.println(b);
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
        }
        if(sum % k != 0){
            return false;
        }
        Arrays.sort(nums); // 排序为了命中 bucket[i] + nums[index] > target，减小树的高度
        int l = 0 ;
        int r = nums.length - 1;
        while(l <= r){
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }

        int target = sum / k;
        int[] bucket = new int[k];
        return putBucket(nums,0,bucket,target);
    }

  public static boolean putBucket(int[] nums,int index,int[] bucket,int target){
      if(index == nums.length){
          return true;
      }
      for(int i = 0 ; i < bucket.length ; i++){
          if( i > 0 && bucket[i] == bucket[i-1]){ // 剪枝
              continue;
          }
          if(bucket[i] + nums[index] > target){
              continue;
          }
          bucket[i] += nums[index];
          putBucket(nums,index+1,bucket,target);
          bucket[i] -= nums[index];
      }
      return false;
  }
}
