package dynamic;

import java.util.HashMap;

/**
 * 设定目标 “abjjhbg”
 * 给定n张卡片，可以裁剪，每张不限张数“afa”，“erdff”，“as”。。。。。
 * 问最少需要多少张卡片
 */
public class StickersToSpellWord {

    public static int minStickers1(String[] stickers,String target){
        int n = stickers.length;
        int[][] map = new int[n][26];
        for(int i = 0;i < n ; i++){
            char[] str = stickers[i].toCharArray();
            for(char c : str){
                map[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("",0);
        return process1(dp,map,target);
    }

    /**
     *
     * @param dp  傻缓存
     * @param map  每个卡片中字母的词频
     * @param rest  剩余的目标
     * @return
     */
    public static int process1(HashMap<String,Integer> dp,int[][] map,String rest){
        if(dp.containsKey(rest)){
            return dp.get(rest);
        }
        int ans = Integer.MAX_VALUE; // 搞定rest的数量
        int n = map.length; // N中贴纸
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        for(char c : target){
            tmap[c - 'a']++;
        }
        for (int i = 0; i < n;i++){
            // 枚举一张贴纸是否包含    小贪心
            if(map[i][target[0] - 'a'] == 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0 ; j<26;j++){
                if(tmap[j] > 0){
                    for(int k = 0 ; k < Math.max(0,tmap[j] - map[i][j]); k++){
                        sb.append((char)('a'+j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = process1(dp, map, s);
            if(tmp != -1){
                ans = Math.min(ans,1+tmp);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String arr[] = {"afdfgfd","arter","sdgd"};
        String target = "sfagag";
        int i = minStickers1(arr, target);
        System.out.println(i);
    }

}
