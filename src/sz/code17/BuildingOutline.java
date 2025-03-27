package sz.code17;

import java.util.*;

public class BuildingOutline {

    public static class Op{
        public int x; // x轴上的值
        public boolean isAdd; // 标识加减
        public int h; // 高度


        public Op(int x,boolean isAdd,int h){
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class OpComparator implements Comparator<Op>{

        @Override
        public int compare(Op o1, Op o2) {
            if(o1.x != o2.x){
                return o1.x - o2.x;
            }
            if(o1.isAdd != o2.isAdd){
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        }
    }

    // 全部流程的主方法
    // [s,e,h]
    // [s,e,h]
    // { {1,5,3} , {6,8,4}  .. ...  ...    }
    public static List<List<Integer>> buildingOutline(int[][] matrix){
        int N = matrix.length;
        Op[] ops = new Op[N << 1];

        for(int i = 0 ; i < N ; i++){
            ops[i << 1] = new Op(matrix[i][0],true,matrix[i][2]);
            ops[i << 1 + 1 ] = new Op(matrix[i][1],false,matrix[i][2]);
        }

        Arrays.sort(ops,new OpComparator());

        // TreeMap就是java中的红黑树结构，直接当作有序表来使用
        // key  某个高度  value  次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        // key   x点，   value 最大高度
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();

        for(int i = 0 ; i < ops.length ; i++){
            Op op = ops[i];
            if(op.isAdd){
                if(mapHeightTimes.containsKey(op.h)){
                    mapHeightTimes.put(op.h,mapHeightTimes.get(op.h)+1);
                }else{
                    mapHeightTimes.put(op.h,1);
                }
            }else {
                if(mapHeightTimes.get(op.h) == 1){
                    mapHeightTimes.remove(op.h);
                }else{
                    mapHeightTimes.put(op.h,mapHeightTimes.get(op.h) - 1);
                }
            }
            if(mapHeightTimes.isEmpty()){
                mapXHeight.put(op.x,0);
            }else {
                mapXHeight.put(op.x,mapHeightTimes.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int preH = 0;
        for(Map.Entry<Integer,Integer> entry : mapXHeight.entrySet()){
            Integer curX = entry.getKey();
            Integer curMaxHeight = entry.getValue();
            if(preH != curMaxHeight){
                if(preH != 0){
                   res.add(Arrays.asList(start,curX,curMaxHeight));
                }
                start = curX;
                preH = curMaxHeight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE-1));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE+1));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

        System.out.println((Integer.MIN_VALUE - 1) > 0 );

       char ch = '3';
        Integer integer = Integer.valueOf(String.valueOf(ch));
        System.out.println(integer);

        StringBuilder sb = new StringBuilder();
        StringBuilder reverse = sb.reverse();
    }
}
