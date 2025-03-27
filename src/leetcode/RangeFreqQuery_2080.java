package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * 2080. 区间内查询数字的频率
 中等
 相关标签
 相关企业
 提示
 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。

 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。

 请你实现 RangeFreqQuery 类：

 RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。



 示例 1：

 输入：
 ["RangeFreqQuery", "query", "query"]
 [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 输出：
 [null, 1, 2]

 解释：
 RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
 rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
 */
public class RangeFreqQuery_2080 {


    HashMap<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
    public RangeFreqQuery_2080(int[] arr) {
        for(int i = 0 ; i < arr.length ; i++){
            map.putIfAbsent(arr[i],new ArrayList<Integer>());
            map.get(arr[i]).add(i);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> orDefault = map.getOrDefault(value, new ArrayList<>());
        // 两次二分查找计算子数组内出现次数
        int i = lowCount(orDefault, left);
        int j = upperCount(orDefault, right);
        return j - i;
    }

    // 计算这个value所在arr中index位置 小于left
    public int lowCount(List<Integer> indexList,int left){
        int low = 0 , high = indexList.size() - 1;
        while(low <= high){
          int mid = low + (high - low) / 2;
          if(indexList.get(mid) < left){
              low = mid + 1;
          }else{
              high = mid - 1;
          }
        }
        return low;
    }
    // 计算这个value所在arr中index位置 小于等于right
    public int upperCount(List<Integer> indexList,int right){
        int low = 0 , high = indexList.size() - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(indexList.get(mid) <= right){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
}
