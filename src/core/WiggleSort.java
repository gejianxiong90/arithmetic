package core;

import java.util.HashMap;

public class WiggleSort {

    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();

        wiggleSort.wiggleSort(new int[]{1,5,1,1,6,4});
    }

    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length < 2){
            return;
        }
        int n = nums.length;
        // 先找到中位数
        findIndexNum(nums,0,n - 1,n/2);
        // 完美洗牌

        if((n & 1) == 1){
            shuffle(nums,1,n-1);
        }else {
            shuffle(nums,0,n-1);
            reverse(nums,0,n-1);
        }


    }

    public void shuffle(int[] nums, int L, int R) {
        while (R - L + 1 > 0) {
            int len = R - L + 1;
            int base = 3;
            int k = 1;
            while (base < (len + 1)/3) {
                base *= 3;
                k++;
            }
            int stepLen = base - 1;
            int mid = (L + R) / 2;

            rotate(nums,L + stepLen/2, mid,mid + stepLen/2);
            cycles(nums,k,L,stepLen);
            L += stepLen;
        }

    }

    public int nextIndex(int len,int index){
        return (index * 2) % (len + 1);
    }
    public void cycles(int[] nums,int k,int L,int len){
        for(int i = 0,trigger = 1; i < k ;i++,trigger *= 3 ){
            int preVal = nums[L + trigger -1];
            int cur = nextIndex(len,trigger);
            while(cur != trigger){
                int temp = nums[L + cur - 1];
                nums[L + cur - 1] = preVal;
                preVal = temp;
                cur = nextIndex(len,cur);
            }
            nums[L +cur - 1] = preVal;

        }
    }

    public void rotate(int[] nums, int L, int mid, int R) {
        reverse(nums, L, mid);
        reverse(nums, mid + 1, R);
        reverse(nums, L, R);
    }

    public void reverse(int[] nums, int L, int R) {

        while (L < R) {
            swap(nums, L++, R--);
        }
    }

    public int findIndexNum(int[] nums, int L, int R, int index) {
        while(L < R ){
            int pivot = nums[L +(int) (Math.random() * (R - L + 1))];
            int[] range = partition(nums, L, R, pivot);
            if (index >= range[0] && index <= range[1]) {
                return nums[index];
            } else if (index < range[0]) {
                R = range[0] - 1;
            } else if (index > range[1]) {
                L = range[1] + 1;
            }
        }
        return nums[L];
    }

    public int[] partition(int[] nums, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int index = L;

        while (index < more) {
            if (nums[index] < pivot) {
                swap(nums, index++, ++less);
            } else if (nums[index] > pivot) {
                swap(nums, index, --more);
            } else {
                index++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
