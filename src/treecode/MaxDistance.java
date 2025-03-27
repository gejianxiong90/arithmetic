package treecode;

public class MaxDistance {


    public static void main(String[] args) {
        Info process = process(Node.initData());
        System.out.println(process.height+"  "+process.maxDistance);
    }


    public static class Info{
        public int maxDistance;
        public int height;

        public Info(int maxDistance,int height){
            this.maxDistance =maxDistance;
            this.height = height;
        }
    }
   public static Info maxDis(Node x){
        if(x == null){
            return new Info(0,0);
        }
       Info left = maxDis(x.left);
       Info right = maxDis(x.right);
       int height = Math.max(left.height,right.height) + 1;
       int maxDis = Math.max(Math.max(left.maxDistance,right.maxDistance),left.height + right.height + 1);
       return new Info(maxDis,height);
   }



    public static Info process(Node x){
        if(x == null){
            return new Info(0,0);
        }
        Info processLeft = process(x.left);
        Info processRight = process(x.right);

        int height = Math.max(processLeft.height,processRight.height) + 1;
        int maxDistance = Math.max(Math.max(processLeft.maxDistance,processRight.maxDistance),
                processLeft.height+processRight.height+1);

        return new Info(maxDistance,height);
    }
}
