package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Yanghui {

    public static void main(String[] args) {

        System.out.println(Integer.toBinaryString(-536870912));
        System.out.println(Integer.toBinaryString(536870911));
        System.out.println(-536870912 & 536870911);

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 50L, TimeUnit.MINUTES, linkedBlockingQueue);

        for(int i = 0 ; i < 100 ; i++){
            final int num = i ;
            threadPoolExecutor.execute(()->{
                System.out.println(num);
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {


                }
            });
        }

//        generate(5);
//
//        ArrayList<Integer> integers = new ArrayList<>();
    }

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0 ; i < numRows;i++){
            List<Integer> levelRes = new ArrayList<Integer>();
            for(int j = 0 ; j <=i ; j++){
                if(i == 0 || i == 1 || j == 0 || j == i){
                    levelRes.add(1);
                }else {
                    List<Integer> pre = ans.get(i-1);
                    for(int k = 0 ; k < pre.size() - 1 ; k++){
                        levelRes.add(pre.get(k) + pre.get(k+1));
                        j++;
                    }
                    j--;

                }

            }
            ans.add(levelRes);
        }
        return ans;
    }
}
