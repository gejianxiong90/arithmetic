package sz.code23;

/**
 * 给定一个字符串如 s1 = “aabcbcb” 和 s2 = “cba”，返回包含s2字符串的最小子串长度
 * 答案是 3， s1中的abc包含cba。（与顺序无关）
 *
 * 解：
 *  用记账的形式去解决
 */
public class MinWindowLength {

    public static int minLength(String s1,String s2){
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0 || s1.length() < s2.length()){
            return -1;
        }
        int[] map = new int[256];
        char[] str2 = s2.toCharArray();
        char[] str1 = s1.toCharArray();
        for(int i = 0 ; i < str2.length ; i++){
            map[str2[i]]++;
        }
        int L = 0;
        int R = 0;
        int all = str2.length;
        // L...R 范围内，需要左闭右闭
        int res = Integer.MAX_VALUE;
        while(R < str1.length){
            map[str1[R]]--;// 欠债表减去一个
            if(map[str1[R]] >= 0){
                all--;
            }
            if(all == 0){
                while(map[str1[L]] < 0){ // 负债表里有负数，所以需要排除左侧使其最短
                    map[str1[L++]]++;
                }
                // while出来L缩到位了，统计最短
                res = Math.min(res,R - L + 1);

                // 强行移出L侧，让欠债表+1，为了将R继续往后滑，而不是停留在当前解
                all++;
                map[str1[L++]]++;
            }
            R++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        int i = minLength("aabcbdcb", "cbad");
        System.out.println(i);
    }
}
