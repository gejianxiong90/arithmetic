package leetcode;

public class MajorityElement_169 {

    public int majorityElement(int[] nums) {

        int v = 1,x = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(v == 0 ){
                x = nums[i];
            }
            if(nums[i] == x){
                v += 1;
            }else {
                v -= 1;
            }
        }
        return x;
    }
}
