package sz.code22;

/**
 * arr中的数字表示气球的分数，打爆一个气球的得分是 左边最近没爆的气球分数 * 打爆气球的分数 * 右边最近没爆的气球分数 ， 右侧或左侧没有气球 就 * 1
 * 比如：arr = [3,2,5]
 *  打爆2 = 3 * 2 * 5
 *  然后再打爆 3和5
 *  答案是 30 + 15 + 5
 *
 *  求最大分数
 *
 *  解： 左右都补上1，假设最后一个没爆的气球
 *
 */
public class BurstBalloons {


    public static int maxCoins(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length-1] = 1;

        for(int i = 0 ; i < arr.length ; i++){
            help[i+1] = arr[i];
        }
        return process(help,1,arr.length);
    }


    public static int process(int[] help,int L , int R){
        if(L == R){
            return help[L-1] * help[L] * help[R+1];
        }
        int res = process(help, L + 1, R) + help[L - 1] * help[L] * help[R + 1]; // L 位置最后一个爆的得分
        res = Math.max(res,process(help,L,R-1) + help[L-1] * help[R] * help[R+1]); // R 位置最后一个爆的得分
        for(int i = L + 1; i < R; i++){ // 枚举打爆i最后一个打爆的得分
            res = Math.max(res,process(help,L,i-1) + process(help,i+1,R) +  help[L-1] * help[i] * help[R+1]);
        }
        return res;
    }

    public static int maxCoins2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0];
        }
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length-1] = 1;

        for(int i = 0 ; i < arr.length ; i++){
            help[i+1] = arr[i];
        }
        Integer[][] dp = new Integer[help.length][help.length];
        return dp(help,1,arr.length,dp);
    }

    public static int dp(int[] help,int L , int R,Integer[][] dp){
        if(dp[L][R] != null){
            return dp[L][R];
        }
        if(L == R){
            dp[L][R] = help[L-1] * help[L] * help[R+1];
            return help[L-1] * help[L] * help[R+1];
        }
        int res = process(help, L + 1, R) + help[L - 1] * help[L] * help[R + 1]; // L 位置最后一个爆的得分
        res = Math.max(res,process(help,L,R-1) + help[L-1] * help[R] * help[R+1]); // R 位置最后一个爆的得分
        for(int i = L + 1; i < R; i++){ // 枚举打爆i最后一个打爆的得分
            res = Math.max(res,process(help,L,i-1) + process(help,i+1,R) + help[L-1] * help[i] * help[R+1]);
        }
        dp[L][R] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,5};

        int i = maxCoins(arr);
        int j = maxCoins2(arr);
        System.out.println(i);
        System.out.println(j);
    }


}
