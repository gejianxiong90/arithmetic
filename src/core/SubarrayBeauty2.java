package core;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SubarrayBeauty2 {


    public static void main(String[] args) {
        SubarrayBeauty2 subarrayBeauty2 = new SubarrayBeauty2();
        int[] nums = {-46,-34,-46};
        int[] subarrayBeauty = subarrayBeauty2.getSubarrayBeauty(nums, 3, 3);

        System.out.println(subarrayBeauty);
    }

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        if(nums.length < k){
            return new  int[]{0};
        }
        int n = nums.length;
        int[] res = new int[n-k+1];
        int index = 0;
        SbTreeMap sbTreeMap = new SbTreeMap();
        for(int i = 0 ; i < k - 1 ; i++){
            Node node = new Node(nums[i], i);
            sbTreeMap.put(node);
        }

        for(int i = k - 1 ; i < nums.length ;i++){
            Node node = new Node(nums[i], i);
            sbTreeMap.put(node);
            SbNode xth = sbTreeMap.getXth(x);
            if(xth != null && xth.node.value < 0){
               res[index] = xth.node.value;
            }
            index++;
            sbTreeMap.remove(new Node(nums[i - k + 1],i - k + 1));
        }

        return res;
    }

    public static class Node implements Comparable<Node> {
        Integer value;
        int index;

        public Node(int value,int index){
            this.value = value;
            this.index = index;
        }


        @Override
        public int compareTo(Node o) {
            return this.value == o.value ? this.index - o.index : this.value.compareTo(o.value);
        }
    }

    public static class SbNode implements Comparator<SbNode>{

        Node node;
        SbNode l;
        SbNode r;
        int size;

        public SbNode(Node node){
            this.node = node;
            this.size = 1;
        }

        @Override
        public int compare(SbNode o1, SbNode o2) {
            return o1.node.compareTo(o2.node);
        }
    }

    public static class SbTreeMap{
        SbNode root;

        private SbNode leftRoate(SbNode cur){
            SbNode r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 :cur.l.size) + 1;
            return r;
        }

        private SbNode rightRoate(SbNode cur){
            SbNode l = cur.l;
            cur.l =l.r;
            l.r = cur;
            l.size = cur.size;
            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 : cur.l.size) + 1;
            return l;
        }

        public void put(Node node){
            SbNode lastNode = findLastNode(node);
            if(lastNode != null && lastNode.node.compareTo(node) == 0 ){
                return;
            }
            root = add(root,node);
        }

        private SbNode add(SbNode cur,Node node){
            if(cur == null){
               return new SbNode(node);
            }
            cur.size++;
            if(cur.node.compareTo(node) > 0){
                cur.l = add(cur.l,node);
            }else {
                cur.r = add(cur.r,node);
            }
            return maintain(cur);
        }

        public void remove(Node node){
            SbNode lastNode = findLastNode(node);
            if(lastNode == null || lastNode.node.compareTo(node) != 0){
                return;
            }
            root = delete(root,node);
        }

        public SbNode delete(SbNode cur,Node node){
            if(cur == null){
                return null;
            }
            cur.size--;
            if(cur.node.compareTo(node) < 0){
                cur.r = delete(cur.r,node);
            }else if(cur.node.compareTo(node) > 0){
                cur.l = delete(cur.l,node);
            }else {
                if(cur.l == null && cur.r == null){
                    cur = null;
                }else if(cur.l == null && cur.r != null){
                    cur = cur.r;
                }else if(cur.l != null && cur.r == null){
                    cur = cur.l;
                }else {
                    SbNode des = cur.r;
                    SbNode pre = null;
                    des.size--;
                    while(des.l != null){
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if(pre != null){
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = (des.l == null ? 0 : des.l.size) + (des.r == null ? 0 : des.r.size) + 1;
                    cur = des;
                }
            }
            return cur;

        }

        private SbNode maintain(SbNode cur){
            if(cur == null){
                return null;
            }
            int l = cur.l == null ? 0 : cur.l.size;
            int r = cur.r == null ? 0 : cur.r.size;
            int rr = r != 0 && cur.r.r != null ? cur.r.r.size : 0;
            int rl = r != 0 && cur.r.l != null ? cur.r.l.size : 0;
            int ll = l != 0 && cur.l.l != null ? cur.l.l.size : 0;
            int lr = l != 0 && cur.l.r != null ? cur.l.r.size : 0;
            if(l < rr ){
                cur = leftRoate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if ( l < rl){
                cur.r = rightRoate(cur.r);
                cur = leftRoate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(r < ll){
                cur = rightRoate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(r < lr){
                cur.l = leftRoate(cur.l);
                cur = rightRoate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SbNode findLastNode(Node node){
            SbNode cur = root;
            SbNode pre = null;
            while(cur != null){
                pre = cur;
                if(cur.node.compareTo(node) == 0){
                    break;
                }else if(cur.node.compareTo(node) > 0){
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        public SbNode getXth(int x){
            SbNode cur = root;
            while(cur != null){
                int l = cur.l == null ? 0 : cur.l.size;
                if(l + 1 == x){
                    return cur;
                }else if(l >= x){
                    cur = cur.l;
                }else {
                    cur = cur.r;
                    x -= l + 1;
                }
            }
            return cur;
        }
    }


}
