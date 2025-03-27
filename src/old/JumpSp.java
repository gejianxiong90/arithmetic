package old;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，

 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。

 输入描述
 第一行输入总的格子数量 n

 第二行输入每个格子的分数 score[i]

 第三行输入最大跳的步长 k

 备注
 格子的总长度 n 和步长 k 的区间在 [1, 100000]

 每个格子的分数 score[i] 在 [-10000, 10000] 区间中

 输出描述
 输出最大得分
 */
public class JumpSp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());

        if(scores.length == 1){
            System.out.println(scores[0]);
            return;
        }

        int[] dp = new int[n];// 每个位置的最大值

        dp[0] = scores[0];
       // 窗口内最大dp值的索引
        LinkedList<Integer> deque = new LinkedList<>();// 维护窗口，保持递减
        deque.add(0);// dp中已经加入0，同步加入
        for(int i = 1 ; i < n ;i++){
            while(!deque.isEmpty() && deque.peekFirst() < i -k ){
                deque.pollFirst(); // 超出窗口，过期弹出
            }
            dp[i] = scores[i] + (deque.isEmpty() ? 0 : dp[deque.peekFirst()]);
            while(!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]){ // 维持递减
                deque.pollLast();
            }
            deque.add(i);
        }
        System.out.println(dp[n-1]);


    }
}
