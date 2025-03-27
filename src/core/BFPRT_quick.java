package core;

import java.util.Random;

public class BFPRT_quick {


    public static void main(String[] args) {
        int[] arr = {3,6,4,2};
//        quicksort(arr,0,3);
//        for(int i : arr){
//            System.out.println(i);
//        }

        int index = 2; //第二大
        int bfprt = bfprt(arr, 0, 3, 2 - 1 );
        System.out.println(bfprt);
    }

    public static void quickSort2(int[] nums,int l ,int r){
        if(l >= r){
            return;
        }
        int povit = nums[l + (int)(Math.random() * (r - l + 1))];
        Integer[] range = partition2(nums, l, r, povit);

        quickSort2(nums,l,range[0] - 1);
        quickSort2(nums,range[1] + 1,r);



    }

    public static Integer[] partition2(int[] nums,int l,int r,Integer povit){
        int less = l - 1;
        int more = r + 1;
        int index = l;
        while(index < more){
            if(nums[index] < povit){
                swap(nums,index++,++less);
            }else if(nums[index] > povit){
                swap(nums,index,--more);
            }else {
                index++;
            }
        }
        return new Integer[]{less+1, more - 1};
    }


    public static void quicksort(int[] arr,int l,int r){
        if( l >= r){
            return;
        }
        int randomIndex = l + (int)(Math.random() * (r - l + 1));
        int[] res = partition(arr,l,r,randomIndex);
        quicksort(arr,l,res[0]);
        quicksort(arr,res[1],r);

    }

    public static int[] partition(int[] arr,int l,int r,int randomIndex){
        if(l >= r){
            return new int[]{-1,-1};
        }
        int low = l - 1;
        int more = r + 1;
        int index = l;
        while(index < more){
            if(arr[index] < arr[randomIndex]){
                swap(arr,index++,++low);
            }else if(arr[index] > arr[randomIndex]){
                swap(arr,index,--more);
            }else{
                index++;
            }
        }

        return new int[]{low,more};
    }

    public static void swap(int[] arr,int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static int bfprt(int[] arr,int L ,int R ,int k){
        if( L == R){
          return arr[L];
        }
        int m = medianOfMedian(arr, L, R);
        int[] range = partition3(arr, L, R, m);
        if( k >= range[0] && k <= range[1]){
            return arr[k];
        }else if( k < range[0]){
            return bfprt(arr,L,range[0] - 1,k);
        }else {
            return bfprt(arr,range[1] + 1,R,k);
        }
    }

    public static int medianOfMedian(int[] arr,int L,int R){
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for(int i = 0 ; i <mArr.length ; i++){
            int first = L + i * 5 ;
            mArr[i] = median(arr,first,Math.min(R,first+4));
        }
        return bfprt(mArr,0,mArr.length - 1,mArr.length / 2);
    }

    public static int median(int[] arr,int L,int R){
        insertionSort(arr,L,R);
        return arr[(R + L)/2];
    }

    public static void insertionSort(int[] arr,int L,int R){
        for(int i = L + 1 ; i <= R ;i++){
            for(int j = i - 1 ; j >= L && arr[j] > arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static int[] partition3(int[] arr, int L,int R,int povit){
        if(L > R){
            return new int[]{-1,-1};
        }
        if(L == R){
            return new int[]{L,R};
        }
        int less = L -1;
        int more = R + 1;
        int index = L;
        while(index < more){
            if(arr[index] < povit){
                swap(arr,++less,index++);
            }else if(arr[index] > povit){
                swap(arr,--more,index);
            }else{
                index++;
            }
        }
        return new int[]{less+1,more - 1};
    }
}
