package sz.code12;

/**
 * 给定一个数组，表示股票的涨跌
 * 可以任意买卖多次，每次只能买卖一股
 * 求可以获得的最大收益
 */
public class BestTimetoBuyandSellStock2 {

    public static int maxProfit(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int ans = 0;
        for(int i = 1; i <prices.length;i++){
            ans += Math.max(prices[i] - prices[i-1],0);
        }
        return ans;
    }
}
