package sz.code8;

/**
 *  0 代表false，1代表true
 *  其中 包含了运算符 & | ^
 *  输入 字符串 1|0&1^0 ，和预期的 值 true或者false
 *  在此字符串上加上括号，输出 有多少种可能达到预期值
 *
 */
public class ExpressionNumber {

    public static boolean isValid(char[] exp){
        if((exp.length & 1) == 0){
            return false;
        }
        for(int i = 0 ; i< exp.length; i+=2){
            if(exp[i] != '1' && exp[i] != '0'){
                return false;
            }
        }
        for(int i = 1 ; i <exp.length; i+=2){
            if(exp[i] != '&' && exp[i] != '|' && exp[i] !='^'){
                return false;
            }
        }
        return true;
    }


    public static int num1(String express,boolean desired){
        if(express == null || express.equals("")){
            return 0;
        }
        char[] exp = express.toCharArray();
        if(!isValid(exp)){
            return 0;
        }

        return f(exp,true,0,exp.length);
    }


    public static int f2(String exp,boolean desired){
        char[] str = exp.toCharArray();
        int N = str.length;
        int[][] tMap = new int[N][N];
        int[][] fMap = new int[N][N];

        for(int i = 0;i <N ;i+=2 ){
            tMap[i][i] = str[i] == '1' ? 1 : 0;
            fMap[i][i] = str[i] == '0' ? 1: 0;
        }

        for(int row = N -3 ;row >= 0;row -= 2){ // 对角线填过了，偶数行跳过
            for(int col = row + 2;col < N;col += 2){
                for(int i = row + 1;i<col;i+=2){

                }
            }
        }

        return 0;
    }


    // str[L...R] 返回期待为desired的方法数
    // 潜台词： L R 必须是偶数位置
    public static int f(char[] str,boolean desired,int L,int R){
        if(L == R){
            if(str[L] == '1'){
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }
        int res = 0;
        if(desired){
            for(int i = L + 1;i < R ; i+=2 ){
                //exp[i] 一定压中逻辑符号
                switch (str[i]){
                    case '&':
                        res += f(str,true,L,i-1) * f(str,true,i+1,R);
                        break;
                    case '|':
                        res += f(str,true,L,i-1) * f(str,false,i+1,R);
                        res += f(str,false,L,i-1) * f(str,true,i+1,R);
                        res += f(str,true,L,i-1) * f(str,true,i+1,R);
                        break;
                    case '^' :
                        res += f(str,true,L,i-1) * f(str,false,i+1,R);
                        res += f(str,false,L,i-1) * f(str,true,i + 1,R);
                        break;
                }
            }
        }else {// 期待为false
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, false, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, true, L, i - 1) * f(str, false, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '|':
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                    case '^':
                        res += f(str, true, L, i - 1) * f(str, true, i + 1, R);
                        res += f(str, false, L, i - 1) * f(str, false, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }
}
