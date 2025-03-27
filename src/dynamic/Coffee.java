package dynamic;


import java.util.Arrays;

/**
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间
 * 三个参数： int[] arr,int a,int b
 */
public class Coffee {

    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,15};
        Arrays.sort(arr);
        int a = 3;
        int b = 10;
        int process = process(arr, a, b, 0, 0);
        int i = process1(arr, a, b);
        System.out.println(process);
        System.out.println(i);
    }

    /**
     * arr[0..index-1]都已经干净了，不用操心
     * arr[index..]都想变干净，这是我操心的
     * arr[index..]变干净，最少时间点返回
     * @param arr  员工喝完的时间  固定变量
     * @param a  洗杯子时间  固定变量
     * @param b  挥发杯子时间 固定变量
     * @param index  当前杯子下标
     * @param washLine 洗的机器何时可用
     * @return
     */
    public static int process(int[] arr,int a,int b,int index,int washLine){
        if(index == arr.length - 1){
            return Math.min(Math.max(arr[index],washLine)+a,arr[index]+b);
        }
        int wash = Math.max(arr[index],washLine)+ a;  // 当前杯子洗的时间
        int p1Next = process(arr, a, b, index + 1, wash);  // 后续杯子洗的时间
        int p1 = Math.max(wash,p1Next);  // 最后洗的时间

        int dry = Math.max(washLine,arr[index]) + b; // 当前杯子挥发的时间
        int p2Next = process(arr,a,b,index+1,washLine); // 后续杯子挥发的时间
        int p2 = Math.max(dry,p2Next); // 最后挥发的时间

        return Math.min(p1,p2);
    }


    public static int process1(int[] arr,int a,int b){
        if(a >= b){
            return arr[arr.length-1] + b;
        }
        int limit = 0;
        for (int i = 0;i < arr.length;i++){
            limit = Math.max(limit,arr[i]) + a;
        }
        int N = arr.length;
        int[][] dp = new int[N][limit+1];

        for(int washLine = 0;washLine <= limit ; washLine++){
            dp[N - 1][washLine] = Math.min(Math.max(arr[N-1],washLine)+a,arr[N-1]+b);
        }

        print(dp);
        System.out.println("--------------------------");
        for(int index = N -2;index >= 0;index--){
            for (int washLine = 0; washLine <= limit ; washLine++){
               // dp[index][washLine] = ?;
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(arr[index],washLine)+ a;  // 当前杯子洗的时间   当前喝完咖啡时间点和咖啡机闲置时间点取最大 加上洗杯子时间
                if(wash <= limit){
                    p1 =  Math.max(wash,dp[index + 1][wash]);   // 当前的和后一个杯子时间取最大
                }
                int p2 = Math.max( arr[index] + b,dp[index+1][washLine]); // 最后挥发的时间
                dp[index][washLine] = Math.min(p1,p2);

            }
        }
        print(dp);
        return dp[0][0];
    }


    public static void print(int[][] dp){
        for(int i = 0 ; i < dp.length; i++){
            for(int j = 0; j < dp[i].length ; j++){
                System.out.print(dp[i][j]+"|");
            }
            System.out.println("");
        }
    }

}
