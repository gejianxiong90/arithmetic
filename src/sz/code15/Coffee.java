package sz.code15;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个arr 数组，每个数表示咖啡机需要制造咖啡的时间
 * 有m个人需要冲咖啡，需要最快冲完咖啡
 * 返回m长度的数组，表示每个人冲咖啡最早的时间
 *
 * 解：利用小根堆，小根堆按照 开始时间+消耗时间 排列
 */
public class Coffee {

    public static class CoffeeMachine{
        public int start;
        public int work;

        public CoffeeMachine(int s,int w){
            this.start = s;
            this.work = w;
        }
    }

    public static class CoffeMachineComparator implements Comparator<CoffeeMachine>{

        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return (o1.start + o1.work) - (o2.start+o2.work) ;
        }
    }

    public static int[] bestChices(int[] arr,int M){
        int[] ints = new int[M];
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeMachineComparator());
        for(int coffeeMachine : arr){
            heap.add(new CoffeeMachine(0,coffeeMachine));
        }
        for(int i = 0 ; i < M ; i++){
            CoffeeMachine cur = heap.poll();
            ints[i] = cur.start + cur.work;
            cur.start = cur.start + cur.work;
            heap.add(cur);
        }

        return ints;
    }
}
