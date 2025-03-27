package core;

/**
 * 股票三连
 */
public class MaxProfit {
    /**
     * 只可以买卖一次求最大收益
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices){
        if(prices == null || prices.length == 1){
            return 0;
        }
        int min = prices[0];
        int res = Integer.MIN_VALUE;
        for(int i = 1 ; i < prices.length ; i++){
            min = Math.min(min,prices[i]);
            res = Math.max(res,prices[i] - min);
        }
        return res;
    }

    /**
     * 无限次购买的最大收益
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices){
        if(prices == null || prices.length == 1){
            return 0;
        }
        int res = 0;
        for(int i = 1 ; i < prices.length ; i++){
            res += Math.max(prices[i] - prices[i-1],0);
        }
        return res;
    }

    /**
     * 只能购买两次求最大收益
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices){
        if(prices == null || prices.length < 2){
            return 0;
        }
        int ans = 0;
        int doneOnceMinusBuyMax = -prices[0] ;
        int doneOnceMax = 0;
        int min = prices[0];
        for(int i = 1 ; i < prices.length ; i++){
            min = Math.min(prices[i],min);
            doneOnceMax = Math.max(prices[i] - min , doneOnceMax); // 0...i 交易一次的最大值
            doneOnceMinusBuyMax = Math.max(doneOnceMax - prices[i],doneOnceMinusBuyMax); // 0...i 交易一次后并买入一次的最大值
            ans = Math.max((doneOnceMinusBuyMax + prices[i]),ans);
        }
        return ans;

    }




    /**
     * k 次 求最大收益 dp[i][j] 表示i次交易0...j的最大收益，返回 dp[k][n-1];
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        if(prices == null || prices.length <2){
            return 0;
        }
        int n = prices.length;
        if(k >= n >> 1){
            return all(prices);
        }
        int[][] dp = new int[k+1][n];

        for(int i = 1 ; i <= k ; i++){
            int pre = dp[i][0];
            int best = pre - prices[0];
            for(int j = 1 ; j < n ; j++){
                dp[i][j] = Math.max(dp[i][j-1],best + prices[j]); // 讨论与j有无关系，在用前一次的最好 + 本次的卖出
                best = Math.max(best,dp[i-1][j] - prices[j]);  // 前一次交易的加上下一次买入的最大值（i-1次交易 - 下一次买入成本）
            }
        }
        return dp[k][n-1];
    }

    /**
     *  不限交易次数，但卖出后有一天的冷冻期，第二天才可以买入
     * @param prices
     * @return
     */
    public static int maxProfit5(int[] prices){
        if(prices == null || prices.length < 2){
            return 0;
        }
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[1] = Math.max(-prices[0],-prices[1]);// 前面的交易 + 0。。。i位置内最后买入的最大值。 // 其实就是在算最低成本价
        sell[1] = Math.max(0,prices[1] - prices[0]);//0...1 卖出的最大值
        for(int i = 2 ; i < n ; i++){
            buy[i] = Math.max(buy[i-1],sell[i-2] - prices[i]); // 前面的交易 + 0。。。i位置内最后买入的最大值。情况一 不包含i位置   情况二 前两位卖出的最大值（有一天冷冻期） - i位置的买入的成本
            sell[i] = Math.max(sell[i-1],buy[i-1] + prices[i]);//0...1 卖出的最大值。情况一 不包含i位置 。 情况二 前一个买入的最大值 + i位置
        }
        return sell[n-1];
    }

    public static int all(int[] prices){
        int res = 0;
        for(int i = 1 ; i < prices.length ; i++){
            res += Math.max(0,prices[i] - prices[i-1]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,0,2};
        maxProfit5(arr);
    }
}
