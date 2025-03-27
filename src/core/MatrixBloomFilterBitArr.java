package core;

public class MatrixBloomFilterBitArr {

    public static int[][] arr = null;


    public static void init(int m){
        int rowCol = 0;
        for(int i = 10 ; i <Integer.MAX_VALUE;i++){
            if(i * i >= m){
                rowCol = i;
                break;
            }
        }
        arr = new int[rowCol][rowCol];
    }

    public static void set(int pos , boolean b){
        int status = getStatus(pos);
        int rowCol =  arr.length;
        if(b){
           int rowIndex = pos / (rowCol * 32);
           int colIndex = (pos % (rowCol*32)) /32;
           arr[rowIndex][colIndex] = arr[rowIndex][colIndex] | (1 << (pos%32));
        }else {
            if(status == 1){
                int rowIndex = pos / (rowCol * 32);
                int colIndex = (pos % (rowCol*32)) / 32;
                arr[rowIndex][colIndex] = arr[rowIndex][colIndex] ^ (1<< (pos%32));
            }
        }
    }

    public static int getStatus(int pos){

        int rowCol = arr.length;

        int rowIndex = pos / (rowCol*32);

        int colIndex = (pos % (rowCol*32)) / 32;

        return  (arr[rowIndex][colIndex] >> (pos% 32)) & 1;
    }


    public static void main(String[] args) {
        init(1000000);
        set(7777,true);
        System.out.println(getStatus(7777));

        set(7777,false);
        System.out.println(getStatus(7777));

        set(7777,true);
        System.out.println(getStatus(7777));
    }
}
