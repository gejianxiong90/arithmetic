package sz.code23;

import java.util.LinkedList;

/**
 * 加油站和距离间隔相连，表示的是加油站能加的油量和中间距离所消耗的油量。oil数组是加油站的油量，dis数组是两个加油站中间所消耗的油量。两个数组等长，返回等长boolean类型的数组，表示从某个加油站出发能否走完一圈
 *
 * 只能逆时针走
 *
 * 解1： oil - dis 得到纯能值数组，然后两圈累加和数组，如果 i是出发点，那么i往后的一圈最小值 - (i-1) >= 0 就是true，否则false
 *
 * 解2： 同样先得到纯能值数组，然后找到纯能值数组中其中一个正数为出发点，利用start（连通区开始位置，包含start），end（连通最后的位置，但不包含end），need（前面i-1位置能接上连通区至少需要的油量），rest（走向下一个点还剩的油量）
 * 情况一：某一个数能走一圈，那么依次往前遍历 need = 0 则为true
 * 情况二：往i+1方向走rest必须>=0,否则走不动，记录end。再往前i-1方向走，加上need值 >=0 就再尝试end的位置，循环过程，直到有一个能走完再执行情况一
 */
public class GasStations {




    public static boolean[] stations3(int[] dis,int[] oil){
        if(dis == null || oil == null || dis.length < 2 || dis.length != oil.length){
            return null;
        }
        int init = changeDisArrayGetInit(dis,oil);
        return init == -1 ? new boolean[dis.length] : enlargeArea(dis,init);

    }

    public static boolean[] enlargeArea(int[] dis,int init){
        boolean[] res = new boolean[dis.length];
        int start = init;
        int end = nextIndex(dis,init);
        int need = 0;
        int rest = 0;
        do {
            // 当前来到的start已经在连通区内，可以确定后续的开始点
            if(start != init && start == lastIndex(dis,end)){
                break;
            }
            //当前来到的start不在连通区域中，就扩充连通区域
            if(dis[start] < need){
                need -= dis[start];
            }else {
                rest += dis[start] - need;
                need = 0;
                while (rest >=0 && end != start){
                    rest += dis[end];
                    end = nextIndex(dis,end);
                }
                //连通区已经覆盖整个环，当前start就是良好的出发点，进入第二阶段
                if(rest >= 0){
                    res[start] = true;
                    connectGood(dis,lastIndex(dis,start),init,res);

                    break;
                }
            }
            start = lastIndex(dis,start);
        }while (start != init);
        return res;
    }

    public static void connectGood(int[] dis,int start,int init,boolean[] res){
        int need = 0;
        while (start != init){
            if(dis[start] < need){
                need -= dis[start];
            }else {
                res[start] = true;
                need = 0;
            }
            start = lastIndex(dis,start);
        }
    }

    public static int changeDisArrayGetInit(int[] dis,int[] oil){
        int init = -1;
        for(int i = 0; i < dis.length;i++){
            dis[i] = oil[i] - dis[i]; // 这里用dis表示纯能值，不用另外申请空间。如果还需要原本的dis也可以恢复
            if(dis[i] >= 0){
                init = i;
            }
        }
        return init;
    }


    public static boolean[] stations2(int[] dis,int[] oil){
        int need = 0; // 头部接上连通区域需要的多少纯能值
        int rest = 0;// 还剩多少
        int start = -1; //
        int end = 0;

        int[] ints = new int[dis.length];
        int init = -1;
        // 计算纯能值，并校验合法性
        for(int i = 0 ; i <dis.length ; i++){
            ints[i] = oil[i] - dis[i];
            if(ints[i] > -1){
                init = ints[i];
                if(ints[i] > 0){
                    start = i;
                }
            }
        }
        if (init == -1){
            return new boolean[dis.length]; //如果全部为负数，则都不可到达
        }

        boolean[] res = new boolean[dis.length];

        setEndIndexAndSetRes(ints,start,rest,end,res);
        // 方法出来后有两种情况
        // 1.没走完一圈，停留在end
        // 2.走完了一圈
        // 扩不动后，利用need往前延伸
        if( res[start]){ // 走了一圈
            doneOneSetRes(res,ints,start,need);

        }else { // 一圈没走完
            while (end != lastIndex(ints,start)){
                need -= ints[lastIndex(ints,start)];
                need = need < 0 ? 0 :need;
                if(need ==0){ // 前面一个数能接上
                    while (rest + ints[end] >= 0 && end != start){
                        rest += ints[end]; // 设置剩余值
                        if(start == nextIndex(ints,end)){
                            break;
                        }
                        end = nextIndex(ints,end);
                    }
                    if(start == nextIndex(ints,end)){
                        break;
                    }
                }
                start = lastIndex(ints,start);
            }
            if (start == nextIndex(ints,end) ){
                doneOneSetRes(res,ints,start,need);
            }
        }

        return res;
    }

    public static void doneOneSetRes(boolean[] res,int[] ints,int start,int need){
        int tmp = start;
        while (tmp != lastIndex(ints,start)){
            start = lastIndex(ints,start);
            need = need - ints[start] < 0 ? 0 : need - ints[start];
            if(need == 0){
                res[start] = true;
            }
        }
    }

    public static void setEndIndexAndSetRes(int[] ints,int start,int rest,int end, boolean[] res){
        int i = start;
        rest = ints[i]; //从第一个正数开始
        //从正数往下扩，最多能到哪个点
        while (start != nextIndex(ints,i)){
            end = nextIndex(ints,i);
            if(rest + ints[nextIndex(ints,i)] < 0 ){
                break;
            }
            rest += ints[nextIndex(ints,i)];
            i++;
        }
        if( start == nextIndex(ints,i)){
            res[start] = true;
        }
    }

    public static int nextIndex(int[] arr,int index){
        if(arr == null || arr.length == 0){
            return -1;
        }
        return index >= arr.length - 1 ? 0 : index + 1;
    }

    public static int lastIndex(int[] arr,int index){
        if(arr == null || arr.length == 0){
            return -1;
        }
        return index == 0 ? arr.length - 1 : index - 1;
    }


    public static boolean[] stations1(int[] dis,int[] oil){
        if(dis == null || oil == null || dis.length <2 ||dis.length != oil.length){
            return null;
        }
        int[] ints = new int[dis.length];
        int init = -1;
        // 计算纯能值，并校验合法性
        for(int i = 0 ; i <dis.length ; i++){
            ints[i] = oil[i] - dis[i];
            if(ints[i] > -1){
                init = ints[i];
            }
        }
        if (init == -1){
            return new boolean[dis.length]; //如果全部为负数，则都不可到达
        }

        int[] initSum = new int[dis.length * 2];
        int N = ints.length;

        // 同等长度的累加和
        for(int j = 0 ; j < N ; j++ ){
            initSum[j] = j == 0 ? ints[j] : ints[j-1] + ints[j];
        }
        // 走第二轮的累加和
        int index = N;
        for(int k = 0 ; k < N;k++){
            initSum[index] = initSum[index-1] + ints[k];
            index++;
        }
        // 获取窗口内最小值
        int L = 0;
        int R = 0;
        LinkedList<Integer> indexLink = new LinkedList<>(); // 存下标
        boolean[] res = new boolean[N];
        while (L <= R && R < initSum.length){
            if(L + N -1 >= R){ // R 距离L 已经形成 N 的数量
                while (!indexLink.isEmpty() && initSum[indexLink.peekLast()] >= initSum[R]){
                    indexLink.pollLast();
                }
                indexLink.addLast(R);
                R++;
            }else { // 左侧移出结算
                // 结算

                Integer cur = indexLink.peekFirst();
                if(cur == L){
                    indexLink.pollFirst();
                }
                if(cur != 0){
                    res[L] = initSum[cur] - initSum[cur - 1]  >= 0 ? true : false;
                }else {
                    res[L] = initSum[cur] >= 0 ? true : false;
                }
                L++;
            }
        }


        return res;
    }

    public static void main(String[] args) {
        int[] oil = {4,7};
        int[] dis = {5,4};
        boolean[] booleans = stations1(dis, oil);
        boolean[] booleans1 = stations2(dis,oil);
        boolean[] booleans2 = stations3(dis, oil);
        System.out.println(booleans);
    }
}
