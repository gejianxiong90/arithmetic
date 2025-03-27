package sz.code14;

/**
 * 给定一个矩阵，
 * 从左上方走到右下方，每步只能向下或向右，
 * 再从右下方回到左上方，每步只能向上火向左
 * 返回一来一回得到的最大路径和
 *
 *
 * 解：两个人同时走从左上到右下 == 从左上到右下 + 从右下到左上
 *  枚举 A和B往下和往右所有的可能
 *  如果AB的行号一样，必然列号一样，必定在一个点。因为走同样的步数，并且只能往下或往右
 *
 *
 */
public class CherryPickup {


    public static int comeGoMaxPathSum(int[][] matrix) {
        return process(matrix, 0, 0, 0);
    }

    public static int process(int[][] matrix,int ar,int ac,int br){
        int N = matrix.length;
        int M = matrix[0].length;
        if(ar == N - 1 && ac == M - 1){
            return matrix[ar][ac];
        }
        // 还没到右下角
        // A 下 B 右
        // A 下 B 下
        // A 右 B 右
        // A 右 B 下
        int bc = ar + ac - br;

        int aRightBRigth = -1;
        if(ac + 1 < M && bc + 1 < M){
            aRightBRigth = process(matrix,ar,ac+1,br);
        }
        int aDownBDown = -1;
        if(ar + 1 < N && br + 1 < N ){
            aDownBDown = process(matrix,ar+1,ac,br+1);
        }
        int aRigthBDown = -1;
        if(ac + 1 < M && br + 1 < N){
            aRigthBDown = process(matrix,ar,ac+1,br+1);
        }
        int aDownBRigth = -1;
        if(ar + 1 < N && bc + 1 < M){
            aDownBRigth = process(matrix,ar+1,ac,br);
        }

        int max = Math.max(Math.max(aDownBDown, aRightBRigth), Math.max(aRigthBDown, aDownBRigth));
        if(ar == br){ // AB两个在一个点，取一个点的值
            return matrix[ar][ac] + max;
        }
        return matrix[ar][ac] + matrix[br][bc] + max; // A和B不同位置
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1}};

        System.out.println(comeGoMaxPathSum(matrix));
    }
}
