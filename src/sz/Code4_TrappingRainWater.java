package sz;

public class Code4_TrappingRainWater {


    /**
     * 给定一个正整数数组arr，把arr想象成直方图，返回这个直方图如果装水，能装下几格水
     */
    public static int water(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int N = arr.length;
        int L = 1;
        int R = N - 2;
        int leftMax = arr[0];
        int rightMax = arr[N - 1];
        int water = 0;
        while(L <= R){
            if(leftMax <= rightMax){
                water += Math.max(0,leftMax - arr[L]);
                leftMax = Math.max(leftMax,arr[L++]);
            }else {
                water += Math.max(0 ,rightMax - arr[R]);
                rightMax = Math.max(rightMax,arr[R--]);
            }
        }
        return water;
    }
}
