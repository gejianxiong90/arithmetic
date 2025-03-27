package sz.code17;

import java.util.Arrays;

/**
 * arr中表示每个人的体重，limit表示一艘船的载重
 * 每艘船最多承载两个人
 * 问最少需要几艘船
 *
 * 解： 假设limit = 10
 *             L | R
 *   1 2 4 4 5 5 | 6 6 7 7  7 8
 *   √√√√××|
 *
 *   最后的7和8没有搞定
 *
 *   num <= limit / 2 作为分界线，L和R往两边滑动
 *   arr[L] + arr[R] <= limit , R往右滑，增加解决数量。否则L往左滑（L - 解决的数量），增加X数量
 *
 *   最终 √总数 + (×+1)/2 + 右边未解决的
 *   （左边 - X）+ 未使用/2 + (总数 - 左边数量 - √数量)
 *
 *
 */
public class MinBoat {

    public static int minBoat(int[] arr, int limit){
        if(arr == null || arr.length == 0){
            return -1;
        }
        for(int num : arr){
            if(num > limit){
                return -1;
            }
        }
        //保证每个人体重的有序
        Arrays.sort(arr);
        int N = arr.length;

        int lessR = -1;
        for(int i = N - 1 ; i >= 0 ; i--){
            if(arr[i] <= limit / 2){
                lessR = i;
                break;
            }
        }
        if(lessR == -1){
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L <= 0){
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit){ // 不断的往右滑，滑到>limit, 能滑动几位就解决了几个
                R++;
                solved++;
            }
            if(solved == 0){ //没有滑动，L往左滑，同时未解决划x的+1
                L--;
                noUsed++;
            }else {
                L = Math.max(-1,L - solved); // 右边划动几位，意味着左边匹配了几位，要减去位数
            }
        }

        // 结果是 解决的solved + 右边划不动的数量 + 左边划X的+1 / 2
        int lAll = lessR + 1; // <=limit 左半区的数量
        int used = lAll - noUsed; // 左边的√
        int rUnSolved = (N - lAll) - used; // 右边未解决的
        return used + ((noUsed + 1) >> 1) + rUnSolved;
    }
}
