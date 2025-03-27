package sz.code20;

import java.util.HashSet;
import java.util.Stack;

/**
 * 可见山峰问题，给定一个数组，将他首尾连接形成一个环
 * 1）相邻的两个数可见
 * 2）两个值的min中间有>min的表示不可见
 * 问其中可见的山峰对有几对？
 *
 * 解：
 * 单调栈来解，整体流程是通过 小的 找 大的
 * 找到最大数，将最大数压入栈底，栈顶是小的，当遇到的数比栈顶大，则结算栈顶的（意味着两侧的数都比他大，2*times + 内部的数，times是这个数遇到的次数，乘2是逆时针顺时针都有比他大的）
 *
 * 最后栈内数要单独结算
 * stack.size > 2  结算逻辑不变
 * stack.size ==2  倒数第二个弹出，下面还有一个数。有可能只有顺时针或逆时针成对，不能与同一个数成对。如果倒数第一个数times>! 则算法相同
 * 最后结算最后一个最大的数，他没有小数匹配所以对外是没有的，只有对内
 */
public class VisibleMountains {

    public static class Record{
        public int value;
        public int times;

        public Record(int value){
            this.value = value;
            this.times = 1;
        }

    }
    public static int nextIndex(int i,int size){
        return i < size - 1 ? i+1 : 0;
    }

    public static int lastIndex(int i , int size){
        return i > 0 ? i-1 :size - 1;
    }



    public static int getVisibleNum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int N = arr.length;
        int maxIndex = 0;
        // 先找出最大值的index
        for(int i = 0 ; i <arr.length;i++){
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        Stack<Record> records = new Stack<>(); // 栈底是最大数，栈顶到栈底从小到大，数字大于栈顶数则弹出结算栈顶，times*2 + c2k
        records.push(new Record(arr[maxIndex]));
        int index = nextIndex(maxIndex, N);
        int res = 0;
        while (index != maxIndex){
            while(records.peek().value < arr[index]){
                int k = records.pop().times;
                res += 2 * k + getInternalSum(k); // 外部有 k*2 个，内部 C（2，k）
            }
            if(records.peek().value == arr[index]){
                records.peek().times++;
            }else {
                records.push(new Record(arr[index]));
            }
            index = nextIndex(index,N); // 获取下一个
        }
        //清算开始，第一阶段
        // 栈的数量>2时，同样的公式，说明顺时针和逆时针都有可以成对的数量
        if(records.size() > 2){
            int k = records.pop().times;
            res += 2 * k + getInternalSum(k);
        }
        // 第二阶段，倒数第二个数弹出时，看倒数第一个times。如果times是1，说明顺时针逆时针遇到的是同一个数，只记k个。times>1 两个方向遇到不同的数，依然2*k
        if(records.size() == 2){
            int k = records.pop().times;
            res += getInternalSum(k) + records.peek().times == 1 ? k : 2 * k;
        }
        res += getInternalSum(records.pop().times);// 最后一个数字最大做清算，他只有内部的，外部的没有（它是最大）
        return res;

    }


    public static int getInternalSum(int k){
        return k == 1 ? 0 : k * (k - 1) / 2;
    }

    // for test, O(N^2)的解法，绝对正确
    public static int rightWay(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        HashSet<String> equalCounted = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            // 枚举从每一个位置出发，根据“小找大”原则能找到多少对儿，并且保证不重复找
            res += getVisibleNumFromIndex(arr, i, equalCounted);
        }
        return res;
    }
    // for test
    // 根据“小找大”的原则返回从index出发能找到多少对
    // 相等情况下，比如arr[1]==3，arr[5]==3
    // 之前如果从位置1找过位置5，那么等到从位置5出发时就不再找位置1（去重）
    // 之前找过的、所有相等情况的山峰对，都保存在了equalCounted中
    public static int getVisibleNumFromIndex(int[] arr, int index,
                                             HashSet<String> equalCounted) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) { // 不找自己
                if (arr[i] == arr[index]) {
                    String key = Math.min(index, i) + "_" + Math.max(index, i);
                    // 相等情况下，确保之前没找过这一对
                    if (equalCounted.add(key) && isVisible(arr, index, i)) {
                        res++;
                    }
                } else if (isVisible(arr, index, i)) { // 不相等的情况下直接找
                    res++;
                }
            }
        }
        return res;
    }

    // for test
    // 调用该函数的前提是，lowIndex和highIndex一定不是同一个位置
    // 在“小找大”的策略下，从lowIndex位置能不能看到highIndex位置
    // next方向或者last方向有一个能走通，就返回true，否则返回false
    public static boolean isVisible(int[] arr, int lowIndex, int highIndex) {
        if (arr[lowIndex] > arr[highIndex]) { // “大找小”的情况直接返回false
            return false;
        }
        int size = arr.length;
        boolean walkNext = true;
        int mid = nextIndex(lowIndex, size);
        // lowIndex通过next方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkNext = false;// next方向失败
                break;
            }
            mid = nextIndex(mid, size);
        }
        boolean walkLast = true;
        mid = lastIndex(lowIndex, size);
        // lowIndex通过last方向走到highIndex，沿途不能出现比arr[lowIndex]大的数
        while (mid != highIndex) {
            if (arr[mid] > arr[lowIndex]) {
                walkLast = false; // last方向失败
                break;
            }
            mid = lastIndex(mid, size);
        }
        return walkNext || walkLast; // 有一个成功就是能相互看见
    }


    public static void main(String[] args) {

        int[] arr = {6,9,5};
        System.out.println(rightWay(arr));
        System.out.println(getVisibleNum(arr));
    }
}
