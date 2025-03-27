package sz.code16;

/**
 * 约瑟夫环问题
 *
 * 给定单环型链表，和m表示几号（比如3），循环数，数到3就杀掉，下一个人从1开始继续数
 * 问：最后会留下谁
 */
public class JosephusProblem {

    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            this.value = data;
        }
    }


    public static Node josephusKill(Node head,int m){
        if(head == null || head.next == head || m < 1){
            return head;
        }
        Node cur = head.next;
        int size = 1;
        // 求出节点数量
        while (cur != head){
            size++;
            cur = cur.next;
        }
        int live = getLive(size,m);
        while (--live != 0){
            head = head.next;
        }
        head.next = head;
        return head;

    }

    // 现在一共有i个节点，数到m就杀死节点，最终会活下来的节点，请返回它在有i个节点时候的编号
    // 旧
    // getLive(N, m)
    public static int getLive(int i, int m){
        if(i == 1){
            return 1;
        }
        // getLive(i - 1, m)   长度为i-1时候，活下来的编号
        return (getLive(i-1,m) + m - 1) % i + 1;
    }


    public static int getLive2(int i, int[] mArr,int index){
        if(i == 1){
            return 1;
        }
        int m = mArr[index % mArr.length];
        // getLive(i - 1, m)   长度为i-1时候，活下来的编号
        return (getLive2(i-1,mArr,index +1) + m - 1) % i + 1;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main(String[] args) {
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill(head2, 3);
        printCircularList(head2);
    }
}
