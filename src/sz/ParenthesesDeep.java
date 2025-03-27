package sz;

public class ParenthesesDeep {

    /**
     * 给定字符串，只包括（和） 求最长有效的子串长度
     * @param s
     * @return
     */
    public static int maxLenght(String s){
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length()];

        int max = 0;
        for(int i = 1 ; i < chars.length ; i++){
            if(chars[i] == ')'){
                int pre = i - dp[i - 1] - 1;
                if(pre >= 0 &&  chars[pre] == '('){
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre-1] : 0);
                }
                max = Math.max(max,dp[i]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        String str = "()()))))(()()())";
        System.out.println(maxLenght(str));
    }
}
