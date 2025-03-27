package sz.code22;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有一个电话拨号盘ring，字符串ring每位一个字母，k为字符串目标，拨打12点钟方向，每拨动一个算一步，每个字母需要按一个确认（所以每个字符完成的步数 = 拨动的几格 + 最后1个确认）
 *
 */
public class FreeDomTrail {
    /**
     *
     * @param i1 起点
     * @param i2 终点
     * @param size 圆盘长度
     * @return 最少的歩数
     */
    public static int dial(int i1,int i2,int size){
        return Math.min(Math.abs(i1- i2),Math.min(i1,i2) + size - Math.max(i1,i2));
    }

    public static int findRotateStep1(String r , String k){
        char[] ring = r.toCharArray();
        char[] aim = k.toCharArray();
        Map<Character,List<Integer>> map = new HashMap<>();

        for(int i = 0 ; i <ring.length ; i++){
            if(!map.containsKey(ring[i])){
                map.put(ring[i],new ArrayList<Integer>());
            }
            map.get(ring[i]).add(i);
        }
        return process(0,0,aim,map,ring.length);
    }

    /**
     *
     *  含义是我当前在preIndex位置完成keyIndex位置的拨号最少的步数
     * @param preIndex  起点
     * @param keyIndex  终点
     * @param key 需要完成的key（固定参数）
     * @param map b -- {2,3,5} 字符所在ring的位置 （固定参数）
     * @param size ring的长度（固定参数）
     * @return
     */
    public static int process(int preIndex,int keyIndex,char[] key,Map<Character,List<Integer>> map,int size){
        if(keyIndex == key.length){
            return 0;
        }
        int res = Integer.MAX_VALUE;
            List<Integer> curList = map.get(key[keyIndex]);
            for(Integer cur : curList){
                res = Math.min(res, dial(preIndex,cur,size) + 1 + process(cur,keyIndex+1,key,map,size));
            }
        return res;
    }


    public static int findRotateStep2(String r , String k){
        char[] ring = r.toCharArray();
        char[] aim = k.toCharArray();
        Map<Character,List<Integer>> map = new HashMap<>();

        for(int i = 0 ; i <ring.length ; i++){
            if(!map.containsKey(ring[i])){
                map.put(ring[i],new ArrayList<Integer>());
            }
            map.get(ring[i]).add(i);
        }
        int[][] ints = new int[r.length()+1][k.length()+1];

        for(int i = 0 ; i < r.length() ; i++){
            for(int j = 0 ; j < k.length() ; j++){
                ints[i][j] = -1;
            }
        }
        return dp(0,0,aim,map,ring.length,ints);
    }

    /**
     * 记忆化搜索
     * @param preIndex
     * @param keyIndex
     * @param key
     * @param map
     * @param size
     * @param dp
     * @return
     */
    public static int dp(int preIndex,int keyIndex,char[] key,Map<Character,List<Integer>> map,int size,int[][] dp){
        if(dp[preIndex][keyIndex] != -1){
            return dp[preIndex][keyIndex];
        }
        if(keyIndex == key.length){
            dp[preIndex][keyIndex] = 0;
            return 0;
        }
        int res = Integer.MAX_VALUE;
        List<Integer> curList = map.get(key[keyIndex]);
        for(Integer cur : curList){
            res = Math.min(res, dial(preIndex,cur,size) + 1 + dp(cur,keyIndex+1,key,map,size,dp));
        }
        dp[preIndex][keyIndex] = res;
        return res;
    }

    /**
     *
     * dp[i][j] 表示 [ring 0...i][key] ring 0..1 拼出key 0...j 最少的步数？？？？？
     * @param s 做行
     * @param k 做列
     * @return dp[0][0]
     */
    public static int dp3(String s,String k){
        char[] sChar = s.toCharArray();
        char[] kChar = k.toCharArray();

        int[][] dp = new int[s.length() + 1][k.length() + 1];

        return 0;

    }


    public static void main(String[] args) {
        int rotateStep1 = findRotateStep1("abcd", "bd");
        int rotateStep2 = findRotateStep2("abcd", "bd");

        System.out.println(rotateStep1);
        System.out.println(rotateStep2);
    }
}
