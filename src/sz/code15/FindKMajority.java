package sz.code15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 水王问题
 *
 */
public class FindKMajority {
    /**
     * 给定一个arr数组，其中有一个数出现的次数超过了数组长度的一半，返回这个数
     */
    public static void printHalfMajor(int[] arr){
        int cand = 0;
        int hp = 0;
        for(int i : arr){
            if(hp == 0){
                cand = i;
                hp = 1;
            }else if(cand == i){
                hp++;
            }else {
                hp--;
            }
        }
        if(hp == 0){
            System.out.println("no such num");
        }
        int times = 0;
        for (int j : arr){
            if(j == cand){
               times++;
            }
        }
        if(times > arr.length / 2){
            System.out.println(cand);
        }else {
            System.out.println("no such num");
        }
    }

    /**
     * 给定一个数组arr，长度N。和整数k，返回超过N/k次数的数字
     * @param arr
     * @param k
     */
    public static void printKMajor(int[] arr,int k){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i : arr){
            if(map.containsKey(i)){
                    map.put(i,map.get(i) + 1);
            }else {
                if(map.size() == k-1) {
                    allCandsMinusOne(map);
                }else {
                    map.put(i,1);
                }

            }
        }

        if(map.isEmpty()){
            System.out.println("no such num");
        }

        Map<Integer, Integer> reals = getReals(arr, map);

        boolean hasPrint = false;
        for(Integer key : reals.keySet()){
            if(reals.get(key) > arr.length / k){
                hasPrint = true;
                System.out.print( key+" ");
            }
        }

        System.out.println(hasPrint ? "" : "no such num");
    }

    public static Map<Integer,Integer> getReals(int[] arr,Map<Integer,Integer> cand){
        HashMap<Integer, Integer> reals = new HashMap<>();

        for(int i :arr){
            if(cand.containsKey(i)){
                if(reals.containsKey(i)){
                    reals.put(i,reals.get(i)+1);
                }else {
                    reals.put(i,1);
                }
            }
        }
        return reals;
    }

    public static void allCandsMinusOne(Map<Integer, Integer> map) {
        List<Integer> removeList = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if(value == 1){
                removeList.add(key);
            }
            map.put(key,value - 1);
        }

        for(Integer key :removeList){
            map.remove(key);
        }
    }


    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr, K);
    }
}
