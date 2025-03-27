package dynamic;

/**
 * 给定一个数组（钱），设定一个值（累计钱的总值），有多少种能累计到这个总数
 */
public class FindMoney {



    public static void main(String[] args) {
        int[] arr = {5,10,50,100};
        int process1 = process1(arr, 0, 1000);
        System.out.println(process1);
        System.out.println(ways2(arr,1000));
        System.out.println(ways3(arr,1000));
        System.out.println(ways4(arr,1000));

    }

    // 暴力递归解
    public static int process1(int[] arr,int index,int rest){
//        if(rest < 0){  //base case
//            return 0;
//        }
        if(arr.length  == index){
            return rest == 0 ? 1 : 0;
        }
        int process = 0;
        for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
            process += process1(arr, index + 1, rest - zhang * arr[index]);
        }
        return process;
    }

    public static int ways2(int[] arr,int rest){
        if(arr == null || arr.length == 0 || rest <=0){
           return 0;
        }
        int length = arr.length;
        int[][] dp = new int[length + 1][rest+1];
        for(int i = 0;i < dp.length;i++){
            for (int j = 0;j < dp[i].length;j++){
                dp[i][j] = -1;
            }
        }
       return  process2(arr,0,rest,dp);
    }
    public static int process2(int[] arr,int index,int rest,int[][] dp){
        if(dp[index][rest] != -1){
            return dp[index][rest];
        }
        if(arr.length  == index){
            return dp[index][rest] = rest == 0 ? 1 : 0;
        }
        int process = 0;
        for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
            process += process1(arr, index + 1, rest - zhang * arr[index]);
        }
        return dp[index][rest]  = process;

    }

    // 动态规划3   记忆化搜索
    public static int ways3(int[] arr,int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim+1];
        dp[N][0] = 1;
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0 ; rest<= aim ;rest++){
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest  ; zhang++) {
                 ways += dp[ index + 1][ rest - zhang * arr[index]];
//                 System.out.println("dp["+ (index + 1)+"]["+ (rest - zhang * arr[index])+"] = "+ dp[ index + 1][ rest - zhang * arr[index]]);
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    public static int ways4(int[] arr,int aim){
        if(arr == null || arr.length == 0 || aim <=0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim+1];
        dp[N][0] = 1;
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0 ; rest<= aim ;rest++){
               dp[index][rest] = dp[index + 1][rest];
               if(rest - arr[index] >= 0){
                   dp[index][rest] += dp[index][rest - arr[index]];
               }
            }
        }
        return dp[0][aim];
    }




}
