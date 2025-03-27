package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MontonousStack {


    public static void main(String[] args) {
        int[] arr = {3,5,3,6,2};
        int[][] nearLess = getNearLess(arr);
        for(int i = 0 ; i < nearLess.length ;  i++){
            for(int j = 0 ; j < nearLess[i].length ; j++){
                System.out.println(i +"---->"+ nearLess[i][j]);
            }
        }
    }

    public static int[][] getNearLess(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0 ; i < arr.length ; i++){
            ArrayList<Integer> integers = new ArrayList<>();
            integers.add(i);
            if(stack.isEmpty()){
                stack.push(integers);
                continue;
            }

            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> pop = stack.pop();
                for(int j = 0 ; j < pop.size() ; j++){
                    res[pop.get(j)][1] = i;
                    List<Integer> peek = stack.isEmpty() ? null : stack.peek();
                    res[pop.get(j)][0] = peek == null ? -1 : peek.get(peek.size()-1);
                }

            }
            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                List<Integer> pop = stack.pop();
                pop.addAll(integers);
                stack.push(pop);
                continue;
            }else {
                stack.push(integers);
            }
        }

//        List<Integer> pop = stack.pop();
//        if(pop != null){
//            for(int j = 0 ; j < pop.size() ; j++){
//                res[pop.get(j)][1] = -1;
//                List<Integer> peek = stack.peek();
//                res[pop.get(j)][0] = peek == null ? -1 : peek.get(peek.size()-1);
//            }
//        }
        while (!stack.isEmpty()){
            List<Integer> pop1 = stack.pop();
            List<Integer> peek = stack.isEmpty() ? null : stack.peek();
            for(int j = 0 ; j < pop1.size() ; j++){
                res[pop1.get(j)][1] = -1;
                res[pop1.get(j)][0] = peek == null ? -1 : peek.get(peek.size()-1);
            }
        }
        return res;
    }
}
