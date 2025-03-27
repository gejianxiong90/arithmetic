package sz.code13;

import java.util.TreeSet;

/**
 * 求数组arr中，子数组累加和是 <=K ,并且是最大的
 * 返回这个最大的累加和
 */
public class MaxSubArraySumLessOrEqualK {

    public static int getMaxLessOrEqualK(int[] arr,int K){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        int max = Integer.MIN_VALUE;

        // 每一步都求 i 结尾的情况下，子数组是 <=K ,并且最大
        for(int i = 0 ; i <arr.length ; i++){
            sum += arr[i];
            if(set.ceiling(sum - K) != null){
                max = Math.max(max,sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return max;
    }


    /**
     * 矩阵
     * @param arr
     * @param K
     * @return
     */
    public static int getMaxLessOrEqualK(int[][] arr,int K){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int rowSum = 0;
        int max = Integer.MIN_VALUE;

        int[] ints = new int[arr[0].length];

        // 每一步都求 i 结尾的情况下，子数组是 <=K ,并且最大
        for(int i = 0 ; i <arr.length ; i++){
            rowSum = 0;
            for(int j = 0 ; j < arr[0].length ; j++){
                ints[j] += arr[i][j];
                rowSum += ints[j];
                if(set.ceiling(rowSum - K) != null){
                    max = Math.max(max,rowSum - set.ceiling(rowSum - K));
                }
                set.add(rowSum);
            }

        }
        return max;
    }

}
