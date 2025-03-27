package core;

import java.util.Stack;

public class ReverseStack {

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = process(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int process(Stack<Integer> stack){
        Integer pop = stack.pop();
        if(stack.isEmpty()){
            return pop;
        }else {
            Integer last = process(stack);
            stack.push(pop);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        integers.push(3);
        integers.push(2);
        integers.push(1);

        reverse(integers);

        while(!integers.isEmpty()){
            System.out.println(integers.pop());
        }
    }
}
