public class Test {

    /**
     * 删除单向链表的倒数第N个元素，返回头节点
     */


    public Node process(Node head,int n){
        Node reHead = revise(head);

        Node cur = reHead;
        Node pre = null;
        while(cur != null){
            n--;
            if(n == 0){
                pre.next = cur.next;
                cur = null;
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        return revise(reHead);
    }

    public Node revise(Node node){
        Node pre = null;
        Node cur = node;
        while(cur != null){
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static class Node<V>{
        V val;
        Node<V> next;

        public Node(V val){
            this.val = val;
        }
    }
}
