package sz.code8;

import java.util.HashMap;


/**
 * 给定一个数组，求怎么划分，数组中的子数组有多少是异或为0的，返回异或为0的子数组数量
 */
public class MostEOR {


    public static int mostZero(int[] arr){

        int[] dp = new int[arr.length];

        int sum = 0;

        HashMap<Integer, Integer> sumIndexMap = new HashMap<>();

        sumIndexMap.put(0,-1);
        for(int i=0 ; i< arr.length;i++){
            sum ^= arr[i];
            if(sumIndexMap.containsKey(sum)){
                Integer pre = sumIndexMap.get(sum);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if( i > 0){
                dp[i] = Math.max(dp[i-1],dp[i]);
            }
            sumIndexMap.put(sum,i);
        }

        return dp[arr.length-1];
    }
}
