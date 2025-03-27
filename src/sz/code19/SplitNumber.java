package sz.code19;

/**
 * 给定一个N，如：N = 4 那么他说能裂开的升序组合有哪些？ 比如 1,1,1,1   1,1,2    1,3    2,2    4 有五种
 * 任意给定一个N，问裂开有多少种
 */
public class SplitNumber {


    /**
     *
     * @param pre  尝试以这个数作为最小切割点，后续的都不能小于这个数
     * @param rest 切割完还剩的数
     * @return
     */
    public static int process(int pre , int rest){
        if(rest == 0){
            return 1;
        }
        if( pre > rest){
            return 0;
        }
        int ways = 0;
        for(int i = pre ; i <= rest; i++){
            ways += process(i,rest - i);
        }
        return ways;
    }

    public static int dp(int n){
        int[][] dp = new int[n + 1][n + 1];// 0行没有用，pre最小为1,数据规模不可能超过N

        for(int i = 1 ; i <= n; i++){
            dp[i][0] = 1; // 零列都为1
        }
        // dp[i][j] 的含义是 使用 pre为最小切割，rest还剩下的数，
        // 如： dp[2][4]  代表2切割4返回的结果
        for(int j = 1 ; j <= n ; j++){
            dp[j][j] = 1 ; // 对角线都为1，用4切割还剩4，当然就是1种，就是4，上面的basecase
        }
        // 除了第一列，左下半部分不用填，应为 pre <= rest
        // 整体从下往上，从左往右
        for(int pre = n -1 ; pre > 0 ; pre-- ){
            for(int rest = pre+1 ; rest <= n ; rest++){
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int process = process(1, 4);
        System.out.println(process);

        int dp = dp(4);
        System.out.println(dp);
    }
}
