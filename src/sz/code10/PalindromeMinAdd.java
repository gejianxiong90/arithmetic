package sz.code10;

/**
 * 给定一个字符串，至少添加几个字符将他补成回文
 * 1.动态规划先算出少几个字符
 * 2.将少的补上，返回最后回文的结果
 */
public class PalindromeMinAdd {


    public static String getPalindrome1(String str){
        char[] chars = str.toCharArray();
        int[][] dp = getDp(str);

        int needLen = dp[0][dp.length-1];
        char[] res = new char[str.length() + needLen];

        int i = 0;
        int j = chars.length - 1;
        int resl = 0;
        int resr = res.length - 1;

        while (i <= j){
            if(chars[i] == chars[j]){
                res[resl++] = chars[i++];
                res[resr--] = chars[j--];
            }else if(dp[i][j - 1] < dp[i+1][j]){
                res[resl++] = chars[j];
                res[resr--] = chars[j++];
            }else {
                res[resl++] = chars[i];
                res[resr--] = chars[i++];
            }
        }

       return String.valueOf(res);
    }


    public static int[][] getDp(String str){
        char[] chars = str.toCharArray();

        int[][] dp = new int[chars.length][chars.length];

        for(int i = 0 ; i < chars.length ; i++){
            dp[i][i] = 0;
        }

        for(int i = 0 ; i < chars.length - 1; i++){
            dp[i][i + 1] = chars[i] == chars[i+1] ? 0 : 1;
        }

        for(int i = chars.length - 3 ; i >= 0 ; i--){
            for(int j = i+2 ; j <chars.length;j++){
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j-1];
                    continue;
                }
                if(i-1 >= 0){
                    dp[i][j] = dp[i - 1][j];
                }
                int min = Math.min(dp[i][j], dp[i][j - 1]);
                dp[i][j] = min + 1;
            }
        }

        return dp;
    }

    public static void main(String[] args) {
        int[][] acbc = getDp("acbc");


        String acbc1 = getPalindrome1("acbc");
        System.out.println(acbc);

        System.out.println(acbc1);
    }
}
