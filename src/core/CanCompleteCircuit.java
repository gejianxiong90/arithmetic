package core;

import java.util.LinkedList;

public class CanCompleteCircuit {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] help = new int[gas.length];
        int[] sum = new int[help.length * 2];
        for(int i = 0 ; i < gas.length ; i++){
            help[i] = gas[i] - cost[i];
            sum[i] = i == 0 ? help[i] : help[i] + sum[i-1];
        }
        for(int i = 0 ; i < help.length ; i++){
            sum[i + help.length] = sum[i + help.length - 1] + help[i];
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0 ; i < help.length ; i++){
            while(!list.isEmpty() && sum[list.peekLast()] >= sum[i] ){
                list.pollLast();
            }
            list.addLast(i);
        }
        if(sum[list.peekFirst()] >= 0){
            return 0;
        }

        int R = help.length;
        int L = 0;
        while(R < sum.length){
            L++;
            if(L > list.peekFirst()){
                list.pollFirst();
            }
            while(!list.isEmpty() && sum[list.peekLast()] >= sum[R] ){
                list.pollLast();
            }
            list.addLast(R);
            if(sum[list.peekFirst()] - sum[L - 1]>= 0){
                return L;
            }
            R++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas = {4,5,2,6,5,3};
        int[] cost = {3,2,7,3,2,9};
        int i = canCompleteCircuit(gas, cost);

        System.out.println(i);
    }
}
