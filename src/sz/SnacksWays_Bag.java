package sz;

public class SnacksWays_Bag {


    public static int process(int[] arr,int index,int rest){
        if(rest < 0){
            return -1;
        }
        if(index == arr.length){
            return 1;
        }

       int yes =  process(arr,index+1,rest - arr[index]);
        int no = process(arr, index + 1, rest);
        return no + (yes == -1 ? 0 : yes);
    }
}
