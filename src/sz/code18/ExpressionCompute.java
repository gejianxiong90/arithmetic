package sz.code18;

import java.util.LinkedList;

/**
 * 给定一个字符串str，str是一个由字符串组成的公式，如“5+7*（8+6）”
 * 返回最终计算的数值
 *  前提条件 ： 负数不可以出现在符号的后面，比如 3 * -4 ， 必须写成 3 * （-4）
 *
 *  递归过程返回数组，arr[0] 返回计算的值  arr[1] 返回下一个处理的index
 *
 *  遇到 （ 交给子过程
 *  遇到 * / 先进行计算
 *  最后结算 + -
 */
public class ExpressionCompute {

    public static int getValue(String str){
       return value(str,0)[0];
    }

    /**
     *
     * @param str
     * @param i 来到的下标
     * @return 返回两个数，0是数值  1是计算来到的字符串下标
     */
    public static int[] value(String str,int i){
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        char[] chars = str.toCharArray();
        while (i < chars.length && chars[i] != ')'){
            if(chars[i] >= '0' && chars[i] <= '9'){
                cur = cur * 10 + chars[i++] - '0';
            }else if(chars[i] == '('){
                int[] value = value(str, i + 1);
                i = value[1] + 1;
                cur = value[0];
            }else { //遇到运算符
                addNum(queue,cur);
                queue.addLast(String.valueOf(chars[i++]));
                cur = 0;
            }
        }
        addNum(queue,cur);
        return new int[]{getNum(queue),i};

    }

    public static void addNum(LinkedList<String> queue,int num){
        if (!queue.isEmpty()){
            String top = queue.pollLast();
            if(top.equals("+") || top.equals("-")){
                queue.addLast(top);
            }else {
                Integer cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? cur * num : cur / num;
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> queue){

        boolean add = true;
        String cur = null;
        int res = 0;
        int num = 0;
        while (!queue.isEmpty()){
            cur = queue.pollFirst();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else {
                num = Integer.valueOf(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
//        System.out.println(getValue(exp));
//
//        exp = "4*(6+78)+53-9/2+45*8";
//        System.out.println(getValue(exp));
//
//        exp = "10-5*3";
//        System.out.println(getValue(exp));

      //  exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+5*4";
        System.out.println(getValue(exp));

        }
}
