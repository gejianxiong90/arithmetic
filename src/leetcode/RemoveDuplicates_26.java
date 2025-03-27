package leetcode;

public class RemoveDuplicates_26 {

    public int removeDuplicates(int[] nums) {
        int count = 0;
        for (int i = 0 ; i < nums.length ; i++){
            if(i == 0 || nums[i - 1] != nums[i]){
                nums[count] = nums[i];
                count++;
            }
        }
        return count;

    }
}
