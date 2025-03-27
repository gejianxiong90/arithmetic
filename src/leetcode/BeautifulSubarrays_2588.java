package leetcode;

import java.util.HashMap;

/**
 *
 * 2588. 统计美丽子数组数目
 中等
 相关标签
 相关企业
 提示
 给你一个下标从 0 开始的整数数组nums 。每次操作中，你可以：

 选择两个满足 0 <= i, j < nums.length 的不同下标 i 和 j 。
 选择一个非负整数 k ，满足 nums[i] 和 nums[j] 在二进制下的第 k 位（下标编号从 0 开始）是 1 。
 将 nums[i] 和 nums[j] 都减去 2k 。
 如果一个子数组内执行上述操作若干次后，该子数组可以变成一个全为 0 的数组，那么我们称它是一个 美丽 的子数组。

 请你返回数组 nums 中 美丽子数组 的数目。

 子数组是一个数组中一段连续 非空 的元素序列。



 示例 1：

 输入：nums = [4,3,1,2,4]
 输出：2
 解释：nums 中有 2 个美丽子数组：[4,3,1,2,4] 和 [4,3,1,2,4] 。
 - 按照下述步骤，我们可以将子数组 [3,1,2] 中所有元素变成 0 ：
 - 选择 [3, 1, 2] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [1, 1, 0] 。
 - 选择 [1, 1, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 0, 0] 。
 - 按照下述步骤，我们可以将子数组 [4,3,1,2,4] 中所有元素变成 0 ：
 - 选择 [4, 3, 1, 2, 4] 和 k = 2 。将 2 个数字都减去 22 ，子数组变成 [0, 3, 1, 2, 0] 。
 - 选择 [0, 3, 1, 2, 0] 和 k = 0 。将 2 个数字都减去 20 ，子数组变成 [0, 2, 0, 2, 0] 。
 - 选择 [0, 2, 0, 2, 0] 和 k = 1 。将 2 个数字都减去 21 ，子数组变成 [0, 0, 0, 0, 0] 。
 示例 2：

 输入：nums = [1,10,4]
 输出：0
 解释：nums 中没有任何美丽子数组。

 *
 */
public class BeautifulSubarrays_2588 {

    public static void main(String[] args) {
        System.out.println(3 ^ 5);
    }

    public long beautifulSubarrays(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        int mask = 0;
        long ans = 0;
        cnt.put(0,1);
        for(int x : nums){
            mask ^= x;
            ans += cnt.getOrDefault(mask,0);
            cnt.put(mask,cnt.getOrDefault(mask,0)+1);
        }

        return ans;
    }
}
