package leetcode;

public class SingleNum {


    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        int i = singleNumber(nums);
        System.out.println(i);
    }

    public static int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0 ;i < nums.length -1 ;i++){
            int temp = nums[i];
            for(int j = i + 1 ; j < nums.length ; j ++){
                if(temp == nums[j]){
                    break;
                }
                if(j == nums.length -1 ){
                    return temp;
                }
            }
        }
        return res;
    }
}
