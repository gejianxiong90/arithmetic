package sz.code8;

/**
 * 给定一个正整数数组，你从0个数跳到最后一个数
 * 每个数的值是你从这个位置可以跳跃的最大长度
 * 计算最小跳跃的次数到最后一个数
 */
public class JumpGame {

    public static int jump(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int step = 0; //跳了多少歩
        int cur = 0; // step歩内，右边界
        int next = 0; // step + 1歩内，右边界

        for(int i = 0;i< arr.length; i++){
            if(cur < i){
                step++;
                cur = next;
            }
            next = Math.max(next,i+arr[i]);
        }

        return step;
    }
}
