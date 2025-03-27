package test;

public class SizeBalancedTreeMapCode {

    public static class SBTNode<K extends Comparable<K>,V>{
        public K key;
        public V value;
        public SBTNode<K,V> l;
        public SBTNode<K,V> r;
        public int size;

        public SBTNode(K key,V value){
            this.key = key;
            this.value = value;
            size = 1;
        }
    }


    public static class SizeBalancedTreeMap<K extends Comparable<K>,V>{
        private SBTNode<K,V> root;

        private SBTNode<K,V> rightRotate(SBTNode<K,V> cur){
            SBTNode<K, V> leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTNode<K,V> leftRotate(SBTNode<K,V> cur){
            SBTNode<K, V> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return rightNode;
        }


        private SBTNode<K,V> maintain(SBTNode<K,V> cur){
            if(cur == null){
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;

            if(leftLeftSize > rightSize){
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(leftRightSize > rightSize){
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(rightRightSize > leftSize){
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if(rightLeftSize > leftSize){
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }



        public void put(K key,V value){
            if(key == null){
                throw new RuntimeException("invalid param");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if(lastNode != null && lastNode.key.compareTo(key) == 0){
                lastNode.value = value;
            }else {
               // SBTNode<K, V> newSbtNode = new SBTNode<>(key, value);
                root = add(root,key,value);
            }

        }

        public V get(K key){
            if (key == null){
                throw new RuntimeException("invalid param");
            }
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if(lastIndex != null && lastIndex.key.compareTo(key) == 0){
                return lastIndex.value;
            }else {
                return null;
            }
        }

        private SBTNode<K,V> add(SBTNode<K,V> cur,K key,V value){
            if(cur == null){
                return new SBTNode<K,V>(key,value);
            }else {
                cur.size++;
                if(key.compareTo(cur.key) < 0){
                    cur.l = add(cur.l,key,value);
                }else{
                    cur.r = add(cur.r,key,value);
                }
                return maintain(cur);
            }
        }


        private SBTNode<K,V> findLastIndex(K key){
            SBTNode<K, V> pre = this.root;
            SBTNode<K, V> cur = this.root;
            while(cur != null){
                pre = cur;
                if(key.compareTo(cur.key) == 0){
                    break;
                }else if (key.compareTo(cur.key) < 0){
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }

            return pre;
        }



        private boolean containsKey(K key){
            if(key == null){
                throw new RuntimeException("invalid params");
            }
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if(lastIndex != null && lastIndex.key.compareTo(key) == 0){
                return true;
            }
            return false;
        }


        public void remove(K key){
            if(containsKey(key)){

                root = delete(root,key);
            }

        }


        private SBTNode<K,V> delete(SBTNode<K,V> cur ,K key){
            cur.size--;
            if(key.compareTo(cur.key) < 0){
                cur.l = delete(cur.l,key);
            }else if(key.compareTo(cur.key) > 0){
                cur.r = delete(cur.r,key);
            }else if(cur.key.compareTo(key) == 0){
                if(cur.l != null && cur.r == null){
                    cur = cur.l;
                }else if(cur.l == null && cur.r ==null){
                    cur = null;
                }else if(cur.l == null && cur.r != null){
                    cur = cur.r;
                }else { // 左右都不空
                    SBTNode<K,V> pre = null;
                    SBTNode<K,V> des = cur.r;
                    while(des.l != null){
                        pre =des;
                        des = des.l;
                        des.size--;
                    }
                    if(pre != null){
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    cur = des;
                }
            }
            return cur;
        }


        public int size(){
            return root == null ? 0 :root.size;
        }
    }


    public static void main(String[] args) {
        SizeBalancedTreeMap<String, Integer> sizeBalancedTreeMap = new SizeBalancedTreeMap<>();
        sizeBalancedTreeMap.put("1",1);
        sizeBalancedTreeMap.put("2",2);
        sizeBalancedTreeMap.put("3",7);
        sizeBalancedTreeMap.put("5",8);

        System.out.println("--->"+sizeBalancedTreeMap.size());

        sizeBalancedTreeMap.remove("2");


        System.out.println("--->"+sizeBalancedTreeMap.size());

        System.out.println(sizeBalancedTreeMap.get("123"));
    }
}
