package sz.code14;

public class DungeonGame {


    public static int needMin(int[][] matrix){

       return process(matrix,matrix.length,matrix[0].length,0,0);
    }

    public static int process(int[][] matrix,int N,int M,int row,int col){
        if(row == N - 1 && col == M - 1){
            return matrix[N][M] < 0 ? -matrix[N][M] + 1 : 1 ;
        }
        if(row == N - 1 ){
            int right = process(matrix, N, M, row, col + 1);
            if(matrix[row][col] < 0){
                return -matrix[row][col] + right;
            }else if(matrix[row][col] >= right){
                return 1; // 至少需要一点血买
            }else{
                return right - matrix[row][col];
            }
        }
        if(col == M - 1){
            int down = process(matrix, N, M, row + 1, col);
            if(matrix[row][col] < 0){
                return -matrix[row][col] + down;
            }else if(matrix[row][col] >= down){
                return 1; // 至少需要一点血买
            }else{
                return down - matrix[row][col];
            }
        }
        int min = Math.min(process(matrix, N, M, row, col + 1), process(matrix, N, M, row + 1, col));
        if(matrix[row][col] < 0){
            return -matrix[row][col] + min;
        }else if(matrix[row][col] >= min){
            return 1; // 至少需要一点血买
        }else{
            return min - matrix[row][col];
        }
    }
}
