package sz;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LCSubstring {

    /**
     * 两个字符串的，公共子串，返回子串
     */

    public static String lcst2(String s1,String s2){
        if(s1 == null || s2 == null || "".equals(s1) || "".equals(s2)){
            return "";
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int row = 0;
        int col = s2.length() - 1;
        int max = 0;
        int end = 0;
        while (row < s1.length()){
            int i = row;
            int j = col;
            int len = 0;
            while ( i < str1.length && j < str2.length){
                if(str1[i] != str2[j]){
                    len = 0;
                }else {
                    len++;
                }

                if(len > max){
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            if(col > 0){
                col--;
            }else {
                row++;
            }
        }

        return s1.substring(end - max + 1,end + 1);
    }



    public static String dp(String str1,String str2){
        if(str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0){
            return null;
        }
        int N = str1.length();
        int M = str2.length();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int[][] dp = new int[N][M];
        int maxLen = -1;
        int endPos = -1;
        for(int i = 1 ; i < N ; i++){
            for(int j = 1 ; j < M ; j++){
                if(arr1[i] == arr2[j]){
                    if(i == 0 || j==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    if(dp[i][j] > maxLen){
                        maxLen = dp[i][j];
                        endPos = j;
                    }
                }
            }
        }
        return str2.substring(endPos - maxLen+1,endPos+1);
    }


    public static void main(String[] args) {
        String s1= "abcdbcd";
        String s2 = "ssdbbcdbcdddcs";
        String s = lcst2(s1, s2);

        System.out.println(s);

        System.out.println(dp(s1,s2));

//        Stack<Integer> integers = new Stack<>();
//        integers.add(1);
//        integers.push(2);
//        integers.push(3);
//        integers.add(4);
//
//
//
//        System.out.println(integers.peek());
//
//
//        Queue<Integer> integers1 = new LinkedList<>();
//        integers1.add(2);
//        integers1.offer(3);
//
//        System.out.println(integers1.peek());
//        System.out.println(integers1.poll());
//        System.out.println(integers1.element());
    }
}
