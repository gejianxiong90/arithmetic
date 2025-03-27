public class MaxValueOnBag {


    public static void main(String[] args) {

        int[] values = {2,3,5};
        int[] weight = {8,1,7};
        int bag = 15;

        int i4 = dpWay(weight, values, bag);

        System.out.println(i4);

        int i = maxValue2(values, weight, 0, 0, bag);
        System.out.println(i);


        int i1 = maxValueByRest(values, weight, 0, 10);
        System.out.println(i1);

        int i2 = maxValueByRest2(values, weight, 0, 10);
        System.out.println(i2);

        int i3 = maxValOnBag(values, weight, 0, 10);
        System.out.println(i3);


    }

    public static int maxVal(int[] values,int[] weights,int index,int alreadW,int bag){
        if(alreadW > bag){
            return -1;
        }
        if(index == weights.length){
            return 0;
        }
        int p = maxVal(values, weights, index + 1, 0, bag);
        int pNext = maxVal(values, weights, index + 1, alreadW + weights[index], bag);
        int p2= -1;
        if(pNext != -1){
            p2 = pNext + values[index];
        }
        return Math.max(p,p2);
    }





















    public static int maxValue2(int[] value,int [] weight,int index,int alreadW,int bag){
        if(alreadW > bag){
            return -1;
        }
        if(index == weight.length){
            return 0;
        }
        int p = maxValue2(value, weight, index + 1, alreadW, bag);
        int pNext = maxValue2(value, weight, index + 1, alreadW + weight[index], bag);
        int p2 = -1;
        if(pNext != -1){
            p2 = value[index] + pNext;
        }
        return Math.max(p,p2);
    }



    public static int maxValueByRest(int[] values,int[] weights,int index,int rest){
        if(rest < 0){
            return -1;
        }

        if(index == weights.length){
            return 0;
        }
        int p = maxValueByRest(values,weights,index + 1,rest);
        int pNext = maxValueByRest(values,weights,index + 1,rest - weights[index]);
        int p2 = -1;
        if(pNext != -1){
            p2 = values[index]+pNext;
        }
        return Math.max(p,p2);
    }


























    public static int maxValueByRest2(int[] values,int[] weights,int index,int rest){
       if(rest < 0){
           return -1;
       }

       if(index == weights.length){
           return 0;
       }

       int p1 = maxValueByRest2(values,weights,index +1,rest);
       int p2Next = maxValueByRest2(values,weights,index+1,rest-weights[index]);
       int p2 = -1;
       if(p2Next != -1){
           p2 = values[index] + p2Next;
       }
        return Math.max(p1,p2);
    }


























    public static int maxValOnBag(int[] values,int[] weights,int index,int rest){
        if(rest < 0){
            return -1;
        }
        if(weights.length == index){
            return 0;
        }

        int p1 = maxValOnBag(values,weights,index+1,rest);
        int p2Next = maxValOnBag(values,weights,index+1,rest-weights[index]);
        int p2 = -1;
        if(p2Next != -1){
            p2 = values[index]+p2Next;
        }
        return Math.max(p1,p2);
    }


    public static int dpWay(int[] w,int[] v,int bag){
        int N = w.length;
        int[][] dp = new int[N+1][bag + 1];
        for(int index = N -1 ; index >=0;index--){
            for(int rest = 0;rest <= bag;rest++){
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if(rest - w[index] >=0){
                    p2= v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }

}
