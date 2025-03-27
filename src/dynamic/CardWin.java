package dynamic;

public class CardWin {

    public static void main(String[] args) {
        int[] a = {2,5,1,6};
        int i = win1(a);
        System.out.println(i);
        int i1 = win2(a);
        System.out.println(i1);
    }

    public static int win2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

        for(int i = 0;i<N;i++){
            f[i][i] = arr[i];
        }

        for(int j = 1;j < N;j++){
            int L = 0;
            int R = j;
            while(L < N && R < N){

                f[L][R] = Math.max(arr[L]+s[L+1][R],arr[R]+s[L][R-1]);

                s[L][R] = Math.min(f[L+1][R],f[L][R-1]);

                L++;
                R++;
            }
        }

        return Math.max(f[0][N-1],s[0][N-1]);
    }

    public static int win1(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
       return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length -1));
    }

    public static int f(int[] arr,int l ,int r){
        if(l == r){
            return arr[l];
        }
        return Math.max(arr[l]+s(arr,l+1,r),
                         arr[r] + s(arr,l,r-1));
    }

    public static int s(int[] arr,int l,int r){
        if(l==r){
            return 0;
        }
        return Math.min(
                f(arr,l+1,r),
                f(arr,l,r-1)
        );
    }
}
