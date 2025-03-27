package core;

import java.util.*;

public class UnionSet<V> {

    public  Map<V,Node<V>> nodeMap = new HashMap<>();
    public Map<Node<V>,Node<V>> parent = new HashMap<>();
    public Map<Node<V>,Integer> size = new HashMap<>();


    public UnionSet(List<V> list){
        for(V value : list){
           Node node =  new Node(value);
           nodeMap.put(value,node);
           parent.put(node,node);
           size.put(node,1);
        }
    }

    public Node findFather(Node<V> node){
        Stack<Node<V>> stack = new Stack<>();
        while(node != parent.get(node)){
            stack.push(node);
            node = parent.get(node);
        }

        while(!stack.isEmpty()){
            Node<V> pop = stack.pop();
            parent.put(pop,node);
        }
        return node;
    }

    public boolean isSameSet(V a,V b){
      if(nodeMap.get(a) == null || nodeMap.get(b) == null){
          return false;
      }

      return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
    }

    public void union(V a,V b){
        if(nodeMap.get(a) == null || nodeMap.get(b) == null){
            throw new RuntimeException("node is not exist");
        }
        Node aHead = findFather(nodeMap.get(a));
        Node bHead = findFather(nodeMap.get(b));
        if(aHead == bHead){
            return;
        }
        int aSize = size.get(aHead);
        int bSize = size.get(bHead);
        Node big = aSize > bSize ? aHead : bHead;
        Node small = big == aHead ? bHead : aHead;
        parent.put(small,big);
        size.put(big,aSize+bSize);
        size.remove(small);

    }


    public static class Node<V>{
        V value;

        public Node(V value){
            this.value = value;
        }
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        UnionSet<Integer> unionSet = new UnionSet<>(list);

        boolean sameSet = unionSet.isSameSet(1, 2);

        System.out.println(sameSet);

        unionSet.union(1,2);
        unionSet.union(1,3);

        System.out.println(unionSet.isSameSet(3, 2));

    }
}
