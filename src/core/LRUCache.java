package core;

import java.util.*;

public class LRUCache {

    MyCache cache = null;
    public LRUCache(int capacity) {
        cache = new MyCache(capacity);
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node != null){
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        cache.put(key,value);
    }


    public static class MyCache{
        Map<Integer,Node> map = new HashMap<Integer,Node>();
        DoubleList dl = new DoubleList();
        int capacity;

        public MyCache(int capacity){
            if(capacity < 1){
                throw new RuntimeException("must more than 1");
            }
            this.capacity = capacity;
        }

        public Node get(int key){
            if(!map.containsKey(key)){
                return null;
            }
            Node val = map.get(key);
            dl.moveToTail(val);
            return val;
        }

        public void put(int key,int value){
            if(map.containsKey(key)){
                Node node = map.get(key);
                node.val = value;
                map.put(key,node);
                dl.moveToTail(node);
            }else{
                if(map.size() == capacity){
                    Node head = dl.removeHead();
                    if(head != null){
                        map.remove(head.key);
                    }
                }
                Node newNode = new Node(key,value);
                map.put(key,newNode);
                dl.addNode(newNode);
            }
        }
    }

    public static class Node{
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key,int val){
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    public static class DoubleList{
        Node head;
        Node tail;

        public void addNode(Node node){
            if(this.head == null){
                this.head = node;
                this.tail = node;
            }else {
                node.pre = this.tail;
                this.tail.next = node;
                this.tail = node;
            }
        }

        public void moveToTail(Node node){
            if(this.tail == node){
                return;
            }
            if(this.head == node){
                this.head = node.next;
                this.head.pre = null;
            }else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            node.pre = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }
        public Node removeHead(){
            if(this.head == null){
                return null;
            }
            Node node = this.head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;

            }else {
                this.head = node.next;
                node.next = null;
                if(this.head != null){
                    this.head.pre = null;

                }
            }
            return node;
        }
    }

    public static void qucikSort(Integer[] arr,int L,int R){
        if(arr == null || L >= R){
            return;
        }
        int povit = arr[L + (int)(Math.random() * ( R - L + 1))];
        int[] range = partition(arr,L,R,povit);
        qucikSort(arr,L,range[0]-1);
        qucikSort(arr,range[1]+1,R);

    }

    public static int[] partition(Integer[] arr,int L , int R,int povit){

        int index = L;
        int less = L - 1;
        int more = R + 1;
        while(index < more){
            if(arr[index] < povit){
                swap(arr,index++,++less);
            }else if(arr[index] > povit){
                swap(arr,index,--more);
            }else {
                index++;
            }
        }
        return new int[]{less+1,more - 1};
    }

    public static void swap(Integer[] arr , int i , int j){
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);;
         lruCache.get(2);
        lruCache.put(4,4);;
        int i = lruCache.get(1);
        System.out.println(i);

        List<Integer> list = new ArrayList();
        list.add(-1);
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(0);
        Integer[] arrInt = new Integer[list.size()];
        list.toArray(arrInt);

        System.out.println(arrInt);

        qucikSort(arrInt,0,4);

        System.out.println(arrInt);




    }

}
