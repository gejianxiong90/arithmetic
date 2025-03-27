package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordBreak {

    public List<String> wordBreak(String s, List<String> wordDict) {
        LinkedList<String> ans = new LinkedList<String>();

        Node root = new Node();
        for(String word : wordDict){
            Node cur = root;
            char[] c = word.toCharArray();
            for(int i = 0 ; i < c.length ; i++){
                int index = c[i] - 'a';
                if(cur.next[index] == null){
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.end = true;
        }



        process(s,wordDict,0,ans,"",root);

        return ans;



    }

    public static class Node{
        boolean end;
        Node[] next;

        public Node(){
            this.end = false;
            next = new Node[26];
        }
    }

    public static void process(String s, List<String> wordDict,int index,LinkedList<String> ans,String path,Node root){
        if(index == s.length()){
            ans.add(path);
        }else {
            Node cur = root;
            char[] chars = s.toCharArray();
            for(int end = index; end < s.length(); end++){
                int nodeIndex = chars[end] - 'a';
                if(cur == null || cur.next[nodeIndex] == null){
                    return;
                }
                cur = cur.next[nodeIndex];

                if(cur.end){
                    String sub = s.substring(index,end+1);
                    process(s,wordDict,end+1,ans,path == "" ? sub : path+" "+sub,root);
                }

            }
        }
    }


    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        List<String> dict = new ArrayList<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        List<String> wordBreak1 = wordBreak.wordBreak("catsanddog", dict);


        LinkedList<Integer> integers = new LinkedList<>();


        System.out.println(wordBreak1);
    }
}
