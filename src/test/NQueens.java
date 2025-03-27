package test;

public class NQueens {


    public static void main(String[] args) {
        int n = 4;
        int[] record = new int[n];
        System.out.println(process(0,record,n));

        System.out.println(num2(n));


//        int a = -1;
//        int b = a >> 2;
//        int c = a >>> 2;
//
        System.out.println(Integer.toBinaryString(-8));
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println(Integer.toBinaryString(c));
    }

    public static int process(int i,int[] record,int n){
        if(i==n){
            return 1;
        }
        int res = 0;
        for(int j = 0 ; j < n ; j++){ // j 为当前列
            if(isValid(record,i,j)){
                record[i] = j; // i 为行
                res += process(i+1,record,n);
            }
        }
        return res;
    }


    public static int num2(int n){
        if(n < 1 || n > 32){
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit,0,0,0);
    }

    /**
     *
     * @param limit  如 n=8 那么 limit = 11111111        1<< 8 -1 (100000000 -1 = 11111111)
     * @param col 列上面的限制 如 00100000 有1的列不可以放
     * @param left 左边的限制  如 01000000 左边斜线1的位置不能放
     * @param right 右边的限制 如 00010000 右边斜线1的位置不能放
     * @return
     */
    public static int process2(int limit,int col,int left,int right){
        if( limit == col){
            return 1;
        }
        int pos = limit & ~(col | left | right); // 所有1的位置可以放  ~（00100000 | 01000000 | 00010000） = 111111....10001111， 11111111 & 111...10001111 =  10001111;
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0 ){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne ;
            res += process2(limit,col | mostRightOne,(left | mostRightOne) << 1 , (right | mostRightOne)>>>1);
        }
        return res;
    }

    public static boolean isValid(int[] record,int i ,int j){
        for(int k = 0 ; k < i ; k++){
            if(record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)){
                return false;
            }
        }
        return true;
    }



}
