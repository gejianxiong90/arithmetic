package sz.code9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 1.给定一个数组，返回最小和 --》 最大累加和 中间无法得到的数（最小不可组成和）
 *  arr = {3,2,5}  2 --》 10 中间不能得到的的数（4,6,9）
 *  解：背包
 *
 *
 * 2. 如果数组中其中包含1，能不能加速
 *    变量range记录， arr[i] > range + 1 就是不能得到的数
 *                   相反 range就能得到，range累加上arr[i]的值就是当前所有得到的总量
 */
public class SmallestUnFormedSum {


    public static void main(String[] args) {
        int[] arr = {3,2,5};

        List<Integer> process = unformedSum1(arr);
        System.out.println(process);
    }


    public static List<Integer> unformedSum1(int[] arr){
        int min = Integer.MAX_VALUE;
        int sum =0;
        for(int i=0; i < arr.length;i++){
            min = Math.min(arr[i],min);
            sum += arr[i];
        }
        List<Integer> integers = new ArrayList<>();
        for(int i = min ;i < sum; i++){
            boolean min1 =  process(arr, 0, i);
            if(!min1){
                integers.add(i);
            }
        }

        return integers;
    }


    public static boolean process(int[] arr,int index,int rest){
        if( arr.length == index){
            return rest == 0 ?true : false;
        }

        return process(arr, index+1, rest - arr[index]) || process(arr,index+1,rest);
    }


    public static boolean minDp(int[] arr,int rest){

        int N = arr.length;
        int M = rest;

        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][0] = true;

        for(int i = 1;i < N+1 ; i++){
            dp[i][0] = false;
        }
        for(int j = 1; j < M + 1 ;j++){
            dp[0][j] = arr[j] == rest ? true : false;
        }

        for(int i = 1;i < N+1 ; i++){
            for(int j = 1 ; j< M + 1 ; j++){
                dp[i][j] =  dp[i+1][j];
                if(j - arr[i] > 0){
                    dp[i][j] =  dp[i+1][j-arr[i]] ||  dp[i][j];
                }
            }
        }

        return dp[N][M];
    }


    /**
     * 包含1
     * @param arr
     * @return
     */
    public static int unformedSum2(int[] arr){

        Arrays.sort(arr);
        int range = 1;
        for(int i = 1; i <arr.length ; i++ ){
            if(arr[i] > range + 1 ){
                return range + 1;
            }else {
                range += arr[i];
            }
        }
        return range + 1;
    }

}
