package core;

public class WildcardMatching {


    public static void main(String[] args) {
        String str = "aa";
        String pattern = "*";

        long l = System.currentTimeMillis();
        System.out.println(isMatch(str,pattern));

        System.out.println(System.currentTimeMillis() - l);

        long l1 = System.currentTimeMillis();

        boolean match1 = isMatch1(str, pattern);

        System.out.println(match1);

        System.out.println(System.currentTimeMillis() - l1);

        long l2 = System.currentTimeMillis();

        isMatch2(str,pattern);

        System.out.println(match1);

        System.out.println(System.currentTimeMillis() - l2);

    }

    public static  boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pStr = p.toCharArray();
        return process(str,pStr,0,0);
    }


    public static boolean process(char[] str,char[] p,int si,int pi){
        if(si == str.length){
            if(pi == p.length){
                return true;
            }
            if(p[pi] == '*'){
                return process(str,p,si,pi+1);
            }
            return false;
        }
        if(pi == p.length){
            if(si == str.length){
                return true;
            }
            return false;
        }
        if(p[pi] != '?' &&  p[pi] != '*'){
            return str[si] == p[pi] && process(str,p,si+1,pi+1);
        }
        if(p[pi] == '?'){
            return process(str,p,si+1,pi+1);
        }

        for(int len = 0 ; len <=str.length - si ; len++){
            if(process(str,p,si+len,pi+1)){
                return true;
            }
        }
        return false;
    }





    public static  boolean isMatch1(String s, String pStr) {
        char[] str = s.toCharArray();
        char[] p = pStr.toCharArray();
        int N = s.length();
        int M = pStr.length();
        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[N][M] = true;


        for(int i = 0 ; i < p.length ; i++){
            if(p[i] == '*'){
                dp[N][i] = dp[N][i+1];
            }
        }

        for(int i = N-1 ; i >=0 ; i--){
            for(int j = M-1 ; j >=0 ; j--){
                if(p[j] != '?' &&  p[j] != '*'){
                    dp[i][j] =  str[i] == p[j] && dp[i+1][j+1];
                    continue;
                }
                if(p[j] == '?'){
                    dp[i][j] = dp[i+1][j+1];
                    continue;
                }

                for(int len = 0 ; len <=str.length - i ; len++){
                    if(dp[i+len][j+1]){
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        return dp[0][0];
    }


    public static  boolean isMatch2(String s, String pStr) {
        char[] str = s.toCharArray();
        char[] p = pStr.toCharArray();
        int N = s.length();
        int M = pStr.length();
        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[N][M] = true;


        for(int i = 0 ; i < p.length ; i++){
            if(p[i] == '*'){
                dp[N][i] = dp[N][i+1];
            }
        }

        for(int i = N-1 ; i >=0 ; i--){
            for(int j = M-1 ; j >=0 ; j--){
                if(p[j] != '?' &&  p[j] != '*'){
                    dp[i][j] =  str[i] == p[j] && dp[i+1][j+1];
                    continue;
                }
                if(p[j] == '?'){
                    dp[i][j] = dp[i+1][j+1];
                    continue;
                }

                // p[j] = '*'

                dp[i][j] = dp[i+1][j] || dp[i][j+1];
            }
        }
        return dp[0][0];
    }
}
