package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxNumAndLevelInTree {

    public static int maxWidth(Node head){
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        int curNodeNum = 0;
        int max = 0;
        int level = 1;

        Queue<Node> queue = new LinkedList<Node>();

        queue.add(head);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int curLevel = levelMap.get(node);
            if(node.left != null){
                queue.add(node.left);
                levelMap.put(node.left,curLevel+1);
            }
            if(node.right != null){
                queue.add(node.right);
                levelMap.put(node.right,curLevel+1);
            }
            if(level == curLevel){
                curNodeNum++;
            }else {
                max = Math.max(max,curNodeNum);
                curNodeNum = 1;
                level = curLevel;
            }
        }
        max = Math.max(max,curNodeNum);
        return max;
    }

    public static int maxWidthNoMap(Node head){
        Queue<Node> queue = new LinkedList<>();
        Node curEnd = head;
        Node nextEnd = null;
        queue.add(head);
        int max = 0;
        int curNodeNum = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node.left != null){
                queue.add(node.left);
                nextEnd = node.left;
            }
            if(node.right != null){
                queue.add(node.right);
                nextEnd = node.right;
            }
            curNodeNum++;
            if(curEnd == node){
                max = Math.max(max,curNodeNum);
                curNodeNum = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }



    public static void leftView(Node head){
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(head,1);

        Queue<Node> queue = new LinkedList<Node>();

        Queue<Node> view = new LinkedList<Node>();

        queue.add(head);
        int level = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int curLevel = map.get(curNode);
            if(curNode.left != null){
                queue.add(curNode.left);
                map.put(curNode.left,curLevel+1);
            }
            if(curNode.right != null){
                queue.add(curNode.right);
                map.put(curNode.right,curLevel+1);
            }

            if(level != curLevel){
                view.add(curNode);
                level = curLevel;
            }
        }

        while (!view.isEmpty()){
            System.out.println(view.poll().value);
        }
    }

    public static void rightView(Node head){
        HashMap<Node, Integer> map = new HashMap<>();
        map.put(head,1);

        Queue<Node> queue = new LinkedList<Node>();

        Queue<Node> view = new LinkedList<Node>();

        queue.add(head);
        int level = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int curLevel = map.get(curNode);
            if(curNode.right != null){
                queue.add(curNode.right);
                map.put(curNode.right,curLevel+1);
            }
            if(curNode.left != null){
                queue.add(curNode.left);
                map.put(curNode.left,curLevel+1);
            }

            if(level != curLevel){
                view.add(curNode);
                level = curLevel;
            }
        }

        while (!view.isEmpty()){
            System.out.println(view.poll().value);
        }
    }


    public static void in(Node node){
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else {
                Node pop = stack.pop();
                System.out.println(pop.value);
                node = pop.right;
            }

        }
    }


    public static void main(String[] args) {
        Node node = Node.generateTree();
        int i = maxWidth(node);
        System.out.println(i);

        System.out.println(maxWidthNoMap(node));


       leftView(node);


        System.out.println(".......");

       rightView(node);

        System.out.println(".......");

       in(node);
    }
}
