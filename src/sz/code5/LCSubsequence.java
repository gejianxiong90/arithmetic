package sz.code5;

/**
 * 最长子序
 */
public class LCSubsequence {

    public static int process(char[] str1,char[] str2,int i1,int i2){
        if(i1 == 0 && i2 == 0){
            return str1[i1] == str2[i1] ? 1 : 0;
        }
        if( i1 == 0){
            return ((str1[i1] == str2[i2]) || process(str1,str2,i1,i2-1) == 1) ? 1 :0;
        }
        if( i2 == 0){
            return ((str1[i1] == str2[i2]) || process(str1,str2,i1 - 1,i2) == 1) ? 1 : 0;
        }
        int p1 = process(str1,str2,i1-1,i2-1);

        int p2 = process(str1,str2,i1,i2 -1 );

        int p3 = process(str1,str2,i1-1,i2);

        int p4 = 0;
        if(str1[i1] == str2[i2]){
            p4 = p1 + 1;
        }

        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }


    public static int dp(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int N = s1.length();
        int M = s2.length();

        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;

        for(int i =1 ;i< N;i++){
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i-1][0];
        }
        for(int j = 1 ; j < M; j++){
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j-1];
        }

        for(int i = 1 ; i < N;i++){
            for (int j =1; j < M;j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                if(str1[i] == str2[j]){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1] + 1);
                }
            }
        }
        return dp[N-1][M-1];
    }


    public static int lcs(String s1,String s2){
        char[] chars = s1.toCharArray();
        char[] chars1 = s2.toCharArray();


      return  process(chars,chars1,chars.length-1,chars1.length-1);
    }

    public static void main(String[] args) {

        String str = "12bd3";
        String str2 = "ac123";

        System.out.println(lcs(str,str2));
        System.out.println(dp(str,str2));

    }
}
