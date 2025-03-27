package core;

import java.util.*;

public class UnionSet2<V> {


    public static class Student{
        String a;
        String b;
        String c;

        public Student(String a,String b,String c){
            this.a = a;
            this.b = b;
            this.c = c;

        }
    }
    public static class Node<V>{
        V value;

        public Node(V value){
            this.value = value;
        }
    }

    Map<V,Node<V>> nodeMap = new HashMap<>();
    Map<Node<V>,Node<V>> parentMap = new HashMap<>();
    Map<Node<V>,Integer> size = new HashMap<>();



    public UnionSet2(List<V> list){
        for(V value : list){
            Node<V> vNode = new Node<>(value);
            nodeMap.put(value,vNode);
            parentMap.put(vNode,vNode);
            size.put(vNode,1);
        }
    }


    public Node<V> findFather(Node<V> node){
        Stack<Node<V>> stack = new Stack<>();
        while(node != parentMap.get(node)){
            stack.push(node);
            node =  parentMap.get(node);
        }
        while(!stack.isEmpty()){
            parentMap.put(stack.pop(),node);
        }
        return node;
    }

    public boolean isSameSet(V a,V b){
        if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
            return false;
        }
        return findFather(nodeMap.get(a)) == findFather(nodeMap.get(b));
    }

    public void union(V a,V b){
        if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
            return;
        }
        Node<V> aHead = findFather(nodeMap.get(a));
        Node<V> bHead = findFather(nodeMap.get(b));
        if(aHead == bHead){
            return;
        }
        Integer aSize = size.get(aHead);
        Integer bSize = size.get(bHead);

        Node<V> big = aSize >= bSize ? aHead : bHead;
        Node<V> small = big == aHead ? bHead : aHead;
        parentMap.put(small,big);
        size.put(big,aSize+bSize);
        size.remove(small);
    }

    public static void main(String[] args) {
        Map<String,Student> aMap = new HashMap<>();
        Map<String,Student> bMap = new HashMap<>();
        Map<String,Student> cMap = new HashMap<>();
        Student student1 = new Student("123", "456", "789");
        Student student2 = new Student("103", "456", "79");
        Student student3 = new Student("120", "46", "789");

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        UnionSet2<Student> studentUnionSet2 = new UnionSet2<Student>(students);
        for(Student student : students){
            if(aMap.containsKey(student.a)){
                studentUnionSet2.union(student,aMap.get(student.a));
            }else {
                aMap.put(student.a,student);
            }
            if(bMap.containsKey(student.b)){
                studentUnionSet2.union(student,bMap.get(student.b));
            }else {
                bMap.put(student.b,student);
            }
            if(cMap.containsKey(student.c)){
                studentUnionSet2.union(student,cMap.get(student.c));
            }else {
                cMap.put(student.c,student);
            }
        }

        boolean sameSet = studentUnionSet2.isSameSet(student1, student2);
        boolean sameSet1 = studentUnionSet2.isSameSet(student3, student2);
    }
}
