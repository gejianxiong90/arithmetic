package graph;

import java.util.*;

//拓扑排序
public class Code3_TopologySort {

    //就是先打印入度为0的
    public static List<Node> sortTopology(Graph graph){
        // key:某一个node  value：剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
        // 剩余入度为0的点，才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<Node>();

        for(Node node : graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in == 0 ){
                zeroInQueue.add(node);
            }
        }
       // 拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<Node>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for(Node next : cur.nexts){
                // 我所有的邻居入度-1
                inMap.put(next,inMap.get(next) -1);
                if(inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
