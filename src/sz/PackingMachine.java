package sz;

public class PackingMachine {

    public static int MinOps(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for(int i =0;i < size ; i++){
            sum += arr[i];
        }
        if(sum % size != 0){
            return -1;
        }

        int avg = sum/size;
        int leftSum = 0;
        int ans = 0;
        // 每个位置都求各自
        for (int i = 0; i < arr.length ; i++){
            // i号机器，就是中间机器 ，左（0 --》 i - 1） i  右（i + 1 --》 N -1 ）
            // 负 需要输入  正需要输出
            int leftRest = leftSum - i * avg;
            // 负 需要输入  正需要输出
            // c - d
            int rightRest = (sum - leftSum - arr[i]) - (size - i -1 ) * avg;
            if(leftRest < 0 && leftRest < 0){
                ans = Math.max(ans,Math.abs(leftRest)+Math.abs(rightRest));
            }else {
                ans = Math.max(Math.abs(leftRest),Math.abs(rightRest));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
