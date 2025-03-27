package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintAllSubsquences {


    public static void main(String[] args) {

        List<String> abc = subs("abc");
        System.out.println(abc);

    }


    public static List<String> subs(String s){
        char[] chars = s.toCharArray();
        String path = "";
        ArrayList<String> ans = new ArrayList<>();
        process1(chars,0,ans,path);
        return ans;
    }

    public static void process1(char[] str,int index,List<String> ans,String path){
        if(index == str.length){
            ans.add(path);
            return;
        }

        String yes = path + String.valueOf(str[index]);
        process1(str,index + 1,ans,yes);

        String no = path;
        process1(str,index+1,ans,no);

    }
}
