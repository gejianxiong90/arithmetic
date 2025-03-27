package core;

public class SizeBalancedTree {

    public static class SBTNode<K extends Comparable<K> , V>{

        K key;
        V value;
        SBTNode<K,V> left;
        SBTNode<K,V> right;
        int size;

      public SBTNode(K key,V v){
        this.key = key;
        this.value = v;
        this.size = 1;
      }
    }

    public static class SBTMap<K extends Comparable<K> ,V>{
        SBTNode<K,V> root;


        public void put(K key,V value){
            if(key == null){
                throw  new RuntimeException("invalid param");
            }
            SBTNode<K, V> node = findLastIndex(key);
            if(node != null && node.key.compareTo(key) == 0){
                node.value = value;
            }else {
                root = add(root,key,value);
            }
        }

        public SBTNode<K,V> findLastIndex(K key){
            if(key == null){
                return null;
            }
            SBTNode<K,V> pre = root;
            SBTNode<K,V> cur = root;
            while(cur != null){
                pre = cur;
                if(key.compareTo(cur.key) == 0){
                    break;
                }else if(key.compareTo(cur.key) < 0){
                    cur = cur.left;
                }else {
                    cur = cur.right;;
                }
            }
            return pre;
        }

        private SBTNode<K,V> add(SBTNode<K,V> cur ,K key,V value){
            if(cur == null){
                return new SBTNode<>(key,value);
            }else {
                cur.size++;
                if(key.compareTo(cur.key) > 0){
                    cur.right = add(cur.right,key,value);
                }else if(key.compareTo(cur.key) < 0){
                    cur.left = add(cur.left,key,value);
                }
                return maintain(cur);
            }
        }

        private SBTNode<K,V> rightRoate(SBTNode<K,V> cur){
            SBTNode<K,V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return leftNode;
        }

        private SBTNode<K,V> leftRoate(SBTNode<K,V> cur){
            SBTNode<K,V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return rightNode;
        }

        public SBTNode<K, V> maintain(SBTNode<K,V> cur){
            if(cur == null){
                return null;
            }
            int ll = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int lr = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rr = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            int rl = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int l = cur.left != null ? cur.left.size : 0;
            int r = cur.right != null ? cur.right.size : 0;

            if(ll > r){
                cur = rightRoate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }else if(lr > r){
                cur.left = leftRoate(cur.left);
                cur = rightRoate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }else if(rr > l){
                cur = leftRoate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }else if (rl > l){
                cur.right = rightRoate(cur.right);
                cur = leftRoate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }




        public void remove(K key){
            if(key == null){
                return;
            }
            root = delete(root,key);
        }

        public V get(K key){
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if(lastIndex != null && lastIndex.key.compareTo(key) == 0){
                return lastIndex.value;
            }
            return null;
        }

        private SBTNode<K,V> delete(SBTNode<K,V> cur,K key){
            cur.size--;
            if(key.compareTo(cur.key) > 0 ){
                cur.right = delete(cur.right,key);
            }else if(key.compareTo(cur.key) < 0 ){
                cur.left = delete(cur.left,key);
            }else {
                if(cur.left == null && cur.right != null){
                    cur = cur.right;
                }else if(cur.left != null && cur.right == null){
                    cur = cur.left;
                }else if(cur.left == null && cur.right == null){
                    cur = null;
                } else if(cur.right != null && cur.left != null){
                    SBTNode<K,V> pre = null;
                    SBTNode<K,V> des = cur.right;
                    des.size--;
                    while(des.left != null){
                        pre = des;
                        des = des.left;
                        des.size--;
                    }
                    if(pre != null){
                        pre.left = des.right;
                        des.right = cur.right;
                    }
                    des.left = cur.left;
                    des.size = des.left.size + (des.right == null ? 0 : des.right.size) +1;
                    cur = des;
                }
            }
            return cur;
        }


        public void print(){
            print(root);
        }
        public void print(SBTNode<K,V> node){
            if(node == null){
                return;
            }
            print(node.left);
            System.out.println(node.value);
            print(node.right);
        }
    }


    public static void main(String[] args) {
        SBTMap<Integer, Integer> sbtMap = new SBTMap<>();
        sbtMap.put(1,1);
        sbtMap.put(2,2);
        sbtMap.put(3,3);
        sbtMap.put(4,4);
        sbtMap.put(6,6);
        sbtMap.put(7,7);
        sbtMap.put(8,8);
        sbtMap.put(9,9);
        sbtMap.put(16,16);
        sbtMap.put(17,17);
        sbtMap.put(18,18);
        sbtMap.put(19,19);
        sbtMap.put(30,30);
        System.out.println(sbtMap.get(2));
        sbtMap.put(2,3);
        System.out.println(sbtMap.get(2));

        sbtMap.remove(2);
        System.out.println(sbtMap.get(2));


        System.out.println("--------");

        sbtMap.print();
    }
}
