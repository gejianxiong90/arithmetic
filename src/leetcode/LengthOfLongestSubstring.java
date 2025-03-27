package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {


    public static void main(String[] args){
        //pwwkew  3  abcabcbb  3 dvdf 3  aaca 2   cdd 2   tmmzuxt 5
        int abcabcdd = lengthOfLongestSubstring("abcabcbb");
        System.out.println(abcabcdd);
    }

    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        int start = -1 ;
        int tmpMax = 0;
        Map<Character,Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);
            Integer cacheIndex = map.get(c) ;
            if(cacheIndex == null){
                ++tmpMax;
            }else if(cacheIndex >= start ){
                start = cacheIndex + 1;   // 计算出start，在tmpMax会有运算
                res = Math.max(res, tmpMax); // 之前扩的区域进行收集
                tmpMax = i - start + 1;   // 计算出目前长度
            }else{
                ++tmpMax;
            }
            map.put(s.charAt(i),i);
        }
        return Math.max(res,tmpMax);


    }
}
