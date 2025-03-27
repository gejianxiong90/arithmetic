package sz.code16;

/**
 * 给定两个有序数组arr1和arr2
 * 求第k大的数，最优解是 log（min（n，m））
 *
 *      // 三种情况
 *      s（短数组长度） l(长数组长度)
 //  1 < k<= s
 //  s < k <= l
 // l < kth <= l + s
 */
public class FindKthMinNumber {




    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        if(arr1 == null || arr2 == null){
            return -1;
        }
        if(kth <1 || arr1.length + arr2.length < kth){
            return -1;
        }
        int[] longs = arr1.length > arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length > arr2.length ? arr2 : arr1;
        int l = longs.length;
        int s = shorts.length;
        // 三种情况
        //  1 < kth <= s
        //  s < kth <= l
        // l < kth <= l + s

        if(kth <= s){
            return getUpMedian(shorts,0,kth-1,longs,0,kth - 1);
        }else if( kth > l){
            if(longs[kth - s - 1] >= shorts[s - 1]){
                return longs[kth - s - 1];
            }
            if(shorts[kth - l - 1] >= longs[l - 1]){
                return shorts[kth - l - 1];
            }

            return getUpMedian(shorts,kth - l,s - 1,longs,kth - s ,l - 1);
        }

        if(longs[kth - s - 1] >= shorts[s-1]){
            return longs[kth - s - 1];
        }

        return getUpMedian(shorts,0,s-1,longs,kth - s ,kth - 1);
    }


    /**
     * 两个有序数组获取中上位数
     * 两个数组必须等长
     *  1234
     *  5678  返回4
     *
     *  12345
     *  678910 返回5
     *
     * @param A
     * @param s1
     * @param e1
     * @param B
     * @param s2
     * @param e2
     * @return
     */
        public static int getUpMedian(int[] A,int s1,int e1,int[] B,int s2,int e2){
        int mid1 = 0;
        int mid2 = 0;
        while(s1 < e1){
            mid1 = (s1 + e1 ) / 2;
            mid2 = (s2 + e2 ) / 2;
            if(A[mid1] == B[mid2]){
                return A[mid1];
            }
            if((( e1 - s1 + 1) & 1) == 1){ // 奇数
                if(A[mid1] > B[mid2]){
                    if(B[mid2] >= A[mid1 - 1]){
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                }else{
                    if(A[mid1] >= B[mid2 - 1] ){
                        return A[mid1];
                    }
                    s1 = mid1 + 1 ;
                    e2 = mid2 - 1 ;

                }
            }else { // 偶数
                if(A[mid1] > B[mid2]){
                    e1 = mid1;
                    s2 = mid2 + 1;
                }else{
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }
        }
        return Math.min(A[s1],B[s2]);
    }


    public static void main(String[] args) {
//        int[] A = {1,2,3,4,11};
//        int[] B = {6,7,8,9,10};
//        int upMedian = getUpMedian(A, 0, 4, B, 0, 4);
//
//        System.out.println(upMedian);

        int[] arr1 = {0,2,5,5,10,12,15,17,18,19};
        int[] arr2 = {4,8,14,24,35,36,40,42,49,50,57,60,61,62,72,76,81,84,84,84,86,88,91};

        int kthNum = findKthNum(arr1, arr2, 8);
        System.out.println(kthNum);


    }
}
