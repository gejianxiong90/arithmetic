package graph;

import java.util.Stack;

public class Hanoi {

    // 汉诺塔
    public static void func(int N,String from,String to,String other){   // 2的N次方 -1 歩
        if(N == 1){
            System.out.println("Move 1 from "+from +" to " + to);
        }else{
            func(N -1 ,from ,other,to);  // n-1 就是除了最下面的，移动中间
            System.out.println("Move " + N +" from " + from +" to "+ to);  // 将第N个移动到 right
            func(N - 1,other,to,from);  // 剩余的n-1个 从other 放到to
        }
    }

    public static void hanoi2(int n){
        if(n > 0){
            func(n,"left","right" ,"mid");
        }
    }

    public static void main(String[] args) {
        hanoi2(3);
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        Integer f = f(stack);
        reverse(stack);
        stack.push(f);
    }


    public static Integer f(Stack<Integer> stack){
        Integer pop = stack.pop();
        if(stack.isEmpty()){
            return pop;
        } else {
            Integer last = f(stack);
            stack.push(pop);
            return last;
        }
    }
}
