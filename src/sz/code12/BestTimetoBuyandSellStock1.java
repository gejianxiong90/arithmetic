package sz.code12;
/**
 * 给定一个数组，表示股票的涨跌
 * 只可以任意买卖一次，每次只能买卖一股
 * 求可以获得的最大收益
 */
public class BestTimetoBuyandSellStock1 {

    public static int maxProfit(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <prices.length;i++){
            min = Math.min(min,prices[i]);
            max = Math.max(max,prices[i] - min);
        }
        return max;
    }
}
