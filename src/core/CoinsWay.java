package core;

public class CoinsWay {

    public static void main(String[] args) {
        int[] arr = {10,50,100};
        System.out.println(process(arr,0,1000));

        System.out.println(dp(arr,1000));
        System.out.println(dp2(arr,1000));
    }

    public static int process(int[] arr,int index,int rest){
        if(rest < 0){
            return 0;
        }
        if(index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for(int zhang = 0 ; arr[index] * zhang <= rest ;zhang++){
            res += process(arr,index + 1, rest - arr[index] * zhang );
        }
        return res;
    }


    public static int dp(int[] arr,int rest){
        int n = arr.length;
        int[][] dp = new int[n + 1][rest + 1];
        dp[n][0] = 1;
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = 0; j <= rest ; j++ ){
                for(int zhang = 0 ; arr[i] * zhang <= j ;zhang++){
                    dp[i][j] +=dp[i + 1][ j - arr[i] * zhang ];
                }
            }
        }
        return dp[0][rest];
    }

    public static int dp2(int[] arr,int rest){
        int n = arr.length;
        int[][] dp = new int[n + 1][rest + 1];
        dp[n][0] = 1;
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = 0; j <= rest ; j++ ){
                dp[i][j] = dp[i+1][j];
                if(j - arr[i] >= 0){
                    dp[i][j] += dp[i][j - arr[i]];
                }
//                for(int zhang = 0 ; arr[i] * zhang <= j ;zhang++){
//                    dp[i][j] +=dp[i + 1][ j - arr[i] * zhang ];
//                }
            }
        }
        return dp[0][rest];
    }
}
