package sz.code6;

/**
 * 给定一个完全二叉树，返回一共有几个节点，复杂度小于N
 */
public class NodeCount {


    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(){}
    }


    public static int bs(Node node,int level,int h){
        if(level == h){
            return 1;
        }
        if(mostLeftLevel(node.right,level+1) == h){
            return (1<< (h - level)) + bs(node.right,level+1,h);
        } else {
            return (1 << (h - level - 1)) + bs(node.left,level + 1,h);
        }
    }


    public static int nodeNum(Node head){
        if(head == null){
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }


    public static int mostLeftLevel(Node head, int level){
        if(head == null){
            return level - 1;
        }
        level++;
        return mostLeftLevel(head.left,level);
    }

    public static void main(String[] args) {
        Node head = bulid();

        int i = mostLeftLevel(head, 1);

        int i1 = nodeNum(head);

        System.out.println(i);
        System.out.println(i1);
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
      //  right.right = rr;
        return head;
    }
}
