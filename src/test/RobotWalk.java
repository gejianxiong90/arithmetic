package test;

public class RobotWalk {

    public static int walk(int N,int cur,int rest,int P){
        if(rest == 0){
           return cur == P ? 1 : 0;
        }
       if(cur == 1 ){
           return walk(N,cur+1,rest - 1 ,P);
       }
       if(cur == N){
           return walk(N,N - 1,rest - 1,P );
       }

      return  walk(N,cur+1,rest-1,P) +  walk(N,cur - 1,rest-1,P);

    }
}
