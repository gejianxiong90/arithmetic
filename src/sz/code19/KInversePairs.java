package sz.code19;

/**
 *
 * 给定n和k，n = 3 k = 2 ， 求3以内的数字包含2个逆序对的数量
 *
 *  [1,2,3]    [1,3,2]     [2,1,3]        [2,3,1]         [3,1,2]         [3,2,1]
 *     0       1个(32)     1个(21)       2个(21,31)       2个(31,32)      3个(32,31,21)
 *
 * 有2个有2个逆序对的
 *
 * 所以返回 2
 *
 * 解：动态规划行列模型，看下面备注
 *
 *
 */
public class KInversePairs {

    public static int dp(int n,int k){
        if( n < 1 || k <0){
            return 0;
        }
        if(k == 0){
            return 1;
        }
        // dp[i][j]的含义是 i这个数字有j个逆序对的数量
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n ; i++){
            dp[i][0] = 1;
        }

        for(int i = 2; i <= n ; i++){
            for(int j = 1; j <= k ; j++){
                // 当 i>j 时，dp[i-1][j....0]
                // 当 i<=j时，dp[i-1][j...j-i+1]
                //所以就是下面，但是有枚举行为
//                for(int s = j ; i <= Math.max(0,j-i+1);s--){
//                    dp[i][j] = dp[i-1][s];
//                }
                // 假设 dp[7][3] = dp[6][3...0]
                // 那么 dp[7][4] = dp[6][4] + dp[6][3...0] , 所以dp[7][4] = dp[7][3] + dp[6][4]
                // 但是如果 dp[7][9]呢？j>=i
                // dp[7][9] = dp[6][9....3]
                // dp[7][10] 呢？ dp[7][10] = dp[6][10] + dp[6][9...4] ,没有dp[6][3]
                // 所以 dp[7][10] = dp[7][9] + dp[6][10] - dp[6][3] ,dp[6][3] 就是 dp[i-1][j-i]

                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                if(i <= j){
                    dp[i][j] -= dp[i-1][j-i];
                }

            }
        }
        return dp[n][k];
    }
}
