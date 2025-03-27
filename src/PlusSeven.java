import java.util.HashMap;
import java.util.HashSet;

public class PlusSeven {



    public static void main(String[] args){
        int[] arr = {1,2,5,6,3,4};

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        HashSet<Integer> integers = new HashSet<>();
        for(int i = 0;i<arr.length;i++){
            map.put(arr[i],1);
        }

        for(int j = 0;j< arr.length;j++){
            if( ( map.get(7 - arr[j])) == 1){
                if(!integers.contains(7 - arr[j])){
                    System.out.println(arr[j]+" --- "+ (7-arr[j]));
                    integers.add(arr[j]);
                    integers.add(7-arr[j]);
                }

            }
        }
    }
}
