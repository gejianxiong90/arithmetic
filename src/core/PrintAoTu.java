package core;

public class PrintAoTu {


    public static void print(int N){

        printAoTu(1,N,true);
    }

    public static void printAoTu(int i,int N,boolean down){
        if(i > N){
            return;
        }
        printAoTu(i + 1, N,true);
        System.out.println(down ? "凹" : "凸");
        printAoTu(i+1,N,false );
    }

    public static void main(String[] args) {
        print(3);
    }
}
