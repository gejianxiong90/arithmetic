package core;

public class Sodoku {


    public static char[][] board =
            {{'.','8','7','6','5','4','3','2','1'},
             {'2','.','.','.','.','.','.','.','.'},
             {'3','.','.','.','.','.','.','.','.'},
             {'4','.','.','.','.','.','.','.','.'},
             {'5','.','.','.','.','.','.','.','.'},
             {'6','.','.','.','.','.','.','.','.'},
             {'7','.','.','.','.','.','.','.','.'},
             {'8','.','.','.','.','.','.','.','.'},
             {'9','.','.','.','.','.','.','.','.'}};


    public static boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];

        for (int i = 0; i <9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (row[i][num] || col[j][num] || bucket[bid][num]) {
                        return false;
                    }
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
        return true;
    }


    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        initMaps(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    public static void initMaps(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + (j / 3);
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    public static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) {
            return true;
        }
        int nexti = j != 8 ? i : i + 1;
        int nextj = j != 8 ? j + 1 : 0;
        if (board[i][j] != '.') {
            return process(board, nexti, nextj, row, col, bucket);
        } else {
            int bid = 3 * (i / 3) + (j / 3);
            for (int num = 1; num <= 9; num++) {
                if ((!row[i][num]) && (!col[j][num]) && (!bucket[bid][num])) {
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                    board[i][j] = (char) (num + '0');
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bid][num] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }




    public static void solveSudoku2(char[][] board){
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        initMap(board,row,col,bucket);
        process(board,row,col,bucket,0,0);

    }

    public static boolean process(char[][] board,boolean[][] row,boolean[][] col,boolean[][] bucket,int i,int j){
        if(i == 9){
            return true;
        }
        int nextI = j == 9 ? i + 1 : i;
        int nextJ = j == 9 ? 0 : j + 1;


        if (board[i][j] == '.') {
            int bid = 3 * (i / 3) + (j / 3);
            for (int k = 1; k <= 9; k++) {
                if (row[i][k] == false && col[j][k] == false && bucket[bid][k] == false) {
                    row[i][k] = true;
                    col[j][k] = true;
                    bucket[bid][k] = true;
                    board[i][j] = (char)k;
                    if(process(board, row, col, bucket, nextI, nextJ)){
                        return true;
                    }
                    row[i][k] = false;
                    col[j][k] = false;
                    bucket[bid][k] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }else {
            return process(board,row,col,bucket,nextI,nextJ);
        }

    }

    public static void initMap(char[][] board,boolean[][] row,boolean[][] col,boolean[][] bucket){
        for(int i = 0 ; i <board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;

                    int bid = 3 * (i / 3) + (j/3);
                    bucket[bid][num] = true;

                }
            }
        }
    }

    public static void main(String[] args) {
        boolean validSudoku = isValidSudoku(board);

        System.out.println(validSudoku);



        solveSudoku(board);

        System.out.println("done");
    }
}
