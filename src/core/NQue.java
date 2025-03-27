package core;

public class NQue {

    public static void main(String[] args) {
        System.out.println( nQ(5));
        System.out.println( nQue2(5));
    }

    public static int nQ(int n){
        int[] record = new int[n];
        return process(record,n,0);
    }

    public static int process(int[] record,int n,int i){
        if(i == record.length){
            return 1;
        }
        int res = 0;
        for(int j = 0 ; j < n ;j++){
            if(valid(record,i,j)){
                record[i] = j;
                res += process(record,n,i+1);
            }
        }
        return res;
    }

    public static boolean valid(int[] record,int i,int j){
        for(int k = 0 ; k < i ; k++){
            if(record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)){
                return false;
            }
        }
        return true;
    }

    public static int nQue2(int n){
        int limit = n == 32 ? -1 : (1 << n ) - 1;
        return process2(limit,0,0,0);
    }

    public static int process2(int limit,int colLim,int leftLim,int rightLim){
     if(limit == colLim){ // 皇后位的所有列都满了
         return 1;
     }
     int pos = limit & ~(colLim | leftLim | rightLim); // 还可以放的位置，提取可以放的位置 11111111 & 1111 。。。。11100111 = 11100111
     int res = 0;
        int mostRightOne = 0;
     while(pos != 0){ // 还有可以放的位置
         mostRightOne = pos & (~pos + 1); // 提取最右侧的1
         pos -= mostRightOne;
         res += process2(limit,colLim | mostRightOne,(leftLim | mostRightOne) << 1,(rightLim | mostRightOne) >>> 1); // 设置好限制列
     }
     return res;
    }
}
