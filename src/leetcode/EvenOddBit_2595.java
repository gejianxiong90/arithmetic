package leetcode;

public class EvenOddBit_2595 {

    public static void main(String[] args) {
        int[] ints = evenOddBit(2);
        System.out.println(ints);
    }

    public static int[] evenOddBit(int n) {
        int[] res = new int[2];
        int even = 0;
        int odd = 0;
        for(int i = 0 ; i < 32 ; i++){
            boolean flag = false;
            if(((n >> i) & 1) == 1){
                flag = true;
            }
            if(flag){
                if(i % 2 == 0){
                    even++;
                }else{
                    odd++;
                }
            }
        }
        res[0] = even;
        res[1] = odd;
        return  res;
    }
}
