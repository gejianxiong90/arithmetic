package test;

import java.util.HashMap;
import java.util.Map;

public class KMP {


//    public static void main(String[] args) {
//        String match = "abcsabcabctt";
//        String str = "fgabcsabcabcttdg";
//        int indexOf = getIndexOf(str, match);
//        System.out.println(indexOf);
//        int[] nextArray = getNextArray(match.toCharArray());
//        for(int next : nextArray){
//            System.out.print(next);
//        }
//    }

    /**
     * 字符串查找匹配问题
     * @param str
     * @param match
     * @return
     */
    public static int getIndexOf(String str,String match){
        if(str.length() == 0 || match.length() == 0 || str.length() < match.length()){
            return -1;
        }
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int[] nextArray = getNextArray(matchChars);
        int x = 0;
        int y = 0;
        while (x < strChars.length && y < matchChars.length){
            if(strChars[x] == matchChars[y]){
                x++;
                y++;
            }else if(nextArray[y] == -1){
                x++;
            }else {
                y = nextArray[y];
            }
        }

        return y == matchChars.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match){
        if(match.length == 1 ){
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0; // i-1位置匹配的长度    同时也是前匹配的后一个字符的位置
        int i = 2;
        while ( i < match.length){
            if(match[i - 1 ] == match[cn]){
               next[i++] = ++cn;
            }else if (cn > 0 ){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }

        return next;
    }
















    public static void main(String[] args) {

        Node node1 = new Node();

        Node node2 = new Node();
        node2.value = 2;

        node1.value = 1;
        node1.next = node2;
        node1.random = node2;


        Node node = process(node1);

        while(node != null){
            System.out.println(node.value);
            System.out.println(node.random == null ? null : node.random.value);
            node = node.next;
        }




    }



    public static Node process(Node head){
        Node root = new Node();
        boolean isRoot = true;
        Map<Node,Node> map = new HashMap<Node,Node>();

        Node tmp = head;
        while(tmp != null){
            Node node = new Node();
            Node randomNode = head.random;
            if(randomNode != null){
                node.value = randomNode.value;
                map.put(randomNode,node);
            }
            tmp = tmp.next;
        }
        while(head != null){
            Node node = null ;
            if(map.get(head) != null){
                node = map.get(head);
            }else{
                node = new Node();
            }
            node.value = head.value;
            node.next = head.next;
            node.random = map.get(head.random);
            if(isRoot){
                root = node;
                isRoot = false;
            }
            head = head.next;
        }
        return root;
    }

    public static class Node{
        int value;
        Node next;
        Node random;
    }

}
