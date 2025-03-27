package sz.code21;

import java.util.TreeSet;

/**
 * 给定一个非负数数组arr，和正数m，返回所有子序列累加和%m的最大值是多少？
 *
 * 解：有三种解法
 * 1.dp[i][sum] i是每个数组的index，sum是累加和，dp[0...i][sum] = true含义是0到i的子序列的累加和是否为sum，最后取最后一行%m的最大值  （不适用于sum很大的，就是arr中数大）
 * 2.dp[i][m] i是数组index。dp[0...i][m]含义是0到i子序列累加和%m后形成的m以内的数 （不适用于m值很大）
 * 3.左右分治，最大值可能来自左边，可能来自右边，也可能来自左右两边
 *
 */
public class SubsquenceMaxModM {

    public static int dp2(int[] arr,int m){
        boolean[][] dp = new boolean[arr.length][m];
        dp[0][0] = true;
        for(int i = 1 ; i < arr.length ; i++){
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for(int i = 1 ; i < arr.length ; i++){
            for(int j = 1 ; j < m ; j++){
                dp[i][j] = dp[i-1][j]; // 第一种可能是不包含i这个数，保持与i-1 一样
                int cur = arr[i] % m ;
                if(j - cur >= 0){
                    dp[i][j] = dp[i][j] || dp[i-1][j - cur];
                }
                if( j - cur < 0){ // j = 3  cur = 5 ,m = 4
                    dp[i][j] = dp[i][j] || dp[i-1][m +j - cur]; // 第三种，可能会有 m 的数
                }
            }
        }

        int res = 0;
        for (int k = m ; k >=0 ; k--){
            if(dp[dp.length-1][k]){
                res = k;
                break;
            }
        }
        return res;
    }

    /**
     * 分治的做法
     * @return
     */
    public static int max3(int[] arr,int m){
        if(arr == null || arr.length == 0){
            return -1; // 无效
        }
        if(arr.length == 1){
            return arr[0] % m;
        }

        int mid = arr.length / 2;
        TreeSet<Integer> leftSet = new TreeSet<>();
        process4(arr,0,mid,0,leftSet,m);
        TreeSet<Integer> rightSet = new TreeSet<>();
        process4(arr,mid+1,arr.length,0,rightSet,m);
        int res = 0;
        for(Integer left :leftSet){
            res = Math.max(left,rightSet.floor(m-1-left)); // 重点因为左右两边都有0，所有也包含了独立左边的可能和独立右边的可能
        }

        return res;
    }

    public static void process4(int[] arr,int index,int end, int sum,TreeSet<Integer> set,int m){
        if(index + 1 == end){
            set.add(sum % m);
        }else {
            process4(arr,index+1,end,sum+arr[index],set,m);
            process4(arr,index+1,end,sum,set,m);
        }

    }

}
