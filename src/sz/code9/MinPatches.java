package sz.code9;

import java.util.Arrays;

/**
 * 给定一个数组arr，如：{1,5,7} 和 一个目标aim 如 15，想要完成任意累加到1-15的所有数(缺2,4)， 需要至少添加几个数
 * 解： 定义range从0开始，小于arr[i],他得不到的数就是 range += range+1，一路累加，>=arr[i]时更新range += arr[i]
 * 结论：给定一个数，得不到的数永远是range累加形成的
 *
 */
public class MinPatches {


    public static void main(String[] args) {
        int[] arr = {1,5,7};
        int i = minPatches(arr, 15);
        System.out.println(i);
    }

    /**
     * 前提保证arr有序，可以先排序
     * @param arr
     * @param aim
     * @return
     */
    public static int minPatches(int[] arr,int aim){
        if(arr == null || arr.length < 1){
            return 0;
        }
        Arrays.sort(arr);
        int count = 0;
        int range = 0;
        for(int i = 0; i < arr.length ; i++){
            while (range + 1 < arr[i]){
                range += range + 1;
                count++;
                if(range >= aim){
                    return count;
                }
            }
            range += arr[i];
            if(range  >= aim){
                return count;
            }
        }

        // 如果所有数加完还没到达aim
        while (range + 1 <= aim){
            range += range+1;
            count++;
        }
        return count;
    }
}
