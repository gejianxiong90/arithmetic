package leetcode;

public class CanJump55 {

    public boolean canJump(int[] nums) {
        if(nums == null || nums.length < 2){
            return true;
        }
        int minCount = 1;
        int max = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(max >= nums.length - 1){
                return true;
            }
            if(i > max){
                return false;
            }
            max = Math.max(max,nums[i]+i);
        }
        return max >= nums.length - 1;
    }

    /**
     * 最少几步
     * @param nums
     * @return
     */
    public int canJump2(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        int step = 0;//需要条的步数
        int cur = 0;// 当前来到的
        int next = nums[0];//下一步要去的,当前走的最远的
        for(int i = 1 ; i < nums.length ; i++){
            if(next >= nums.length - 1){
                return step + 1;
            }
            if(i > cur){
                cur = next;
                step++;
            }
            if(nums[i]+i > next){ //能跳的最远的
                next = nums[i]+i;
            }
        }
        return step;
    }
}
