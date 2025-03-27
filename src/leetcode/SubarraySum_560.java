package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum_560 {

    public int subarraySum(int[] nums, int k) {
        int count = 0,pre = 0 ;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);//出现0的次数为1次
        for(int i = 0 ; i < nums.length ; i++){
            pre += nums[i];
            if(map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre,map.getOrDefault(pre,0) + 1);
        }
        return count;
    }
}
