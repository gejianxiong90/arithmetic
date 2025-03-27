package zhaoshang;

/**
 * 任意长度的字符串中找到最长的不重复子串
 *
 * abcdd abcd
 */
public class Code1 {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(process(s));
    }

    public static String process(String str){
        int len = str.length();
        int l = 0;
        int r = 0;
        char[] arr = str.toCharArray();
        int[] map = new int[256];
        int maxLen = -1;
        int start = 0;
        while(r < len && l <= r){
            if(map[arr[r] - 'a'] == 0){
               if(r-l + 1 > maxLen){
                   maxLen = r - l + 1;
                   start = l;
               }
               map[arr[r] - 'a']++;
               r++;
            }else {
               map[arr[l] - 'a']--;
               l++;
            }
        }
        return str.substring(start,start + maxLen);
    }
}
