package core;

/**
 * 给定一个有序数组arr表示时间节点，a是咖啡机洗消耗的时间，b是咖啡杯自然晾干的时间，咖啡机只能一个一个洗，咖啡杯可以并行自然晾干，问最后最少洗完的时间
 *
 */
public class Coffee {


    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,15};
        int a = 3;
        int b = 10;
        System.out.println(process(arr,a,b,0,0));
        System.out.println(dp(arr,a,b));
    }

    /**
     *
     * @param drinks
     * @param a 洗杯子消耗的时间
     * @param b 自然晾干的时间
     * @param index 假设index - 1 都一样决定好了，从index开始决定
     * @param washLine 记录来到的时间点
     * @return
     */
    public static int process(int[] drinks,int a,int b,int index,int washLine){
        if(index == drinks.length - 1){ // 来到最后一个杯子
            return Math.min(Math.max(drinks[index],washLine) + a,drinks[index] + b);
        }
        int wash = Math.max(drinks[index],washLine) + a; // 洗完当前index杯子的时间
        int nextP1 = process(drinks,a,b,index+1,wash);
        int p1 = Math.max(wash,nextP1); // index杯子洗完，并且后面的杯子也洗完，取最晚时间

        int dry = drinks[index] + b;
        int nextP2 = process(drinks,a,b,index+1,washLine);
        int p2 = Math.max(dry,nextP2);

        return Math.min(p1,p2);
    }



    public static int process2(int[] drinks,int a,int b,int index,int washLine){
        if(index == drinks.length - 1){
            return Math.min(Math.max(drinks[index],washLine) + a,drinks[index] + b);
        }

        int wash = Math.max(drinks[index],washLine) + a;
        int next1 = process(drinks,a,b,index+1,wash);
        int p1 = Math.max(wash,next1);

        int dry = drinks[index] + b;
        int next2 = process(drinks,a,b,index+1,washLine);
        int p2 = Math.max(dry,next2);
        return Math.min(p1,p2);
    }























    public static int dp(int[] drinks,int a,int b){
        if(a >= b){
            return drinks[drinks.length - 1] + b ;
        }
        int limit = 0;
        int n = drinks.length;
        for(int i = 0 ; i < n ; i++){
            limit = Math.max(limit,drinks[i]) + a;
        }
        int[][] dp = new int[n][limit + 1];
        for(int i = 0 ; i <= limit ; i++){
            dp[n -1][i] = Math.min(Math.max(drinks[n-1],i) + a,drinks[n-1] + b);
        }

        for(int index = n -2 ; index >=0 ; index--){
            for (int washLine = 0; washLine <= limit ; washLine++){

                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(drinks[index],washLine) + a; // 洗完当前index杯子的时间
                if(wash <= limit){
                    p1 = Math.max(wash,dp[index+1][wash]); // index杯子洗完，并且后面的杯子也洗完，取最晚时间
                }
                int p2 = Math.max(drinks[index] + b,dp[index+1][washLine]);

                dp[index][washLine] =  Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }
}
