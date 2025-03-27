package sz.code9;


/**
 * 判断两个字符是否交叉组成第三个字符串
 * 如 s1 = “abc”  s2 = “cde”  组成了 s3=“acbdce”
 *
 * 解：
 *    用动态规划 s1[i] + s2[j] 是不等于 s3[i+j]
 *    假设 s3[i+j-1] == s1[i-1] ,含义是 该位置设为与s1[i-1]相等，那么需要再去看 dp[i-1][j] 的位置是否相等
 *    假设 s3[i+j-1] == s2[j-1], 含义是 该位置设为与s2[j-1]相等，那么需要再去看 dp[i][j-1] 的位置是否相等
 *    上面两个条件满足其一则dp[i][j] = true
 */
public class StringCross {


    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "cde";
        String s3 = "acbdce";

        boolean cross = isCross(s1, s2, s3);

        System.out.println(cross);
    }

    public static boolean isCross(String s1,String s2,String s3){
        if(s1 == null || s2 == null || s3 == null){
            return false;
        }
        int N = s1.length();
        int M = s2.length();
        if( N + M != s3.length()){
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();

        boolean dp[][] = new boolean[N+1][M+1];

        dp[0][0] = true;

        for(int j = 1 ; j <= M ; j++){
            if(str2[j - 1] != str3[j - 1]){
                break;
            }
            dp[0][j] = true;
        }

        for(int i = 1 ; i <= N ; i++){
            if(str1[i - 1] != str3[i - 1]){
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1 ; i <= N ;i++){
            for (int j = 1; j <= M ; j++){
                if ((str3[i + j - 1] == str1[i - 1] && dp[i - 1][j])
                        ||
                        (str3[i + j - 1] == str2[j - 1] && dp[i][j - 1])
                        ) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[N][M];
    }

}
