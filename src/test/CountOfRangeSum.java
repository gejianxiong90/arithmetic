package test;

import java.util.HashSet;
import java.util.Set;

public class CountOfRangeSum {


    public static class SBTNode{
        public long key;
        public SBTNode l;
        public SBTNode r;
        public long size; // 不同key的size
        public long all;  // 总size


        public SBTNode(long k){
            key = k;
            size = 1;
            all = 1;
        }
    }


    public static class SizeBalancedTreeSet{
        private SBTNode root;
        private Set<Long> set = new HashSet<>();


        private SBTNode rightRotate(SBTNode cur){
            long same = cur.all - (cur.l !=null ? cur.l.size : 0) - (cur.r != null ? cur.r.size : 0);
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0 ) + 1;
            leftNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
            return leftNode;
        }


        private SBTNode leftRotate(SBTNode cur){
            long same = cur.all - (cur.l != null ? cur.l.size : 0) - (cur.r != null ? cur.r.size : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            rightNode.all = cur.all;
            cur.all = (cur.l != null ? cur.l.all : 0 ) + (cur.r != null ? cur.r.all : 0) + same;
            return  rightNode;
        }

        private SBTNode maintain(SBTNode cur){
            if(cur == null){
                return  null;
            }
            long leftSize = cur.l != null ? cur.l.size : 0;
            long leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            long leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

            long rightSize = cur.r != null ? cur.r.size : 0;
            long rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            long rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;

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
                maintain(cur.l);
                maintain(cur.r);
                maintain(cur);
            }
            return cur;
        }

        public void add(long sum){
            boolean contains = set.contains(sum);
            root = add(root,sum,contains);
            set.add(sum);
        }

        private SBTNode add(SBTNode cur,long key,boolean contains){
            if (cur == null){
                return new SBTNode(key);
            }else {
                cur.all++;
                if(cur.key == key){
                    return cur;
                }else {
                    if(!contains){
                        cur.size++;
                    }
                    if(key < cur.key){
                        cur.l = add(cur.l,key,contains);
                    }else {
                        cur.r = add(cur.r,key,contains);
                    }
                    return maintain(cur);
                }
            }
        }

        public long lessKeySize(long key){
            SBTNode cur = root;
            long ans = 0;
            while (cur != null){
                if(key == cur.key){
                    return ans + (cur.l != null ? cur.l.all : 0);
                }else if(key < cur.key){
                    cur = cur.l;
                }else {
                    ans += cur.all - (cur.r != null ? cur.r.all :0 );
                    cur = cur.r;
                }
            }
            return  ans;
        }
    }

    public static int countRangeSum(int[] nums,int lower,int upper){
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum =0;
        int ans = 0;
        treeSet.add(0);
        for(int i = 0 ; i< nums.length ; i++){
            sum += nums[i];

            long a = treeSet.lessKeySize(sum - lower + 1);
            long b = treeSet.lessKeySize(sum - upper);
            ans += a - b;
            treeSet.add(sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0};
        int i = countRangeSum(arr, 0, 0);
        System.out.println(i);
    }

}
