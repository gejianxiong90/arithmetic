package core;

public class Hanoi {

    public void leftToRight(int n){
        if(n == 1){
            System.out.println("left mov "+ n + "to right");
            return;
        }
        leftToMid(n-1);
        System.out.println("left mov "+ n + "to right");
        midToRight(n-1);
    }

    public void leftToMid(int n){
        if(n == 1){
            System.out.println("left mov "+ n + "to mid");
            return;
        }
        leftToRight(n-1);
        System.out.println("left mov "+ n + "to mid");
        rightToMid(n-1);

    }

    public void rightToMid(int n){
        if(n == 1){
            System.out.println("right mov " + n + "to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("right mov " + n + "to mid");
        leftToMid(n-1);
    }

    public void rightToLeft(int n){
        if(n == 1){
            System.out.println("right mov " + n + "to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("right mov "+ n + "to left");
        midToLeft(n-1);

    }

    public void midToLeft(int n){
        if(n == 1){
            System.out.println("mid mov "+ n + "to left");
            return;
        }
        midToRight(n-1);
        System.out.println("mid mov "+ n + "to left");
        rightToLeft(n-1);
    }

    public void midToRight(int n){
        if(n == 1){
            System.out.println("mid mov " + n + "to right");
            return;
        }
        midToLeft(n-1);
        System.out.println("mid mov " + n + "to right");
        leftToRight(n-1);
    }

    public void process(int n,String from,String other,String to){
        if(n == 1){
            System.out.println(from + " mov " + n + " to "+ to);
        }else {
            process(n - 1,from,to,other);
            System.out.println(from + " mov " + n + " to " + to);
            process(n-1,other,from,to);
        }
    }


    public static void main(String[] args) {
        Hanoi hanoi = new Hanoi();
      //  hanoi.leftToRight(3);
        hanoi.process(3,"left","other","to");
    }
}
