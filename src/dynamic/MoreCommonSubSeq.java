package dynamic;

/**动态规划4
 * 两个字符串最长公共子序列
 */
public class MoreCommonSubSeq {


    public static void main(String[] args) {
        String str1 = "agagfdd35";
        String str2 = "sdg4sdgdg";

        System.out.println(process(str1,str2));

    }

    public static int process(String str1,String str2){
        char[] str1Chars = str1.toCharArray();
        char[] str2Chars = str2.toCharArray();

        int i = str1Chars.length;
        int j = str2Chars.length;

        int[][] dp = new int[i][j];

        dp[0][0] = str1Chars[0] == str2Chars[0] ? 1 : 0;  // 如果str1 0 位置和str2 0位置字符一样，那么为1否则为0

        for(int k = 1 ; k <i ; k++){
            dp[k][0] = Math.max(dp[k - 1][0],str1Chars[k] == str2Chars[0] ? 1 : 0);   // 填充第一列的所有行；
        }

        for(int z = 1 ; z < j ; z++){
            dp[0][z] = Math.max(dp[0][z -1],str1Chars[0] == str2Chars[z] ? 1 : 0 );  // 填充第一行的所有列；
        }
        /**
         *  dp[x][y]值的4种可能性，等于如下
         * 1.dp[x-1][y]
         * 2.dp[x][y-1]
         * 3.dp[x-1][y-1]
         * 4.dp[x-1][y-1] + 1
         */
        for(int x = 1 ; x < i ; x++){
            for(int y = 1; y < j ; y++){
                dp[x][y] = Math.max(dp[x -1][y],dp[x][y-1]);  //可能性1、2
                if(str1Chars[x] == str2Chars[y]){
                    dp[x][y] = Math.max(dp[x][y],dp[x-1][y-1]+1); // 可能性4       // 为什么省略了可能性3，因为可能性1,2必定大于可能性3
                }

            }
        }
        return dp[i - 1][j-1];
    }
}
