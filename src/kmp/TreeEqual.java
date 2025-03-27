package kmp;

public class TreeEqual {


    public static void main(String[] args) {
        boolean b = containsTree1(generateBigNode(), generateSmallNode());
        System.out.println(b);
    }


    public static Node generateBigNode (){
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        return node;
    }

    public static Node generateSmallNode(){
        Node node1 = new Node(2);
        Node node3 = new Node(4);
        node1.left = node3;
        return node1;
    }

    public static class Node{
        public int value;
        public Node right;
        public Node left;
        public Node(int value){
            this.value = value;
        }
    }
    public static boolean containsTree1(Node big,Node small){
        if(small == null){
            return true;
        }
        if(big == null){
            return false;
        }
        if(isSameStructure(big,small)){
            return true;
        }
        return containsTree1(big.left,small) || containsTree1(big.right,small);
    }

    public static boolean isSameStructure(Node head1,Node head2){
        if(head1 == null && head2 != null){
            return false;
        }
        if(head1 != null && head2 == null){
            return false;
        }
        if(head1 == null && head2 == null){
            return true;
        }
        if(head1.value != head2.value){
            return false;
        }
        return isSameStructure(head1.left,head2.left) && isSameStructure(head1.right,head2.right);
    }
}
