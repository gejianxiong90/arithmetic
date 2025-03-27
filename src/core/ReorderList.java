package core;

import java.util.ArrayList;
import java.util.List;

public class ReorderList {


public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

    public void reorderList(ListNode head) {

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        for(ListNode node : list){
            node.next = null;
        }

        int L = 0;
        int R = list.size() - 1;
        ListNode next = null;
        head.next = list.get(R);
        head = head.next;
        while (R > L){
            head.next =  list.get(++L);
            head = head.next;
            head.next = list.get(--R);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode head = new ListNode(1);
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(4);
        head.next = listNode;
        listNode.next = listNode1;
        listNode1.next = listNode2;

        reorderList.reorderList(head);

        System.out.println(head);

    }

}
