package core;

public class PrintStart {

    public static void main(String[] args) {
        printStart(16);
    }

    public static void printStart(int N){
        int tr = 0;
        int tc = 0;
        int zr = N - 1;
        int zc = N - 1;
        String[][] ans = new String[N][N];
        for(int i = 0 ; i < ans.length; i++){
            for(int j = 0 ; j < ans[0].length;j++){
                ans[i][j] = " ";
            }
        }

        while(tr < zr){
            process(ans,tr,tc,zr,zc);
            tr += 2;
            tc += 2;
            zr -= 2;
            zc -= 2;
        }

        for(int i = 0 ; i < ans.length; i++){
            System.out.println();
            for(int j = 0 ; j < ans[0].length;j++){
                System.out.print(ans[i][j]+" ");
            }
        }
    }

    public static void process(String[][] ans,int tr,int tc,int zr,int zc){


        for(int i = tc ; i < zc ; i++){
            ans[tr][i] = "*";
        }
        for(int j = tr ; j <= zr ; j++){
            ans[j][zc] = "*";
        }
        for(int k = zc ; k > tc + 1 ; k--){
            ans[zr][k] = "*";
        }
        for(int z = zr ; z > tr + 1;z--){
            ans[z][tc+1] = "*";
        }
    }
}
