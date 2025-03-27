package sz.code9;

/**
 * 给定一个无序数组arr，如果只能在一个子数组上排序
 * 返回如果让arr整体有序，需要排序的最短子数组长度
 *
 * 解： 从左--》右，记录最小的数，如果左边大于最小数，说明左边的数站不住，记录当前的下标
 *      从右--》左，记录最大数，如果右边小于最大数，说明右边的数站不住，记录当前的小标
 *
 *      两边走完，记录的最大值的右边的下标 - 记录最小值的左边的下标 ， 然后再 +1 就是需要的长度
 */
public class MinLengForSort {

    public static int getMinLength(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int min = arr[arr.length - 1];
        int minIndex = -1;
        for(int i = arr.length - 2; i >= 0 ; i--){
            if(arr[i] > min ){
                minIndex = i;
            }else {
                min = Math.min(min,arr[i]);
            }
        }
        if(minIndex == -1){
            return 0;
        }
        int max = arr[0];
        int maxIndex = -1;
        for(int j = 1;j <arr.length ; j++){
            if(arr[j] < max){
                max = j;
            }else {
                max = Math.max(arr[j],max);
            }
        }

        return maxIndex - minIndex + 1;
    }
}
