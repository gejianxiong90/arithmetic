package sz.code5;

/**
 * 找到数组中最长递增序的最大长度
 */
public class LIS {

    // 返回dp表，取其中最大的就是
    //
    public static int[] getdp(int[] arr){
        int N = arr.length;
        int[] dp = new int[N];
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = arr[0];

        int l = 0;
        int r = 0;
        int m = 0;
        int right = 0;

        for(int i = 1; i< arr.length;i++){
            l = 0;
            r = right;

            while (l <= r){
                m = (l + r) >> 1;
                if(arr[i] > ends[m]){
                    l = m + 1;
                }else {
                    r = m - 1;
                }
            }
            right = Math.max(right,l);
            dp[i] = l + 1;
            ends[l] = arr[i];
        }
        return dp;
    }


    public static void main(String[] args) {
        int[] arr = {3,1,2,3};
        int[] getdp = getdp(arr);

        System.out.println(getdp);
    }
}
