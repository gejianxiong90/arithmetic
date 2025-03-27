package core;

public class MyPow {

    public static double myPow(double x,int n){
        if(n == 0){
            return 1D;
        }
        if(n == Integer.MIN_VALUE){
            return (x == 1D || x == -1D) ? 1 : 0;
        }

        int pow = Math.abs(n);
        double t = x;
        double res = 1D;
        while(pow!=0){
            if((pow & 1) != 0){
                res *= t;
            }
            pow>>= 1;
            t *= t;
        }

        return n < 0 ? (1D / res) : res;
    }

    public static void main(String[] args) {
        double v = myPow(1.0000000000001, -2147483648);

        System.out.println(v);

        System.out.println(3249158 % 1000);
    }
}
