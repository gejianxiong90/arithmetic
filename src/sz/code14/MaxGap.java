package sz.code14;

/**
 * 给定一个数组，返回排完序之后，相邻两个数的最大差值
 * {3，1，7，9} 排序后 {1，3，7，9}，相邻两个最大差值来自3和7，返回4
 * 要求：不能真的进行排序，要求时间复杂度O（N）
 *
 * 解:
 *  arr.length + 1 个桶，至少有一个桶是空桶，平分区间，分别放入桶
 * 注：为什么要多一个桶？
 *      1.必然有一个空桶，排除了一个桶内的最大差值，因为空桶两个侧相减必然大于一个桶内的相减
 *      2.排除了桶内的可能性，所以每个桶只需要记录最大值和最小值就行
 *  分别放入不同的桶，每个桶记录最大值和最小值。后一个桶最小值减去前一个桶最大值（注意有空桶），答案必在其中
 */
public class MaxGap {

    public static int process(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < arr.length ; i++){
            max = Math.max(arr[i],max);
            min = Math.min(arr[i],min);
        }
        if(max == min){
            return 0;
        }
        int len = arr.length;
        int[] maxArr = new int[len + 1];
        int[] minArr = new int[len + 1];
        boolean[] hasNum = new boolean[len+1];

        for(int j = 0; j < arr.length ; j++){
            int bucket = bucket(len, max, min, arr[j]);
            maxArr[bucket] = hasNum[bucket] ? Math.max(maxArr[bucket],arr[j]) : arr[j];
            minArr[bucket] = hasNum[bucket] ? Math.max(maxArr[bucket],arr[j]) : arr[j];
            hasNum[bucket] = true;
        }

        int res = Integer.MIN_VALUE;
        int maxTemp = maxArr[0];
        for(int i = 1 ; i <= len ; i++){
            if(hasNum[i]){
                res = Math.max(minArr[i] - maxTemp,res);
                maxTemp =  maxArr[i] ;
            }

        }

        return res;
    }

    public static int bucket(int len , int max , int min,int val){
        return (val - min) * len / (max - min);
    }

    public static void main(String[] args) {
        int[] arr = {3,1,7,9,15};
        int process = process(arr);

        System.out.println(process);
    }
}
