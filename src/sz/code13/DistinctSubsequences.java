package sz.code13;

/**
 * 给定两个字符串S和T，返回S子序列等于T的不同子序列个数
 * S = “rabbbit” T = “rabit”
 * 返回3
 */
public class DistinctSubsequences {


    public static int process(char[] s,char[] t, int i,int j){
        if(j==0){ // 如果 t都能匹配上 则j - 1 直到变0
            return 1;
        }
        if(i==0){ // s
            return 0;
        }

        int res = process(s,t,i-1,j); // 情况一： 0...i-1匹配0...j
        if(s[i-1] == t[j-1]){ // 情况二：s[i] == t[j] 看 i-1 j-1 怎么匹配
            res += process(s,t,i-1,j-1);
        }
        return res;
    }

    public static int dp(char[] s, char[] t){
        int N = s.length;
        int M = t.length;

        int[][] dp = new int[N+1][M+1];
        for(int j = 0;j <= M ; j++){
            dp[0][j] = 0;
        }
        for(int i = 0; i <= N ; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= N ; i++){
            for(int j = 1; j <= M ; j++){
                dp[i][j] = dp[i-1][j] + (s[i-1] == t[j-1] ? dp[i-1][j-1] : 0);
            }
        }
        return dp[N][M];
    }

    public static int numDistinct1(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return dp(s, t);
    }

    public static void main(String[] args) {

        String s = "abbbit";
        String t = "abit";

        int i = numDistinct1(s, t);
        System.out.println(i);

        System.out.println(process(s.toCharArray(),t.toCharArray(),s.length(),t.length()));
    }

}
