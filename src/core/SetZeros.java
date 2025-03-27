package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetZeros {
    public static void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<String> zeroList = new ArrayList<String>();
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ;j++){
                if(matrix[i][j] == 0){
                    zeroList.add(i+"_"+j);
                }
            }
        }

        setZero(matrix,zeroList);
    }

    public static void setZero(int[][] matrix, List<String> zeroList){
        int N = matrix.length;
        int M = matrix[0].length;
        for(String str : zeroList){
            String[] split = str.split("_");
            for(int i =0;i < matrix.length;i++){
                matrix[i][Integer.valueOf(split[1])] = 0;
            }
            for(int j = 0 ; j <matrix[0].length;j++){
                matrix[Integer.valueOf(split[0])][j] = 0;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
    }
}
