package treecode;

public class IsCbt {


    public static void main(String[] args) {
        Node node = Node.initBSTData();
        Node node1 = Node.initData();

        Info process = process(node);
        Info process1 = process(node1);

        System.out.println(process);
        System.out.println(process1);

    }

    public static class Info{
        boolean isFull;
        boolean isCbt;
        int height;

        public Info(boolean isFull,boolean isCbt,int height){
            this.isFull = isFull;  // 是不是满二叉树
            this.isCbt = isCbt;    // 是不是完全二叉树
            this.height = height;  // 高度
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isFull=" + isFull +
                    ", isCbt=" + isCbt +
                    ", height=" + height +
                    '}';
        }
    }

    public static Info process(Node x){
        if(x == null){
            return new Info(true,true,0);
        }
        Info left = process(x.left);
        Info right = process(x.right);

        int height = Math.max(left.height,right.height) + 1;

        boolean isFull = left.isFull && right.isFull && left.height == right.height;

        boolean isCbt = false;

        if(isFull){
           isCbt = true;
        }else{
            if(left.isCbt && right.isCbt){
                if(left.isCbt && right.isFull && left.height - right.height == 1){
                    isCbt = true;
                }else if(left.isFull && right.isCbt && left.height == right.height){
                    isCbt = true;
                }else if(left.isFull && right.isFull && left.height - right.height == 1){
                    isCbt = true;
                }
            }
        }

        return new Info(isFull,isCbt,height);
    }

}
