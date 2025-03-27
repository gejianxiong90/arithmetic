package core;

import java.util.HashMap;
import java.util.Map;

public class TakeCharacters {

    public static int takeCharacters(String s, int k) {
       int[] map = new int[3];
       Map<String,Integer> dp = new HashMap<String,Integer>();
        int[] sum = new int[1];
        sum[0] = Integer.MAX_VALUE;
        int res = process(s,0,s.length()-1, k, map,sum,dp);

        return res == Integer.MAX_VALUE ? -1 : res;
    }


    public static int process(String s,int start,int end,int k,int[] map,int[] sum, Map<String,Integer> dp){
        String key = start+"_"+end;
        if(dp.containsKey(key)){
            return dp.get(key);
        }
        if(valid(map,k)){
            int curSum = getSum(map);
            sum[0] = Math.min(curSum,sum[0]);
            dp.put(key,sum[0]);
            return sum[0];
        }
        if(start > end){
            return Integer.MAX_VALUE;
        }
        if(getSum(map) >= sum[0]){
            dp.put(key,sum[0]);
            return sum[0];
        }

        char r = s.charAt(end);
        map[r - 'a']++;
        int preSub = process(s,0,end - 1,k,map,sum,dp);
        map[r - 'a']--;

        char l = s.charAt(start);
        map[l- 'a']++;
        int sufSub = process(s,start+1,end,k,map,sum,dp);
        map[l - 'a']--;
        dp.put(key,Math.min(sufSub,preSub) == Integer.MAX_VALUE ? -1 :  Math.min(sufSub,preSub));
        return dp.get(key);

    }

    public static int getSum(int[] map){
        int res = 0 ;
        for(int v : map){
            res += v;
        }
        return res;
    }


    public static boolean valid(int[] map,int k){
        if(map[0] >= k && map[1] >= k && map[2] >= k){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE == Integer.MAX_VALUE);
        int aabaaaacaabc = takeCharacters("aabaaaacaabc", 2);
        System.out.println(aabaaaacaabc);

    }
}
