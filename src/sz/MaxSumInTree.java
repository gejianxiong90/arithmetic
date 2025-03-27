package sz;

public class MaxSumInTree {


    public static  Integer maxsum = Integer.MIN_VALUE;


    public static void max(Node node,int i){
        if(node.left == null && node.right == null){
            maxsum = Math.max(maxsum,node.value + i);
        }
        int sum = node.value + i;
        if(node.left != null){
            max(node.left, sum);
        }

        if(node.right != null){
            max(node.right,sum);
        }
    }


    public static int max2(Node node){
        if(node.left == null && node.right == null){
            return node.value;
        }
        int maxSum = Integer.MIN_VALUE;
        if(node.left != null){
            maxSum = max2(node.left );
        }

        if(node.right != null){
            maxSum = Math.max(maxSum,max2(node.right));
        }

        return maxSum + node.value;
    }


    public static void main(String[] args) {
        for(int j = 0 ; j < 10 ; j++){
            Node bulid = bulid();
            max(bulid,0);
            int i = max2(bulid);

            System.out.println(maxsum+"---->"+i);
            maxsum = Integer.MIN_VALUE;
        }


    }

    private static Node bulid(){
        Node head = new Node();

        head.value = (int)(Math.random() * 10);

        Node left = new Node();
        left.value = (int)(Math.random() * 10);

        Node right = new Node();
        right.value = (int)(Math.random() * 10);

        head.left = left;
        head.right = right;


        Node ll = new Node();
        ll.value = (int)(Math.random() * 10);

        Node lr = new Node();
        lr.value = (int)(Math.random() * 10);

        Node rl = new Node();
        rl.value = (int)(Math.random() * 10);

        Node rr = new Node();
        rr.value = (int)(Math.random() * 10);


        left.left = ll;
        left.right = lr;
        right.left = rl;
        right.right = rr;
        return head;
    }


    public static int maxFromTo(Node from,Node to){
        if(from == to){
            return to.value;
        }
        int maxSum = Integer.MIN_VALUE;
        if (from.left != null){
            maxSum = maxFromTo(from.left,to);
        }

        if(from.right != null){
            maxSum = Math.max(maxSum,maxFromTo(from.right,to));
        }
        return Math.max(from.value,from.value + maxSum);
    }

    public static class Node{
        int value;
        Node left;
        Node right;
    }



    public static int maxSum2(Node head){

        if(head == null){
            return 0;
        }
        return f(head).allTreeMaxSum;

    }


    // 从上往下走任意节点的最大路径和
    // 1) X 无关的时候，    1.左树上的整体最大路径和     2.右树上的整体最大路径和
    // 2）X 有关的时候      3.x自己      4.x往左走       5.x往右走
    public static Info f(Node x){
        if(x == null){
            return null;
        }
        Info left = f(x.left);
        Info right = f(x.right);
        int p1 = Integer.MIN_VALUE;
        if(x.left != null){
            p1 = left.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if(x.right != null){
            p2 = right.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if(x.left != null){
            p4 = left.fromHeadMaxSum + x.value;
        }
        int p5 = Integer.MIN_VALUE;
        if(x.right != null){
            p5 = left.fromHeadMaxSum + x.value;
        }

        int allTreeMaxSum = Math.max(Math.max(p1,Math.max(p2,p3)),Math.max(p4,p5));
        int fromHeadMaxSum = Math.max(p3,Math.max(p4,p5));

        return new Info(allTreeMaxSum,fromHeadMaxSum);
    }

    // 任意节点的最大路径和
    // 1) X 无关的时候，    1.左树上的整体最大路径和     2.右树上的整体最大路径和
    // 2）X 有关的时候      3.x自己      4.x往左走       5.x往右走    6.以x为桥梁，往左右两边走
    public static Info f2(Node x){
        if(x == null){
            return null;
        }
        Info left = f2(x.left);
        Info right = f2(x.right);
        int p1 = Integer.MIN_VALUE;
        if(x.left != null){
            p1 = left.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if(x.right != null){
            p2 = right.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if(x.left != null){
            p4 = left.fromHeadMaxSum + x.value;
        }
        int p5 = Integer.MIN_VALUE;
        if(x.right != null){
            p5 = left.fromHeadMaxSum + x.value;
        }

        int p6 = Integer.MIN_VALUE;
        if(x.left != null && x.right != null){
            p6 = left.fromHeadMaxSum + right.fromHeadMaxSum + x.value;
        }

        int allTreeMaxSum = Math.max(Math.max(p1,Math.max(p2,p3)),Math.max(p4,Math.max(p5,p6)));
        int fromHeadMaxSum = Math.max(p3,Math.max(p4,p5));

        return new Info(allTreeMaxSum,fromHeadMaxSum);
    }


    public static int bigShuai(Node head){
        if(head.left == null && head.right == null){
            maxsum = Math.max(maxsum,head.value);
            return head.value;
        }
        int nextMax = Integer.MIN_VALUE;
        if(head.left != null){
           nextMax =  bigShuai(head.left);
        }
        if(head.right != null){
            nextMax = Math.max(nextMax,bigShuai(head.right));
        }

        int ans = head.value + nextMax;
        maxsum =Math.max(maxsum,ans);
        return ans;
    }


    public static class Info{
        public int allTreeMaxSum;
        public int fromHeadMaxSum;

        public Info(int all,int headMax){
            allTreeMaxSum = all;
            fromHeadMaxSum = headMax;
        }
    }
}
