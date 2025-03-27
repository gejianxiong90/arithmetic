package sz.code23;

/**
 * 给定两个字符串str1和str2 ，是不是互为旋变串,比如下面是str1，每一层都可以交换，推到最顶端是否可以形成str2
 *                abc                abc                       bac                 bca
 *               /  \                / \                      /  \                /  \
 *              a   b c            ab   c     ----》》       ba   c     或      bc    a       。。。。。。。。。
 *                  / \           / \                       / \                /\
 *                 b   c         a   b                     b   a              b  c
 */
public class ScrambleString {




    public static boolean isScramble(String s1,String s2){
        if((s1 == null && s2 !=null) || (s1 != null && s2 == null)){
            return false;
        }
        if(s1 == null && s2 == null){
            return true;
        }
        if(s1.equals(s2)){
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1,str2,0,0,str1.length);
    }

    /**
     * 词频的方式检查类型和数量
     */
    public static boolean sameTypeSameNumber(char[] str1,char[] str2){
        if(str1.length != str2.length){
            return false;
        }
        int[] map = new int[256];
        for(int i = 0 ; i < str1.length ; i++){
            map[str1[i]]++;
        }

        for(int j = 0 ; j < str2.length ; j++){
            if(map[str1[j]]-- < 0 ){
                return false;
            }
        }

        return true;
    }


    /**
     *  str1和str2 在 L。。。L+size 的范围上是不是旋变串
     * @param str1
     * @param str2
     * @param L1
     * @param L2
     * @param size
     * @return
     */
    public static boolean process(char[] str1, char[] str2, int L1, int L2, int size) {
        if (size == 1) {
            return str1[L1] == str2[L2];
        }
        for (int leftPartSize = 1; leftPartSize < size; leftPartSize++) {
            if (
                    (process(str1, str2, L1, L2, leftPartSize)
                            && process(str1, str2, L1 + leftPartSize, L2 + leftPartSize, size - leftPartSize)) // 同侧
                            ||
                            (process(str1, str2, L1, L2 + size - leftPartSize, leftPartSize)) // 旋变侧
                                    && process(str1, str2, L1 + leftPartSize, L2, size - leftPartSize)

                    ) {


                return true;
            }
        }
        return false;
    }
}
