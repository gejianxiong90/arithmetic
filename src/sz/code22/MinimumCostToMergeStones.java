package sz.code22;

/**
 * 给定一个arr数组，和k ， k是合并需要的数量，arr按k个数量合并最终合并成一个，合并的数字累加，最后累加的和最小是多少
 * 比如 arr：{3,2,4,1} k = 2
 *  3+2 = 5 代价是5
 *  4+1=5 代价是5
 *  最后 5 + 5 = 10 ，所以最优解就是 20
 *
 *  如果k=3，就是-1无解，3+2+4 完了之后没有3个数了
 *
 *  解：范围上的尝试 + 从左到右的尝试
 *   L 。。 R 范围的最小代价
 *   在L。。R范围上从左往右去枚举
 */
public class MinimumCostToMergeStones {



    public static int mergeMinNum(int[] arr, int k){
        int N = arr.length;
        if((N-1) % (k-1) > 0){
            return -1;
        }
        int[] help = new int[N+1];
        for(int i = 0; i < arr.length ; i++){
            help[i+1] += help[i] + arr[i];
        }
        return process(arr,0,arr.length - 1,1,k,help);
    }

    /**
     * arr[L..R] 一定要弄出part份，返回最低代价
     * @param arr
     * @param L
     * @param R
     * @param part
     * @param k
     * @param sum
     * @return
     */
     //0 2 5 9 14
    public static int  process(int[] arr,int L,int R,int part,int k,int[] sum){
        if(L == R){
            return part == 1 ? 0 : -1; // 自己本来就是一份返回0，不需要代价
        }
        if(part == 1){
            int next = process(arr, L, R, k, k, sum); // 只需要1个part时，就分成k个
            if(next == -1){
                return -1;
            }
            return next + sum[R+1] - sum[L];
        }else {
            int res = Integer.MAX_VALUE;
            for(int mid = L ; mid < R ; mid += k-1){
                int next1 = process(arr, L, mid, 1, k, sum);
                int next2 = process(arr, mid + 1, R, part - 1, k, sum);
                if(next1 != -1 && next2 != -1){
                   res = Math.min(res,next1+next2);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,3,4,5};
        int i = mergeMinNum(arr, 2);

        System.out.println(i);
    }
}
