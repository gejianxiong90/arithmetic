package core;

public class ConvertNum {


    public static int process(char[] chars,int index){
        if(index == chars.length){
            return 1;
        }
        if(chars[index] == '0'){
            return 0;
        }
        int res = 0;
        if(chars[index] == '1'){
            res += process(chars,index+1);
            if(index + 1 < chars.length){
                res += process(chars,index + 2);
            }
            return res;
        }
        if(chars[index] == '2'){
            res += process(chars,index + 1);
            if(index + 1 <chars.length && chars[index + 1] <= '6'){
                res += process(chars,index + 2);
            }
            return res;
        }
        return process(chars,index + 1);
    }


    public static int bag(int[] weights,int[] values,int bag,int index,int wei){
        if(wei > bag){
            return -1;
        }
        if(index == weights.length){
            return 0;
        }
        int p1 = bag(weights,values,bag,index+1,wei);
        int p2Next = bag(weights,values,bag,index + 1,wei + weights[index]);
        int p2 = -1;
        if(p2Next != -1){
            p2 = values[index] + p2Next;
        }

        return Math.max(p1,p2);
    }


    public static int bag(int[] w,int[] v,int index,int rest){
        if(rest < 0){
            return -1;
        }
        if(index == w.length){
            return 0;
        }
        int p1 = bag(w,v,index+1,rest);
        int p2Next = bag(w,v,index+1,rest - w[index]);
        int p2 = -1;
        if(p2Next != -1){
            p2 = v[index] + p2Next;
        }
        return Math.max(p1,p2);
    }

    public static int bagDp(int[] w,int[] v,int bag){
        int n = w.length;
        int[][] dp = new int[n+1][bag + 1];

        for(int index = n-1; index >= 0 ; index-- ){
            for(int rest = 0 ; rest <= bag ; rest++){
                int p1 = dp[index+1][rest];
                int p2 = 0 ;
                if(rest - w[index] >= 0 ){
                    int p2Next = dp[index+1][rest] - w[index];
                    p2 = v[index] + p2Next;
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag-1];
    }
}
