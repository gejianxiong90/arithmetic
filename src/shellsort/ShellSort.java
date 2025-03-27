package shellsort;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {

        int[] array = {11,12,10,6,8,4};
        sort(array);

        System.out.println(Arrays.toString(array));

    }

    public static void sort(int[] array){
        int d = array.length; //希尔排序数量
        while( d> 1){
            d >>>= 1;  // 使用希尔增量方式，每次折半 d = 4 2 1
            for(int x = 0;x<d;x++){ // 折半后分组比较，分几组  x = 0 1 2 3
                for(int i = x + d;i< array.length;i = i+d){  // 歩长角标大的数 i= 4 5 6 7
                    int temp = array[i];
                    int j;
                    for (j = i - d;j>=0 && array[j] > temp;j = j-d){  // 歩长角标小的数 j = 0 1 2 3
                        array[j+d] = array[j];
                    }
                    array[j+d] = temp;
                }
            }
        }
    }
    public static void sort1(int[] array){
        int length = array.length;
        while(length > 1){
            length >>>= 1;
            for (int i = 0; i < length ; i++) {
                for (int j = i+length;j < array.length;j += length){
                    int temp = array[j];
                    int k;
                    for(k = j - length; k >=0 && array[k] > temp ; k -=length){
                        array[k + length] =  array[k];
                    }
                    array[k+length] = temp;
                }
            }
        }
    }





















    public static void shellSort(int[] arr){
        int length = arr.length;
        while(length>1){
            length >>>= 1;
            for (int i = 0; i <length ; i++) {
                for (int j = i + length; j < arr.length ; j += length){
                    int temp = arr[j];
                    int k ;
                    for ( k = j - length; k >=0 && arr[k] > temp ; k -= length){
                        arr[k + length] = arr[k];
                    }
                    arr[k+length] = temp;
                }
            }
        }
    }
}
