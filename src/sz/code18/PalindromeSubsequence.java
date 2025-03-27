package sz.code18;

/**
 *  给定一个字符串，求最长回文子序列长度
 *  如： dg1j2s3k3f2d1     最长回文子序列是  123k321
 *
 *  解：
 *  两种： 1.一个样本做行一个样本做列（将另一个逆序生成）
 *        2.从左到右的尝试模型
 *
 */
public class PalindromeSubsequence {

    public static int maxLen1(String str){
        String str2 = reverse(str);

        return lcse(str,str2);
    }


    public static String reverse(String str){
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        for(int i = length - 1 ; i >= 0 ; i--){
           stringBuilder.append( chars[i]);
        }

        return stringBuilder.toString();
    }

    public static int lcse(String str1,String str2){
        if(str1 == null || str1.length() ==0 || str2 == null || str2.length() == 0){
            return -1;
        }
        int N = str1.length();
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int[][] dp = new int[N][N];
        dp[0][0] = char1[0] == char2[0] ? 1 : 0;
        for(int i = 1 ; i < N ;i++){
            dp[i][0] = Math.max(dp[i-1][0], char1[i] == char2[0] ? 1 : 0);
        }
        for(int j = 1 ; j < N ; j++){
            dp[0][j] = Math.max(dp[0][j-1],char1[0] == char2[j] ? 1 : 0);
        }

        for(int i = 1 ;i < N ; i++){
            for(int j = 1 ; j < N ; j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                if(char1[i] == char2[j]){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1] + 1);
                }
            }
        }

        return dp[N-1][N-1];
    }



    public static int maxLen2(String str){

        return dp2(str);
    }

    public static int dp2(String str){
        if(str == null || str.length() == 0){
            return -1;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;

        int[][] dp = new int[N][N];

        for(int i=0 ; i < N ;i++){
            dp[i][i] = 1;
        }
        for (int j = 0 ; j < N - 1;j++){
            dp[j][j+1] = chars[j] == chars[j+1] ? 2 : 1;
        }

        for(int L = N - 2 ; L >= 0 ; L--){
            for(int R = L+2 ; R < N ; R++){
                dp[L][R] = Math.max(dp[L+1][R],dp[L][R-1]);
                if(chars[L] == chars[R]){
                    dp[L][R] = Math.max(dp[L][R],dp[L+1][R-1] + 2);
                }
            }
        }

        return dp[0][N -1];
    }

    public static String longestPalindrome(String s) {

        if(s == null || "".equals(s)){
            return null;
        }
        char[] chars = s.toCharArray();

        if(s.length() == 1 || (s.length() == 2 && chars[0] == chars[1])){
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int index = 0;
        int L = 0;
        int R = 0;
        for(int i = 0 ; i < n ; i++){
            dp[i][i] = true;
        }
        for(int i = 0 ; i < n - 1 ; i++)
        {
            dp[i][i+1] = chars[i] == chars[i+1];
            if(dp[i][i+1]){
                L = i;
                R = i+1;
                index = 1;
            }
        }

        for(int i = n - 2 ; i >= 0 ; i--){
            for(int j = i + 2; j < n ;j++ ){
                if(chars[i] == chars[j] && dp[i+1][j-1]){
                    dp[i][j] = true;
                }
                if(dp[i][j] && j - i > index){
                    index = j - i;
                    L = i;
                    R = j;
                }
            }
        }

        return s.substring(L,R+1);
    }


    public static void main(String[] args) {
        String test = "A1BBI";
        System.out.println(maxLen1(test));
        System.out.println(maxLen2(test));

        System.out.println(test.substring(0,2));
        System.out.println(false ^ false);

        System.out.println(longestPalindrome("cbbd"));
    }
}
