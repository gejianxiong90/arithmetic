package leetcode;

public class IsPalindrome_125 {
    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
    }

    public boolean isPalindrome(String s) {
        if(s == null || "".equals(s.trim()) || s.length() == 1){
            return true;
        }
        String str = s.toLowerCase().replaceAll(" ","");
        char[] ch = str.toCharArray();
        StringBuilder validStr = new StringBuilder();
        for(int i = 0 ; i < ch.length ; i++){
            if((ch[i] >= 97 && ch[i] <= 122) || (ch[i] >= '0' && ch[i] <= '9')){
                 validStr.append(ch[i]);
            }
        }
        if(validStr.length() == 0){
            return false;
        }

        String manaString = manacherStr(validStr.toString());

        int max = manache(manaString);


        return max == validStr.length();
    }

    public static int manache(String manancherStr){
        char[] chars = manancherStr.toCharArray();
        int c = -1;
        int r = -1;
        int max = Integer.MIN_VALUE;
        int n = chars.length;
        int[] pArr = new int[n];
        for(int i = 0 ; i < n ; i++){
            pArr[i] = r > i ? Math.min(pArr[2 * c - i],pArr[r - i]) : 1;//至少不用验的长度
            while( i + pArr[i] < n && i - pArr[i] > -1){
                if( chars[i + pArr[i]] == chars[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if( i + pArr[i] > r){ // 刷新更右的r
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(max,pArr[i]);
            if(r == n){
                break;
            }
        }
        return max - 1;
    }

    public static String manacherStr(String s){
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for(int i = 0 ; i < chars.length ; i++){
            sb.append(chars[i]);
            sb.append("#");
        }
        return sb.toString();
    }
}
