package huawei;

import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {
       // Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        long x = 260;
        long y = 1;
//        while (in.hasNextLong()) { // 注意 while 处理多个 case
//            x = in.nextLong();
//            y = in.nextLong();
//        }
//        in.close();

        long num = Math.max(1,(long)Math.ceil(Math.log10(x / Math.pow(26,y))));
        System.out.println(num);

    }
}
