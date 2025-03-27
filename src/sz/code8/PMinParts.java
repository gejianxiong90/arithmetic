package sz.code8;

/**
 * 给定一个字符串，将此字符串切分成 每个都是回文的子串，问至少有几个部分是回文
 * 如 abacdc  返回1
 */
public class PMinParts {

    /***
     * 生成每个范围是否是回文的dp表
     * @param arr
     * @return
     */
    public static boolean[][] isP(char[] arr){
        int N = arr.length;

        boolean[][] dp = new boolean[N][N];

        for(int i = 0;i<N ; i++){  // 对角线都为true， 0-0,1-1，i-i 都为回文
            dp[i][i] = true;
        }

        for(int j = 0; j < N-1;j++){ // 对角线上一个对角线  0-1,1-2,2-3，只需要判断两个位置是否相等就行了
            dp[j][j+1] = arr[j] == arr[j+1];
        }

        for(int k = N-2 ;k >= 0 ; k--){
            for(int z = k+2; z< N;z++){
                dp[k][z] = arr[k] == arr[z] && dp[k+1][z-1];  // 当前是不是回文根据两点，k-z范围，arr[k] == arr[z] 并且判断 arr[k+1] == arr[z-1] ，也就是dp[k+1][z-1]
            }
        }

        return dp;
    }


    public static int pMinPart(String arr){

        boolean[][] p = isP(arr.toCharArray());
        int N = arr.length();
        int[] dp = new int[N + 1];
        for(int i = 0;i<=N;i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[N] = 0;
        for(int i = N -1;i >= 0;i--){
            for(int end = i;end < N;end++){
                if(p[i][end]){
                    int i2 = dp[i];
                    int i1 = dp[end + 1];
                    dp[i] = Math.min(dp[i],1 + dp[end+1]);
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String test = "aba12321412321TabaKFK";
        test = "abacbc";
        int i = pMinPart(test);
        System.out.println(i);


        int sqrt = (int)Math.sqrt(23);
        System.out.println(sqrt);
    }
}
