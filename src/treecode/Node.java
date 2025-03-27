package treecode;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
    Node left;
    Node right;
    int value;

    public Node(int val){
        this.value = val;
    }

    public static Node initBSTData(){
        Node node = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        node.left = node2;
        node.right = node3;
        node2.left = new Node(1);
        node2.right = new Node(4);
        node3.left = new Node(6);
        node3.right = new Node(8);
        return node;
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


    public static void main(String[] str){
//        List<String> strings = new ArrayList<String>();
//        String[] strings1 = {"c", "d", "e", "a","c"};
//        boolean b =  wordDiv( "accde" ,strings1,strings);
//
//        System.out.println(strings.size() == strings1.length);


        ReentrantLock reentrantLock = new ReentrantLock(true);

       // boolean b = reentrantLock.tryLock();
        reentrantLock.lock();
        reentrantLock.lock();



    }

    public static void process(int[] arr){
       // int
    }


    public static boolean wordDiv (String s, String[] dic,List<String> matchSize) {
        if(s == null || "".equals(s) || dic == null || dic.length < 1 ){
            return false;
        }

        List<String> strList = Arrays.asList(dic);
        Queue<String> queue =  new LinkedList<String>(strList);
        while (!queue.isEmpty()){
            String str = queue.poll();
            if(s.contains(str)){
                matchSize.add(str);
                String substring1 = s.substring(0, s.indexOf(str));
                String substring2 = s.substring(s.indexOf(str)+1, s.length());
                boolean flag = true;
                if(queue.size() > 0){
                    boolean leftFlag =  wordDiv(substring1,queue.toArray(new String[]{}),matchSize);
                    boolean rightFlag =  wordDiv(substring2,queue.toArray(new String[]{}),matchSize);
                    flag =  leftFlag || rightFlag;
                }
                return flag;
            }
        }
        return false;
        // write code here
    }


}
