package test;

import java.util.*;

public class UnionSet<V> {

    public HashMap<V,Node<V>> nodes = new HashMap<>();
    public HashMap<Node<V>,Node<V>> parents = new HashMap<>();
    public HashMap<Node<V>,Integer> sizeMap = new HashMap<>();

    public UnionSet(List<V> values){
        for(V cur : values){
            Node node = new Node(cur);
            nodes.put(cur,node);
            parents.put(node,node);
            sizeMap.put(node,1);
        }
    }

    public Node<V> findFather(Node<V> cur){
        Stack<Node<V>> path = new Stack<>();
        while(cur != parents.get(cur)){
            path.push(cur);
            cur = parents.get(cur);
        }
        while(!path.isEmpty()){
            parents.put(path.pop(),cur);
        }
        return cur;
    }

    public boolean isSameSet(V a,V b){
        if(!nodes.containsKey(a) || !nodes.containsKey(b)){
            return false;
        }
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    public void union(V a,V b){
        if(!nodes.containsKey(a) || !nodes.containsKey(b)){
            return;
        }
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        if(aHead == bHead){
            return;
        }
        Integer aSize = sizeMap.get(aHead);
        Integer bSize = sizeMap.get(bHead);
        Node<V> big = aSize >= bSize ? aHead : bHead;
        Node<V> small = big == aHead ? bHead : aHead;
        parents.put(small,big);
        sizeMap.put(big,aSize+bSize);
        sizeMap.remove(small);


    }


    public static class Node<V>{
        private V value;

        public Node(V v){
            this.value = v;
        }
    }


    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        UnionSet<Integer> integerUnionSet = new UnionSet<Integer>(integers);
        boolean sameSet = integerUnionSet.isSameSet(1, 2);
        System.out.println(sameSet);
        integerUnionSet.union(1,2);
        sameSet = integerUnionSet.isSameSet(1,2);
        System.out.println(sameSet);

    }
}
