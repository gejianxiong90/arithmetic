package sz;

public class ColorMin {
    /**
     * RRRRGRRGGGR
     * R 是红，G 是绿
     * 必须R在G的左侧，最少要涂几个颜色
     */

    public static int minColor(String s){
        char[] chars = s.toCharArray();
        int N = chars.length;
        int rNum = 0;
        for(char c : chars){
            if(c == 'R'){
                rNum++;
            }
        }
        int gNum = 0;
        int res  = rNum;
        for(int i = 0 ;i < N  ; i++ ){
            gNum += chars[i] == 'G' ? 1 : 0;
            rNum -= chars[i] == 'R' ? 1 : 0;
            res = Math.min(res,rNum + gNum);
        }

      //  res = Math.min(res,gNum + (chars[N - 1] == 'G' ? 1 : 0));

        return res;
    }

    public static void main(String[] args) {
        String str = "GRR";

        System.out.println(minColor(str));

    }
}
