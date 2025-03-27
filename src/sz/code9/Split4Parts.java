package sz.code9;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定一个正数字符数组，切3刀变成4个部分，每个部分累加和相等，如：{2，1,4,3,5，2,1,7，3} 必须切在数字上， 可以切成 2 1 | 3 |2 1 | 3
 * 问给定的数组，能不能切成功
 *
 * 解：遍历一遍数组，用map 记录每个位置的累加和
 *     从左往右尝试第一刀的位置(第一刀切下，左边必须还有5个数，不然后面不好切)，
 *     第一刀确定后根据累加和校验第二刀、第三刀。不满足则尝试下一个第一刀
 *     （第一刀能切下必须满足 左边 a + i + 二刀之前和一刀之后的累加和 X，a == X ） 得出 lefSum * 2 + arr[1];
 *
 *      第二刀必然在第一刀之后 + leftSum累加和的位置
 *
 */
public class Split4Parts {

    public static boolean canSplit2(int[] arr){
        // 能切三到，数组长度至少为7
        if(arr == null || arr.length < 7){
            return false;
        }

       // 累加和， leftSum
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for(int i = 1; i <arr.length;i++){
            map.put(sum,i); // 表示 1左边的累加和，2左边的累加和
            sum += arr[i];
        }
        int lsum = arr[0];// 第一刀左侧的累加和
        // s1第一刀位置，从左往右尝试第一刀，-5 表示第一刀右侧至少有5数才行
        for(int s1 = 1 ; s1 <arr.length - 5;s1++){
            // 第一刀能切下必须满足 左边 a + i + 二刀之前和一刀之后的累加和 X，a == X
            int checkSum = lsum * 2 + arr[s1];
            // map中累加和的坐标
            if(map.containsKey(checkSum)){
                int s2 = map.get(checkSum);
                // 第二刀的位置
                checkSum += lsum + arr[s2];
                if(map.containsKey(checkSum)){
                    //第三刀的位置
                    int s3 = map.get(checkSum);
                    // 第三刀最后一个部分校验
                    // 加上最后一个部分是不是等于总累加和
                    if(checkSum + arr[s3] + lsum == sum){
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }


    public static void main(String[] args) {
        int[] arr = {2,1,4,3,5,2,1,7,3};

        boolean b = canSplit2(arr);
        System.out.println(b);
    }
}
