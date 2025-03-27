package xzhsort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class XZHSort {

    public static void main(String[] args) {
        Node node = initData();
//        preRecursion(node);
//        inRecursion(node);
//        posRecursion(node);
//        preNonRecursion(node);
//        posNonRecursion(node);
//        inNonRecursion(node);
        level(node);
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


    public static Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<String>();
        pres(head,ans);
        return ans;
    }


    public static void pres(Node head,Queue<String> ans){
        if(head == null){
            ans.add(null);
        }else{
            ans.add(String.valueOf(head.value));
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }

    public static Node buildByPreQueue(Queue<String> prelist){
        if(prelist == null ||prelist.size() == 0){
            return null;
        }
        return preb(prelist);
    }

    public static Node preb(Queue<String> prelist){
        String value = prelist.poll();
        if(value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(prelist);
        head.right = preb(prelist);
        return head;
    }



    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<String>();
        if(head == null){
            ans.add(null);
        }else{
            Queue<Node> q = new LinkedList<Node>();
            q.add(head);
            ans.add(String.valueOf(head.value));
            while(!q.isEmpty()){
                Node poll = q.poll();
                if(poll.left != null){
                    ans.add(String.valueOf(poll.left.value));
                    q.add(poll.left);
                }else{
                    ans.add(null);
                }
                if(poll.right != null){
                    ans.add(String.valueOf(poll.right.value));
                    q.add(poll.right);
                }else{
                    ans.add(null);
                }
            }
        }

        return ans;
    }


    public static void level(Node head){   // 宽度遍历
        if(head != null){
          Queue<Node> q =   new LinkedList<Node>();
          q.add(head);
          while(!q.isEmpty()){
              Node poll = q.poll();
              System.out.println(poll.value);
              if(poll.left != null){
                  q.add(poll.left);
              }
              if(poll.right != null){
                  q.add(poll.right);
              }

          }
            ;
        }
    }

    public static void recursion(Node head){   // 递归序核心思想，每个节点返3次. 1 2 4 4 4 2 5 5 5 2 1 3 6 6 6 3 7 7 7 3 1
        if(head == null){
            return;
        }

        recursion(head.left);

        recursion(head.right);
    }

    public static void preRecursion(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value);
        preRecursion(head.left);
        preRecursion(head.right);
    }

    public static void preNonRecursion(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while(!stack.isEmpty()){
                 head = stack.pop(); // 弹出就打印
                System.out.println(head.value);
                if(head.right != null){  // 右不为null，先压右
                    stack.push(head.right);
                }
                if(head.left != null){  // 左不为null，压左
                    stack.push(head.left);
                }
            }
        }
    }

    public static void inNonRecursion(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null){
                if(head != null){     // 1.整条左边界一次入栈
                    stack.push(head);
                    head = head.left;
                }else{                      // 弹出打印，执行右树条件1
                    Node pop = stack.pop();
                    System.out.println(pop.value);
                    head = pop.right;
                }
            }
        }
    }

    public static void posNonRecursion(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            Stack<Integer> printStack = new Stack<Integer>();
            stack.push(head);
            while (!stack.isEmpty()){
                Node pop = stack.pop();
                printStack.push(pop.value);
                if(pop.left != null){
                    stack.push(pop.left);
                }
                if(pop.right != null){
                    stack.push(pop.right);
                }
            }

            while (!printStack.isEmpty()){
                System.out.println(printStack.pop());
            }
        }
    }

    public static void inRecursion(Node head){
        if(head == null){
            return;
        }
        inRecursion(head.left);
        System.out.println(head.value);
        inRecursion(head.right);

    }

    public static void posRecursion(Node head){
        if(head == null){
            return;
        }
        posRecursion(head.left);
        posRecursion(head.right);
        System.out.println(head.value);

    }


    public static void inSort(Node head){
        if(head != null){
            Stack<Node> nodes = new Stack<Node>();
            nodes.push(head);
            while(head != null || !nodes.isEmpty()){
                if(head.left != null){
                    nodes.push(head.left);
                }
            }
        }
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
