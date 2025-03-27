package core;

public class DivideTwoIntegers {

    /**
     * 两数相加
     * @param a
     * @param b
     * @return
     */
    public static int add(int a,int b){
        int sum = a;
        while(b != 0){
            sum = a ^ b;    // 无进位相加
            b = (a & b) << 1; // 进位信息
            a = sum;
        }
        return sum;
    }

    /**
     * 一个数取反
     *  6 --》 -6
     *    6 取反+1
     * @param n
     * @return
     */
    public static int negNum(int n){
        return add(~n,1);
    }

    /**
     * 两数相减
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a,int b){
        return add(a,negNum(b));
    }


    public static int multi(int a,int b){
        int res = 0;
        while (b != 0){
            if((b & 1) == 1){
                res = add(res,a);
            }
            a<<=1;
            b>>>=1;
        }
        return res;
    }


    public static boolean isNeg(int n){
        return n < 0;
    }
    public static int div(int a,int b){
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for(int i = 31 ; i > negNum(1) ; i = minus(i,1)){
            if((x >> i) >= y){
                res |= (1 <<i);
                x = minus(x,y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int dividend,int divisor){
        if(divisor == Integer.MIN_VALUE){
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        if(dividend == Integer.MIN_VALUE){
            if(divisor == negNum(1)){
                return Integer.MAX_VALUE;
            }
            int res = div(add(dividend,1),divisor);
            return add(res,div(minus(dividend,multi(res,divisor)),divisor));
        }
        return div(dividend,divisor);
    }


    public static String printNumBinary(int num){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 31; i >= 0 ; i--){
            stringBuilder.append(((num >> i) & 1) == 1 ? "1":"0");
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        System.out.println(add(1,5));
        System.out.println(add(9,15));
        System.out.println(add(101,25));
        System.out.println(add(441,522));

        System.out.println(minus(522,441));


        System.out.println(multi(3,6));
        System.out.println(multi(-3,9));


        int res = 0;
        res |= 1 << 3;
        res |= 1 << 2;
        System.out.println(res);


        int divide = divide(100, 10);

        System.out.println(divide);


        String s = printNumBinary(-1);
        System.out.println(s);

    }
}
