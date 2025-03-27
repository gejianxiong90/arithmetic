package leetcode;


import java.util.*;

public class Connect_117 {

    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Queue<Node> q = new LinkedList<Node>();
        HashMap<Node, Integer> levelMap = new HashMap<>();
        q.add(root);
        levelMap.put(root,1);
        int level = 1;
        Queue<Node> connectNode = new LinkedList<Node>();
        while(!q.isEmpty()){
            Node cur = q.poll();
            int curLevel = levelMap.get(cur);
            if(cur.left != null){
                q.add(cur.left);
                levelMap.put(cur.left,curLevel + 1);
            }
            if(cur.right != null){
                q.add(cur.right);
                levelMap.put(cur.right,curLevel + 1);
            }
            if(level != curLevel){
                level = curLevel;
                Node pre = connectNode.poll();
                while(!connectNode.isEmpty()){
                    Node node = connectNode.poll();
                    pre.next = node;
                    pre = node;
                }
            }
            connectNode.add(cur);
        }
        Node pre = connectNode.poll();
        while(!connectNode.isEmpty()){
            Node node = connectNode.poll();
            pre.next = node;
            pre = node;
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        Node curEnd = root;
        Node nextEnd = null;
        Queue<Node> queue = new LinkedList<Node>();
        Queue<Node> levelQueue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.left != null){
                queue.add(node.left);
                nextEnd = node.left;
            }
            if(node.right != null){
                queue.add(node.right);
                nextEnd = node.right;
            }
            levelQueue.add(node);
            if(curEnd == node){
                curEnd = nextEnd;
                if(!levelQueue.isEmpty()){
                    Node cur = levelQueue.poll();
                    while(!levelQueue.isEmpty()){
                        Node poll = levelQueue.poll();
                        cur.next = poll;
                        cur = poll;
                    }
                }
            }
        }
        return root;
    }


    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
