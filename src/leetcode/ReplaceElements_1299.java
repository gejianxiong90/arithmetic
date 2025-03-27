package leetcode;

import java.util.LinkedList;

public class ReplaceElements_1299 {

    public static void main(String[] args) {
        int[] arr = {17,1};

        int[] ints = replaceElements2(arr);

        System.out.println(ints);
    }
    public static int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        res[n-1] = -1;
        for(int i = n - 2 ; i >= 0; i--){
            res[i] = Math.max(res[i+1],arr[i+1]);
        }
        return res;
    }

    /**
     *  89/90
     * @param arr
     * @return
     */
    public static int[] replaceElements2(int[] arr) {
        if(arr.length == 1){
            arr[0] = -1;
            return arr;
        }
        LinkedList<Integer> linked = new LinkedList<Integer>();// 单调栈从下往上是从大到小
        for(int i= 0 ; i < arr.length ;i++){
            if(linked.isEmpty() || arr[linked.peekLast()] > arr[i]){
                linked.add(i);
            }else{
                int count = 0;
                while(!linked.isEmpty() && arr[linked.peekLast()] < arr[i]){
                    linked.pollLast();
                }
                count = i - linked.size();
                for(int j = 0 ; j < count ; j++){
                    linked.add(i);
                }
            }
        }
        while(linked.size() < arr.length){
            linked.add(-1);
        }
        int[] res = new int[linked.size()];
        for(int i = 0; i < res.length ;i++){
            int index = linked.pollFirst();
            if( index== -1){
                res[i] = -1;
            }else {
                res[i] = arr[index];
            }
        }

        return res;
    }
}
