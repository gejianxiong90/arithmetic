package core;

public class LongestPalindrome {

    public static void main(String[] args) {
        String cbbd = longestPalindrome("abacab");

        System.out.println(cbbd);
    }

    public static String longestPalindrome(String s) {
        if("".equals(s) || s.length() == 1 ){
            return s;
        }
        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0 ; i < n ;i++){
            dp[i][i] = true;
        }
        int from = -1;
        int to = -1;
        for(int j = 0 ; j < n - 1 ;j++){
            dp[j][j+1] = arr[j] == arr[j+1];
            if(dp[j][j+1]){
                from = j;
                to = j+1;
            }
        }
        for(int i = n-3 ; i >= 0 ;i--){
            for(int j = i+2 ; j < n ;j++){
                dp[i][j] = false;
                if(dp[i+1][j-1] && arr[i] == arr[j]){
                    dp[i][j] = true;
                    from = i;
                    to = j;
                }
            }
        }
        if(from == -1){
            return "";
        }

        return s.substring(from,to+1);
    }
}
