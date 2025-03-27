package leetcode;

public class RemoveElement_27 {

    public int removeElement(int[] nums, int val) {
        int end = 0; // 有效数字的index
        for(int i= 0 ; i < nums.length ; i++ ){
            if(nums[i] != val){
                nums[end++] = nums[i];
            }
        }
        return end;
    }

}
