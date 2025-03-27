package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MergeUser {

    public static class Node<V>{
        V value;

        public Node(V value){
            this.value = value;
        }
    }

    public static class UnionSet<V>{

        // v - > 节点
        public HashMap<V,Node<V>> nodes;
        // 父节点映射关系
        public HashMap<Node<V>,Node<V>> parents;
        // 只有一个点，它是代表点，才有记录
        public HashMap<Node<V>,Integer> sizeMap;

        public UnionSet(List<V> values){
            for ( V value: values) {
                Node<V> vNode = new Node<V>(value);
                nodes.put(value,vNode);
                parents.put(vNode,vNode);
                sizeMap.put(vNode,1);
            }
        }

        // 从点cur开始，一直往上找，找到不能再往上的代表点，返回
        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> path = new Stack<Node<V>>();
            while(cur != parents.get(cur)){
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur节点
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

            if(aHead != bHead){
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if(aSetSize >= bSetSize){
                    parents.put(bHead,aHead);
                    sizeMap.put(aHead,aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                }else{
                    parents.put(aHead,bHead);
                    sizeMap.put(bHead,aSetSize +bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        public int sizeNum(){
            return sizeMap.size();
        }
    }

    public static class User{
        String a;
        String b;
        String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        //(1,19,13) (2,10,37) (400,500,37)
        //如果两个user，a字段一样、或者b字段一样，或者c字段一样，就认为是一个人
        // 请合并users，返回合并之后的用户数量
        public static int mergeUser(List<User> users){
            UnionSet<User> unionFind = new UnionSet<User>(users);
            HashMap<String, User> mapA = new HashMap<String, User>();
            HashMap<String, User> mapB = new HashMap<String, User>();
            HashMap<String, User> mapC = new HashMap<String, User>();

            for (User user: users) {
                if(mapA.containsKey(user.a)){
                    unionFind.union(user,mapA.get(user.a));
                }else{
                    mapA.put(user.a,user);
                }
                if(mapB.containsKey(user.b)){
                    unionFind.union(user,mapB.get(user.b));
                }else{
                    mapB.put(user.b,user);
                }
                if(mapC.containsKey(user.c)){
                    unionFind.union(user,mapC.get(user.c));
                }else{
                    mapC.put(user.c,user);
                }
            }
            return unionFind.sizeNum();
        }
    }
}
