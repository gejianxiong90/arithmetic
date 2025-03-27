package graph;

import java.util.*;

// k p 算法 生成最小树
public class Code4_Kruskal {

    public static class UnionFind{
        // key 某一个节点，value key节点往上的节点
        private HashMap<Node,Node> fatherMap;
        // key 某一个集合的代表节点，value key所在集合的节点个数
        private HashMap<Node,Integer> sizeMap;

        public UnionFind(){
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node: nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        private Node findFather(Node n){
            Stack<Node> path = new Stack<Node>();
            while( n != fatherMap.get(n)){
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()){
                fatherMap.put(path.pop(),n);
            }
            return n;
        }

        public boolean isSameSet(Node a,Node b){
            return findFather(a) == findFather(b);
        }

        public void union(Node a,Node b){
            if(a == null || b == null){
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if(aDai != bDai){
                Integer aSetSize = sizeMap.get(aDai);
                Integer bSetSize = sizeMap.get(bDai);
                if(aSetSize <= bSetSize){
                    fatherMap.put(aDai,bDai);
                    sizeMap.put(bDai,aSetSize+bSetSize);
                    sizeMap.remove(aDai);
                }else{
                    fatherMap.put(bDai,aDai);
                    sizeMap.put(aDai,aSetSize+bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    //k算法 最小生成树
    public static Set<Edge> kruskalMS(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //优先级队列权重排序
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(new EdgeComparator());
        for(Edge edge : graph.edges){  // M 条边
            edges.add(edge);
        }
        HashSet<Edge> result = new HashSet<>();
        while(!edges.isEmpty()){
            Edge poll = edges.poll();
            if(!unionFind.isSameSet(poll.from,poll.to)){
                result.add(poll);
                unionFind.union(poll.from,poll.to);
            }
        }
        return result;
    }


    // 把所有点上的边进行排序，小到大加入就是最小生成树
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 哪些点被解锁出来了
        HashSet<Node> set = new HashSet<>();
        HashSet<Edge> result = new HashSet<>();

        HashSet<Edge> edgeSet = new HashSet<>(); // 边不重复记录
        for (Node node: graph.nodes.values()) {  // 随便取一node，可以先不看，目的是防森林
            if(!set.contains(node)){   // node不重复记录
                set.add(node);
                for(Edge edge : node.edges){   // 任意node的所有边加入优先级队列
                    if(!edgeSet.contains(edge)){
                        priorityQueue.add(edge);
                        edgeSet.add(edge);
                    }
                }
                while(!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();  // 弹出最小边
                    //以点到边加入优先队列
                    Node to = edge.to;
                    if(!set.contains(to)){
                        set.add(to);
                        result.add(edge);
                        for(Edge nextEdge : to.edges){
                            if(!edgeSet.contains(nextEdge)){
                                priorityQueue.add(nextEdge);
                                edgeSet.add(nextEdge);
                            }
                        }
                    }
                }
            }
            break;
        }
        return result;
    }

}
