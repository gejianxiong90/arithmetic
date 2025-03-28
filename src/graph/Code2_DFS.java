package graph;

import java.util.HashSet;
import java.util.Stack;

public class Code2_DFS {

    //深度优先遍历
    public static void dfs(Node node){
        if(node == null){
            return;
        }
        //记录深度优先的路径
        Stack<Node> stack = new Stack<Node>();
        HashSet set = new HashSet<Node>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next :cur.nexts){
                if(!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
