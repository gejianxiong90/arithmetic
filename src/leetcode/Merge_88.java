package leetcode;

public class Merge_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int mi = 0;
        int ni = 0;
        int resi = 0;

        while (ni < n && mi < m ) {
            res[resi++] = nums1[mi] < nums2[ni] ? nums1[mi++] : nums2[ni++];
        }
        while(ni < n){
            res[resi++] = nums2[ni++] ;
        }
        while(mi < m){
            res[resi++] = nums1[mi++] ;
        }

        for(int i = 0 ; i < nums1.length && i < res.length ; i++){
            nums1[i] = res[i];
        }

    }
}
