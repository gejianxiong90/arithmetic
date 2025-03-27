package sz;

public class MakeNo {
    /**
     *
     *  给定任意一个长度，生成这个长度的数组
     *  i < j < k 满足   i + k ！= 2*j
     */


    public static int[] makeNo(int size){
        if(size == 1){
            return new int[]{1};
        }
        int halfSize = (size + 1) /2 ;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for(;index < halfSize;index++){
            ans[index] = base[index] * 2 -1;
        }
        for(int i = 0; index < size; index++,i++){
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    public static int[] makeNo2(int size){
        if(size == 1){
            return new int[]{1};
        }

        int halfSize = (size + 1) >> 1;
        int[] ints = makeNo(halfSize);
        int[] res = new int[size];
        int index = 0;
        for(;index < halfSize;index++){
            res[index] = ints[index] * 2 - 1;
        }
        for(int i = 0 ; index < size; index++,i++){
            res[index] = ints[i] * 2;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] ints = makeNo(5);
        for (int i : ints){
            System.out.println(i);
        }
    }





}
