package test;

import java.util.*;

public class SegmentTreeSquares {
    public static void main(String[] args) {

//        int [][] arr = {
//                {1,2},
//                {1,2},
//                {1,1},
//                {5,6}
//        };
//        List<Integer> process = process(arr);
//
//        System.out.println(process);

        char[][] grid = {
                {'1'},
                {'1'}
        };

        int i = numIslands(grid);
        System.out.println(i);
    }



    public static Map<Integer,Integer> index(int[][] arr){
        TreeSet<Integer> integers = new TreeSet<>();
        for(int[] a : arr){
            integers.add(a[0]);
            integers.add(a[0]+a[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i : integers){
            map.put(i,++count);
        }
        return map;
    }


    public static List<Integer> process(int[][] positions){
        Map<Integer, Integer> index = index(positions);
        int N = index.size();
        SegmentTreeSq segmentTreeSq = new SegmentTreeSq(N);
        ArrayList<Integer> res = new ArrayList<>();
        int max = 0 ;
        for(int[] j : positions){
           int L =  index.get(j[0]);
           int R =  index.get(j[0]+j[1] - 1);
           int height =  segmentTreeSq.query(L, R, 1, N, 1) + j[1];
           max = Math.max(max,height);
           res.add(max);
           segmentTreeSq.update(L,R,height,1,N,1);
        }
        return res;
    }


    public static class SegmentTreeSq{


        boolean[] update;
        int[] change;
        int MAX;

        int[] max;


        public SegmentTreeSq(int n){
            MAX = n + 1;
            update = new boolean[MAX << 2];
            change = new int[MAX << 2];
            max = new int[MAX << 2];
        }


        public void update(int L,int R,int C,int l ,int r,int rt){
            if(L <= l && r<= R){
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = (r + l) >> 1;
            pushDown(mid - l + 1,r- mid,rt);
            if(L <= mid){
                update(L,R,C,l,mid,rt << 1);
            }
            if(R > mid){
                update(L,R,C,mid+1,r,rt << 1 | 1);
            }
            pushUp(rt);
        }


        public int query(int L,int R,int l ,int r,int rt){
            if(L <= l && r<= R){
                return max[rt];
            }
            int mid = (r + l) >> 1;
            pushDown(mid - l + 1,r- mid,rt);
            int left = 0;
            int right = 0;
            if(L <= mid){
                left = query(L,R,l,mid,rt);
            }
            if(R > mid){
                right = query(L,R,mid+1,r,rt);
            }
            return Math.max(left,right);
        }

        public void pushDown(int ln,int rn,int rt){
            if(update[rt]){
                update[rt<<1] = true;
                update[rt<<1 | 1] = true;
                change[rt<<1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                max[rt<<1] = change[rt];
                max[rt << 1|1] = change[rt];
                update[rt] = false;
            }
        }


        public void pushUp(int rt){
            max[rt] = Math.max(max[rt<<1],max[rt<<1 | 1]);
        }
    }


    public static int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for(int i = 0; i < n;i++){
            for(int j = 0; j < m ;j++){
                if(grid[i][j] == '1'){
                    res++;
                    infect(grid,i,j,n,m);
                }
            }
        }
        return res;
    }

    public static void infect(char[][] grid,int i,int j,int n,int m){
        if(i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != '1')
        {
            return;
        }
        grid[i][j] = '2';
        infect(grid,i - 1,j,n,m);
        infect(grid,i + 1,j,n,m);
        infect(grid,i,j - 1,n,m);
        infect(grid,i,j + 1,n,m);

    }
}
