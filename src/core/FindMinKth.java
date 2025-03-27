package core;

public class FindMinKth {


    public static void main(String[] args) {
        int[] arr = {4,-1,5,0,1};
        int k = 3;
        int process = process(arr, 0, 4, k - 1);

        System.out.println(process);

        int[] arr2 = {4,-1,5,0,1};

        int bfprt = bfprt(arr2, 0, 4, k - 1);

        System.out.println(bfprt);
    }


    public static int bfprt(int[] arr,int L,int R,int index){
        if(L == R){
            return arr[L];
        }

        int pvoit = getMedianOfMedians(arr, L, R);
        int[] partion = partion1(arr, L, R, pvoit);
        if(index >= partion[0] && index <= partion[1]){
            return arr[index];
        }else if (index < partion[0]){
            return bfprt(arr,0,partion[0] - 1,index);
        }else {
            return bfprt(arr,partion[1] + 1,R,index);
        }
    }


    public static int getMedianOfMedians(int[] arr,int L,int R){
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int mArrLen = size / 5 + offset;
        int[] mArr = new int[mArrLen];

        for(int i = 0 ; i < mArrLen ; i++){
            int itemFirst = L + i * 5;
            mArr[i] = getMedian(arr,itemFirst,Math.min(R,itemFirst + 4));
        }
        return bfprt(mArr,0,mArrLen - 1,mArrLen/2);
    }

    public static int getMedian(int[] arr,int L,int R){
        quickSort(arr,L,R);
        return arr[(L+R) / 2];

    }

    public static void quickSort(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        swap(arr,R,L + (int)(Math.random() * (R - L +1)));
        int[] partion = partion(arr, L, R, R);
        quickSort(arr,L,partion[0]-1);
        quickSort(arr,partion[1]+1,R);
    }
    /**
     * index必须在L。。。R 中
     * @param arr
     * @param L
     * @param R
     * @param index
     * @return
     */
    public static int process(int[] arr,int L,int R,int index){
        if(L == R){
            return arr[L];
        }
        int pvoit = L + (int)(Math.random() * (R - L + 1));
        int[] range = partion(arr,L,R,pvoit);
        if(index >=range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return process(arr,L,range[0] - 1,index);
        }else {
            return process(arr,range[1]+1,R,index);
        }

    }

    public static int[] partion1(int[] arr,int L,int R,int pvoit){

        int less = L - 1;
        int more = R+1;
        int cur = L;

        while (cur < more){
            if(arr[cur] < pvoit){
                swap(arr,++less,cur++);
            }else if(arr[cur] > pvoit){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }

    public static int[] partion(int[] arr,int L,int R,int pvoit){

        int less = L - 1;
        int more = R+1;
        int cur = L;

        while (cur < more){
            if(arr[cur] < arr[pvoit]){
                swap(arr,++less,cur++);
            }else if(arr[cur] > arr[pvoit]){
                swap(arr,--more,cur);
            }else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }


    public static void swap(int[] arr,int i ,int j){
        if(i >= arr.length || j >= arr.length){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
