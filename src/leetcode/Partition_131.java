package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 131. 分割回文串
 中等
 相关标签
 相关企业
 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 示例 1：

 输入：s = "aab"
 输出：[["a","a","b"],["aa","b"]]
 示例 2：

 输入：s = "a"
 输出：[["a"]]


 提示：

 1 <= s.length <= 16
 s 仅
 *
 */
public class Partition_131 {


    public static void main(String[] args) {
        String str = "aab";

        partition(str);
    }
    public static List<List<String>> partition(String s) {
        boolean[][] dp = dp(s);
        LinkedList<String> path = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        process(s,0,path,res,dp);
        return res;
    }

    public static void process(String str, int start, LinkedList<String> path, List<List<String>> res , boolean[][] dp){
        if(start == str.length()){
            res.add(copy(path));
        }else{
            for(int end = start ; end < str.length() ; end++){
                System.out.println("("+start+","+end+")");
                if(dp[start][end]){
                    path.add(str.substring(start,end+1));
                    process(str,end+1,path,res,dp);
                    path.pollLast();
                }
            }
        }
    }

    public static LinkedList<String> copy(LinkedList<String> org){
        return new LinkedList<String>(org);
    }

    public static boolean[][] dp(String s){
        char[] ch = s.toCharArray();
        int n = ch.length;
        boolean[][] dp = new boolean[n][n];
        for(int i = 0 ; i < n ;i++){
            dp[i][i] = true;
        }
        for(int j = 0 ; j < n -1 ;j++){
            dp[j][j+1] = ch[j] == ch[j+1];
        }
        for(int i = n -3 ; i >=0 ; i--){
            for(int j = i + 2 ; j < n ; j++){
                dp[i][j] = dp[i+1][j-1] ? ch[i] == ch[j] : false;
            }
        }
        return dp;
    }
}
