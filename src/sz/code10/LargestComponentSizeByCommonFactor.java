package sz.code10;

import java.util.HashMap;

/**
 * 一个数组中，两个数的公共因子大于1的，认为这两个数之间有通路，返回数组中有多少独立的域；
 *
 * 解：利用并查集连通
 *     连通的依据是什么？
 *     1. 最大公约数
 *     2. 1到根号下num，其中相乘等于num的数（除了1）
 */

public class LargestComponentSizeByCommonFactor {


    public static void main(String[] args) {
        int[] arr = {4,5,3,7,9};
        int i = largestComponentSize1(arr);

        int i1 = largestComponentSize2(arr);

        System.out.println(i);
        System.out.println(i1);
    }


    public static int largestComponentSize1(int[] arr){
        int N = arr.length;
        UnionFind set = new UnionFind(N);
        for(int i = 0 ; i < N ;i++){
            for(int j = i + 1;j < N ; j++){
                if(gcd(arr[i],arr[j]) != 1){
                    set.union(i,j);
                }
            }
        }

        return set.maxSize();
    }

    public static int largestComponentSize2(int[] arr){
        int N = arr.length;
        UnionFind unionFind = new UnionFind(N);
        HashMap<Integer, Integer> fatorsMap = new HashMap<>();
        for(int i=0 ; i< N ;i++){
            int num = arr[i];
            int limit = (int)Math.sqrt(num);
            for(int j = 1 ; j <= limit ;j++){
                if(num % j == 0){
                    if( j != 1){
                        if(!fatorsMap.containsKey(j)){
                            fatorsMap.put(j,i);
                        }else {
                            unionFind.union(fatorsMap.get(j),i);
                        }
                    }
                    int other = num / j;
                    if(other != 1){
                        if(!fatorsMap.containsKey(other)){
                            fatorsMap.put(other,i);
                        }else {
                            unionFind.union(fatorsMap.get(other),i);
                        }
                    }
                }
            }
        }

        return unionFind.maxSize();
    }


    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a%b);
    }

    public static class UnionFind{
        private int[] parnets;
        private int[] sizes;
        private int[] help;

        public UnionFind(int N){
            parnets = new int[N];
            sizes = new int[N];
            help = new int[N];

            for(int i = 0; i < N ; i++){
                parnets[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize(){
            int ans = 0;
            for(int size : sizes){
                ans = Math.max(ans,size);
            }
            return ans;
        }

        private int find(int i){
            int hi = 0;
            while(i != parnets[i]){
                help[hi++] = i;
                i = parnets[i];
            }
            for(hi--;hi >=0 ; hi--){
                parnets[help[hi]] = i;
            }
            return i;
        }

        public void union(int i,int j){
            int f1 = find(i);
            int f2 = find(j);
            if(f1 != f2){
                int big = sizes[f1] >= sizes[f2] ? f1 : f2;
                int small = big == f1 ? f2 : f1;
                parnets[small] = big;
                sizes[big] = sizes[f1] + sizes[f2];
            }
        }
    }
}
