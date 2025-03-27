package sz.code15;

/**
 * 已知一个int数组，其中一个数出现了1次，其他数出现了K次，求这个出现了一次的数
 *
 * 解：转化成K进制，每个位相加，并模上k，最终就是那个出现一次的k进制数组（转成10进制就得出）
 */
public class KTimesOneTime {

    public static int onceNum(int[] arr,int k){
        int[] ints = new int[32];
        // 先把十进制的数转化成k进制的数

        for(int i = 0 ; i < arr.length ; i++){
            setExclusiveOr(ints,arr[i],k);
        }
        // K进制转成十进制
        return getNumFromK(ints,k);
    }


    /**
     * 转化成k进制，并模K
     * @param e0
     * @param value
     * @param k
     */
    public static void setExclusiveOr(int[] e0,int value,int k){
        int[] kFromNum = getKFromNum(value, k);
        for(int i = 0 ; i < e0.length ; i++){
            e0[i] = (e0[i] + kFromNum[i]) % k;
        }
    }

    /**
     * 十进制转化成k进制  14 --》 21100000 00000000 。。。。。。 （前面是低位，后面是高位）
     * @param num
     * @param k
     * @return
     */
    public static int[] getKFromNum(int num , int k){
        int index = 0;
        int[] kArr = new int[32];
        int value = num;

        while(value != 0){
            kArr[index++] = value % k;
            value = value/k;
        }
        return kArr;
    }

    /**
     * k进制转化成十进制
     * @param arr
     * @param k
     * @return
     */
    public static int getNumFromK(int[] arr, int k){
        int res = 0;
        for(int i = arr.length-1 ; i >= 0  ; i--){
            res = res * k + arr[i];
        }
        return res;
    }

    public static void main(String[] args) {


        int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
        System.out.println(onceNum(test1, 3));

        int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
        System.out.println(onceNum(test2, 5));
    }
}
