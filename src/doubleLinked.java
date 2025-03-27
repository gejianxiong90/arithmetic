public class doubleLinked {

    /**
     *  单向链表翻转后相加，最终返回翻转后的链表
     * 1-》2-》3     321
     * 4-》 5 -》 6  654
     *
     * 5 -》 7 -》 9
     */


    public Node<Integer> process(Node<Integer> node1,Node<Integer> node2){

        Node<Integer> newNode1 = reverse(node1);
        Node<Integer> newNode2 = reverse(node2);

        Node cur1 = newNode1;
        StringBuilder sb1 = new StringBuilder();
        while(cur1 != null){
            sb1.append(cur1.val);
        }

        Node cur2 = newNode2;
        StringBuilder sb2 = new StringBuilder();
        while(cur2 != null){
            sb1.append(cur2.val);
        }

        Integer num1 =  Integer.valueOf(sb1.toString());

        Integer num2 =  Integer.valueOf(sb2.toString());

        Integer res = num1 + num2;

        String s = String.valueOf(res);

        char[] chars = s.toCharArray();
        Node node = new Node(Integer.valueOf(chars[chars.length - 1]));
        for(int i = chars.length -2 ; i >=0 ;i--){
           node.next =  new Node(Integer.valueOf(chars[i]));
           node = node.next;
        }


        return node;
    }

    public Node reverse(Node<Integer> node){
        Node<Integer> cur = node;
        Node<Integer> pre = null;

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

        public Node(V v){
            this.val = v;

        }
    }
}
