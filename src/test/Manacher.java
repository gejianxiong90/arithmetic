package test;

public class Manacher {


    public static void main(String[] args) {
        String str = "abccbaabcc";
        int manacher = manacher(str);
        int i = manacher2(str);

        System.out.println(manacher);

        int dp = dp(str);

        System.out.println(dp);


        String abc1221 = shortestEnd("abc1221");
        System.out.println(abc1221);
    }

    public static int dp(String str){
        char[] c = str.toCharArray();
        int n = c.length;
        boolean[][] dp = new boolean[n][n];
        for(int i = 0 ; i < n ; i++){
            dp[i][i] = true;
        }
        for(int j = 0 ; j < n -1 ; j++){
            dp[j][j+1] = c[j] == c[j+1];
        }
        int res = 0;
        for(int i = n - 3 ; i  >= 0 ; i--){
            for(int j = i + 2 ; j < n ; j++){
                dp[i][j] = dp[i+1][j-1] && c[i] == c[j];
                if(dp[i][j]){
                    res = Math.max(j - i + 1,res);
                }
            }
        }
        return res;
    }

    public static int manacher(String s){
        if(s==null || s.length() ==0){
            return 0;
        }
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置。coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length ; i++){
            // 不用验的区域，已经确定是回文
            // R 第一个违规的位置，i>=R
            // i位置扩出来的答案，i位置扩的区域，至少是多大
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            // 1) i在R外    直接暴力扩
            // 2) i在R内
            //         i对称点的回文在LR内，直接取对应点的值    //  [        i对称点    c       i       ]R
            //         i对称点的回文在LR外，取 R-i      // （   [  i对称点   )      c         (       i  ]R
            //         i对称点的回文正好在L上压线，R内部对称点到R不用验证，直接验证R的下一个值  //   [(   i对称点   )      c      (     i  ]R????
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i+pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if( i + pArr[i] > R ){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String s){
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        char[] chars = s.toCharArray();
        for(int i = 0; i< chars.length;i++){
            sb.append(chars[i]).append("#");
        }
        return sb.toString().toCharArray();
    }


    // 最短添加什么字符生成回文   "abc1221"  -> "abc1221cba"
    public static String shortestEnd(String s){
        if(s==null || s.length() ==0){
            return null;
        }
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置。coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        int maxContainsEnd = 0;
        for (int i = 0; i < str.length ; i++){
            // 不用验的区域，已经确定是回文
            // R 第一个违规的位置，i>=R
            // i位置扩出来的答案，i位置扩的区域，至少是多大
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            // 1) i在R外    直接暴力扩
            // 2) i在R内
            //         i对称点的回文在LR内，直接取对应点的值    //  [        i对称点    c       i       ]R
            //         i对称点的回文在LR外，取 R-i      // （   [  i对称点   )      c         (       i  ]R
            //         i对称点的回文正好在L上压线，R内部对称点到R不用验证，直接验证R的下一个值  //   [(   i对称点   )      c      (     i  ]R????
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i+pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if( i + pArr[i] > R ){
                R = i + pArr[i];
                C = i;
            }
            if(R == str.length ){
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0 ; i < res.length ; i++){
            res[res.length - i -1 ] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }













    public static int manacher2(String str){
        if(str == null || "".equals(str)){
            return 0;
        }
        char[] newStr = manacherString(str);
        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        int[] pArr = new int[newStr.length];
        for(int i = 0 ; i < newStr.length;i++){
            pArr[i] = r > i ? Math.min((c * 2 - i),r - i) : 1;
            while(i + pArr[i] < newStr.length && i - pArr[i] > -1){
                if(newStr[i+pArr[i]] == newStr[i-pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > r){
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(max,pArr[i]);
        }

        return max - 1;
    }
}
