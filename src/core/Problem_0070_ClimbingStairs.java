package core;

import java.util.HashMap;
import java.util.Map;

public class Problem_0070_ClimbingStairs {

    public int climbStairs1(int n) {
        int[] ans = new int[n+1];
        process(n,ans);

        Map<Integer, Integer> map = new HashMap<>();
        map.forEach((k,v)->{

        });
        return ans[n];
    }

    public static int process(int n,int[] ans){
        if(ans[n] != 0){
            return ans[n];
        }

        if(n==1 || n ==2){
            ans[n] = n;
            return n;
        }
        //    if(n == 1){
        //     ans[n] = 1;
        //     return 1;
        //     }
        //     if( n ==2){
        //         ans[n] = 2;
        //         return 2;
        //     }
        ans[n] = process(n-1,ans) + process(n-2,ans);
        return ans[n] ;
    }


    public static int climbStairs(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return n;
		}
		int[][] base = { { 1, 1 }, { 1, 0 } };
		int[][] res = matrixPower(base, n - 2);
		return 2 * res[0][0] + res[1][0];
	}
    
	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i][i] = 1;
		}
		
		// res = 矩阵中的1
		int[][] tmp = m;// 矩阵1次方
		for (; p != 0; p >>= 1) {
			if ((p & 1) != 0) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}

	// 两个矩阵乘完之后的结果返回
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length];
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		return res;
	}


	public static void main(String[] args) {
        int i = climbStairs(4);

        System.out.println(i);
    }
}

