package core;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

    public static void main(String[] args) {
        int c = (-1 << 29) | 0;
        int mask = (1 << 29) -1;
        String string = Integer.toBinaryString(c+100);
        String string1 = Integer.toBinaryString(mask);
        System.out.println( string);
        System.out.println( string1);
        System.out.println(Integer.toBinaryString(-1));

        System.out.println( workerCountOf(c +100));
        System.out.println( workerCountOf(c +100));

       // 0 << COUNT_BITS;
        System.out.println( 0 << COUNT_BITS);
    }

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    private static int workerCountOf(int c)  { return c & COUNT_MASK; }

}
