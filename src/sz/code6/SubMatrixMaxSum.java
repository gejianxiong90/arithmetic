package sz.code6;

/**
 * 一个矩阵的最大子矩阵累加和
 */
public class SubMatrixMaxSum {

    public static int maxSum(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s =null;
        for(int i = 0; i < m.length;i++){
            s = new int[m[0].length];
            for(int j = i ; j < m.length ; j++){
                cur = 0;
                for(int k = 0 ; k < m[0].length ; k++){
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(max,cur);
                    cur = cur < 0 ? 0 :cur;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {-90,48,78},
                {-64,-40,-64},
                {-81,-7,66}
        };

        int i = maxSum(matrix);
        System.out.println(i);
    }
}
