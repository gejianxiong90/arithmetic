package test;

public class ZigzagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix){
        int ar = 0;
        int ac = 0;
        int br = 0;
        int bc = 0;
        int endr = matrix.length - 1;
        int endc = matrix[0].length - 1;
        boolean fromUp = false;

        while(ar != endr+1){
            printLevel(matrix,ar,ac,br,bc,fromUp);
            ar = ac == endc ? ar + 1 : ar;
            ac = ac == endc ? ac : ac + 1;
            bc = br == endr ? bc + 1 : bc;
            br = br == endr ? br : br + 1;
            fromUp = !fromUp;
        }
        System.out.println();

    }


    public static void printLevel(int[][] m,int ar,int ac,int br,int bc,boolean fromUp){
        if(fromUp){
            while(ar != br + 1){
                System.out.print(m[ar++][ac--] + "  ");
            }
        }else{
            while (br != ar - 1){
                System.out.print(m[br--][bc++]+"  ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };

        printMatrixZigZag(matrix);

        spiralOrderPrint(matrix);
    }





    public static void spiralOrderPrint(int[][] matrix ){
        int tr = 0;
        int tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;
        while (tr <= dr && tc <= dc){
            printEdge(matrix,tr++,tc++,dr--,dc--);
        }
    }

    /**
     *
     * @param m
     * @param a 行
     * @param b 列
     * @param c
     * @param d
     */
    public static void printEdge(int[][] m,int a,int b,int c,int d){
        if(a == c){
            for(int i = b; i <=b;i++){
                System.out.println(m[a][i] + " ");
            }
        }else if(b == d){
            for(int i = a;i <= a;i++){
                System.out.println(m[i][b] + " ");
            }
        }else{
            int curC = b;
            int curR = a;
            while (curC != d){
                System.out.println(m[a][curC++] + " ");
            }
            while (curR != c){
                System.out.println(m[curR++][d] + " ");
            }
            while (curC != b){
                System.out.println(m[c][curC--] + " ");
            }
            while(curR != a){
                System.out.println(m[curR--][b] + " ");
            }
        }
    }



    public static void rotate(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;

        while(a < c){
            rotateEdge(matrix,a++,b++,c--,d--);
        }
    }

    public static void rotateEdge(int[][] m , int a , int b,int c ,int d){
        int tmp = 0;
        for(int i = 0 ; i < b - d ; i++){
            tmp = m[a][b+i];
            m[a][b+i] =  m[c-i][b];
            m[c-i][b] =  m[c][d-i];
            m[c][d-i] =  m[a+i][d];
            m[a+i][d] = tmp;
        }
    }
}
