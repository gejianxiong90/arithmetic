package test;

/**
 * 若干连续正数和的数
 * 比如：
 * 5 = 2+3 ，5就是这样的数
 * 12 = 3+4+5 ，12就是这样的数
 *
 * 1不是
 * 2 = 1+ 1 也不是
 * 给定参数N，问是不是
 */
public class SumToN {


    public static  boolean isSumToN(int n){
        int sum = 0;
        for(int i = 1 ; i < n ; i++){
            sum = i;
            for(int j = i + 1; j < n ; j++){
                sum += j;
                if(sum == n){
                    return true;
                }
                if(sum > n){
                    break;
                }

            }
        }
        return false;
    }

    public static boolean isSumToN2(int n){
        if(n < 3){
            return false;
        }
        return (n & (n - 1)) == 0 ? false : true; // 判断这个n 是不是2的几次方
    }

    public static void main(String[] args) {
        for(int i = 1; i < 200 ; i++){
            if(isSumToN(i) != isSumToN2(i)){
                System.out.println("oops!");
            }

            System.out.println( i +" : "+isSumToN(i));
        }
    }
}
