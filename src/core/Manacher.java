package core;

public class Manacher {


    public static void main(String[] args) {
        int abccba = manacher("abccba");

        System.out.println(abccba);
        String s = addCharToP("112abccba");

        System.out.println(s);
    }

    /**
     * 最后添加什么字符，使整体回文
     * @param str
     * @return
     */
    public static String addCharToP(String str){
        String manacherStr = manacheString(str);
        int r = -1;
        int c = -1;
        int max = Integer.MIN_VALUE;
        int[] pArr = new int[manacherStr.length()];
        char[] ch = manacherStr.toCharArray();
        for(int i = 0 ; i <ch.length ; i++){
            pArr[i] = r > i ? Math.min(pArr[2 * c - i],r- i):1;
            while( i + pArr[i] < ch.length && i - pArr[i] > -1){
                if(ch[i + pArr[i]] == ch[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] >r){
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(max,pArr[i]);
            if(r == ch.length - 1){
                break;
            }
        }

        int addLen = str.length() - (max-1);
        StringBuilder sb = new StringBuilder();
        char[] orCh = str.toCharArray();
        for(int i = 0; i < addLen ;i++){
            sb.append(orCh[i]);
        }
        return sb.reverse().toString();
    }

    public static int manacher(String str){
        String s = manacheString(str);
        int r = -1;
        int c = -1;
        int max = Integer.MIN_VALUE;
        int[] pArr = new int[s.length()];
        char[] ch = s.toCharArray();
        for(int i = 0 ; i <s.length();i++){
            pArr[i] = r > i ? Math.min(pArr[2 * c -i],pArr[r - i]):1;
            while( i + pArr[i] < s.length() && i - pArr[i] > -1){
                if(ch[i + pArr[i]] == ch[i+pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if( i + pArr[i] > r){
                r = i + pArr[i];
                c = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }

    public static String manacheString(String str){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        char[] chars = str.toCharArray();
        int len = chars.length;
        for (int i = 0 ; i < len ; i++){
            stringBuilder.append(chars[i]);
            stringBuilder.append("#");
        }
        return stringBuilder.toString();
    }
}
