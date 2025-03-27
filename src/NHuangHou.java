public class NHuangHou {

    public static void main(String[] args) {

        System.out.println(num2(8));
        System.out.println(num3(8));
    }

    // 不要超过32皇后问题
    public static int num2(int n){
        if(n < 1 || n > 32){
            return 0;
        }
        int limit = n == 32 ? -1 : (1<<n)-1;
        return process2(limit,0,0,0);
    }

    // limit划定了问题的规模 --》固定
    // colLimit 列的限制，1位置不能放皇后，0位置可以
    // leftDiaLim左斜线的限制，1的位置不能放皇后，0位置可以
    // rightDiaLim 右斜线的限制，1位置不能放皇后，0位置可以
    public static int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim){
        if(colLim == limit){
            return 1;
        }

        // 所有可以放皇后的位置，都在pos上，用1标识
        // colLim | leftDiaLim | rightDiaLim  --> 总限制
        //~(colLim | leftDiaLim | rightDiaLim)  --> 左侧的一坨0干扰，右侧每个1，可尝试
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while(pos != 0){
            // 其取出pos中，最右侧的1来，剩下位置都是0
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,colLim|mostRightOne,(leftDiaLim|mostRightOne) << 1,(rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }




   public static int num3(int num){
        if(num < 1 || num > 32){
            return 0;
        }
        int limit = num == 32 ? -1 : (1<<num) -1;
        return process3(limit,0,0,0);
   }

    public static int process3(int limit,int colLimit,int rightLimit,int leftLimit){
        if(limit == colLimit){
            return 1;
        }
        int pos = limit & ~(colLimit | leftLimit | rightLimit);
        int mostRightOne = 0;
        int res = 0;
        while(pos != 0){
            mostRightOne = (~pos+1) & pos;
            pos = pos - mostRightOne;
            res += process3(limit,colLimit | mostRightOne,(rightLimit | mostRightOne) >>> 1,(leftLimit | mostRightOne) << 1);
        }
        return res;
    }

}
