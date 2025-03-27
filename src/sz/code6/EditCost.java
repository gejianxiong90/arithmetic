package sz.code6;


/**
 *  最小编辑距离
 * s1 变成 s2 的最小代价，ic 是插入代价，rc是替换代价，dc是删除代价
 */

public class EditCost {


    public static void main(String[] args) {
        String str1 = "abcde";

        String str2 = "axbc";

        int i1 = minCost(str1, str2, 1000, 1000, 1);
        System.out.println(i1);
    }


    public static int minCost(String s1,String s2,int ic,int rc,int dc){

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int N = str1.length +1;
        int M = str2.length +1;

        int[][] dp = new int[N][M];

        // dp[0][0] = 0;  可省略

        for(int i = 1 ;i <N ; i++){
            dp[i][0] = dc * i;
        }

        for(int j = 1;j < M ; j++){
            dp[0][j] = j * ic;
        }

        for(int i = 1 ; i< N ;i++){
            for (int j = 1 ;j < M ;j++){
//                if(str1[i - 1] == str2[j - 1]){
//                    dp[i][j] = dp[i-1][j-1];
//                }else {
//                    dp[i][j] = dp[i-1][j-1] + rc;
//                }

                dp[i][j] = dp[i - 1][j - 1] + (str1[i - 1] == str2[j - 1] ? 0 : rc);

                dp[i][j] = Math.min(dp[i][j],dp[i-1][j] + dc);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1] + ic);
            }
        }

        return dp[N-1][M-1];
    }
}
