public class HappyNum {


    // 华为笔试题
    // 球从500米高空落下，每次弹起一半高，第n次落地时走过的路程是多少？
    // 输入 5
    // 输出 1437.5

    public static void main(String[] args){
        System.out.println(jump(5));
        System.out.println(jump2(5));
    }


    public static float jump(int count){
        float sum = 500;
        float meter = 500;
        while(--count > 0){
                meter = meter/2;
                sum += meter * 2;
        }
        return sum;
    }

    public static float jump2(int count){
        float sum = 500;
        float meter = 500;
        while(--count > 0){
            meter = Float.intBitsToFloat( Float.floatToIntBits(meter) >>> 1);
            sum += meter * 2;
        }
        return sum;
    }


    public static boolean isHappyNum(int i){
        if(i == 1 || i ==0){
            return i == 1;
        }
        int i1 = subNum(i);
        if(i1 == 1){
            return true;
        }else if(i1 < 10){
            return false;
        }else{
            return isHappyNum(i1);
        }
    }


    public static int subNum(int k){
        String s = String.valueOf(k);
        char[] chars = s.toCharArray();

        int z = 0;
        for (int j = 0; j <chars.length ; j++) {
            z +=Integer.valueOf( Character.toString(chars[j])) * Integer.valueOf(Character.toString(chars[j]));
        }
        return z;
    }


}
