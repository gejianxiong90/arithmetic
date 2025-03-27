package core;

public class Node {

    public int value;

    public Node left;

    public Node right;


    public Node parent;

    public Node(int value){
        this.value = value;
    }


    public static Node generateTree(){
        Node head = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        Node leftL = new Node(4);
        Node leftR = new Node(5);
        Node rightL = new Node(6);
        Node rightR = new Node(7);

        head.left = left;
        head.right = right;

        left.left = leftL;
        left.right = leftR;

        right.left = rightL;
        right.right = rightR;

        return head;
    }
}
