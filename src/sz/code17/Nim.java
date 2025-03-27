package sz.code17;

/**
 * Nim 博弈问题
 *  两个人，从arr数组中一次从任意一个数字中拿走>=1的数量，面对全部都是0没数可拿就输了，问谁能赢
 *
 *  结论：异或和！=0 先手赢，否则后手赢
 */
public class Nim {

    // arr是正数数组
    public static void printWinner(int[] arr){
        int eor = 0;
        for(int num : arr){
            eor ^= num;
        }

        if(eor!= 0){
            System.out.println("先手赢");
        }else {
            System.out.println("后手赢");
        }
    }
}
