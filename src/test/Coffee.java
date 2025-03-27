package test;

public class Coffee {

    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,15};
        int a = 3;
        int b = 10;
        System.out.println(process(arr,3,10,0,0));
        System.out.println(dp(arr,a,b));
    }

    // a是洗杯子时间
    // b是挥发的时间
    public static int process(int [] drinks, int a , int b,int index , int washLine){
        if(index == drinks.length - 1){
          return Math.min(Math.max(drinks[index],washLine) + a, drinks[index] + b); //当前洗杯子和挥发时间取最小
        }
        int wash = Math.max(drinks[index], washLine) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash,next1);

        int next2 = process(drinks,a,b,index+1,washLine);
        int p2 = Math.max(drinks[index]+b,next2);

        return Math.min(p1,p2);
    }


    public static int dp(int [] drinks,int a,int b){
        if(a >= b){
            return drinks[drinks.length-1] + b;
        }
        int limit = 0;
        int N = drinks.length;
        for (int i = 0 ;i < N ; i++){
            limit = Math.max(limit,drinks[i]) + a ;
        }
        int[][] dp = new int[N][limit + 1];
        // N - 1 行所有的值
        for(int washLine = 0 ; washLine <= limit ; washLine++ ){
            dp[N - 1][washLine] = Math.min(Math.max(drinks[N - 1],washLine) + a,  drinks[N - 1] + b);
        }
        for(int index = N - 2  ; index >= 0 ; index--){
            for (int washLine = 0 ; washLine <= limit ; washLine++){
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(drinks[index], washLine) + a;
                if (wash <= limit){
                     p1 = Math.max(wash,dp[index+1][wash]);
                }
                int p2 = Math.max(drinks[index]+b,dp[index+1][washLine]);
                dp[index][washLine] = Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }
}
