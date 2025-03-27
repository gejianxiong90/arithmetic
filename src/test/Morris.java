package test;
//
//当前节点cur,一开始cur来到整颗树头
//1）cur无左树，cur = cur.right
//2)cur有左树，找到左树最右节点mostright
//    1.mostright的右指针指向null的，
//      mostright.right = cur ,cur = cur.left
//    2.mostright的右指针指向cur
//      mostright.right = null,cur = cur.right


import java.util.HashMap;

public class Morris {


    public static void main(String[] args) {
        String string = Integer.toBinaryString(-1);

        System.out.println(string);

        int i = 10;
        i -= 6;
        System.out.println(i);

        System.out.println("abdc".hashCode());


        for(int j = 0; j < 10 ; j++){
            System.out.println((int)(Math.random() * 10) + 1);

        }


        int [][] arr= {

            {1,2},
                {3,4}

        };

        for(int[] a : arr){
            System.out.println(a[0]);
            System.out.println(a[1]);;
        }

    }

    public static void morris(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur有没有左树
            mostRight = cur.left;
            if(mostRight != null){ // 有左树的情况下
                // 找到cur左树上，真实的最右
                while (mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // 从while出来，mostright一定是cur左树上的最右节点
                // mostright
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // mostRight.right != null -> mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }





    public static class Node<T> {
        T value;
        Node left;
        Node right;

    }


    /**
     * 中序
     * @param head
     */
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value+" ");
            cur = cur.right;
        }
    }

    public static boolean isBST(Node<Integer> head) {
        if (head == null) {
            return true;
        }
        Node<Integer> cur = head;
        Node mostRight = null;
        Integer pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if(pre != null && pre >= cur.value){
               return false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return true;
    }


    /**
     * 先序
     * @param head
     */
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }else {
                System.out.println(cur.value+" ");
            }
            cur = cur.right;
        }
    }


    public static void morrisMinHeight(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int leftHeight = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    leftHeight++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次到达
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次到达
                    if(mostRight.left == null){
                        minHeight = Math.min(minHeight,curLevel);
                    }
                    curLevel -= leftHeight;
                    mostRight.right = null;
                }
            }else { // 只有一次到达
                curLevel++;
            }
            cur = cur.right;
        }
    }


    public static void morris3(Node node){
        Node cur = node;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            while(mostRight.right != null && mostRight.right != cur){
                mostRight = mostRight.right;
            }
            if(mostRight.right == null){
                mostRight.right = cur;
                cur = cur.left;
                continue;
            }else{
                mostRight.right = null;
            }
            cur = cur.right;
        }
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
            System.out.println(cur.value + " ");
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











}
