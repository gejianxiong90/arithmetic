package test;

import java.util.ArrayList;
import java.util.List;

public class SkipListMapCode {

    public static class SkipListNode<K extends Comparable<K>,V>{
        public K key;
        public V val;
        public List<SkipListNode<K,V>> nextNodes;

        public SkipListNode(K k,V v){
            key = k;
            val = v;
            nextNodes = new ArrayList<SkipListNode<K,V>>();
        }

        // 遍历的时候，如果是往右遍历到的null（next == null）遍历结束
        // 头（null） 头节点的null，认为最小
        // node -》 头，node（null，“”） node.isKeyLess(!null) true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey){
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey){
            return (key == null && otherKey == null)
                    || (key != null && otherKey!= null && key.compareTo(otherKey) == 0);
        }
    }


    public static class SkipListMap<K extends Comparable<K>,V>{
        private static final double probability = 0.5;
        private SkipListNode<K,V> head;
        private int size;
        private int maxLevel;

        public SkipListMap(){
            head = new SkipListNode<K,V>(null,null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        // 从最高层开始，一路找下去
        // 最终找到第0层key的最右边的节点
        private SkipListNode<K,V> mostRightLessNodeInTree(K key){
            if(key == null){
                return null;
            }
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            while ( level >= 0){
                cur = mostRightLessNodeInLevel(key,cur,level--);
            }
            return cur;
        }
        //在level层里，如何往右移动
        // 现在来到的节点是cur，来到了cur的level层，在level层上，找到key最后一个节点并返回
        private SkipListNode<K,V> mostRightLessNodeInLevel(K key,SkipListNode<K,V> cur,int level){
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while(next != null && next.isKeyLess(key)){
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key){
            if(key == null){
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        // 新增 或改value
        public void put(K key,V value){
            if(key == null){
                return ;
            }

            // 0层上最右一个
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0); // 最底层的

            if(find != null && find.isKeyEqual(key)){
                find.val = value;
            }else {
               size++;
               int newNodeLevel = 0;
               while (Math.random() < probability){  // 随机生成高度
                   newNodeLevel++;
               }

               while(newNodeLevel > maxLevel){  // 大于最大高度，需要将head的节点高度初始化。 修改最大高度
                   head.nextNodes.add(null);
                   maxLevel++;
               }

               SkipListNode<K, V> newNode = new SkipListNode<>(key, value); // 初始化新节点
               for (int i = 0; i <= newNodeLevel;i++){
                   newNode.nextNodes.add(null);
               }
               int level = maxLevel;
               SkipListNode<K,V> pre = head;
               // 将新节点插入有序表到合适的位置
               while ( level >= 0){
                   //level 层中，找到最右的 < key的节点
                  pre = mostRightLessNodeInLevel(key, pre, level);
                  if(level <= newNodeLevel){  // 设置该节点在自己的高度上做调整，高于自己节点高度的无需调整
                      newNode.nextNodes.set(level,pre.nextNodes.get(level));
                      pre.nextNodes.set(level,newNode);
                  }
                  level--;
               }
            }

        }


        public V get(K key){
            if(key == null){
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }


        public void remove(K key){
            if(containsKey(key)){
                size--;
                int level = maxLevel;
                SkipListNode<K,V> pre = head;
                while (level >= 0){
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    // 在这一层中，pre下一个就是key
                    // 在这一层中，pre的下一个key是>要删除key
                    if(next != null && next.isKeyEqual(key)){
                        pre.nextNodes.set(level,next.nextNodes.get(level));
                    }

                    if(level != 0 && pre ==head && pre.nextNodes.get(level) == null){
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }


        public K firstKey(){
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey(){
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            while (level >= 0){
                SkipListNode<K,V> next = cur.nextNodes.get(level);
                while ( next != null){
                    cur = next;
                    next = cur.nextNodes.get(level);
               }
               level--;
            }

            return cur.key;
        }


        public K ceilingKey(K key){
            if(key == null){
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }


        public K floorKey(K key){
            if(key == null){
                return null;
            }
            int level = maxLevel;
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

    }


    public static void main(String[] args) {
        double random = Math.random();
        while (random != 1){
            random = Math.random();
            System.out.println(random);
        }
        System.out.println(random);
    }
}
