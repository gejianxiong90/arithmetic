package core;

import test.SkipListMapCode2;

import java.util.ArrayList;
import java.util.List;

public class SkipListMap<K extends Comparable<K>,V> {

    SkipNode<K,V> head;

    int maxLevel;

    int size;

    public SkipListMap(){
        this.head = new SkipNode<>(null,null);
        head.next.add(null);
        this.maxLevel = 0;
        size = 0;
    }


    public V get(K key){
        SkipNode<K, V> node = mostRightLessNodeInTree(key);
        if(node != null && node.next.get(0).iskeyEqual(key)){
            return node.next.get(0).value;
        }
        return null;
    }


    public SkipNode<K, V> mostRightLessNodeInTree(K key) {
        SkipNode<K,V> cur = head;
        int level = this.maxLevel;
        while(level >= 0){
            cur = mostRightLessNodeInLevel(cur,level,key);
            level--;
        }
        return cur;
    }

    public SkipNode<K,V> mostRightLessNodeInLevel(SkipNode<K,V> cur,int level,K key){
        SkipNode<K,V> next = cur.next.get(level);
        while(next != null && next.isKeyLess(key)){
            cur = next;
            next = next.next.get(level);
        }
        return cur;
    }

    public SkipNode<K,V> mostRightLessNodeInLevel2(SkipNode<K,V> cur,int level,K key){
        SkipNode<K,V> pre = cur;
        SkipNode<K, V> node = cur.next.get(level);
        while(node != null && cur.isKeyLess(key)){
            pre = node;
            node = node.next.get(level);
        }

        return pre;
    }

    public void remove(K key){
        if(containsKey(key)){
            size--;
            int level = this.maxLevel;
            SkipNode<K,V> cur = head;
            while(level >= 0){
                cur = mostRightLessNodeInLevel(cur, level, key);
                SkipNode<K,V> node = cur.next.get(level);
                if(node != null && node.iskeyEqual(key) ){
                      cur.next.set(level,node.next.get(level));
                     // node = null;
                }
                if(level != 0 && cur == head && head.next.get(level) == null ){
                    head.next.remove(level); //删除当前层
                   this.maxLevel--;
                }
                level--;
            }
        }
    }

    public boolean containsKey(K key){
        SkipNode<K, V> node = mostRightLessNodeInTree(key);
        if(node == null){
            return false;
        }
        SkipNode<K, V> cur = node.next.get(0);

        if(cur != null && cur.key.compareTo(key) == 0){
            return true;
        }
        return false;
    }


    public void put2(K key,V value){
        if(key == null){
            return;
        }
        SkipNode<K, V> curNode = mostRightLessNodeInTree(key);
        SkipNode<K, V> next = curNode.next.get(0);
        if(next != null && next.iskeyEqual(key)){
            next.value = value;
            return;
        }
        size++;
        int curLlevel = 0;
        while(Math.random() < 0.5){
            curLlevel++;
        }
        while(this.maxLevel < curLlevel){
            this.head.next.add(null);
            this.maxLevel++;
        }
        SkipNode<K, V> newNode = new SkipNode<>(key, value);
        for(int i = 0 ; i <= curLlevel ;i++){
            newNode.next.add(null);
        }
        int level = this.maxLevel;
        SkipNode<K,V> curTop = head;
        while(level >=0){
            curTop = mostRightLessNodeInLevel(curTop, level, key);
            if(level <= curLlevel){
                newNode.next.set(level,curTop.next.get(level));
                curTop.next.set(level,newNode);
            }
            level--;
        }
    }

    public SkipNode<K,V> mostRightLessNodeInTree2(K key){
        SkipNode<K,V> cur = this.head;
        int level = this.maxLevel;

        while(level >= 0){
            cur = mostRightLessNodeInLevel2(cur, level, key);
            level--;
        }
        return cur;
    }



    public void put(K key,V value){
        if(key == null){
            return;
        }
        SkipNode<K, V> mostRighNode = mostRightLessNodeInTree(key);
        SkipNode<K, V> next = mostRighNode.next.get(0);
        if(next != null && next.iskeyEqual(key)){
            next.value = value;
            return;
        }

        size++;
        SkipNode<K, V> node = new SkipNode<>(key, value);
        int curLevel = 0;
        while (Math.random() < 0.5){
            curLevel++;
        }
        while (this.maxLevel < curLevel){
            head.next.add(null);
            maxLevel++;
        }
        for(int i = 0 ; i <= curLevel ;i++){
            node.next.add(null);
        }
        SkipNode<K,V> cur = head;
        int level = this.maxLevel;
        while(level >=0){
            cur = mostRightLessNodeInLevel(cur, level, key);
            if(level <= curLevel){
                node.next.set(level,cur.next.get(level));
                cur.next.set(level,node);
            }
            level--;
        }
    }

    public static class SkipNode<K extends Comparable<K>,V>{
        K key;
        V value;

        List<SkipNode<K,V>> next;

        public SkipNode(K key,V value){
            this.key = key;
            this.value = value;
            next = new ArrayList<SkipNode<K,V>>();

        }

        public boolean isKeyLess(K otherKey){
            if(otherKey == null){
                return false;
            }
            if(this.key == null){
                return true;
            }
            return  this.key.compareTo(otherKey) < 0;
        }

        public boolean iskeyEqual(K otherKey){
            if(this.key == null && otherKey == null){
                return true;
            }
            if(this.key == null || otherKey == null){
                return false;
            }
            return this.key.compareTo(otherKey) == 0;
        }
    }


    public static void main(String[] args) {
        SkipListMap<Integer, Integer> skipListMap = new SkipListMap<>();

        skipListMap.put(1,1);
        skipListMap.put(2,2);
        skipListMap.put(3,3);
        skipListMap.put(4,4);
        skipListMap.put(14,14);

        System.out.println(skipListMap.get(1));
        skipListMap.remove(1);
        System.out.println(skipListMap.get(1));

        System.out.println(skipListMap.get(14));
        skipListMap.put(14,15);
        System.out.println(skipListMap.get(14));

    }
}
