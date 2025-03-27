package core;

public class CardsLine {

    public static int win1(int[] arr){

        return Math.max(f(arr,0,arr.length - 1),s(arr,0,arr.length-1));
    }

    public static int f(int[] arr,int L,int R){
        if(L ==R){
            return arr[L];
        }

        return Math.max(arr[L] + s(arr,L+1,R),arr[R] + s(arr,L,R - 1));

    }

    public static int s(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        return Math.min(f(arr,L + 1,R),f(arr,L,R - 1));
    }

    public static int win2(int[] arr){
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        for(int i = 0 ; i <n ; i++){
            f[i][i] = arr[i];
        }

        for(int i = 1 ; i < n; i++){
            int L = 0;
            int R = i;
            while(L < n && R < n){
                f[L][R] = Math.max(arr[L] + s[L+1][R],arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R],f[L][R - 1]);
                L++;
                R++;
            }
        }

        return Math.max(f[0][arr.length - 1],s[0][arr.length-1]);
    }

    public static void main(String[] args) {
        int[] arr = {4,7,9,5};
        int i = win1(arr);
        System.out.println(i);

        System.out.println(win2(arr));
    }
}
