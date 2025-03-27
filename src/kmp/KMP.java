package kmp;


/**
 *   字符串    dfhdfdsabcsabckdddd
 *   匹配字符   a  b c s a b c k
 *              -----    ----
 *             -1 0 0 0 0 1 2 3          之前的相同字符最大长度
 */

public class KMP {

    public static void main(String[] args) {
        int index = getIndexOf("dfhdfdsabcsabckdddd", "abcsabck");
        System.out.println(index);
    }


    public static int getIndexOf(String str,String match){
        char[] strs = str.toCharArray();
        char[] matchs = match.toCharArray();
        int x = 0;
        int y = 0;
        int[] nextArray = getNextArray(matchs);
        while(x < str.length() && y < matchs.length){
            if(strs[x] == matchs[y]){     // 位置匹配上，进行下一个位置匹配
                x++;
                y++;
            }else if(nextArray[y] == -1){ // 到matchs 0 位置时
               x++;
            }else{
                y = nextArray[y];          //  位置匹配不上，matchs 往前移动
            }
        }
        return y == matchs.length ? x - y : -1;   // y都匹配上，位置才会到达matchs.length. 减去matchs的长度就是最早匹配位置，否则就是没有匹配上返回-1
    }


    /**
     *  a b c s a b c k
     *       cn       cn = 3
     *
     * @param matchs
     * @return
     */
    public static int[] getNextArray(char[] matchs){
        if(matchs.length == 0){
            return null;
        }
        if (matchs.length == 1){
            return new int[]{-1};
        }
        int[] ints = new int[matchs.length];
        ints[0] = -1;
        ints[1] = 0;
        int cn = 0;
        int i = 2;
        while(i < matchs.length){            // a b c s a b c k
                                              // ---    ---
            if(matchs[i - 1] == matchs[cn]){  //  matchs[7-1] == matchs[2] ?   (c==c?)
                ints[i++] = ++cn;        // 如果相等 k位置+1 = cn + 1 ， cn自增是为了下次循环计算时使用
            }else if(cn > 0 ){          // 不相等时 往前面位置跳，再进行匹配
                cn = ints[cn];
            }else{
                ints[i++] = 0;          // 没有匹配上等于0
            }
        }
        return ints;
    }
}
