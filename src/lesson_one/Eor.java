package lesson_one;

public class Eor {

    public static void main(String[] args) {
        //0000 0101
        //0000 0110

        //0000 0011;   eor
        //1111 1101   ~eor + 1
        //0000 0001
    //   int i =  5 ^ 6 ;
    //    System.out.println(i);
    //   int rightOne = i & (~i + 1);

        printOne(6);
     //   getTwo(arr);
    }


    public static int[] arr = {1,2,5,3,1,2,6,3};


    public static void getTwo(int [] arr){

        int eor = 0;

        for(int i : arr){
            eor ^= i;
        }

        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for( int j : arr ){
            if((j & rightOne) == rightOne){
                onlyOne ^= j;
            }
        }

        System.out.println(onlyOne);
        System.out.println(onlyOne ^ eor);
    }


    public static void printOne(int i){

        while( i > 0 ){

           int rightOne =  i & (~i + 1);
            System.out.println(rightOne);
           i ^=  rightOne;
          //  System.out.println(i);
        }
    }
}
