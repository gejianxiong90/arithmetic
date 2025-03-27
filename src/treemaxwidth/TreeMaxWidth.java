package treemaxwidth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {

    public static void main(String[] args) {
        Node node = initData();
//        int i = maxWidthUseMap(node);

        int i1 = maxWidth(node);
        System.out.println(i1);
    }


    public static int maxWidthUseMap(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);
        // key   在哪一层
        HashMap<Node, Integer> levelMap = new HashMap<Node, Integer>();
        levelMap.put(head,1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0;// 当前层curLevel宽度目前是多少
        int max = 0;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(cur.left != null){
                levelMap.put(cur.left,curNodeLevel + 1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right,curNodeLevel +1 );
                queue.add(cur.right);
            }
            if(curNodeLevel == curLevel){
                curLevelNodes++;
            }else{
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max,curLevelNodes);
        return max;
    }




    public static int maxWidth(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> q = new LinkedList<Node>();
        HashMap<Node, Integer> nodeLevelMap = new HashMap<Node, Integer>();
        int curLevel = 1;
        int curLevelNodeCount = 0;
        int max = 0;
        q.add(head);
        nodeLevelMap.put(head,1);
        while(!q.isEmpty()){
            Node node = q.poll();
            Integer nodeLevel = nodeLevelMap.get(node);
            if(node.left != null){
                q.add(node.left);
                nodeLevelMap.put(node.left,nodeLevel +1);
            }
            if(node.right != null){
                q.add(node.right);
                nodeLevelMap.put(node.right,nodeLevel +1);
            }
            if(nodeLevel == curLevel){
                curLevelNodeCount++;
            }else{
                max = Math.max(max,curLevelNodeCount);
                curLevel++;
                curLevelNodeCount = 1;
            }
        }
        max = Math.max(max,curLevelNodeCount);
        return max;
    }

    public static Node initData(){
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node.left = node2;
        node.right = node3;
        node2.left = new Node(4);
        node2.right = new Node(5);
        node3.left = new Node(6);
        node3.right = new Node(7);
        return node;

    }


    static class Node{
        Node left;
        Node right;
        int value;

        public Node(int val){
            this.value = val;
        }
    }
}
