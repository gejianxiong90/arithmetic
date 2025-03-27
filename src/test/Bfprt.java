package test;

import java.util.Arrays;

public class Bfprt {

    public static void main(String[] args) {

        int arr[] = {1,2,3,4,-1,7};
        int k = 3;
        System.out.println(bfprt(arr,0,5,k - 1));
    }

    public static int bfprt(int arr[],int L,int R,int index){
        if(L == R){
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] range = partiton(arr, L, R,pivot);
        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return bfprt(arr,L,range[0]-1,index);
        }else {
            return bfprt(arr,range[1]+1,R,index);
        }
    }

    public static int medianOfMedians(int arr[],int L,int R){
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for(int team = 0 ;team < mArr.length;team++){
            int teamFirst = L + team * 5;
            mArr[team] = getMedian(arr,teamFirst,Math.min(R,teamFirst+4));
        }
        return bfprt(mArr,0,mArr.length - 1,mArr.length/2);
    }

    public static int getMedian(int[] arr,int L,int R){
        insertionSort(arr,L,R);;
        return arr[(L+R)/2];
    }

    public static int[] partiton(int[] arr,int L,int R,int pivot){
        if(L >R){
            return new int[]{-1,-1};
        }
        if(L == R){
            return new int[]{L,R};
        }
        int less = L -1; // < 区右边界
        int more = R;  // > 区域左边界
        int index = L;
        while(index < more){
            if(arr[index] == pivot){
                ++index;
            }else if(arr[index] < pivot){
                swap(arr,++less,index++);
            }else {
                swap(arr,index,--more);
            }
        }
        swap(arr,more,R);

        return new int[]{less+1,more};
    }


    public static void insertionSort(int[] arr,int L,int R){
        for(int i = L + 1; i <= R;i++){
            for(int j = i-1 ; j >= L && arr[j] > arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static void swap(int arr[],int i,int j){
        if(arr==null || arr.length < 1 || i >= arr.length || j >= arr.length){
            return ;
        }
        int temp = arr[i];
        arr[i] = arr[j] ;
        arr[j] = temp;
    }
}
