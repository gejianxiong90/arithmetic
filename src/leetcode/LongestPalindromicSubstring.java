package leetcode;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String p = "wr121f";


        String s = longestPalindrome(p);

        System.out.println(s);
    }

    public static String longestPalindrome(String s) {

        String str = manacherString(s);
        int[] pArr = new int[str.length()];
        int R = -1;
        int C = -1;
        int max = Integer.MIN_VALUE;
        String maxStr = null;
        int mid = 0;
        char[] c = str.toCharArray();
        for(int i = 0 ; i < c.length ; i++){
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            while(i +pArr[i] < c.length && i - pArr[i] > -1){
                if(c[i+pArr[i]] == c[i-pArr[i]] ){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > R){
                R = i +pArr[i];
                C = i;
            }
            if(pArr[i] > max){
                max =pArr[i];
                mid = i;
            }
        }
        mid = (mid - 1) / 2;
        max = max - 1;
        return s.substring((max & 1) == 0 ? mid - (max / 2) +1 : mid - (max / 2) , mid + (max/2)+1);
    }

    public static String manacherString(String s){
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i <str.length; i++){
            sb.append("#");
            sb.append(str[i]);
        }
        sb.append("#");
        return sb.toString();
    }
}
