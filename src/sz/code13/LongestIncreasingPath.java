package sz.code13;

public class LongestIncreasingPath {

    public static int maxPath(int[][] matrix){
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i < matrix.length ; i++){
            for (int j = 0 ; j < matrix[0].length ; j++){
               res = Math.max(res, process(matrix,i,j));
            }
        }

        return res;
    }

    public static int process(int[][] matrix,int i ,int j){
        int row = matrix.length;
        int col = matrix[0].length;
        if(i > row || i < 0 || j > col || j< 0){
            return -1;
        }
        int next1 = 0;
        if( i+1 < row && matrix[i][j] < matrix[i+1][j]){
            next1 = process(matrix,i+1,j);
        }
        int next2 = 0;
        if(i - 1 >= 0 && matrix[i][j] < matrix[i-1][j]){
            next2 = process(matrix,i-1,j);
        }
        int next3 = 0;
        if( j + 1 < col && matrix[i][j] < matrix[i][j+1]){
            next3 = process(matrix,i,j+1);
        }
        int next4 = 0;
        if( j -1 <= 0 && matrix[i][j] < matrix[i][j-1]){
            next4 = process(matrix,i,j-1);
        }
        return 1+Math.max(Math.max(next1,next2),Math.max(next3,next4));
    }


    public static int maxPath2(int[][] matrix){
        int res = Integer.MIN_VALUE;

        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int i = 0 ; i < matrix.length ; i++){
            for (int j = 0 ; j < matrix[0].length ; j++){
                res = Math.max(res, process2(matrix,i,j,dp));
            }
        }

        return res;
    }

    public static int process2(int[][] matrix,int i ,int j, int[][] dp){
        int row = matrix.length;
        int col = matrix[0].length;
        if(i > row || i < 0 || j > col || j< 0){
            return -1;
        }
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int next1 = 0;
        if( i+1 < row && matrix[i][j] < matrix[i+1][j]){
            next1 = process(matrix,i+1,j);
        }
        int next2 = 0;
        if(i - 1 >= 0 && matrix[i][j] < matrix[i-1][j]){
            next2 = process(matrix,i-1,j);
        }
        int next3 = 0;
        if( j + 1 < col && matrix[i][j] < matrix[i][j+1]){
            next3 = process(matrix,i,j+1);
        }
        int next4 = 0;
        if( j -1 <= 0 && matrix[i][j] < matrix[i][j-1]){
            next4 = process(matrix,i,j-1);
        }
        int res =  1+Math.max(Math.max(next1,next2),Math.max(next3,next4));

        dp[i][j] = res;
        return res;
    }
}
