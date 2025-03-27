package dynamic;

import java.util.concurrent.ConcurrentHashMap;

public class RobotWalk {

    public static void main(String[] args) {

        int i = ways1(7, 3, 4, 5);
        System.out.println(i);

        int ways = ways(7, 3, 4, 5);
        System.out.println(ways);


        ConcurrentHashMap<Integer, Integer> integerIntegerConcurrentHashMap = new ConcurrentHashMap<>();
        integerIntegerConcurrentHashMap.put(1,1);
        integerIntegerConcurrentHashMap.size();
        System.out.println(integerIntegerConcurrentHashMap.size());

    }


    public static int ways(int N,int M,int K,int P){
        if(N<2 || K < 1 || M < 1|| M > N || P < 1 || P >N){
            return 0;
        }
        return walk1(N,M,K,P);
    }

    public static int walk1(int N ,int cur,int rest,int P){
        if(rest == 0){
            int val = cur == P ? 1 : 0;
            return val;
        }

        if(cur == 1){
            int walk = walk1(N, cur + 1, rest - 1, P);
            return walk;
        }
        if(cur == N){
            int walk = walk1(N, cur - 1, rest - 1, P);
            return  walk;
        }

        return walk1(N,cur+1,rest-1,P) + walk1(N,cur -1,rest-1,P);
    }


    public static int ways1(int N,int M,int K,int P){
        if(N<2 || K < 1 || M < 1|| M > N || P < 1 || P >N){
            return 0;
        }
        int[][] cache = new int[N+1][K+1];
        for(int i = 0;i<cache.length;i++){
            int[] row = cache[i];
            for (int j = 0;j< row.length;j++){
                row[j] = -1;
            }
        }
        return walk(N,M,K,P,cache);
    }

    /**
     *
     * @param N  位置为 1到N，固定参数
     * @param cur  当前在cur位置，可变参数
     * @param rest  还剩res歩没有走，可变参数
     * @param P  最终目标位置是P，固定参数
     *           只能在1到N位置上移动，当前在cur位置，走完rest歩之后，停在P位置
     * @return
     */
    public static int walk(int N ,int cur,int rest,int P,int[][] cache){
        if(cache[cur][rest] != -1){
            return cache[cur][rest];
        }
        if(rest == 0){
            int val = cur == P ? 1 : 0;
            cache[cur][rest] = val;
            return val;
        }

        if(cur == 1){
            int walk = walk(N, cur + 1, rest - 1, P, cache);
            cache[cur][rest] = walk;
            return walk;
        }
        if(cur == N){
            int walk = walk(N, cur - 1, rest - 1, P, cache);
            cache[cur][rest] = walk;
            return  walk;
        }
       cache[cur][rest] =  walk(N,cur+1,rest-1,P,cache) + walk(N,cur -1,rest-1,P,cache);
       return cache[cur][rest];
    }
}
