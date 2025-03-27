package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AllOrder {


    public static void main(String[] args) {
        String[] strs = {"a","b","c"};
        HashSet<Integer> used = new HashSet<>();
        ArrayList<String> path = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();
        process(strs,used,path,ans);

        System.out.println(ans);
    }


    public static void process(String[] strs, HashSet<Integer> used,List<String> path,List<String> ans){
        if(path.size() == strs.length){
            StringBuffer sb = new StringBuffer();
            for(String str : path){
                sb.append(str);
            }
            ans.add(sb.toString());
        }else {
            for(int i = 0 ; i <strs.length ; i++){
                if(!used.contains(i)){
                    path.add(strs[i]);
                   // used.add(i);
                    process(strs,used,path,ans);
                    path.remove(strs[i]);
                    used.remove(i);
                }
            }
        }
    }
}
