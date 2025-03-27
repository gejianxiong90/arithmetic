package sz.code11;

/**
 * 随便写一个数num，要求返回从1-num的所有的1的数量
 *
 * 解：求每一个位出现1的可能性数量
 *     如：13625
 *     高位为1： 万位有10000-13625 ，其中有3626个1，公式 13625 % 10000 + 1
 *               千百十个位，都为10的3次方，假设千位为1，百十个位分别有10总取值的可能10*10*10。
 *
 *     如：47625
 *     高位不为1：万位有 10000-19999，有10000个1，公式为 10的len-1次方，
 *                千百十个位，都有10^3 * 4 ,10的3次方解释同上。4是组数有4组，在每组中都有相同的数，7626 - 17625，17626 - 27625 ， 27626 - 37625 ， 37626 - 47625
 *
 */
public class OneNumber {

    public static int getLenOfNum(int num){
        int len = 0;
        while(num != 0){
            len++;
            num /= 10;
        }
        return len;
    }

    public static int powerBaseOf10(int base){
        return (int)Math.pow(10,base);
    }

    public static int solution2(int num) {
        if (num <= 0) {
            return 0;
        }
        int len = getLenOfNum(num);

        if (len == 1) {
            return 1;
        }

        int tmp = powerBaseOf10(len - 1);

        int first = num / tmp;

        int firstNum = first == 1 ? (num % tmp) + 1 : tmp;
        int otherNum = (len - 1) * (tmp/10) * first;

        return firstNum + otherNum + solution2(num % tmp);
    }

        public static void main(String[] args) {
        int lenOfNum = getLenOfNum(80);
    }
}
