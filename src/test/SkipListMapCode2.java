package test;

import java.util.ArrayList;
import java.util.List;

public class SkipListMapCode2 {

    public static class SkipNode<K extends Comparable<K>,V>{
        public K key;
        public V value;
        public List<SkipNode<K,V>> nextNode;


        public SkipNode(K k, V v){
            this.key = k;
            this.value = v;
            nextNode = new ArrayList<SkipNode<K,V>>();
        }

        public boolean isKeyLess(K otherKey){
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEquak(K otherKey){
            return( key == null && otherKey == null) ||
                    (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }

    }

    public static class SkipListMap<K extends Comparable<K> ,V>{
        public static double p = 0.5;
        public  int maxLevel = 0;
        public int size;
        public SkipNode<K,V> head;
        public SkipListMap(){
            head = new SkipNode<>(null,null);
            head.nextNode.add(null);
            size = 0;
            maxLevel = 0;
        }


        private SkipNode<K,V> mostRighrLessNodeInTree(K key){
            int level = maxLevel;
            SkipNode<K,V> cur = head;
            while (level >= 0){
                cur = mostRightLessNodeInLevel(cur, level, key);
                level--;
            }
            return cur;
        }

        private SkipNode<K,V> mostRightLessNodeInLevel(SkipNode<K,V> cur,int level,K key){
            SkipNode<K,V> next = cur.nextNode.get(level);
            while(next != null && next.isKeyLess(key)){
                cur = next;
                next = next.nextNode.get(level);
            }

            return cur;
        }


        private boolean containsKey(K key){
            SkipNode<K, V> kvSkipNode = mostRighrLessNodeInTree(key);
            return kvSkipNode == null ? false : kvSkipNode.nextNode.get(0).isKeyEquak(key);
        }


        public V get(K key){
            if(key == null){
                return null;
            }
            SkipNode<K, V> less = mostRighrLessNodeInTree(key);
            return less == null ? null : (less.nextNode.get(0).isKeyEquak(key) ? less.nextNode.get(0).value : null) ;
        }

        public void remove(K key){
            if(!containsKey(key)){
                return;
            }
            size--;
            int level = maxLevel;
            SkipNode<K,V> pre = head;
            while (level >= 0){
               pre = mostRightLessNodeInLevel(pre, level, key);
               SkipNode<K, V> next = pre.nextNode.get(level);

               if(next != null && next.isKeyEquak(key)){
                   pre.nextNode.set(level,next.nextNode.get(level));
               }

               if(level != 0 && pre == head && pre.nextNode.get(level) == null){
                   pre.nextNode.remove(level);
                   maxLevel--;
               }
               level--;

            }

        }


        public void put(K key,V value){

            SkipNode<K, V> less = mostRighrLessNodeInTree(key);
            SkipNode find = less.nextNode.get(0);

            if(find != null && find.isKeyEquak(key)){
                find.value = value;
            }else {
                size++;
                int newNodeLevel = 0;
                if(Math.random() < p){
                    newNodeLevel++;
                }

                while (maxLevel < newNodeLevel){
                    maxLevel++;
                    head.nextNode.add(null);
                }

                SkipNode<K, V> newNode = new SkipNode<>(key, value);
                for(int i = 0;i <= newNodeLevel;i++){
                    newNode.nextNode.add(null);
                }

                int level = maxLevel;
                SkipNode<K,V> pre = head;

                while (level >= 0){
                    pre = mostRightLessNodeInLevel(pre, level, key);

                    if(level <= newNodeLevel){
                        newNode.nextNode.set(level,pre.nextNode.get(level));
                        pre.nextNode.set(level,newNode);
                    }
                    level--;
                }
            }
        }


        public int size(){
            return size;
        }

    }


    public static void main(String[] args) {
        SkipListMap<Integer, Integer> skipList = new SkipListMap<Integer,Integer>();
        skipList.put(1,1);
        skipList.put(2,2);
        skipList.put(3,3);


        System.out.println(skipList.size());

        skipList.put(4,4);
        skipList.put(4,5);

        System.out.println(skipList.size());

        System.out.println(skipList.get(3));
    }
}
