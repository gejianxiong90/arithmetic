package leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150
 *
 *
 */
public class RandomizedSet_380 {
    HashMap<Integer,Integer> map; // key：数值 value：nums中的index
    List<Integer> nums; //具体数值，删除时用最后一个替换掉删除的 nums[index] = map的key，index通过nums.size算出出来
    Random random;
    public RandomizedSet_380() {
        map = new HashMap<>();
        nums = new ArrayList<Integer>();
        random = new Random();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        int index = nums.size();
        map.put(val,index);
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int index = map.get(val); //获取删除的存放的index
        int last = nums.get(nums.size() - 1); //将最后一个值取出，移动到index位置
        nums.set(index,last); //所有的添加要在删除之前。先删除后别的数值可能已经占位
        nums.remove(nums.size() - 1);//删除最后一个
        map.put(last,index);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int size = nums.size();
        return nums.get(random.nextInt(size));
    }
}
