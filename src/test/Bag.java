package test;

public class Bag {

    public static void main(String[] args) {

    }

    public static int process(int[] weights,int[] values,int i,int maxWeight,int currentWeight){
        if(i == weights.length){
            return 0;
        }
        if(currentWeight > maxWeight){
            return -1;
        }
       int yes =  process(weights,values,i+1,maxWeight,currentWeight+values[i]);
        int no = process(weights, values, i+1, maxWeight, currentWeight);
        int p2 = 0;
        if(yes != -1){
            p2 = values[i] + yes;
        }

       return Math.max(p2,no);
    }
}
