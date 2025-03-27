package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AC {


    public static class Node{
        public String end;
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node(){
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }


    public static class ACAutomation{
        private Node root;

        public ACAutomation(){
            root = new Node();
        }


        public void insert(String s){
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for(int i = 0 ; i < str.length; i++){
                index = str[i] - 'a';
                if(cur.nexts[index] == null){
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }

            cur.end = s;
        }


        public void build(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;

            while (!queue.isEmpty()){
                // 某个父亲,cur
                cur = queue.poll();
                for (int i = 0; i < 26 ; i++){
                    // cur -> 父亲， i号儿子，必须把i号儿子的fail指针设置好
                    if(cur.nexts[i] != null){  // 如果真的有i号儿子
                        cur.nexts[i].fail = root; // 儿子的fail指针先指向父
                        cfail = cur.fail; // 父的fail指针
                        while (cfail != null){  // 父亲的fail指针不为null
                            if(cfail.nexts[i] != null){  //
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }


        public List<String> containsWords(String content){
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();

            for(int i = 0; i < str.length;i++){
                index = str[i] - 'a'; // 路
                while (cur.nexts[index] == null && cur != root){
                    cur = cur.fail;
                }

                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root){
                    if(follow.endUse){
                        break;
                    }
                    if(follow.end != null){
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    follow = follow.fail;
                }

            }
            return ans;
        }
    }
    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("he");
        ac.insert("heg");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containsWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }

    }
}
