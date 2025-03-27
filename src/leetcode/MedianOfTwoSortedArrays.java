package leetcode;

public class MedianOfTwoSortedArrays {


    public static void main(String[] args) {

        int[] nums1 = {1,2};
        int[] nums2 = {3,4};

        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);

        System.out.println(medianSortedArrays);

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if(nums1.length != 0 && nums2.length != 0){

            if(even){
                return (double) (getKth(nums1,nums2,size/2) +
                        getKth(nums1,nums2,size/2 + 1)) / 2;
            }else {
                return (double)getKth(nums1,nums2,size/2 +1);
            }
        }else if(nums1.length != 0){
            if(even){
                return (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            }else{
                return nums1[size / 2];
            }
        }else if (nums2.length != 0){
            if(even){
                return (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            }else{
                return nums2[size / 2];
            }
        }else {
            return 0;
        }

    }


    public static int getKth(int[] arr1,int[] arr2,int kth){

        if(kth > arr1.length + arr2.length){
            return -1;
        }
        int[] longs = arr1.length > arr2.length ? arr1 : arr2;
        int[] shorts = longs == arr1 ? arr2 : arr1;

        int s = shorts.length;
        int l = longs.length;
        if(kth <= s){
            return getUpMedian(longs,0,kth-1,shorts,0,kth-1);
        }

        if(kth > l){

            if(longs[kth - s - 1]  >= shorts[s - 1]){
                return longs[kth - s - 1];
            }
            if(shorts[kth - l - 1] >= longs[l-1]){
                return shorts[kth - l - 1];
            }
            return getUpMedian(longs,kth - s,l-1,shorts,kth - l,s-1);
        }

        if(longs[kth - s - 1] >= shorts[s-1]){
            return longs[kth - s - 1];
        }


        return getUpMedian(shorts,0,s-1,longs,kth - s,kth-1);
    }

    // 返回上中位数
    public static int getUpMedian(int[] arr1,int s1,int e1,int[] arr2,int s2,int e2){

        int mid1 = 0;
        int mid2 = 0;

        while(s1 < e1){
            int size = e1-s1+1;
            mid1 = (s1+e1) / 2;
            mid2 = (s2+e2) / 2;

            if(arr1[mid1] == arr2[mid2]){
                return arr1[mid1];
            }

            if((size & 1) == 1){ // 奇数个数

                if(arr1[mid1] > arr2[mid2] ){
                    if(arr2[mid2] >= arr1[mid1-1]){
                        return arr2[mid2];
                    }
                    e1 = mid1-1;
                    s2 = mid2+1;
                }else {// arr1[mid1] < arr2[mid2]
                    if(arr2[mid2-1] <= arr1[mid1]){
                        return arr1[mid1];
                    }
                    e2 = mid2-1;
                    s1 = mid1+1;
                }

            }else{ // 偶数个数
                if(arr1[mid1] > arr2[mid2]){
                    e1 = mid1;
                    s2 = mid2 + 1;
                }else {//arr1[mid1] < arr2[mid2]
                    s1 = mid1 + 1;
                    e2 = mid2;
                }

            }

        }

        return Math.min(arr1[s1],arr2[s2]);
    }
}
