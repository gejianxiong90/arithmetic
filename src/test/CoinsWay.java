package test;

public class CoinsWay {

    public static void main(String[] args) {
        int[] arr = {10,100,50,5};
        System.out.println(ways1(arr,1000));
        System.out.println(ways2(arr,1000));
        System.out.println(process3(arr,1000));
        System.out.println(process4(arr,1000));




    }

    public static int ways1(int[] arr,int rest){

        return process1(arr,0,rest);
    }

    public static int process1(int[] arr,int index,int rest){
      if(index == arr.length){
          return rest == 0 ? 1 : 0;
      }
      int res = 0;
      for(int zhang = 0; zhang * arr[index] <= rest ; zhang++ ){
         res += process1(arr, index + 1, rest - zhang * arr[index]);
      }
      return res;
    }

    public static int ways2(int[] arr,int rest){
        int[][] dp = new int[arr.length + 1][rest + 1];
        for(int i = 0 ; i <dp.length ; i++){
            for(int j = 0 ; j < dp[i].length; j++){
                dp[i][j] = -1;
            }
        }

        return process2(arr,0,rest,dp);
    }

    public static int process2(int[] arr,int index,int rest,int[][] dp){
        if(dp[index][rest] != -1){
            return dp[index][rest];
        }
        if(index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int res = 0 ;
        for(int zhang = 0; zhang * arr[index] <= rest ; zhang++ ){
            res += process2(arr, index + 1, rest - zhang * arr[index],dp);
        }
        dp[index][rest] = res;
        return dp[index][rest];
    }



    public static int process3(int[] arr,int aim){
        int[][] dp = new int[arr.length + 1][ aim + 1];
        dp[arr.length][0] = 1;
        for(int index =  arr.length -1 ; index >= 0 ; index--){
            for (int rest = 0 ; rest <= aim; rest++ ){
                int res = 0;
                for(int zhang = 0; zhang * arr[index] <= rest ; zhang++ ){
                    res += dp[ index + 1] [rest - zhang * arr[index]];
                }
                dp[index][rest] = res;
            }
        }
       return dp[0][aim];
    }

    public static int process4(int[] arr,int aim){
        int[][] dp = new int[arr.length + 1][ aim + 1];
        dp[arr.length][0] = 1;
        for(int index =  arr.length -1 ; index >= 0 ; index--){
            for (int rest = 0 ; rest <= aim; rest++ ){
                dp[index][rest] = dp[index+1][rest];
                if(rest - arr[index] >=0 ){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }
}
