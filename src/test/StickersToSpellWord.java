package test;

import java.util.HashMap;
import java.util.Map;

public class StickersToSpellWord {

    public static int minStickers(String[] stickers,String rest){
        int[][] stickerMap = new int[stickers.length][26];
        Map<String,Integer> dp = new HashMap<>();
        for(int i = 0 ; i < stickerMap.length ; i++){
            char[] chars = stickers[i].toCharArray();
            for (int j = 0 ;j < chars.length ; j++){
                stickerMap[i][chars[j] - 'a']++;
            }
        }
        dp.put("",0);
        return process(dp,stickerMap,rest);
    }

    public static void main(String[] args) {
        String[] arr = {"abc","cab","xi","abcd"};
        int abcabccabbcax = minStickers(arr, "abcabccabbcax");
        System.out.println(abcabccabbcax);
    }


    public static int process(Map<String,Integer> dp, int[][] stickerMap,String rest){

        if(dp.get(rest) != null){
            return dp.get(rest);
        }

        int[] restMap = new int[26];
        char[] chars = rest.toCharArray();
        for(char c : chars){
            restMap[c -'a']++;
        }
//        for (int y = 0; y < chars.length; y++) {
//                restMap[chars[y] - 'a']++;
//        }
        int ans = Integer.MAX_VALUE;
        for(int x = 0 ; x < stickerMap.length ; x++){
            if(stickerMap[x][chars[0] - 'a'] == 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for(int z = 0 ; z < stickerMap[x].length ; z++){
                if(restMap[z] > 0 ){
                    for(int y = 0 ;y < Math.max(0,restMap[z] - stickerMap[x][z]) ; y++){
                        sb.append( (char) ('a' + z));
                    }
                }
            }
            int process = process(dp, stickerMap, sb.toString());
            if(process != -1){
                ans = Math.min(ans,1 + process);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans );
        return dp.get(rest);
    }
}
