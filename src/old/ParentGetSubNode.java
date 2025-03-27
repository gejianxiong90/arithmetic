package old;

import java.util.*;

/**
 * 题目描述
 通常使用多行的节点、父节点表示一棵树，比如

 西安 陕西
 陕西 中国
 江西 中国
 中国 亚洲
 泰国 亚洲

 输入一个节点之后，请打印出来树中他的所有下层节点

 输入描述
 第一行输入行数，下面是多行数据，每行以空格区分节点和父节点

 接着是查询节点

 输出描述
 输出查询节点的所有下层节点。以字典序排序

 示例1
 输入

 5
 b a
 c a
 d c
 e c
 f d
 c
 1
 2
 3
 4
 5
 6
 7
 输出

 d
 e
 f
 */
public class ParentGetSubNode {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for(int i = 0 ; i < row ;i++){
            String sub = in.next();
            String parent = in.next();
            map.computeIfAbsent(parent,k->new HashSet<String>()).add(sub);
        }
        String target = in.next();
        TreeSet<String> res = new TreeSet<>();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> strings = map.get(target);
        if(strings.isEmpty()){
            return;
        }
        for(String str : strings){
            queue.add(str);
        }
        while(!queue.isEmpty()){
            String node = queue.poll();
            res.add(node);
            HashSet<String> subs = map.get(node);
            if(subs != null){
                queue.addAll(subs);
            }
        }

        res.forEach(System.out::println);

    }
}
