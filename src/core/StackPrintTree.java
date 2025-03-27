package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 非递归打印前 中 后 序
 */
public class StackPrintTree {

    public static class Node{
        int value;
        Node left;
        Node right;

        Node parent;

        public Node(int value){
            this.value = value;
        }
    }

    /**
     *
     * 非递归方式，先序打印一棵树  头 左 右
     * @param head
     *
     * 1.先将头入栈，弹出就打印
     * 2.有右先压右，再压左
     */
    public static void pre(Node head){


        System.out.println("pre 打印开始。。。。");
        Stack<Node> nodes = new Stack<>();
        nodes.push(head);

        while(!nodes.isEmpty()){
            Node pop = nodes.pop();
            System.out.println(pop.value);
            if(pop.right != null){
                nodes.push(pop.right);
            }
            if(pop.left != null){
                nodes.push(pop.left);
            }
        }
    }



    public static void pre3(Node head){
        Node cur = head;
        Stack<Node> stack = new Stack<Node>();
        stack.push(cur);
        while(!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.value);
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }

        }
    }


    /**
     * 非递归，后序打印一颗树  左 右 头 ， 其实就是先序（头左右）调换左右后，逆着打印
     * @param head
     *
     * 1.先将头压入，弹出压入另一个栈
     * 2.有左先压再压右
     * 3. 打印另一个栈
     */
    public static void pos(Node head){
        Stack<Node> nodes = new Stack<>();
        Stack<Node> cache = new Stack<>();
        System.out.println("pos后序打印");

        nodes.push(head);

        while (!nodes.isEmpty()){
            Node pop = nodes.pop();
            cache.push(pop);

            if(pop.left != null){
                nodes.push(pop.left);
            }
            if(pop.right != null){
                nodes.push(pop.right);
            }
        }

        while(!cache.isEmpty()){
            System.out.println(cache.pop().value);
        }

    }

    /**
     * 用一个栈，实现后序的打印
     * @param h
     */
    public static void pos2(Node h){
        System.out.println("后序2 打印开始");
        Stack<Node> stack = new Stack<>();
        stack.push(h);
        Node c = null;
        while(!stack.isEmpty()){
            c = stack.peek();
            if(c.left != null && h != c.left && h != c.right){
                stack.push(c.left);
            }else if(c.right != null && h != c.right){
                stack.push(c.right);
            }else {
                System.out.println(stack.pop().value);
                h = c;
            }
        }
    }

    /**
     * 非递归中序打印数   左头右
     * @param head
     *
     * 1.将左全部压入
     * 2. 1走不动弹出打印，去右数
     */
    public static void in(Node head){
        Node cur = head;
        System.out.println("中序打印开始");
        if(cur != null){
            Stack<Node> nodes = new Stack<>();
            while(cur != null || !nodes.isEmpty()){
                if(cur != null){
                    nodes.push(cur);
                    cur = cur.left;
                }else {
                    cur = nodes.pop();
                    System.out.println(cur.value);
                    cur = cur.right;
                }
            }
        }

    }

    public static void in3(Node head){
        Node cur = head;
        Stack<Node> nodes = new Stack<>();
        while(!nodes.isEmpty() || cur != null){
            if(cur != null){
                nodes.push(cur.left);
                cur = cur.left;
            }else {
                cur = nodes.pop();
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    public static void printPos2(Node head){
        if(head == null){
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Node h = head;
        Node c = null;
        stack.push(head);
        while(!stack.isEmpty()){
            c = stack.peek();
            if(c.left != null && c.left != h && c.right != h){
                stack.push(c.left);
            }else if(c.right != null && c.right != h){
                stack.push(c.right);
            }else {
                System.out.println(stack.pop().value);
                h = c;
            }
        }
    }

    /**
     *
     * 中序  找出当前节点的后继节点
     * @param head
     * @return
     */
    public static Node getNextNode(Node head){
        if(head == null){
            return head;
        }
        if(head.right != null){
            Node right = head.right;
            if(right.left == null){
                return right;
            }
            Node left = right.left;
            while (left != null){
                left = left.left;
            }
            return left;
        }else { // 无右数，往左
            Node parent = head.parent;
            while (parent != null && head == parent.right){
                head = parent;
                parent = head.parent;
            }
            return parent;
        }
    }


    public static int minLevel(Node head){
        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while(cur != null){
            int rightBoardCount = 1;
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    rightBoardCount++;
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    if(mostRight.left == null){
                        minHeight = Math.min(minHeight,curLevel);
                    }
                    curLevel -= rightBoardCount;
                    mostRight.right = null;
                }
            }else {
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRightheight = 1;
        cur = head;
        while(cur.right != null){
            finalRightheight++;
            cur = cur.right;
        }
        if(cur.left == null){
            minHeight = Math.min(minHeight,finalRightheight);
        }
        return minHeight;
    }


    public static int maxWidth(Node head){
        if(head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> levelMap = new HashMap<>();
        queue.add(head);
        levelMap.put(head,1);
        int max = 1;
        int curLevel = 1;
        int curNodeCount = 0;
        int nodeLevel = 1;
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            nodeLevel = levelMap.get(cur);
            if(cur.left != null){
                queue.add(cur.left);
                levelMap.put(cur.left,nodeLevel+1);
            }
            if(cur.right != null){
                queue.add(cur.right);
                levelMap.put(cur.right,nodeLevel+1);
            }
            if(nodeLevel == curLevel){
                curNodeCount++;
            }else {
                max = Math.max(max,curNodeCount);
                curNodeCount = 1;
                curLevel = nodeLevel;
            }
        }
        max = Math.max(curNodeCount,max);
        return max;
    }


    public static int maxWidth2(Node head){
        if(head == null){
            return 0;
        }
        Node curNodeEnd = head;
        Node nextNodeEnd = null;
        int max = 0;
        int curNodeCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextNodeEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextNodeEnd = cur.right;
            }
            curNodeCount++;
            if(cur == curNodeEnd){
                max = Math.max(max,curNodeCount);
                curNodeEnd = nextNodeEnd;
                curNodeCount = 0;
            }
        }
        return max;
    }

    public static void morrispost(Node head){
        Node cur = head;
        while(cur != null){
            Node mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    print(cur.left);
                }
            }
            cur = cur.right;
        }
        print(head);
    }

    public static void print(Node x){
        Node tail = reverse(x);
        Node cur = tail;
        while(cur != null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverse(tail);

    }

    public static Node reverse(Node x){
        Node pre = null;
        Node cur = x;
        while(cur != null){
            Node right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
        return pre;
    }


    public static void main(String[] arRgs) {
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



        pre(head);


        pos(head);


        pos2(head);

        morrispost(head);


        System.out.println("11111111111111");

        printPos2(head);


        in(head);

        int i = minLevel(head);

        System.out.println(i);

        System.out.println(maxWidth(head));

        System.out.println(maxWidth2(head));

    }
}
