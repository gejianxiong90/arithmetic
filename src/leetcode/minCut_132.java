package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 132. 分割回文串 II
 困难
 相关标签
 相关企业
 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。

 返回符合要求的 最少分割次数 。



 示例 1：

 输入：s = "aab"
 输出：1
 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 示例 2：

 输入：s = "a"
 输出：0
 示例 3：

 输入：s = "ab"
 输出：1
 */
public class minCut_132 {

    public static void main(String[] args) {
        int aab = minCut("aab");
    }

    public static int minCut(String s) {
        if(s.length() == 1){
            return 0;
        }

        boolean[][] dp = dp(s);
//        LinkedList<String> path = new LinkedList<>();
//        int[] minNum = {Integer.MAX_VALUE};
//        process(s,0,path,dp,minNum);
//        return minNum[0]-1;
        int n = s.length();
        int[] f = new int[s.length()]; // f[i]表示0到i最小的几个次数
        Arrays.fill(f,Integer.MAX_VALUE);
        for(int i = 0 ; i < n ; i++){
            if(dp[0][i]){
                f[i] = 0 ;
            }else {
                for(int j = 0 ; j < i ;j++){
                    if(dp[j+1][i]){ // 从前往后缩小范围，枚举后缀为回文的
                        f[i] = Math.min(f[i],f[j] + 1); //基于前面计算过的j位置的基础上加一个切割点
                    }
                }
            }
        }
        return f[n-1];
    }

    public static void process(String str,int start,LinkedList<String> path,boolean[][] dp,int[] minNum){
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
