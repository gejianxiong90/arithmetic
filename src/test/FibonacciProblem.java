package test;

/**
 *  1,1,2,3,5,8,13,21,34
 *
 *    | 8 ,5 |  = |5,3|  * | 11|
 *                         | 10|
 *
 *              ^
 *
 *  |f(n)，f(n-1)|  = | 1,1| * | 11| ^n-2
 *                             | 10|
 */
public class FibonacciProblem {

    public static int f1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return f1(n - 1) +  f1(n - 2);
    }

    public static int f3(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }

        int[][] base = {
                {1,1},
                {1,0}
        };

        int [][] res = matrixPower(base, n -2);
        return res[0][0] +  res[1][0];
    }

    public static int[][] matrixPower(int[][] m , int p){
        int[][] res = new int[m.length][m[0].length];
        for(int i = 0; i < res.length ; i++){
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for(; p != 0 ; p>>=1 ){
            if((p & 1) != 0){
               res = muliMatrix(res,tmp);
            }
            tmp = muliMatrix(tmp,tmp);
        }

        return res;
    }

    public static int[][] muliMatrix(int[][] m1 , int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for(int i = 0 ; i < m1.length;i++){  // 矩阵相乘，满足a的列数等于b的行数
            for(int j = 0 ; j < m2[0].length; j++){
                for(int k = 0 ; k < m2.length ; k++){
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static int[][] muliMatrix2(int[][] m1,int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];

        for(int i = 0 ; i < m1.length ;i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int i = 1;

        System.out.println(~i + 1);

        System.out.println(2 & -2);


        System.out.println(f1(10));
        System.out.println(f3(10));

        int p = 888;
        System.out.println(p>>1);
        System.out.println(p);
        System.out.println(p>>=1);
        System.out.println(p);
    }
}
