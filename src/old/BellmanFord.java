package old;

import java.util.Arrays;
import java.util.Scanner;

public class BellmanFord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int connections = scanner.nextInt();
        int[][] times = new int[connections][3];
        for(int i = 0 ; i < connections ; i++){
            times[i][0] = scanner.nextInt() - 1;
            times[i][1] = scanner.nextInt() - 1;
            times[i][2] = scanner.nextInt();
        }

        int initial = scanner.nextInt() - 1;
        scanner.close();

        System.out.println(netWorkDelayTime(times,N,initial));
    }

    /**
     *
     * @param times 边的权重
     * @param N 有多少台机器
     * @param K 出发的点
     * @return
     */
    public static int netWorkDelayTime(int[][] times,int N,int K){
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[N]; // 从源电脑到其他所有电脑的最短感染时间
        Arrays.fill(dist,INF);
        dist[K] = 0;
        // 使用Bellman-Ford算法更新所有电脑的最短感染时间
        for(int i = 0 ; i < N ;i++){
            for(int[] time : times){
                int u = time[0];
                int v = time[1];
                int w = time[2];
                if(dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w;
                }
            }
        }
        int maxWait = 0;
        for(int i = 0 ; i < N ;i++){
            if(dist[i] == INF){
                return -1;
            }
            maxWait = Math.max(maxWait,dist[i]);
        }
        return maxWait;
    }
}
