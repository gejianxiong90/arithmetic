package core;

public class UniquePaths {

    public static int uniquePaths(int m,int n){
        int part = n -1 ;
        int all = m + n -2;
        long o1 = 1;
        long o2 = 1;
        for(int i = part+1,j=1; i <= all || j <= all - part ;i++,j++){
            System.out.println( o1 + "*"+i);
            System.out.println(o2 + "*" + j);
            o1 *= i;
            o2 *= j;
            long gcd = gcd(o1,o2);
            System.out.println("----"+gcd);
            o1 /= gcd;
            o2 /= gcd;
        }
        return (int)o1;
    }

    public static long gcd(long m , long n){
        return n == 0 ? m : gcd(n,m % n);
    }


    public static void main(String[] args) {
        int i = uniquePaths(7, 8);

        System.out.println(i);
    }
}
