package graph;

import java.util.Stack;

public class ReverseStackUsingRecursive {


    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        reverse(integers);
        while (!integers.isEmpty()){
            System.out.println(integers.pop());
        }
    }


    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int f = f(stack);
        reverse(stack);
        stack.push(f);
    }


    // 弹出最底部的数
    public static int f(Stack<Integer> stack){
        Integer pop = stack.pop(); // 3
        if(stack.isEmpty()){
            return pop;
        }else{
            int last = f(stack);
            stack.push(pop);   // 3
            return last;
        }
    }
}
