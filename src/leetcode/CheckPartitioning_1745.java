package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 1745. 分割回文串 IV
 困难
 相关标签
 相关企业
 提示
 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。

 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。



 示例 1：

 输入：s = "abcbdd"
 输出：true
 解释："abcbdd" = "a" + "bcb" + "dd"，三个子字符串都是回文的。
 示例 2：

 输入：s = "bcbddxy"
 输出：false
 解释：s 没办法被分割成 3 个回文子字符串。


 提示：

 3 <= s.length <= 2000
 s​​​​​​ 只包含小写英文字母。
 */
public class CheckPartitioning_1745 {

    public boolean checkPartitioning(String s) {
        return canCut(s);
    }

    public static boolean canCut(String s) {
        if(s.length() == 1){
            return false;
        }

        boolean[][] dp = dp(s);
//        LinkedList<String> path = new LinkedList<>();
//        int[] minNum = {Integer.MAX_VALUE};
//        process(s,0,path,dp,minNum);
//        return minNum[0]-1;
        int n = s.length();
        for(int start = 1;start < n - 1; start++){
            if(!dp[0][start-1]){
                continue;
            }
            for(int end = start ; end < n - 1;end++){
                if(dp[start][end] && dp[end+1][n-1]){
                    return true;
                }
            }
        }
        return false;
    }

    public static void process(String str, int start, LinkedList<String> path, boolean[][] dp, int[] minNum){
        if(start == str.length()){
            if(path.size() < minNum[0]){
                minNum[0] = path.size();
            }
        }else{
            for(int end = start ; end < str.length() ; end++){
                if(path.size() >= minNum[0]){
                    continue;
                }
                if(dp[start][end]){
                    path.add(str.substring(start,end+1));
                    process(str,end+1,path,dp,minNum);
                    path.pollLast();
                }
            }
        }
    }

    public static boolean[][] dp(String str){
        int n = str.length();
        char[] ch = str.toCharArray();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0 ; i < n ;i++){
            dp[i][i] = true;
        }
        for(int j = 0 ; j < n -1 ; j++){
            dp[j][j+1] = ch[j] == ch[j+1];
        }

        for(int i = n - 3 ; i >= 0 ;i-- ){
            for(int j = i + 2 ; j < n ; j++){
                dp[i][j] = dp[i+1][j-1] ? ch[i] == ch[j] : false;
            }
        }
        return dp;
    }
}
