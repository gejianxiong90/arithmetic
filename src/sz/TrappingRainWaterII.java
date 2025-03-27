package sz;

import java.util.Comparator;
import java.util.PriorityQueue;


// 二维接雨水
// 给你一个 m * n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
// 请计算图中形状最多能接多少体积的雨水。
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/
public class TrappingRainWaterII {

    public static class Node{
        public int value;
        public int row;
        public int col;

        public Node(int v,int r ,int c){
            value = v;
            row = r;
            col = c;
        }
    }


    public static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap){
        if(heightMap == null || heightMap.length == 0){
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;

        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        for(int i = 0 ; i<N ; i++){
            for(int j = 0 ;j < M ;j++){
                if(i == 0 || i == N -1 || j == 0 || j == M -1){
                    heap.add(new Node(heightMap[i][j],i,j));
                    isEnter[i][j] = true;
                }
            }
        }
        int water = 0; // 每个位置的水量，累加到water上去
        int max = 0; // 每个node在弹出的时候，如果value更大，更新max，否则max的值维持不变
        while(!heap.isEmpty()){
            Node cur = heap.poll();
            max = Math.max(max,cur.value);
            int r = cur.row;
            int c = cur.col;

            if(r > 0 && !isEnter[r - 1][c]){
                water += Math.max(0,max - heightMap[r-1][c]);
                isEnter[r-1][c] = true;
                heap.add(new Node(heightMap[r-1][c],r-1,c));
            }

            if(r < N-1 && !isEnter[r + 1][c]){
                water += Math.max(0,max - heightMap[r+1][c]);
                isEnter[r+1][c] = true;
                heap.add(new Node(heightMap[r+1][c],r+1,c));
            }

            if(c > 0 && !isEnter[r][c - 1]){
                water += Math.max(0,max - heightMap[r][c-1]);
                isEnter[r][c-1] = true;
                heap.add(new Node(heightMap[r][c-1],r,c-1));
            }

            if(c < M - 1 && !isEnter[r][c + 1]){
                water += Math.max(0,max - heightMap[r][c+1]);
                isEnter[r][c+1] = true;
                heap.add(new Node(heightMap[r][c+1],r,c+1));
            }

        }

        return water;

    }
}
