package dynamic;

public class XiangQi {

    public static void main(String[] args) {

        int process = process(0, 0, 3, 2, 3);
        System.out.println(process);
    }

    public static int process(int x ,int y , int k , int x1 , int y1 ){
        if( x < 0 || x > 8 || y < 0 || y > 9){
           return 0;
        }
        if(k == 0){
           return (x == x1 && y == y1) ? 1 : 0;
        }

        int process = process(x + 2, y + 1, k - 1, x1, y1);

        return process( x + 1 , y + 2, k -1 ,x1,y1);

    }

}
