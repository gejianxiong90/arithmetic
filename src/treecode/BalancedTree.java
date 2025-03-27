package treecode;


public class BalancedTree {


    public static void main(String[] args){
        Info process = process(initData());
        System.out.println(process.height + "   "+process.isBalanced);

    }


    public static Info process(Node x){
        if(x == null){
            return new Info(true,0);
        }
        Info processLeft = process(x.left);
        Info processRight = process(x.right);

        int height = Math.max(processLeft.height,processRight.height) + 1;

        boolean isBalanced = true;

        if(!processLeft.isBalanced || !processRight.isBalanced || Math.abs(processLeft.height - processRight.height) >1 ){
            isBalanced = false;
        }
        return new Info(isBalanced,height);
    }


    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean isBalanced,int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }


    static class Node{
        Node left;
        Node right;
        int value;

        public Node(int val){
            this.value = val;
        }
    }

    public static Node initData(){
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node.left = node2;
        node.right = node3;
        node2.left = new Node(4);
        node2.right = new Node(5);
        node3.left = new Node(6);
        node3.right = new Node(7);
        return node;

    }
}
