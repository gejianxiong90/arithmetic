package sz.code20;

import java.util.HashMap;
import java.util.Map;

/**
 * 给一个树，要求返回符合搜索二叉树的拓扑结构的节点数量（注意不是子树）
 *             7
 *           / \
 *          6   9
 *        /    /
 *       5    4
 *
 *
 *   4比7大，所以是
 *             7
 *           / \
 *          6   9
 *        /
 *       5
 *
 *   去掉4，返回 4 这个节点数量
 *
 */
public class BiggestBSTTopologyInTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Record{
        int l;
        int r;

        public Record(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    public static int modify(Node node , int v,Map<Node,Record> map,boolean s){
        if(node == null || !map.containsKey(node)){
            return 0;
        }
        Record record = map.get(node);
        if((s && node.value > v) || (!s && node.value < v)){
            map.remove(node);
            return record.r+record.l+1;
        }
        int minus = modify(s ? node.right : node.left, v, map, s);
        if(s){
            record.r -= minus;
        }else {
            record.l -= minus;
        }
        map.put(node,record);
        return minus;
    }

    /**
     *
     * @param h 当前节点
     * @param map 记录表,维持了node和左右达标节点的数量
     * @return 返回当前节点下符合条件的数量
     */
    public static int posOrder(Node h, Map<Node,Record> map){
        if(h == null){
            return 0;
        }
        int left = posOrder(h.left, map);
        int right =  posOrder(h.right, map);

        modifyMap(h.left,h.value,map,true);
        modifyMap(h.right,h.value,map,false);


        Record lRecord = map.get(h.left);
        Record rRecord = map.get(h.right);
        int lBst = lRecord == null ? 0 : lRecord.l+lRecord.r + 1;
        int rBst = rRecord == null ? 0 : rRecord.l+rRecord.r + 1;
        map.put(h,new Record(lBst,rBst));

        return Math.max(lBst+rBst+1,Math.max(left,right));
    }

    /**
     *
     * @param n 左右节点
     * @param v 头节点值
     * @param map node达标的左右节点数量
     * @param s
     * @return 不达标的数量
     */
    public static int modifyMap(Node n,int v,Map<Node,Record> map,boolean s){
        if(n == null || (!map.containsKey(n))){
            return 0;
        }
        Record record = map.get(n); // 当前节点左边和右边的数量
        if((s && n.value > v) || ((!s) && n.value < v)){
            map.remove(n);
            return record.l + record.r + 1;
        }
        int minus = modifyMap(s ? n.right : n.left, v, map, s);
        if(s){
            record.r -= minus;
        }else {
            record.l -= minus;
        }
        map.put(n,record);
        return minus;
    }

    public static int bstTopoSize1(Node head){
        Map<Node, Record> nodeRecordHashMap = new HashMap<>();
        return posOrder(head,nodeRecordHashMap);
    }

    public static int bstTopoSize(Node head){
        if(head == null){
            return 0;
        }
        int max = maxTopo(head,head);
        max = Math.max(bstTopoSize(head.left),max);
        max = Math.max(bstTopoSize(head.right),max);

        return max;
    }

    public static int maxTopo(Node h,Node n){
        if(h != null && n != null && isBstNode(h,n,n.value)){
            return maxTopo(h,h.left) + maxTopo(h,h.right) + 1;
        }
        return 0;
    }

    public static boolean isBstNode(Node h,Node n,int value){
        if(h == null){
            return false;
        }
        if(h == n){
            return true;
        }
        return isBstNode(h.value > value ? h.left : h.right,n,value);
    }

    public static boolean isBst(Node h){
        if(h == null){
            return true;
        }
        if((h.left != null && h.left.value > h.value)
                || (h.right != null && h.right.value < h.value)){
            return false;
        }

        return isBst(h.right) && isBst(h.left);
    }

    public static boolean isBst2(Node h){
        if(h == null){
            return true;
        }
        boolean flag = false;
        if((h.left == null || h.left.value < h.value)
                && (h.right == null || h.right.value > h.value)){
            flag =  true;
        }else {
            flag = false;
        }

        return flag && isBst2(h.right) && isBst(h.left);
    }

    public static void main(String[] args) {
//        Node head = new Node(7);
//        Node left = new Node(4);
//        Node leftL = new Node(3);
//        Node leftR = new Node(5);
//        Node right = new Node(9);
//        Node rightL = new Node(8);
//
//        head.left = left;
//        head.right = right;
//        left.right = leftR;
//        left.left = leftL;
//
//        right.left = rightL;
//
//        boolean bst = isBst(head);
//        boolean bst2 = isBst2(head);

//        System.out.println(bst);
//        System.out.println(bst2);

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

        System.out.println(bstTopoSize1(head));

    }
}
