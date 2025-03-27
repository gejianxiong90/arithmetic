package zhaoshang;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Test1 {


    public static void main(String[] args) {
        String[] first = {"abc","ab","abcdef"};
        String[] second = {"a","abcd","abdce"};
        String[] strings = mergeArray(first, second);
        System.out.println(strings);
    }
    public static String[] mergeArray (String[] first, String[] second) {
        // write code here

        Node root = new Node();
        if(first != null && first.length > 0){
            for(int i = 0 ; i < first.length ;i++){
                char[] chars = first[i].toCharArray();
                Node cur = root;
                for(int j = 0 ; j < chars.length ;j++){
                    int path = chars[j] - 'a';
                    if(cur.next[path] == null){
                        cur.next[path] = new Node();
                    }
                    cur = cur.next[path];
                }
                cur.end = true;
            }
        }

        if(second != null && second.length > 0){
            for(int i = 0 ; i < second.length ;i++){
                char[] chars = second[i].toCharArray();
                Node cur = root;
                for(int j = 0 ; j < chars.length ;j++){
                    int path = chars[j] - 'a';
                    if(cur.next[path] == null){
                        cur.next[path] = new Node();
                    }
                    cur = cur.next[path];
                }
                cur.end = true;
            }
        }

        LinkedList<Character> characters = new LinkedList<>();
        List<String> list = new ArrayList<>();
        path(root,characters,list);
        String[] res = new String[list.size()];
        list.toArray(res);
        return res;
    }

    public static void path(Node cur, LinkedList<Character> path, List<String> res){
        if(cur.end ){
            cur.end = false;
            StringBuilder sb = new StringBuilder();
            for(Character c : path){
                sb.append(c);
            }
            res.add(sb.toString());
           // return;
        }
        for(int i = 0 ; i < 26;i++){
           if(cur.next[i] == null){
               continue;
           }else {
               char c = (char) ('a' + i);
               path.addLast(c);
               path(cur.next[i],path,res);
               path.removeLast();
           }
        }
    }

    public static class Node{
        Node[] next;
        boolean end;

        public Node(){
            next = new Node[26];
            this.end = false;
        }
    }
}
