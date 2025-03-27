package sz.code22;

/**
 * 给定一个数组只包含1/2/3分别表示左中右位置，求当前是最优解的第几歩
 * arr= {3,2,1,2,3,1,1}  汉诺塔的圆盘大小是从小到大
 * 3表示最小的在最右侧，2 次小的在中间
 *
 * 解：
 *  最优解arr[i]位置不可能在other位置
 *  分解成三步
 *  1.  0....i-1 from -> other
 *  2.  i        from -> to
 *  3.  0....i-1 other -> to
 *
 */
public class HanoiProblem {

    /**
     * N 层汉诺塔 从左到右需要几步
     * @param N
     */
    public static void hanoi(int N){
        if(N > 0){
            process(N,"left","right","mid");
        }
    }

    /**
     * N层汉诺塔的问题可以拆解成3歩 , 小压大
     * 1. from ---》 to 最上层从from到to
     * 2. from ---》 other 剩下的从from到other
     * 3. other ---》 to  最后剩下的从other到to
     * @param N
     * @param from
     * @param to
     * @param other
     */
    public static void process(int N , String from , String to , String other){
        if(N == 1){
            System.out.println( "move "+ 1 + " " + from + "  到  "+ to);
        }else {
            process(N - 1,from,other,to);
            System.out.println("move "+ (N) + " " + from + "  到   " + to );
            process(N - 1,other,to,from);
        }
    }


    public static int hanoiStep(int[] arr){
        if (arr == null || arr.length == 0) {
            return -1;
        }

        return process(arr,arr.length-1,1,2,3);
    }

    /**
     * 汉诺塔的步数最优解释 2^N   - 1
     *
     *  1.  0....i-1 from -> other        2^(i-1) - 1
     *  2.  i        from -> to           2^(i-1) - 1+1
     *  3.  0....i-1 other -> to          整体2^i - 1
     *
     * @param arr
     * @param i
     * @param from
     * @param other
     * @param to
     * @return
     */
    public static int process(int[] arr,int i ,int from,int other,int to){
        if(i == -1){
            return 0;
        }
        if(arr[i] != from && arr[i] != to){
            return -1;  // 最优解arr[i]不可能在other位置
        }
        int res = 0;
        if(arr[i] == from){ // 说明在第一步  0...i-1 从from到other ，第一步没走完就是总目标走多少歩
            return process(arr,i-1,from,to,other);        // 一二两歩 总和步数是 2^(i-1)  （ 第一步的总和是  2^(i-1) - 1，加上第二i到to就是  2^(i-1) - 1 + 1）
        }else { // arr[i] 在to的位置
            // 到to了说明一二两步走完了，剩下走第三歩
            res = process(arr,i-1,other,from,to); // 最后将other移动到to
            if(res == -1){
                return -1;
            }
            return (1<< i) + res; // 因为i从o开始的所有 所以是 i-1就是i
        }
    }

    public static void main(String[] args) {

        System.out.println('a'-0);

        System.out.println(1<<4);
        hanoi(3);


            int[] arr = { 3, 3, 2, 1 };
            System.out.println(hanoiStep(arr));

    }
}
