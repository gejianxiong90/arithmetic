package sz.code9;

import java.util.HashMap;

/***
 * 给定一个字符串，求最长的无重复子串如“abccddb”，最长为abc返回3
 * 解：pre 整体重复的位置，map[i]（记录重复字符的下标）上次这个字符重复的位置，两个位置拿离你最近的就是i无重复的区间
 *
 */
public class LongestNoRepeatSubstring {



    public static int maxUnique(String str){
        char[] chars = str.toCharArray();
        // 代替hash表，假设字符码是0-255
        int[] map = new int[256];
        for (int i = 0;i < 256;i++){
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for(int i = 0; i < chars.length ;i++){
            pre = Math.max(pre,map[chars[i]]); // map[char[i]]是上次i位置重复字符出现的位置，i-1或之前，pre是整体重复的位置
            cur = i - pre; // 减去上次出现的位置
            len = Math.max(len,cur); // 前面的长度 和 我当前减去相同出现的地方 做对比
            map[chars[i]] = i;
        }
        return len;
    }

    public static void main(String[] args) {
        int abccddb = maxUnique("abccddb");

        System.out.println(abccddb);
    }
}
