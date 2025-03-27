package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class LadderLength {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList);
        if(!dict.contains(endWord)){
            return 0;
        }
        HashSet<String> start = new HashSet<String>();
        HashSet<String> end = new HashSet<String>();
        HashSet<String> visit = new HashSet<String>();
        start.add(beginWord);
        end.add(endWord);
        for(int len = 2; !start.isEmpty() ; len++){
            HashSet next = new HashSet<String>();
            for(String word : start){
                if(end.contains(word)){
                    return len;
                }
                for(int i = 0 ; i < word.length() ; i++){
                    char[] c = word.toCharArray();
                    char cur = c[i];
                    for(char j = 'a' ; j <= 'z' ; j++){
                        if(cur != j){
                            c[i] = j;
                            String str = String.valueOf(c);
                            if(end.contains(str)){
                                return len;
                            }
                            if(dict.contains(str) && !visit.contains(str)){
                                next.add(str);
                                visit.add(str);
                            }
                        }
                    }
                }
            }
            start = next.size() > end.size() ? end : next;
            end = start == next ? end : next;
        }

        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");

        int i = ladderLength("hit", "cog", list);
        System.out.println(i);

        LinkedList<String> path = new LinkedList<String>(list);

    }
}
