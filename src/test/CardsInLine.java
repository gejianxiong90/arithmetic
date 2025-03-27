package test;

public class CardsInLine {

    public static int f (int[] arr ,int i ,int j ){
        if(i == j){
            return arr[i];
        }
        return Math.max(arr[i] + s(arr,i+1,j),arr[j]+s(arr,i,j-1));
    }

    public static int s (int[] arr, int i ,int j){
        if(i== j){
            return 0;
        }
       return  Math.min( f(arr,i+1,j),
         f(arr,i,j-1));
    }


    public static void main(String[] args) {
        int[] arr = {4,7,9,5};
        System.out.println(f(arr,0,3));
        System.out.println(s(arr,0,3));
    }
}
