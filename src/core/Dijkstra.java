package core;

import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

    public static class Node{
        Edge[] next;
        int in;
        int out;
    }

    public static class Edge{
        Node from;
        Node to;
        int weight;
    }
    public static class NodeRecord{
        Node node;
        int distance;

        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{
        Node[] nodes;
        Map<Node,Integer> nodeIndex;
        int size;
        Map<Node,Integer> distanceMap;

        public NodeHeap(int size){
            nodes = new Node[size];
            this.size = 0;
            nodeIndex = new HashMap<>();
            distanceMap = new HashMap<>();
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if(isInHeap(node)){
                distanceMap.put(node,Math.min(distance,distanceMap.get(node)));
                heapInsert(node,nodeIndex.get(node));
            }
            if(!isEntered(node)){
                nodes[size] = node;
                nodeIndex.put(node,size);
                distanceMap.put(node,distance);
                heapInsert(node,size++);
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0,size-1);
            nodeIndex.put(nodes[size - 1] , -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1 ] = null;
            heapify(0,--size);

            return nodeRecord;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public boolean isEntered(Node node){
            return nodeIndex.containsKey(node);
        }

        public boolean isInHeap(Node node){
            return isEntered(node) && nodeIndex.get(node) != -1;
        }

        public void heapInsert(Node node,int index){
            while(distanceMap.get(nodes[(index - 1) / 2])>distanceMap.get( nodes[index])){
                    swap((index - 1) / 2, index);
                    index = (index - 1) / 2;
            }
        }

        public void heapify(int index,int size){
            int left = index << 1 | 1;
            while(left < size){
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;
                if(smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index << 1 | 1;
            }
        }


        public void swap(int i , int j){
            nodeIndex.put(nodes[i],j);
            nodeIndex.put(nodes[j],i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }


        public Map<Node,Integer> dijkstra(Node node,int size){
            NodeHeap nodeHeap = new NodeHeap(size);
            nodeHeap.addOrUpdateOrIgnore(node,0);
            HashMap<Node, Integer> result = new HashMap<>();
            while(!nodeHeap.isEmpty()){
                NodeRecord pop = nodeHeap.pop();
                Node cur = pop.node;
                int distance = pop.distance;
                for(Edge edge : cur.next){
                    nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight + distance);
                }
                result.put(cur,distance);
            }
            return result;
        }
    }
}
