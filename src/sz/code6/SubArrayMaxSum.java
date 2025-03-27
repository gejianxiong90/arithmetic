package sz.code6;

public class SubArrayMaxSum {

    /**
     *
     * 给定一个数组arr，返回子数组的最大累加和
     *
     *
     * 结论是，最大的累加和，同时也是最长的子数组
     * @param arr
     * @return
     */
    public static int maxSum(int[] arr){
        if(arr == null | arr.length ==0){
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < arr.length;i++){
            cur += arr[i];
            max = Math.max(cur,max);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }
}
