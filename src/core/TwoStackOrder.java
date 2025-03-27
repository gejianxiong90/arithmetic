package core;

import java.util.Stack;

public class TwoStackOrder {

    public static void main(String[] args) {
        Stack<Integer> a = new Stack<>();
        a.push(2);
        a.push(1);
        a.push(3);
        Stack<Integer> b = new Stack<>(); // 单调性

        while(!a.isEmpty()){
            if(b.isEmpty()){
                b.push(a.pop());
            }else {
                Integer ai = a.pop();
                while(!b.isEmpty() && b.peek() < ai){
                    a.push(b.pop());
                }
                b.push(ai);
            }
        }


        while(!b.isEmpty()){
            System.out.println(b.pop());
        }
    }
}
