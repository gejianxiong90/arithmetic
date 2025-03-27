package core;

public class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        int L = 0;
        int R = nums.length;

        while(L < R){
            if(nums[L] == L + 1){
                L++;
            }else if(nums[L] <= L || nums[L] > R || nums[L] == nums[nums[L]-1] ){
                swap(nums,L,--R);
            }else {
                swap(nums,L,nums[L]-1);

            }
        }
        return L +1;
    }


    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {100,2,1};

        int i = firstMissingPositive(nums);

        System.out.println(i);
    }
}
