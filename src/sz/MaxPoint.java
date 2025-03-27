package sz;

public class MaxPoint {


    public static void main(String[] args) {
        int[] arr = {1,2,5,6,7,9,10,15};
        int i = maxPoint(arr, 5);
        System.out.println(i);
    }

    public static int maxPoint(int[] arr,int k){

        int l = 0;
        int r = 0;
        int max = 0;
        int N = arr.length;
        while (l < N){
            while ( r < N && arr[r] - arr[l] <= k ){
                r++;
            }
            max = Math.max(max,r - (l++));
        }

        return max;
    }
}
