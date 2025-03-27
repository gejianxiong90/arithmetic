package leetcode;

import java.util.Arrays;

/**
 *
 * https://leetcode.cn/problems/3sum-closest/description/
 * 16. 最接近的三数之和
 中等
 相关标签
 相关企业
 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

 返回这三个数的和。

 假定每组输入只存在恰好一个解。



 示例 1：

 输入：nums = [-1,2,1,-4], target = 1
 输出：2
 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
 示例 2：

 输入：nums = [0,0,0], target = 1
 输出：0
 解释：与 target 最接近的和是 0（0 + 0 + 0 = 0）。

 */
public class ThreeSumClosest_16 {

    /**
     * a b c 三个指针
     * 将nums做排序
     * 枚举a ， b 和c 指针在两边往中间走
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = Integer.MAX_VALUE;
        for (int a = 0 ;a < n ; a++){
            if(a > 0 && nums[a] == nums[a - 1]){ //加速
                continue;
            }
            int b = a + 1;
            int c = n - 1;
            while( b < c){
                int sum = nums[a] + nums[b] + nums[c];
                if(sum == target){
                    return sum;
                }
                if(Math.abs(sum - target) < Math.abs(best - target)){
                    best = sum;
                }
                if(sum > target){
                    int c0 = c - 1;
                    while(c0 > b && nums[c0] == nums[c]){
                        c0--;
                    }
                    c = c0;
                }else {
                    int b0 = b + 1;
                    while(b0 < c && nums[b0] == nums[b]){
                        b0++;
                    }
                    b = b0;
                }
            }
        }
        return best;
    };
}
