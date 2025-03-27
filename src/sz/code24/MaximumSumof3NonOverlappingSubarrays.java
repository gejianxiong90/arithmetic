package sz.code24;

/**
 * 给定一个数组nums，将数组分成3份不重叠的子数组，子数组要求的长度是k个
 * 最终形成三个子串总和最大
 *
 */
public class MaximumSumof3NonOverlappingSubarrays {

    public static int maxSumOfThreeSubarrays(int[] nums,int k){
        if(nums == null || nums.length < k * 3){
            return -1;
        }
        int N = nums.length;
        int[] left = new int[N];
        // 左侧预处理累加和,左部分的最大右边界
        for(int i = 0 ; i < N - 2*k;i++){
            if(i == 0){
                left[i] = nums[i];
            } else if(i < k){
                left[i] = nums[i] + left[i-1]; // 要求k位数相加的和，目前状态是最左侧3位数相加
            }else {
                if(nums[i] + left[i-1] - left[i-k]> left[i-1]){ // 要i位置，同时需要左侧缩
                    left[i] = nums[i] + left[i-1] - left[i-k];
                }else {
                    left[i] = left[i-1]; // 不要i位置
                }
            }
        }

        int[] mid = new int[N];
        // 中间预处理累加和
        for(int i = k;i < N - k;i++){
            mid[i] = i == k ? nums[i] : nums[i] + mid[i-1];
        }

        int[] right = new int[N];
        //右侧预处理累加和，右部分的最大左边界
        for (int i = N -1; i >= 2 * k ; i--){
            if(i == N - 1){
                right[i] = nums[i];
            }else if(i >= N - k) { // 第一个K位的累加和
                right[i] = right[i+1] + nums[i];
            }else { //
                if(right[i+1] + nums[i] - right[i+k] > right[i+1]){
                    right[i] = right[i+1] + nums[i] - right[i+k];
                }else {
                    right[i] = right[i+1];
                }
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for(int i = k ; i < N - 2 * k ;i++){
            int leftSum = left[i];
            int midSum = i == k? mid[i+ k -1] : mid[i+k-1] - nums[i-1] ;
            int rightSum = i== k ? right[i+k]: right[i + k] - nums[i];
            maxSum = Math.max(maxSum,leftSum+midSum+rightSum);

        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = { 9, 9, 7, 9, 9, 5, 5, 2 };
        int k = 2;
        int ans = maxSumOfThreeSubarrays(nums, k);

        System.out.println(ans);

    }
}
