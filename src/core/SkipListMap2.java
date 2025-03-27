package core;

import java.util.ArrayList;
import java.util.List;

public class SkipListMap2<K extends Comparable<K>,V> {


    SkipNode<K,V> head;

    int levelMax;

    int size;

    public SkipListMap2(){
        this.head = new SkipNode<>(null,null);
        this.head.nextNode.add(null);
        this.size = 0;
        this.levelMax = 0;

    }

    private SkipNode mostRightInTree(K key){
        int level = levelMax;
        SkipNode pre = head;
        while(level >= 0){
            pre = mostRightInLevel(key,level,pre);
            level--;
        }
        return pre;
    }

    private SkipNode mostRightInLevel(K key,int level,SkipNode<K,V> cur){
        SkipNode<K,V> pre = cur;
        while(cur != null && cur.isLessKey(key)) {
            pre = cur;
            cur = cur.nextNode.get(level);
        }
        return pre;
    }

    public boolean containsKey(K key){
        SkipNode<K,V> skipNode = mostRightInTree(key);
        SkipNode<K, V> next = skipNode.nextNode.get(0);
        if(next == null || !next.isEqualKey(key)){
            return false;
        }
        return true;
    }

    public void remove(K key){
       if(!containsKey(key)){
           return;
       }
        int level = levelMax;
        SkipNode<K,V> cur = head;
        this.size--;
        while(level >= 0){
            cur = mostRightInLevel(key,level,cur);
            SkipNode<K, V> curNode = cur.nextNode.get(level);
            if(curNode != null && curNode.isEqualKey(key)){
                cur.nextNode.set(level,curNode.nextNode.get(level));
            }
            if(cur == head && level != 0 && head.nextNode.get(level) == null){
                head.nextNode.remove(level);
                 levelMax--;
            }
            level--;
        }


    }

    public void put(K key,V value){
        if(key == null){
            return;
        }
        SkipNode<K,V> skipNode = mostRightInTree(key);
        SkipNode<K,V> next = skipNode.nextNode.get(0);
        if(next != null && next.isEqualKey(key)){
            next.value = value;
            return;
        }
        this.size++;
        SkipNode<K,V> newNode =new SkipNode<K,V>(key,value);
        int level = 0;
        while(Math.random() >= 0.5){
            level++;
        }
        while (level > levelMax){
            head.nextNode.add(null);
            levelMax++;
        }
        for(int i = 0 ; i <= level ;i++){
            newNode.nextNode.add(null);
        }
        SkipNode<K,V> cur = head;
        int curLevel = levelMax;
        while(curLevel >= 0){
             cur = mostRightInLevel(key,curLevel,cur);
             if(curLevel <= level){
                 newNode.nextNode.set(curLevel,cur.nextNode.get(curLevel));
                 cur.nextNode.set(curLevel,newNode);
             }
            curLevel--;
        }
    }


    public static class SkipNode<K extends Comparable<K>,V>{
        K key;
        V value;

        List<SkipNode<K,V>> nextNode;

        public SkipNode(K key,V value){
            this.key = key;
            this.value = value;
            nextNode = new ArrayList<SkipNode<K,V>>();
        }

        public boolean isLessKey(K otherKey){
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isEqualKey(K otherKey){
            if(this.key == null && otherKey == null){
                return true;
            }
            if(this.key == null || otherKey == null){
                return false;
            }
            return this.key.compareTo(otherKey) == 0;
        }
    }

    public V get(K key){
        SkipNode<K,V> skipNode = mostRightInTree(key);
        SkipNode<K, V> cur = skipNode.nextNode.get(0);
        if(cur != null && cur.isEqualKey(key)){
            return cur.value;
        }
        return null;
    }

    public static void main(String[] args) {
        SkipListMap2<Integer, Integer> skipListMap = new SkipListMap2<>();

        skipListMap.put(1,1);
        skipListMap.put(2,2);
        skipListMap.put(3,3);
        skipListMap.put(4,4);
        skipListMap.put(14,14);
        skipListMap.put(15,15);
        skipListMap.put(16,16);
        skipListMap.put(17,17);
        skipListMap.put(18,18);

        System.out.println(skipListMap.get(1));
        skipListMap.remove(1);
        System.out.println(skipListMap.get(1));

        System.out.println(skipListMap.get(14));
        skipListMap.put(14,15);
        System.out.println(skipListMap.get(14));

        System.out.println(skipListMap.size);

        System.out.println(skipListMap.levelMax);


        System.out.println(Integer.MIN_VALUE - Integer.MAX_VALUE);
    }
}
