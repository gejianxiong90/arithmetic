package core;

public class BloomFilterBitArr {

    public static int[] arr = null;

    public static int[] init(int m){
        if(m % 32 == 0 ){
            arr =  new int[m / 32];
            return arr;
        }else {
            arr = new int[(m / 32)+1];
            return arr;
        }
    }

    public static void set(int pos,boolean b){
        if(b){
            arr[pos/32] = arr[pos / 32] | (1 << (pos % 32));
        }else {
            int status = getStatus(pos);
            if(status == 1){
                arr[pos/32] = arr[pos / 32] ^ (1 << (pos % 32));
            }
        }
    }

    public static int getStatus(int pos){
        return (arr[pos/32] >> (pos % 32)) & 1;
    }


    public static void main(String[] args) {
        init(1000);
        set(721,true);
        int status = getStatus(721);

        System.out.println(status);

        set(721,false);
        System.out.println(getStatus(721));

        set(721,true);
        System.out.println(getStatus(721));
    }
}
