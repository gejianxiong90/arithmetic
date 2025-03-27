package sz.code7;

import java.util.HashMap;

public class LeastRecentlyUsedCache {

    public static class Node<K,V>{
        public K key;
        public V value;
        public Node<K,V> last;
        public Node<K,V> next;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }


    public static class NodeDoubleLinkedList<K,V>{
        private Node<K,V> head;
        private Node<K,V> tail;

        public NodeDoubleLinkedList(){
            this.head = null;
            this.tail = null;
        }

        /**
         * 如果一个新的节点加入，放到尾巴上去
         * @param newNode
         */
        public void addNode(Node<K,V> newNode){
            if(newNode == null){
                return;
            }
            if(head == null){
                this.head = newNode;
                this.tail = newNode;
            }else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                tail = newNode;
            }
        }

        public void moveNodeToTail(Node<K,V> node){
            if(this.tail == node){
                return;
            }
            if(this.head == node){
               this.head = node.next;
               this.head.last = null;
            }else {
             node.last.next = node.next;
             node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        public Node<K,V> removeHead(){
            if(this.head == null){
                return null;
            }
            Node<K, V> res = this.head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }


    public static class MyCache<K,V>{
        private HashMap<K,Node<K,V>> keyNodeMap;
        private NodeDoubleLinkedList<K,V> nodeList;
        private Integer capacity = null;


        public MyCache(int cap){
            if(cap < 1){
                throw new RuntimeException("should be more than 0");
            }
            keyNodeMap = new HashMap<K,Node<K,V>>(cap);
            nodeList = new NodeDoubleLinkedList<K,V>();
            capacity = cap;
        }


        public V get(K k){
            if(keyNodeMap.containsKey(k)){
                Node<K, V> kvNode = keyNodeMap.get(k);
                nodeList.moveNodeToTail(kvNode);
                return kvNode.value;
            }
            return null;
        }

        public void set(K key,V value){
            if(keyNodeMap.containsKey(key)){
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }else {
                if(keyNodeMap.size() == capacity){
                    removeMostUnsedCache();
                }
                Node node = new Node(key, value);
                keyNodeMap.put(key,node);
                nodeList.addNode(node);
            }
        }

        private void removeMostUnsedCache(){
            Node<K, V> kvNode = nodeList.removeHead();
            keyNodeMap.remove(kvNode.key);
        }
    }
}
