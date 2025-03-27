package core;

import java.util.HashMap;
import java.util.Map;

public class Leetcode691 {
    /**
     * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。

     您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。

     返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。

     注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。

     示例 1：

     输入： stickers = ["with","example","science"], target = "thehat"
     输出：3
     解释：
     我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
     把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     此外，这是形成目标字符串所需的最小贴纸数量。
     示例 2:

     输入：stickers = ["notice","possible"], target = "basicbasic"
     输出：-1
     解释：我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
     */


    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickerCount = new int[n][26];
        Map<String,Integer> dp = new HashMap<String,Integer>();
        for(int i = 0 ; i < n ; i++){
            char[] chars = stickers[i].toCharArray();
            for(int j = 0 ; j < chars.length ; j++){
                stickerCount[i][chars[j] - 'a']++;
            }
        }
        dp.put("",0);
        return process(target,stickerCount,dp);
    }

    public  int process(String rest,int[][] map,Map<String,Integer> dp){
        if(dp.containsKey(rest)){
            return dp.get(rest);
        }
        char[] target = rest.toCharArray();
        int[] tmap = new int[26];
        for(char c : target){
            tmap[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < map.length ;i++){
            if(map[i][target[0] - 'a'] == 0 ){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0 ; j < 26 ; j++){
                if(tmap[j] > 0){
                    for(int k = 0 ; k < Math.max(0,tmap[j] - map[i][j]) ; k++ ){
                        sb.append((char)('a'+ j));
                    }
                }
            }
            int tmp = process(sb.toString(), map,dp);
            if(tmp != -1){
                ans = Math.min(ans,1+ tmp);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? - 1 : ans);

        return dp.get(rest);
    }


    public static void main(String[] args) {
        Leetcode691 leetcode691 = new Leetcode691();

        String[] stickers ={"these","guess","about","garden","him"};
        String target = "atomher";

        int i = leetcode691.minStickers(stickers, target);

        System.out.println(i);
    }
}
