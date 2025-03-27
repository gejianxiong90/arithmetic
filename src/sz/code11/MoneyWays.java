package sz.code11;

/**
 * 有两种硬币，n1是普通币，n2是纪念币。
 * n1可以使用任意张数，n2只能使用一张，能有多少种方法得到m的面值
 *
 * 比如m是10
 * 解：
 *    使用动态规划分别求出n1和n2得出10的表
 *      n1的得到1元 * n2得到9元
 *               +
 *      n1得到2元 * n2得到8元
 *               +
 *      n1得到3元 * n2得到7元
 *           。。。。。。
 *
 */
public class MoneyWays {

    public static int moneyWays(int[] arbitrary, int[] onlyone, int money){
        if(money <= 0){
            return 0;
        }

        int[][] dpArbRes = dpArb(arbitrary, money);
        int[][] dpOneRes = dpOnlyOne(onlyone, money);


        int res = 0;
        for(int j = 0 ; j <= money ; j++){
            res += dpArbRes[dpArbRes.length-1][j] * dpOneRes[dpArbRes.length-1][ money - j];
        }

        return res;
    }

    public static int[][] dpArb(int[] arbitrary,int money){
        int[][] dp = new int[arbitrary.length][money + 1];

        for(int i = 0 ;i < dp.length ; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j <=money ; j++ ){
            dp[0][j] = money % arbitrary[0] == 0 ? 1 : 0;
        }
        for(int i = 1 ; i < dp.length ; i++ ){
            for(int j = 1; j <= money ; j++){
                dp[i][j] = dp[i-1][j];
                if(j - arbitrary[i] >=0){
                    dp[i][j] += dp[i][j - arbitrary[i]];
                }
            }
        }
        return dp;
    }

    public static int[][] dpOnlyOne(int[] onlyOne,int money){

        int[][] dp = new int[onlyOne.length][money + 1];

        for(int i = 0 ; i < onlyOne.length ; i++){
            dp[i][0] = 1;
        }

        if(onlyOne[0] <= money){
            dp[0][onlyOne[0]] = 1;
        }

        for(int i = 1;i < onlyOne.length ; i++){
            for(int j = 1 ; j <= money ; j++){

                dp[i][j] = dp[i-1][j]; // i不用

                if(j - onlyOne[i] >=0){
                    dp[i][j] += dp[i-1][j - onlyOne[i]];
                }
            }
        }
        return dp;
    }
}
