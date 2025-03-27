package treecode;

public class AoTuPrint {

    public static void main(String[] args){
        printAOTU(1,3,false);
    }

    public static void printAOTU(int i,int deep,boolean aotu){
       if(i > deep){
           return;
       }
        printAOTU(i+1,deep,false);

        System.out.println(i+":"+(aotu ? "凸":"凹"));

        printAOTU(i+1,deep,true);
    }
}
