package treecode;

public class MaxSubBSTSize {
    public static void main(String[] args) {
        Node node = Node.initBSTData();
        Info process = process(node);

        System.out.println(process);

    }

    public static class Info{
       public int maxSubBSTSize;
       public boolean isAllBST;
       public int max;
       public int min;

        public Info(int maxSubBSTSize, boolean isAllBST, int max, int min) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "maxSubBSTSize=" + maxSubBSTSize +
                    ", isAllBST=" + isAllBST +
                    ", max=" + max +
                    ", min=" + min +
                    '}';
        }
    }

    public static Info process(Node x){
        if(x == null){
            return null;
        }
        Info processLeft = process(x.left);
        Info processRight = process(x.right);
        int max = x.value;
        int min = x.value;
        int maxSubBSTSize = 0;
        boolean isAllBST = false;
        if(processLeft != null){
            max = Math.max(max,processLeft.max);
            min = Math.min(min,processLeft.min);
        }
        if (processRight != null){
            max = Math.max(max,processRight.max);
            min = Math.min(min,processRight.min);
        }

        if((processLeft == null ? true :processLeft.isAllBST)&&
                (processRight == null ? true : processRight.isAllBST)&&
                (processLeft == null ? true : processLeft.max < x.value)&&
                (processRight == null ? true : processRight.min > x.value)){
            isAllBST = true;
            maxSubBSTSize =(processLeft == null ? 0 : processLeft.maxSubBSTSize) +
                    (processRight == null ? 0 : processRight.maxSubBSTSize) + 1;
        }

    return new Info(maxSubBSTSize,isAllBST,max,min);

    }
}
