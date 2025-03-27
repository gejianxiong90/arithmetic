package core;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SubarrayBeauty {

    public static void main(String[] args) {
        int[] nums = {-50,4,36,-22};
        SubarrayBeauty subarrayBeauty = new SubarrayBeauty();
        int[] subarrayBeauty1 = subarrayBeauty.getSubarrayBeauty(nums, 4, 3);


        System.out.println(subarrayBeauty1);
    }

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        if(nums.length < k){
            return new  int[]{0};
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for(int i = 0 ; i < k - 1 ; i++){
           queue.addLast(nums[i]);
        }
        Integer[] kArr = new Integer[k];
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->a-b);
        heap.addAll(queue);
        for(int i = k - 1 ; i < nums.length ;i++){
            queue.addLast(nums[i]);
            heap.add(nums[i]);
            int temp = 0;
            for(int count = 0 ; count < x ; count++){
               temp = heap.poll();
            }
            if(temp < 0 ){
                res[index] = temp;
            }

           // heap.clear();
//            queue.toArray(kArr);
//            int r = bfprt(kArr,x);
//            if(r < 0){
//              res[index] = r;
//            }
            index++;
            Integer integer = queue.removeFirst();
            heap.remove(integer);
        }

        return res;
    }

    private int bfprt(Integer[] nums,int k){

        return process(nums,0,nums.length - 1, k - 1);
    }

    private int process(Integer[] nums,int l,int r,int index){
        if(l == r){
            return nums[l];
        }
        Integer medianOfMedia = getMedianOfMedia(nums, l, r);
        Integer[] partition = partition(nums, l, r, medianOfMedia);
        if(index >= partition[0] && index <= partition[1]){
            return nums[index];
        }else if(index < partition[0]){
           return  process(nums,l,partition[0]-1,index);
        }else {
           return process(nums,partition[1]+1,r,index);
        }

     //   return 0;
    }

    private Integer getMedianOfMedia(Integer[] nums,int l,int r){
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        Integer[] mArr = new Integer[size / 5 + offset];
        for(int team = 0 ; team < mArr.length ; team++){
            int teamFirst = l + team * 5;
            mArr[team] = getMedian(nums,teamFirst,Math.min(teamFirst + 4 ,r));
        }
        return process(mArr,0,mArr.length - 1,mArr.length / 2);
    }

    public Integer getMedian(Integer[] nums,int l,int r){

        quickSort(nums,l,r);

        return nums[(r + l) / 2];

    }
    public void quickSort(Integer[] nums,int l ,int r){
        if(l >= r){
            return;
        }
        int povit = nums[l + (int)(Math.random() * (r - l + 1))];
        Integer[] range = partition(nums, l, r, povit);

        quickSort(nums,l,range[0] - 1);
        quickSort(nums,range[1] + 1,r);



    }

    public Integer[] partition(Integer[] nums,int l,int r,Integer povit){
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

    public void swap(Integer[] nums,int i,int j){
        Integer temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
