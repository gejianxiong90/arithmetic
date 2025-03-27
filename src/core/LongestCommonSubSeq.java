package core;

public class LongestCommonSubSeq {


    public static void main(String[] args) {

        String str1 = "1234";
        String str2 = "gd1j2f534";
        System.out.println(lcss(str1,str2));

        System.out.println(lcsstring(str1,str2));

    }

    public static int lcss(String str1,String str2){
        if(str1 == null || str2 == null){
            return 0;
        }
        int[][] dp = new int[str1.length()][str2.length()];

        char[] str1Char = str1.toCharArray();
        char[] str2Char = str2.toCharArray();

        if(str1Char[0] == str2Char[0]){
            dp[0][0] = 1;
        }
        for(int i = 1 ; i < str1.length();i++){
            dp[i][0] = str1Char[i] == str2Char[0] ? 1 : dp[i-1][0];
        }
        for(int j = 1 ; j < str2.length();j++){
            dp[0][j] = str1Char[0] == str2Char[j] ? 1 : dp[0][j-1];
        }
        for(int i = 1 ; i < str1.length() ; i++){
            for(int j = 1 ; j < str2.length() ; j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                if(str1Char[i] == str2Char[j]){
                    dp[i][j] = Math.max(dp[i-1][j-1] + 1,dp[i][j]);
                }
            }
        }
        return dp[str1Char.length-1][str2Char.length - 1];
    }



    public static String lcsstring(String str1,String str2){
        if(str1 == null || str2 == null){
            return null;
        }
        String[][] dp =  new String[str1.length()][str2.length()];


        for(int i = 0 ; i <dp.length;i++){
            for (int j = 0 ; j < dp[0].length ; j++){
                dp[i][j] = "";
            }
        }
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();

        if(char1[0] == char2[0]){
            dp[0][0] = String.valueOf(char1[0]);
        }

        for(int i = 1; i < char1.length ; i++){
            dp[i][0] = char1[i] == char2[0] ? String.valueOf(char1[i]) : dp[i-1][0];
        }
        for(int j = 1 ; j < char2.length;j++){
            dp[0][j] = char1[0] == char2[j] ? String.valueOf(char2[j]) : dp[0][j-1];
        }
        for(int i = 1 ; i < char1.length ; i++){
            for(int j = 1 ; j < char2.length ; j++){
                String str = "".equals(dp[i-1][j]) ? "" : dp[i-1][j];
                str = "".equals(dp[i][j-1]) ? str : (str.length() > dp[i][j-1].length() ? str : dp[i][j-1]);
                dp[i][j] = str;
                if(char1[i] == char2[j]){
                    dp[i][j] = dp[i][j].length() > (dp[i-1][j-1] + char1[i]).length() ? dp[i][j] : dp[i-1][j-1] + char1[i];
                }
            }
        }

        return dp[char1.length - 1][char2.length - 1];
    }
}
