package sz.code12;

import java.util.HashSet;
import java.util.Set;

/**
 * 给出可整合数组的定义：如果一个数组排序之后，每个相邻的两个数差的绝对值都为1，则可整合
 * [5,3,4,6,2]排序后[2,3,4,5,6]
 * 返回可整合的长度，如[5,5,3,2,6,4,3],最大可整合子数组[5,3,2,6,4],返回5
 *
 * 解：记录从左到右区间中maxValue和minValue相减 = 长度 - 1
 */
public class LongestIntegratedLength {



    public static int getL1L2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;

        Set<Integer> set = new HashSet<>();
        for(int L = 0;L < arr.length ; L++){
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int R = L;R <arr.length ; R++){
                if(set.contains(arr[R])){
                    break; // 有重复数，换开头
                }
                set.add(arr[R]);
                max = Math.max(arr[R],max);
                min = Math.min(arr[R],min);
                if(max - min == R - L){ // L..R 可整合
                    len = Math.max(len,R - L + 1);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(getL1L2(arr));

    }

}
