package sz.code20;

/**
 * 完美洗牌问题
 *
 * 给定一个偶数长度的数组
 * 假设为 L1 L2 L3 R1 R2 R3
 * 生成   R1 L1 R2 L2 R3 L3
 */
public class ShuffleProblem {

    /**
     * 下标从1开始
     * @param index 现在的下标
     * @param len 数组长度
     * @return 去的index
     */
    public static int modifyIndex1(int index,int len){
        return index > len / 2 ? ((index - len / 2) * 2) - 1 : index * 2;
    }

    /**
     * 下标从1开始
     * @param index 现在的下标
     * @param len 数组长度
     * @return 去的index
     */
    public static int modifyIndex2(int index,int len){
        return (index * 2) % (len + 1);
    }

    public static void shuffle(int[] arr){
        if(arr == null || arr.length == 0 || (arr.length & 1) == 1){
            return;
        }

        shuffle(arr,0,arr.length-1);
    }

    /**
     * 以 3^k - 1 做切分，2,8,26 。。。 3^k - 1
     * 以1,3,9。。。3^k 做不同环的开始点
     *  长度     2     8     26
     *  出发点   1    1,3   1,3,9
     * @param arr
     * @param L
     * @param R
     */
    public static void shuffle(int[] arr, int L,int R){
        while (R - L + 1 > 0){
            int base = 3; // 3 为底数
            int k = 1; // k为3的几次房
            int len = R - L + 1;
            // 计算小于等于len并且是离len最近的，满足(3^k)-1的数
            // 也就是找到最大的k，满足3^k <= len+1
            if(base <= (len+1)/3 ){ // 找到len小于某次方的第一个数 如len = 23 ，base = 9 ，k = 2  , base - 1就是那个数
                base *= 3;
                k++;
            }
            //3^k - 1   , 要解决base-1的数据量，/2为一半长度
            int half = (base - 1)/2;
            // L..R的中心位置
            int mid = (R + L) / 2;

            rotate(arr,L+half,mid,mid+half);
            cycles(arr,L,base - 1,k);
            L = L + base - 1; // base - 1是上一组处理完的长度，L往右移动这个长度
        }
    }

    /**
     *
     * 下标循环怼
     * @param arr
     * @param L 左边开始的下标
     * @param len 处理的数组长度
     * @param k  有K个环，比如 1,3,9 环的起始点（不是数组下标）
     */
    public static void cycles(int[] arr, int L,int len,int k){
        for(int i = 0, trigger = 1;i < k ; i++, trigger *= 3 ){
            int preValue = arr[L + trigger -1]; // +trigger是环开始的位置，-1是因为下标是从0开始
            int cur = modifyIndex2(trigger, len); // 环的第一个位置要去的下标
            while (cur != trigger){ // 相等就是一个环的闭环，需要跳出开始下一个环
                int temp = arr[L + cur - 1]; // 第一个所到达下标的值先存临时变量
                arr[L + cur -1] = preValue; // 第一个到达的值赋予开始 起点的值
                cur = modifyIndex2(cur,len); // 到达的下标需要去的下标
                preValue = temp; // 当前到达的数值，变成下一个达到的
            }
            arr[L + cur - 1] = preValue; // 到了一个闭环，cur==trigger，需要将最后一个给前一个；
        }
    }

    /**
     *
     * @param arr
     * @param L
     * @param mid
     * @param R
     * L...mid是左半部分
     * mid+1...R是右半部分
     * 注意在这里下标是从0开始
     *
     * 彻底以中心点调换位置，需要把左部分翻转 + 右部分翻转 + 整体翻转
     */
    public static void rotate(int[] arr,int L,int mid,int R){
        reverse(arr,L,mid);
        reverse(arr,mid+1,R);
        reverse(arr,L,R);
    }

    public static void reverse(int[] arr,int L,int R){
        while(R > L){
            int temp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = temp;
        }
    }
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7,8};
        shuffle(arr);

        for(int i :arr){
            System.out.print(i+",");
        }
    }
}
