package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 进阶 滑动窗口
public class SlidingWindowMaxArr {

    public static void main(String[] args) {

        System.out.println("barfoofoobarthefoobarman".indexOf("fko"));

        int[] arr = {4,3,5,4,3,3,6,7};
        int[] integers = winMaxArr(arr, 3);
        for(Integer i : integers){
            System.out.print(arr[i]);
        }
    }

    public static int[] winMaxArr(int[] arr,int w){
        int[] ints = new int[arr.length - w + 1];
        if(arr.length < w) {
            return null;
        }
        LinkedList<Integer> tmpMax = new LinkedList<>();
        int index = 0;
        for (int R = 0 ; R < arr.length ; R++ ){
            while (!tmpMax .isEmpty() && arr[R] >= arr[tmpMax.peekLast()]){
                tmpMax.pollLast();
            }
            tmpMax.addLast(R);
            if (!tmpMax.isEmpty() && tmpMax.peekFirst() == R - w){ // L = R - w
                tmpMax.pollFirst();
            }
            if(R >= w -1 ){
               ints[index++] = tmpMax.peekFirst();
            }
        }
        return  ints;
    }








    public static int getNums(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return 0;
        }
        LinkedList<Integer> max = new LinkedList<Integer>();
        LinkedList<Integer> min = new LinkedList<Integer>();
        int L = 0 ;
        int R = 0;
        int ans = 0;

        while(L < arr.length){
            while (R < arr.length){
                while(!max.isEmpty() && arr[max.peekLast()] <= arr[R]){
                    max.pollLast();
                }
                max.addLast(R);
                while(!min.isEmpty() && arr[min.peekLast()] >= arr[R]){
                    min.pollLast();
                }
                min.addLast(R);
                if(arr[max.peekFirst()] - arr[min.peekFirst()] > num){
                    break;
                }
                R++;
            }
            ans += R - L;
            if(!max.isEmpty() && max.peekFirst() <= L){
                max.pollFirst();
            }
            if(!min.isEmpty() && min.peekFirst() <= L){
                min.pollFirst();
            }
            L++;
        }
        return ans;
    }



    /**
     * 一个数组，从左到右，范围内的最大值和最小值相减 <= null 算达标，问有多少的子数组达标
     * @param arr
     * @param num
     * @return
     */
    public static int getNum(int[] arr,int num){
        if(arr == null || arr.length < 1){
            return 0;
        }
        int valid = 0;
        LinkedList<Integer> max = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();
        int L = 0;
        int R = 0;
        while ( L < arr.length){
            while (R < arr.length){
                while (!max.isEmpty() && arr[max.peekLast()] <= arr[R]){
                    max.pollLast();
                }
                max.addLast(R);
                while (!min.isEmpty() && arr[max.peekLast()] >= arr[R]){
                    min.pollLast();
                }
                min.addLast(R);
                if(arr[max.peekFirst()] -  arr[min.peekFirst()] > num){
                    break;
                }
                R++;
            }
            valid += R - L;
            while (!max.isEmpty() && max.peekFirst() ==  L ){
                max.pollFirst();
            }
            while (!min.isEmpty() && min.peekFirst() == L ){
                min.pollFirst();
            }

        }

        return valid;
    }
}
