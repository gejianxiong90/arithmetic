package core;

public class MeidianSlidingWindows {

    public static void main(String[] args) {
        MeidianSlidingWindows meidianSlidingWindows = new MeidianSlidingWindows();
        int[] a = {-2147483648,-2147483648,2147483647,-2147483648,1,3,-2147483648,-100,8,17,22,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};

        double[] doubles = meidianSlidingWindows.medianSlidingWindow(a, 6);

        System.out.println(doubles);

    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        SbTreeMap sbTreeMap = new SbTreeMap();
        for(int i = 0 ; i < k - 1 ; i++){
           sbTreeMap.put(new Node(nums[i],i));
       }
       double[] res = new double[nums.length - k + 1];
        int index = 0;
        for(int i = k - 1 ; i <nums.length ;i++){
            sbTreeMap.put(new Node(nums[i],i));
            if((sbTreeMap.root.size & 1) == 1){
                res[index++] = sbTreeMap.getIndexValue(sbTreeMap.root.size / 2 + 1);
            }else {
                res[index++] =  ((double)sbTreeMap.getIndexValue(sbTreeMap.root.size / 2 + 1) + (double)sbTreeMap.getIndexValue(sbTreeMap.root.size / 2 )) /2;
            }
            sbTreeMap.remove(new Node(nums[i - k + 1],i - k + 1));
        }
        return res;
    }

    public static class Node implements Comparable<Node>{
        int key;
        int index;

        public Node(int key,int index){
            this.key = key;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if(this.key != o.key){
                return Integer.valueOf(this.key).compareTo(Integer.valueOf( o.key));
            }
            return this.index - o.index;
        }
    }

    public static class SbNode{
        Node node;
        int size;
        SbNode l;
        SbNode r;

        public SbNode(Node node){
            this.node = node;
            this.size = 1;
        }
    }


    public static class SbTreeMap{
        SbNode root;

        void put(Node node){
           // SbNode cur = lastNode(node);
            root = add(root,node);
        }

        SbNode add(SbNode cur,Node newNode){   // {5,2,2,7,3,7,9,0,2,3};
            if(cur == null){
                return new SbNode(newNode);
            }else {
                cur.size++;
                if(newNode.compareTo(cur.node) > 0){
                    cur.r = add(cur.r,newNode);
                }else {
                    cur.l = add(cur.l,newNode);
                }
                return maintain(cur);
            }

        }

        public void remove(Node node){
            SbNode sbNode = lastNode(node);
            if(sbNode == null || sbNode.node.compareTo(node) != 0){
                return;
            }

            root = delete(root,node);
        }

        public SbNode getKth(SbNode cur,int kth){
            if(cur == null){
                return null;
            }
            if((cur.l != null ? cur.l.size : 0 ) + 1 == kth){
                return cur;
            }
            if((cur.l != null ? cur.l.size : 0 ) >= kth){
                return getKth(cur.l,kth);
            }else {
                return getKth(cur.r,kth - (cur.l != null ? cur.l.size : 0) - 1);
            }

        }

        public int getIndexValue(int index){
            SbNode kth = getKth(root, index);
            if(kth != null){
                return kth.node.key;
            }else {
                return Integer.MIN_VALUE;
            }
        }

        public SbNode delete(SbNode cur,Node node){
            cur.size--;
            if(cur.node.compareTo(node) > 0){
                cur.l = delete(cur.l,node);
            }else if(cur.node.compareTo(node) < 0){
                cur.r = delete(cur.r,node);
            }else {
                if(cur.l == null && cur.r == null){
                    cur = null;
                }else if(cur.l != null && cur.r == null){
                    cur = cur.l;
                }else if(cur.l == null && cur.r != null){
                    cur = cur.r;
                }else {
                    SbNode pre = null;
                    SbNode des = cur.r;
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

        public SbNode rightRoate(SbNode cur){
            SbNode l = cur.l;
            cur.l = l.r;
            l.r = cur;
            l.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return l;
        }

        public SbNode leftRoate(SbNode cur){
            SbNode r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return r;
        }

        public SbNode maintain(SbNode cur){
            if(cur == null){
                return null;
            }
            int l = cur.l == null ? 0 : cur.l.size;
            int r = cur.r == null ? 0 : cur.r.size;
            int ll = l > 0 && cur.l.l != null ? cur.l.l.size : 0;
            int lr = l > 0 && cur.l.r != null ? cur.l.r.size : 0;
            int rr = r > 0 && cur.r.r != null ? cur.r.r.size : 0;
            int rl = r > 0 && cur.r.l != null ? cur.r.l.size : 0;

            if(ll >r){
                cur = rightRoate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(lr > r){
                cur.l = leftRoate(cur.l);
                cur = rightRoate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(rr > l){
                cur = leftRoate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if(rl > l){
                cur.r = rightRoate(cur.r);
                cur = leftRoate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        public SbNode lastNode(Node key){
            SbNode cur = root;
            SbNode pre = null;
            while(cur != null){
                pre = cur;
                if(key.compareTo(cur.node) == 0){
                    break;
                }else if(key.compareTo(cur.node) > 0){
                    cur = cur.r;
                }else {
                    cur = cur.l;
                }
            }

            return pre;

        }
    }
}
