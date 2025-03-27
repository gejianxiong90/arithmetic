package core;

public class SizeBanlanceTree<K extends Comparable<K>,V> {

    SBNode<K,V> root;

    private SBNode<K,V> leftRoate(SBNode cur){
        SBNode right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return right;
    }

    private SBNode<K,V> rightRoate(SBNode cur){
        SBNode left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.size = cur.size;
        cur.size = (cur.left == null ?  0 : cur.left.size ) + (cur.right == null ? 0 : cur.right.size) + 1;
        return left;
    }

    private SBNode<K,V> lastNode(K key){
        if(key == null){
            return null;
        }
        SBNode<K,V> cur = this.root;
        SBNode<K,V> pre = null;
        while(cur != null){
            pre = cur;
            if(cur.key.compareTo(key) == 0 ){
                break;
            }else if(cur.key.compareTo(key) > 0){
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        return pre;
    }

    public SBNode<K,V> add(SBNode<K,V> cur,K key,V value){
        if(cur == null){
            return new SBNode(key,value);
        }else {
            cur.size++;
            if(cur.key.compareTo(key) > 0 ){
                cur.left =  add(cur.left,key,value);
            }else if(cur.key.compareTo(key) < 0){
                cur.right = add(cur.right,key,value);
            }
        }
        return maintain(cur);
    }


    public void put(K key,V value){
        if(key == null){
            throw new RuntimeException("invalid params");
        }
        SBNode<K, V> curNode = lastNode(key);
        if(curNode != null && curNode.key.compareTo(key) == 0){
            curNode.val = value;
            return;
        }
        root = add(root,key,value);
    }

    public V get(K key){
        SBNode<K, V> kvsbNode = lastNode(key);
        if(kvsbNode == null || kvsbNode.key.compareTo(key) != 0){
            return null;
        }
        return kvsbNode.val;
    }

    public void remove(K key){
        SBNode<K, V> kvsbNode = lastNode(key);
        if(kvsbNode == null || kvsbNode.key.compareTo(key) != 0){
            return;
        }
       root =  delete(root,key);
    }

    private SBNode delete(SBNode<K,V> cur,K key){
        cur.size--;
        if(cur.key.compareTo(key) > 0 ){
            cur.left = delete(cur.left,key);
        }else if(cur.key.compareTo(key) < 0){
            cur.right = delete(cur.right,key);
        }else {
            if(cur.right == null && cur.left == null){
                cur = null;
            }else if(cur.right == null && cur.left != null){
                cur = cur.left;
            }else if(cur.right != null && cur.left == null){
                cur = cur.right;
            }else {
                SBNode<K,V> pre = null;
                SBNode<K,V> dis = cur.right;
                while(dis.left != null){
                    pre = dis;
                    dis = dis.left;
                    dis.size--;
                }

                if(pre != null){
                    pre.left = dis.right;
                    dis.right = cur.right;
                }
                dis.left = cur.left;
                dis.size = (dis.right == null ? 0 : dis.right.size) + (dis.left == null ? 0 : dis.left.size) + 1;
                cur = dis;
            }
        }
        return cur;

    }




    private  SBNode<K,V> maintain(SBNode<K,V> cur){
        if(cur == null){
            return null;
        }
        int r = cur.right != null ? cur.right.size : 0;
        int l = cur.left != null ? cur.left.size : 0;
        int ll = cur.left != null && cur.left.left != null ?  cur.left.left.size : 0;
        int lr =  cur.left != null && cur.left.right != null ?  cur.left.right.size : 0;
        int rr =  cur.right != null && cur.right.right != null ?  cur.right.right.size : 0;
        int rl = cur.right != null && cur.right.left != null ?  cur.right.left.size : 0;
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
        }else if(rl > l){
            cur.right = rightRoate(cur.right);
            cur = leftRoate(cur);
            cur.right =  maintain(cur.right);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        }
        return cur;
    }



    public void print(){
       print(root);
    }

    private void print(SBNode cur){
        if(cur == null){
            return;
        }
        print(cur.left);
        System.out.println(cur.val);
        print(cur.right);
    }







    public static class SBNode<K extends Comparable<K>,V>{
        K key;
        V val;
        SBNode<K,V> left;
        SBNode<K,V> right;
        int size;

        public SBNode(K key,V value){
            this.key = key;
            this.val = value;
            this.size = 1;
        }
    }


    public static void main(String[] args) {
        SizeBanlanceTree<Integer, Integer> sbtMap = new SizeBanlanceTree<>();

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
