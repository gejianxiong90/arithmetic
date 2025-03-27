package core;

import java.util.LinkedList;
import java.util.Queue;


public class SlidingWindowMaxArray {
    /**
     * 给定数组arr，w为窗口长度，求出每个窗口长度中的最大值
     */
    public static int[] maxArr(int[] arr, int w){
        if(w > arr.length){
           return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> qMax = new LinkedList<>();
        for(int i = 0 ; i < w ; i++){
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            qMax.addLast(i);
        }
        res[index++] = arr[qMax.peekFirst()];
        for(int j = w ; j < arr.length ; j++){
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j] ){
                qMax.pollLast();
            }
            qMax.addLast(j);
            res[index++] = arr[qMax.peekFirst()];
        }
        return res;
    }


    /**
     * 窗口中最大值-最小值 <= num 为达标，给定一个arr，返回达标数量
     * @param arr
     * @param num
     * @return
     */
    public static int getNum(int[] arr,int num){

        int L = 0 ;
        int R = 0;
        int res = 0;
        LinkedList<Integer> qMax = new LinkedList<Integer>();
        LinkedList<Integer> qMin = new LinkedList<Integer>();
        while(L < arr.length){
            while(R < arr.length){
                while(!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
                    qMax.pollLast();
                }
                qMax.addLast(R);

                while(!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[R]){
                    qMin.pollLast();
                }
                qMin.addLast(R);

                if(qMax.peekFirst() - qMin.peekFirst() > num){
                    break;
                }
                R++;
            }

            res += R - L;
            if(qMax.peekFirst() == L){
                qMax.pollFirst();
            }
            if(qMin.peekFirst() == L){
                qMin.pollFirst();
            }
            L++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,5,2,6,2,6};
        int w = 2;
        int[] ints = maxArr(arr, w);

        int num = getNum(arr, 3);
        System.out.println(num);

        System.out.println(ints);


    }
}
