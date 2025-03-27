package core;

public class MinimumWindowsSubstring {

    public static void main(String[] args) {
        String s = minWindow("ADOBECODEBANC", "ABC");

        System.out.println(s);
    }

    public static String minWindow(String s, String t) {
        if(t.length() > s.length()){
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        for(char c : target){
            map[c]++;
        }
        int all = target.length;
        int minLen = 0;
        int L = 0;
        int R = 0;
        int ansL = -1;
        int ansR = -1;
        while(R < str.length ){
            map[str[R]]--;
            if(map[str[R]] >= 0){ // 有负债的情况，只减负债的
                all--;
            }
            if(all == 0){
                while(map[str[L]] < 0){ // 碰到了0 ， 这个字符必然属于target中的，因为R给了多少L就会收回多少，不会多收
                    map[str[L++]]++;
                }
                if(minLen == 0 || minLen > R - L + 1){
                    minLen = R - L + 1;
                    ansL = L;
                    ansR = R;
                }
                all++; // 上面碰到0，再滑总欠账+1
                map[str[L++]]++;
            }

            R++;
        }

        return minLen == 0 ? "" : s.substring(ansL,ansR+1);
    }
}
