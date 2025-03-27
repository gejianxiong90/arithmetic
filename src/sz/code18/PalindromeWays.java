package sz.code18;

/**
 *  给定一个字符串，求回文子序列的数量，空串不属于回文
 *  如：“ABA” 有5种。 A B A AA ABA 。 “XXY”有4中种， X X Y XX
 *
 *  解：从左到右范围的尝试模型
 *
 *    可能性有4种
 *    L×  R×   a
 *    L√  R×   b
 *    L×  R√   c
 *    L√  R√   d
 *
 *    有abcd四种可能
 *
 *    普遍的位置 dp[L][R] = a + b + c ,如果 arr[L] == arr[R], 再加上 dp[L][R] = a+1  就是d
 *    dp[L+1][R]: 忽略L的可能，所以是a + c
 *    dp[L][R-1]: 忽略R的可能，所以是a + b
 *    那么dp[L+1][R] + dp[L][R-1] = 2a + b + c , 怎么得到a + b + c ， 再减去dp[L+1][R-1]就是a
 *
 *    arr[L] == arr[R] 为什么是a+1 ？ A。。。。A，省略中间空白部分是不含L和R，也就是a。如果中间加上L和R就是新增1
 *
 *
 *     dp[L][R] = dp[L+1][R] + dp[L][R-1] - dp[L+1][R-1]  如果 arr[L] == arr[R]再加上, dp[L][R] += dp[L+1][R-1] + 1
 *
 */
public class PalindromeWays {


    public static int dp(String str){
        char[] arr = str.toCharArray();
        int N = str.length();
        int[][] dp = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            dp[i][i] = 1; // 对角线相等都为1
        }
        for(int j = 0 ; j < N - 1; j++){
            dp[j][j+1] = arr[j] == arr[j+1] ? 3 : 2 ;// 两个字符串相等就是3种，如BB可以是B B BB。不相等就是2种，如AB可以是A B
        }
        for(int L = N -3 ; L >=0 ; L--){
            for (int R = L + 2 ; R < N ; R++){
                dp[L][R] = dp[L+1][R] + dp[L][R-1] - dp[L+1][R-1];// a + b + c
                if(arr[L] == arr[R]){
                    dp[L][R] += dp[L+1][R-1] + 1;
                }
            }
        }

        return dp[0][N - 1];
    }


    public static void main(String[] args) {
        int aba = dp("ABA");

        System.out.println(aba);
    }
}
