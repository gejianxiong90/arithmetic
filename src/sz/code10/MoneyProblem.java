package sz.code10;

/**
 * 给定两个数组，d数组表示怪兽的武力值，p怪兽代表对应p怪兽要求的钱数
 * 用钱通过后自己的武力值累加上怪兽的武力值，武力值大于等于怪兽武力值时可以花钱也可以直接通过
 * 问：通过所有怪兽最少花多少钱
 * 解1：暴力递归
 * 解2：动态规划，分析表的规模，如果能力值数据小可以用能力值作为列,dp[i][j]求钱
 *                            如果钱的数据小可以用钱作为列，dp[i][j]求最大能力值
 *
 */
public class MoneyProblem {

    /**
     *
     * @param d  i号怪兽的武力值
     * @param p  i号怪兽要求的钱
     * @param ability 当前能力
     * @param index 来到第index怪兽
     * @return
     */
    public static long process(int[] d,int[] p,int ability,int money,int index){
        if(index == p.length){
            return money;
        }
        long min = Integer.MAX_VALUE;
        if(ability > d[index]){
            min = Math.min(process(d, p, ability, money,index + 1),
                    process(d, p, ability + d[index],money + p[index], index + 1));
        }else {
            min = Math.min(min,process(d,p,ability+d[index],money+p[index],index+1));
        }

        return min;
    }


    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱
    // ability 当前你所具有的能力
    // index 来到了第index个怪兽的面前

    // 目前，你的能力是ability，你来到了index号怪兽的面前，如果要通过后续所有的怪兽，
    // 请返回需要花的最少钱数
    public static long process(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) {
            return p[index] + process(d, p, ability + d[index], index + 1);
        } else { // 可以贿赂，也可以不贿赂
            return
                    Math.min(
                            p[index] + process(d, p, ability + d[index], index + 1),
                            process(d, p, ability, index + 1)
                    );
        }
    }

    /**
     * 用钱做列
     * @param d
     * @param p
     * @return
     */
    public static long dp(int[] d,int[] p){
        int moneySum = 0;
        for(int i = 0;i < p.length;i++){
            moneySum += p[i];
        }
        long[][] dp = new long[d.length][moneySum+1];

        for(int i = 0;i< d.length;i++){
            for(int j = 0; j <= moneySum;j++){
                dp[i][j] = -1;
            }
        }

//        for(int i = 0;i <d.length ; i++){
//            dp[i][0] = -1;
//        }
//        for(int i = 0 ; i< moneySum+1 ; i++){
//            dp[0][i] = p[0] == i ? d[0] : -1;
//        }
        dp[0][p[0]] = d[0];

        for(int i = 1 ; i <d.length ;i++){
            for(int j = 0 ; j <= moneySum;j++){
                if(dp[i-1][j] != -1 && dp[i-1][j] >= d[i]){
                    dp[i][j] = dp[i-1][j];
                }
                if(j >= p[i] && dp[i-1][j - p[i]] != -1 ){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j - p[i]] + d[i]);
                }
            }
        }


        long res = 0;
        for (int j = 0 ; j<=moneySum;j++){
            if(dp[d.length-1][j] > -1){
                res = j;
                break;
            }
        }

        return res;
    }


    public static long func3(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arrd = {10,8,15,4,20,5,6};
        int[] arrp = {2,5,20,7,8,4,19};
        long process = process(arrd, arrp, 0, 0, 0);

        long process1 = process(arrd, arrp, 0, 0);

        long dp = dp(arrd, arrp);
        long dp2 = func3(arrd, arrp);

        System.out.println(process1);

        System.out.println(process);


        System.out.println(dp);

        System.out.println(dp2);
    }
}
