package core;

import java.util.*;

public class Morris {

    public static void morris(Node head){
       Node cur = head;
       Node mostRight = null;
       while(cur != null){
           System.out.println(cur.value);
           mostRight = cur.left;
           if(mostRight != null){
               while(mostRight.right != null && mostRight.right != cur){
                   mostRight = mostRight.right;
               }
               if(mostRight.right == null){
                   mostRight.right = cur; // 第一次进来
                   cur = cur.left;
                   continue;
               }else {
                   mostRight.right = null;
               }
           }
           cur = cur.right;
       }

    }


    public  static void morrisIn(Node head){
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//morris遍历第一来到
                    mostRight.right = cur; // 中序遍历中，有左树的第一次都不打
                    cur = cur.left;
                    continue;
                }else { // 第二次来到 mostRight.right == cur ,一定是mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public  static void morrisPre(Node head){
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){//morris遍历第一来到
                    mostRight.right = cur;
                    System.out.print(cur.value+" ");
                    cur = cur.left;
                    continue;
                }else { // 第二次来到 mostRight.right == cur ,一定是mostRight.right == cur
                    mostRight.right = null;
                }
            }else {
                   System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }


    public static void in2(Node node){
        Stack<Node> stack = new Stack<>();
        while(!stack.isEmpty()|| node != null){
            if(node != null){
                stack.push(node);
                node =node.left;
            }else {
                Node cur = stack.pop();
                System.out.println(cur.value);
                node = cur.right;
            }
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
               System.out.print(pop.value+" ");
               node = pop.right;
           }
        }
    }


    public static void morrisPos(Node node){
        Node cur = node;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right == cur
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(node);
    }

    public static void in3(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(!stack.isEmpty() ||cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                Node pop = stack.pop();
                System.out.print(pop.value + " ");
                cur = pop.right;
            }
        }
    }

    public static void printEdge(Node head){
        Node tail = reverseEdge(head);
        Node cur = tail;
        while(cur != null){
            System.out.print(cur.value+" ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from){
        Node pre = null;
        Node cur = from;
        while(cur != null){
            Node right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
        return pre;
    }


    public static boolean isBst(Node node){
        Node cur = node;
        Node mostRight = null;
        Node pre = null;
        while(cur != null){
            mostRight = cur.left;
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
                }
            }
            if(pre != null && pre.value >= cur.value){
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
    public static void main(String[] args) {
        Node node = Node.generateTree();
        morris(node);
        morrisIn(node);
        in3(node);
//        morrisPre(node);
        System.out.println("------");

        morrisPos(node);
        System.out.println("------");
        morrisPos2(node);
//        in(node);
        List<List<Integer>> res = new ArrayList<List<Integer>>();


        LinkedList<Node> list = new LinkedList<Node>();


        boolean falg = false;
        falg = !falg;
        System.out.println(falg);


    }


    public static void morris2(Node head){
        Node cur = head;
        Node mostRight = cur.left;
        while(cur != null){
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

                }
            }
            cur = cur.right;
        }
    }





















    public static void morrisPos2(Node head){
        Node cur = head;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if (mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){ // 第一次到达
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge2(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge2(head);
    }

    public static void printEdge2(Node from){
        Node tail = reverseNode2(from);
        Node cur = tail;
        while(cur != null){
            System.out.print(cur.value+" ");
           cur =  cur.right;
        }
        reverseNode2(tail);
    }

    public static Node reverseNode2(Node from){
        Node pre = null;
        Node cur = from;

        while(cur != null){
            Node right = cur.right;
            cur.right = pre;
            pre = cur;
            cur = right;
        }
        return pre;
    }



    public static void morris3(Node head){
        if(head == null){
            return;
        }
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
                }
            }

            cur = cur.right;

        }
    }


    public void morris4(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        while(cur != null){
            Node mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    cur.right = null;
                }
            }
            cur = cur.right;
        }
    }
}
