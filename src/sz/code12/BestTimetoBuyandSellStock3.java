package sz.code12;

/**
 * 给定一个数组，表示股票的涨跌
 * 可以买卖K次，每次只能买卖一股
 * 求可以获得的最大收益
 */
public class BestTimetoBuyandSellStock3 {

    public static int maxProfit(int[] prices,int k) {
        if(prices == null || prices.length == 0 || k <= 0){
            return 0;
        }
        if( k >= prices.length / 2){
            return BestTimetoBuyandSellStock2.maxProfit(prices);
        }
        int[][] dp = new int[prices.length][k+1];

        for(int i = 1;i < prices.length;i++){
            for(int j = 1 ; j <= k ; k++){
                dp[i][j] = dp[i-1][j]; // 不参与

                for(int y = i; y >=0 ; y--){
                    int t = dp[y][j - 1] + prices[i] - prices[y];
                    int max = Math.max(dp[i][j], t);
                    dp[i][j] = max;
                }

            }
        }


        return dp[prices.length - 1][k];
    }


    public static int dp(int[] prices, int k){
        if(prices == null || prices.length == 0 || k <= 0){
            return 0;
        }
        if( k >= prices.length / 2){
            return BestTimetoBuyandSellStock2.maxProfit(prices);
        }
        int[][] dp = new int[prices.length][k+1];
        int ans = Integer.MIN_VALUE;
        for(int j = 1 ; j <= k ; k++){
            int t = dp[0][j-1] - prices[0];
            for(int i = 1 ; i < prices.length;i++){
                t = Math.max(t,dp[i][j-1] - prices[i]);
                dp[i][j] = Math.max(dp[i-1][j],t + prices[i]);
                ans = Math.max(dp[i][j],ans);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] test = { 4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146 };
        int K = 3;
        System.out.println(maxProfit(test,K));
    }
}
