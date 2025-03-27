package sz.code14;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个小写字母串数组arr，和一个长字符串str
 * arr中可以使用无限张数，问可以拼成str有多少方法数
 */
public class Word {


    public static int way(String str , String[] arr){
        HashSet<String> set = new HashSet<>();
        for(String s : arr){
            set.add(s);
        }
       return process(str,0,set);

    }

    public static int process(String str, int i, Set<String> set){
        if( str.length() == i){
            return 1;
        }
        int res = 0;
        for(int end = i ; end < str.length() ; end++){
            String pre = str.substring(i, end + 1);
            if(set.contains(pre)){
                res += process(str,end+1,set);
            }
        }
        return res ;
    }


    public static class Node{
        Node[] next;
        boolean end;

        public Node(){
            next = new Node[26];
            end = false;
        }
    }


    public static void initTrie(String[] arr,Node head){
        for(String str : arr){
            Node cur = head;
            int index = 0;
            for(char c : str.toCharArray()){
                index = c - 'a';
                if(cur.next[index] == null){
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = true;
        }
    }

    public static int way2(String[] arr, String str){
        Node head = new Node();
        initTrie(arr,head);
        return process2(str.toCharArray(), 0, head);

    }


    public static int process2(char[] str,int i,Node root){
        if( i == str.length){
            return 1;
        }
        int res = 0;
        Node cur = root;
        for(int end = i ; end < str.length ; end++){
            int path = str[end] - 'a';
            if(cur.next[path] == null){
                break;
            }
            cur = cur.next[path];
            if(cur.end){
                res += process2(str,end+1,root);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String str = "aaabc";
        String[] arr = {"a","aa","aaa","b","c"};

        int way = way(str, arr);
        System.out.println(way);
        int i = way2(arr, str);
        System.out.println(i);
    }
}
