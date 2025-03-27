package core;


import java.util.HashMap;
import java.util.Map;

/**
 * 1-9 所有数字（无重复数字），给定一个n值，求有几个“带分数”形式
 * 要求满足 n = p1+ p2/p3 ，相等则是一个有效的“带分数”形式，p2/p3必须整除
 * 假设  100 = 12 + 35467/98 , 相等则满足
 *
 */
public class AddDevideNum {

    public static Map<Integer,Integer> map = new HashMap<Integer, Integer>();

    public static int[] arr = {1,10,100,1000,10000,100000,1000000,10000000,100000000};


    public static int ways(int n){

        process(123456789,8);
        return map.containsKey(n) ? map.get(n) : 0;
    }


   public static int process(int num,int index){
       if(index == -1){
          for(int addSplit = 8 ; addSplit >= 2 ; addSplit--){
              int p1 = num / arr[addSplit];
              int rest = num % arr[addSplit];
              for(int devSplit = addSplit >> 1 ; devSplit >= 1 ; devSplit--){
                  int p2 = rest / arr[devSplit];
                  int p3 = rest % arr[devSplit];
                  if(p2 % p3 ==0){
                      int ans = p1 + p2 / p3;
                      if(!map.containsKey(ans)){
                          map.put(ans,1);
                      }else {
                          map.put(ans,map.get(ans)+1);
                      }
                  }
              }
          }
       }else {
           for(int swap = index ; swap >= 0 ;swap--){
               int next = swapNum(num,swap,index);
               process(next,index - 1);
           }
       }

       return 0;
   }

   public static int swapNum(int num,int L,int R){
         int bitL = (num / arr[L]) % 10;
         int bitR = (num / arr[R]) % 10;
         return  (num - (bitL - bitR) * arr[L]) + (bitL - bitR) * arr[R];
   }

    public static void main(String[] args) {
       // int swap = swapNum(123456789, 0, 8);

       // System.out.println(swap);

        int ways = ways(10008);

        System.out.println(ways);
    }
}
