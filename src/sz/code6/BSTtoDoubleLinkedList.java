package sz.code6;

// 本题测试链接 : https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

public class BSTtoDoubleLinkedList {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }

        public Node(){

        }
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end){
            this.start = start;
            this.end = end;
        }
    }

    // 提交下面的代码
    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }
        Info allInfo = process(head);
//        allInfo.end.right = allInfo.start;
//        allInfo.start.left = allInfo.end;
        return allInfo.start;
    }


     static int count = 0 ;
    public static Info process(Node x){
        count++;
        if(x == null){
            return new Info(null,null);
        }

        Info lInfo = process(x.left);
        Info rInfo = process(x.right);

        if(lInfo.end != null){ // 其实就是左树
            lInfo.end.right = x;
        }
        if(rInfo.start != null){ // 其实就是右树
            rInfo.start.left = x;
        }
        x.left = lInfo.end;
        x.right = rInfo.start;

        return new Info(lInfo.start != null ? lInfo.start : x,
                rInfo.end != null ? rInfo.end : x);
    }


    public static Info process2(Node head){
        if(head == null){
            return new Info(null,null);
        }

        Info l = process(head.left);
        Info r = process(head.right);

        if(l.end != null){
            l.end.right = head;
        }
        if(r.start != null){
            r.start.left = head;
        }
        head.left = l.end;
        head.right = r.start;

        return new Info(l.start == null ? null : l.start,r.end == null ? null : r.end);
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

    public static void main(String[] args) {

        Node head = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);

        head.left = left;
        head.right = right;

        Node node = treeToDoublyList(head);

        System.out.println(node);

        System.out.println(count);
    }
}
