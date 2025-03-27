package sz.code21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  给定一个二维数组arr ，pms（项目经理数量），sde（程序员数量），每个项目经理每次只能提交一个，程序做完才能再次提交，返回每个每个项目完成时间的数组
 *  {
 *  { 1, 1, 1, 2 },
 *  { 1, 2, 1, 1 },
 *  { 1, 3, 2, 2 },
 *  { 2, 1, 1, 2 },
 *  { 2, 3, 5, 5 }  代表一个项目， 2号代表项目经理，3产品被项目经理润色出来的时间，5是优先级，5是程序员花费的时间
 *  }
 *
 * 对于项目经理来说，项目越喜欢会被越早提交。一个项目优先级越高越被喜欢，优先级一样，花费时间越少越喜欢，花费时间一样，越早润色出来越被喜欢
 * 对于程序员来说，一个项目花费时间越少越被喜欢，花费时间一样，该项目pm越小越被喜欢
 *
 * 解：
 *  两个堆的组合调整，项目经理用priorityqueue按他的喜欢排。程序员需要手写堆，因为程序员喜欢的建立在项目经理喜欢的提交上，项目经理一旦，程序员会伴随调整堆
 *
 *
 *
 */
public class SDEandPM {

    public static class Program {
        public int index; // 记录项目最后给出答案对应的index
        public int pm; // 项目经理编号
        public int start; // 项目经理润色出来的开始时间
        public int rank; // 优先级
        public int cost; // 程序员花费的时间


        public Program(int index,int pm,int start,int rank,int cost){
            this.index = index;
            this.pm = pm;
            this.start = start;
            this.rank = rank;
            this.cost = cost;
        }
    }

    public static class PmQueueComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) { // 项目经理的喜欢的优先级
            if(o1.rank != o2.rank){
                return o1.rank - o2.rank;
            }else if(o1.cost != o2.cost){
                return o1.cost - o2.cost;
            }else if(o1.start != o2.start){
                return o1.start - o2.start;
            }
            return 0;
        }
    }

    public static class BigQueue{
        // 每一个项目经理有一个优先级队列
        public List<PriorityQueue<Program>> pmQueue = new ArrayList<PriorityQueue<Program>>();
        // 程序员任务堆
        public Program[] sdeHeap;
        // 任务堆的size
        public int heapSize;
        // 记录pm堆顶映射程序员任务堆中index
        public int[] indexs;


        public BigQueue(int pmNum){
            sdeHeap = new Program[pmNum];  // 程序员任务堆的大小就等于项目经理的数量
            indexs = new int[pmNum+1]; // 0位置不用

            for(int j = 0 ; j <= pmNum ; j++){ // 初始化没有
                indexs[j] = -1;
            }

            heapSize = 0;
            for(int i = 0 ; i<= pmNum ; i++){
                PriorityQueue<Program> programs = new PriorityQueue<>(new PmQueueComparator());
                pmQueue.add(programs);
            }
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public void add(Program program){
            PriorityQueue<Program> programs = pmQueue.get(program.pm);
            programs.add(program);
            Program head = programs.peek(); // 这个pm弹出优先级最高的
            int index = indexs[head.pm]; // 当前pm对应任务池中的index
            if(index == -1){ // 说明该pm对应到程序员任务堆中是没有任务的
                sdeHeap[heapSize] = head;
                indexs[head.pm] = heapSize;
                heapInsert(heapSize++); //程序员的角度调整堆
            }else {
                sdeHeap[index] = head; // 直接替换之前的，一定是映射项目经理最喜欢的
                heapInsert(index);
                heapify(index);
            }
        }

        public Program pop(){
            Program program = sdeHeap[0];
            PriorityQueue<Program> programs = pmQueue.get(program.pm); // pm对应的优先级队列
            programs.poll();
            if(programs.isEmpty()){
                swap(0,heapSize - 1);
                //sdeHeap[0] = sdeHeap[heapSize-1]; // 最后的与堆顶交换，删除0节点
                sdeHeap[--heapSize] = null; // 交换后，最后的为null，并且size--
                indexs[program.pm] = -1; // 设置为-1，表示该pm任务池里没有任务
            }else{
                sdeHeap[0] = programs.peek();// 堆顶设置
            }
            heapify(0); // 做从上到下的heapify，堆顶排，因为不知道其他项目的优先级
            return program;
        }


        public void heapInsert(int index){ // heapInsert 是从下往上与父对比，趟了一个高度 logn
            while (index > 0 ){
                int parent = (index - 1) / 2;
                if(sdeLoveRule(sdeHeap[index],sdeHeap[parent]) < 0){
                   swap(index,parent);
                    index = parent;
                }else {break;}
            }
        }

        public void heapify(int index){ // heapify 从上往下，父对比左右，不符合就往下沉，也是一个高度 logn
            while (index< heapSize ){
                int left = index * 2 + 1;
                int best = index;
                if(left <heapSize){
                    best = sdeLoveRule(sdeHeap[index],sdeHeap[left]) < 0 ? index : left;
                }
                int right = left + 1;
                if(right < heapSize){
                    best = sdeLoveRule(sdeHeap[index],sdeHeap[right]) < 0 ? index : right;
                }
                if(index == best){
                    break;
                }
                swap(index,best);
                index = best;
            }

        }
        public void swap(int index1,int index2){ // 交换的逻辑 heapify和heapinsert调整堆的时候需要交换，最后要交换indexs里的映射关系
            Program program1 = sdeHeap[index1];
            Program program2 = sdeHeap[index2];

            sdeHeap[index1] = program2;
            sdeHeap[index2] = program1;

            indexs[program1.pm] = index2;
            indexs[program2.pm] = index1;
        }

        public int sdeLoveRule(Program p1,Program p2){ // 程序员喜欢的规则
            if(p1.cost != p2.cost){
                return p1.cost - p2.cost;
            }else{
                return p1.pm - p2.pm;
            }
        }

    }

    public static class StartRule implements Comparator<Program>{  // 以润色出来的时间排序

        @Override
        public int compare(Program o1, Program o2) {
            return o1.start - o2.start;
        }
    }

    public static int[] workFinish(int pms, int sdes, int[][] programs) {
        // 润色出来的时间做排序，最早的在堆顶
        PriorityQueue<Program> startQueue = new PriorityQueue<Program>(new StartRule());
        for(int i = 0 ; i < programs.length ; i++){
            Program program = new Program(i, programs[i][0], programs[i][1], programs[1][2], programs[i][3]);
            startQueue.add(program);
        }
        // 唤醒队列
        PriorityQueue<Integer> wakeQueue = new PriorityQueue<>();//时间线，小根堆，最早的在堆顶
        for(int i = 0 ; i < sdes ; i++){
            wakeQueue.add(1); // 时间线
        }
        int finish = 0;
        int[] res = new int[programs.length];
        BigQueue bigQueue = new BigQueue(pms);
        while (finish != res.length){
            Integer time = wakeQueue.poll(); // 时间线
            while (!startQueue.isEmpty()){
                if(startQueue.peek().start > time){ // 润色出来的时间 晚于 程序员开始时间，直接跳出
                   break;
                }
                bigQueue.add(startQueue.poll()); // 最先被润色出来的先弹出
            }
            if(bigQueue.isEmpty()){ // 早于润色出来的时间，bigqueue会为空。加入被润色出来的时间，就是程序员开始的时间
                wakeQueue.add(startQueue.peek().start); //时间线增加
            }else {
                Program pop = bigQueue.pop();
                res[pop.index] = time + pop.cost; //完成时间 = 开始时间 + 耗费的时间
                wakeQueue.add(res[pop.index]);
                finish++;
            }
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        int pms = 2;
        int sde = 2;
        int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
        int[] ans = workFinish(pms, sde, programs);
        printArray(ans);
    }


}
