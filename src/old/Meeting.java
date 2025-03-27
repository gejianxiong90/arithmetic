package old;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目描述
 为了庆祝中国共产党成立100周年，某公园将举行多场文艺表演，很多演出都是同时进行，一个人只能同时观看一场演出，且不能迟到早退，由于演出分布在不同的演出场地，所以连续观看的演出最少有15分钟的时间间隔，

 小明是一个狂热的文艺迷，想观看尽可能多的演出， 现给出演出时间表，请帮小明计算他最多能观看几场演出。

 输入描述
 第一行为一个数 N，表示演出场数

 1 ≤ N ≤ 1000
 接下来 N 行，每行有被空格分割的整数，第一个整数 T 表示演出的开始时间，第二个整数 L 表示演出的持续时间

 T 和 L 的单位为分钟
 0 ≤ T ≤ 1440
 0 < L ≤ 180
 输出描述
 输出最多能观看的演出场数。

 示例1
 输入

 2
 720 120
 840 120
 1
 2
 3
 输出

 1
 */
public class Meeting {

    public static class M{
        int start;
        int end;
        public M(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        PriorityQueue<M> ms = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        for(int i = 0 ; i < n ;i++){
            int start = in.nextInt();
            int end = start + in.nextInt();
            M m = new M(start, end);
            ms.add(m);
        }

        M m = ms.poll();
        int end = m.end;
        int times = 1;

        while(!ms.isEmpty()){
           M cur =  ms.poll();
           if(end + 15 <= cur.start){
               times++;
               end = cur.end;
           }
        }
        System.out.println(times);
    }


}
