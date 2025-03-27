package sz;

public class KthMinPair {



    public static int[] kthMinPair(int[] arr,int k){
        int N = arr.length;
        if(k > N * N){
            return null;
        }
        // 在无序数组中找到第K小的树，返回值
        // 第K小
        int fristNum = getMinKth(arr, (k - 1) / N);
        // 第一维数字
        int lessFristNumSize = 0;
        int fristNumSize = 0;
        for(int i = 0 ; i < arr.length;i++){
            if(arr[i] < fristNum){
                lessFristNumSize++;
            }
            if(arr[i] == fristNumSize){
                fristNumSize++;
            }
        }

        int rest = k - lessFristNumSize * N;
        return new int[]{
                fristNum,getMinKth(arr,(rest - 1)/fristNumSize)
        };
    }


    public static int getMinKth(int[] arr,int index){
        int L = 0;
        int R = arr.length-1;
        int pivot = 0;
        int [] range = null;
        while(L < R){
            pivot = arr[L + (int)(Math.random()*(R-L+1))];
            range = partition(arr,L,R,pivot);
            if(index < range[0]){
                R = range[0] - 1;
            }else if (index > range[1]){
                L = range[1] + 1;
            }else{
                return pivot;
            }
        }
        return arr[L];
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,5,4};
        int minKth = getMinKth(arr, 3);
        int minKth1 = getMinKth1(arr, 3);
        System.out.println(minKth);
        System.out.println(minKth1);
    }

    public static int[] partition(int[] arr,int L,int R ,int pivot){
        int less = L - 1 ;
        int more = R + 1;
        int cur = L;
        while(cur < more){
            if(arr[cur] < pivot){
                swap(arr,++less,cur++);
            }else if(arr[cur] > pivot){
                swap(arr,cur,--more);
            }else{
                cur++;
            }
        }
        return new int[]{less+1,more -1};
    }



    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



    public static int getMinKth1(int[] arr,int index){
        if(index > arr.length){
            throw new RuntimeException("invalid params");
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R){
            int pivot = L + (int)(Math.random() * (R - L + 1));
            int[] range = partition1(arr, L, R, pivot);
            if(index > range[1]){
                L = range[1]+ 1;
            }else if(index < range[0]){
                R = range[0] - 1;
            }else {
                return pivot;
            }
        }
        return arr[L];
    }

    private static int[] partition1(int[] arr,int L,int R,int pivot){
        int less = L - 1;
        int more = R + 1;
        int cur = L;

        while (cur < more){
            if(arr[cur] < pivot){
                swap(arr,++less,cur++);
            }else if(arr[cur] > pivot){
                swap(arr,cur,--more);
            }else {
                cur++;
            }

        }

        return new int[]{less+1,more -1};
    }
}
