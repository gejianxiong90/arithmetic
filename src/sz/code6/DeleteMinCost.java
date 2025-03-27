package sz.code6;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符串可以成为s1的子串
 * 比如 s1 = “abcde” ，s2 = “axbc”
 * 返回1 。 s2删掉‘x’就是s1的子串
 *
 */
public class DeleteMinCost {



    public static int minCost1(String s1,String s2){
        char[] chars = s2.toCharArray();
        List<String> res = new ArrayList<>();
        process(chars,0,"",res);
        res.sort((o1,o2)->o2.length() - o1.length());

        for(String str : res){
            if(s1.indexOf(str) != -1) {
                return s2.length() - str.length();
            }
        }

        return s2.length();
    }


    public static int minCost2Convert(String s1,String s2,int ic,int rc,int dc ){
        char[] chars = s2.toCharArray();
        List<String> res = new ArrayList<>();
        process(chars,0,"",res);
        int min = Integer.MAX_VALUE;

        for(int start = 0 ; start < s2.length(); start++){
            for(int end = start + 1; end < s2.length();end++){
                min = minCost2(s2.substring(start,end),s1,ic,rc,dc) ;
            }
        }

        return min;
    }

    public static int minCost2(String s1,String s2,int ic,int rc,int dc){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length +1;
        int M = str2.length +1;

        int[][] dp = new int[N][M];
        //dp[0][0] = 0;

        for(int i =1 ; i< N ;i++){
            dp[i][0] = dc * i;
        }

        for(int j = 0; j< M;j++){
            dp[0][j] = j * ic;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1 ;i< N;i++){
            for(int j = 1; j < M ;j++){
                if(str1[i - 1] == str2[j - 1]){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i-1][j-1] + rc;
                }

                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);
                dp[i][j] = Math.min(dp[i][j],dp[i][j-1] + ic);

            }
        }

        return dp[N-1][M-1];
    }





    public static void process(char[]arr, int index, String path, List<String> subStrs){
        if(index == arr.length){
            subStrs.add(path);
            return;
        }
        process(arr,index+1,path,subStrs);

        process(arr,index+1,path + arr[index],subStrs);
    }




    public static void main(String[] args) {
        String str1 = "ab123cd";

        String str2 = "1x23";

//        int i = minCost1(str1, str2);
//
//        int i1 = minCost2Convert(str1, str2, 1, 1000, 1000);
//        System.out.println(i);
//        System.out.println(i1);


        int i = minCostX(str1, str2);

        System.out.println(i);

       // System.out.println("minCost2  --- >"+minCost2(str1,str2));
    }

    // 解法二
    // 生成所有s1的子串
    // 然后考察每个子串和s2的编辑距离(假设编辑距离只有删除动作且删除一个字符的代价为1)
    // 如果s1的长度较小，s2长度较大，这个方法比较合适
    public static int minCost2(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int ans = Integer.MAX_VALUE;
        char[] str2 = s2.toCharArray();
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                // str1[start....end]
                // substring -> [ 0,1 )
                String sub = s1.substring(start, end);

                int i = distance(str2,sub.toCharArray());
                int j = distance2(sub, s2);

                if(j == 1){
                    System.out.println();
                }
                ans = Math.min(ans, j);
//
//                if(i != j){
//                    throw new RuntimeException("不相等");
//                }
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    /**
     *  子串s1需要插入多小才能成为s2
     * @param s1 子串
     * @param s2 目标字符串
     * @return
     */
    public static int distance2(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];

        dp[0][0] = str1[0] == str2[0] ? 0 : Integer.MAX_VALUE;

        for(int i = 1;i < N ; i++){
            dp[i][0] = Integer.MAX_VALUE;
        }

        for(int j = 1; j<M;j++){
            dp[0][j] = dp[0][j - 1] != Integer.MAX_VALUE ? dp[0][j - 1] +1 : Integer.MAX_VALUE;
        }

        for(int i = 1 ; i < N ; i++){
            for(int j = 1 ; j < M ; j++){
               dp[i][j] = Integer.MAX_VALUE;
               if(dp[i][j-1] != Integer.MAX_VALUE){
                   dp[i][j] = dp[i][j-1] +1;
               }

               if(str1[i] == str2[j] && dp[i-1][j-1] != Integer.MAX_VALUE){
                   dp[i][j] = Math.min(dp[i-1][j-1],dp[i][j]);
               }
            }
        }


        return dp[N-1][M-1];
    }



    // 求str2到s1sub的编辑距离
    // 假设编辑距离只有删除动作且删除一个字符的代价为1
    public static int distance(char[] str2, char[] s1sub) {
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        // dp[i][j]的含义：
        // str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
        // 可能性一：
        // str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
        // 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
        // 可能性二：
        // str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
        // 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
        // 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }
        return dp[row - 1][col - 1];
    }


    public static class StrComparator implements Comparator<String>{


        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }



    // 来自学生的解，最优解
    // 比课上讲的解还要好！
    // 这是时间复杂度O(N*M)的方法
    // 强烈推荐同学们看懂，有解释也不难看懂
    // 感谢陆振星同学提供的方法
    // 链接 : https://www.mashibing.com/question/detail/68090
    public static int minCostX(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[][] dp = new int[c2.length + 1][c1.length + 1];
        for (int i = 1; i <= c2.length; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= c1.length; j++) {
                if (c2[i - 1] == c1[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
            }
        }
        // 返回最后一行的最小值即可
        int ans = dp[c2.length][0];
        for (int j = 1; j <= c1.length; j++) {
            ans = Math.min(ans, dp[c2.length][j]);
        }
        return ans;
    }
}
