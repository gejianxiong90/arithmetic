package test;

import java.util.HashMap;
import java.util.Map;

public class MaxLength {


    /**
     * 正整数中，等于K的最长子数组
     * 求最长的数组长度
     * @param arr
     * @param k
     * @return
     */
    public static int maxLen(int[] arr,int k){
        int len = 0;
        int l = 0;
        int r = 0;
        int sum = arr[0];

        while ( r < arr.length ){
            if(sum == k){
                len = Math.max(len,r-l+1);
                sum -= arr[l++];
            }else if(sum < k){
                r++;
                if(r == arr.length){
                    break;
                }
                sum += arr[r];
            }else{
                sum -= arr[l++];
            }
        }
        return len;
    }


    /**
     *  数组中（包含负数和0），等于K的最长子数组
     *  求最长的数组长度
     * @param arr
     * @param k
     */
    public static int maxLenContans0(int[] arr,int k){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int len = 0;
        int sum = 0;
        for(int i = 0;i < arr.length ; i++){
            sum += arr[i];
            if(map.containsKey(sum - k)){
                len = Math.max(len,i - map.get(sum - k));
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return len;
    }


    /**
     *  数组中（包含负数和0），小于等于K的最长子数组
     *  求最长的数组长度
     * @param arr
     * @param k
     * @return
     */
    public static int maxLenMinK(int[] arr,int k){

        int n = arr.length;
        // 从右遍历，以我为左起点的最小累加和
        int[] minSum = new int[n];
        // 左起点的右边的最小累加和的坐标
        int[] minSumEnd = new int[n];
        // 最右不用计算，直接填
        minSum[n - 1] = arr[n - 1];
        minSumEnd[n - 1] = n - 1;
        for(int i = n - 2 ; i >=0 ; i--){
            if(minSum[i+1] <= 0){
                minSum[i] = arr[i] + minSum[i+1];
                minSumEnd[i] = minSumEnd[i+1];
            }else{
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        // 长度变量
        int len = 0;
        // 累加和
        int sum = 0;
        // 从左到右，能够扩到的最后一个数的下一个,不包含end
        // （j....）( ....) (end ..X
        int end = 0;
        for(int j = 0; j < arr.length ; j++){
            while (end < arr.length && sum + minSum[end] <= k){
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            len = Math.max(len,end - j);
            if(end > j){ // 怕窗口内没数
                sum -= arr[j];
            }else {// 窗口没数，end直接往右移动
                end = j + 1;
            }
        }

        return len;
    }


    public static void main(String[] args) {


        Long start = System.currentTimeMillis();
        for(int i = 0 ; i < 100000000; i++){

            int j = i;
        }

        System.out.println((System.currentTimeMillis() - start) / 1000);

        int[] arr = {4,6,1,5,7,8,5,2,2,3,5,1,1,1,1,1,2};

        int i = maxLen(arr, 10);

        int i1 = maxLenContans0(arr, 10);

        int i2 = maxLenMinK(arr, 10);

        System.out.println(i);
        System.out.println(i1);

        System.out.println(i2);
    }
}
