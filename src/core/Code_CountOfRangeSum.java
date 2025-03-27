package core;

import java.util.HashSet;

public class Code_CountOfRangeSum {

    public static void main(String[] args) {
        int[] nums = {2147483647,-2147483648,-1,0};
        Code_CountOfRangeSum code_countOfRangeSum = new Code_CountOfRangeSum();
        int i = code_countOfRangeSum.countRangeSum(nums, -1, 0);
        System.out.println(i);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        CountOfRangeSum.SBTMap map = new CountOfRangeSum.SBTMap();
        map.add(0L);
        long sum = 0;
        int count = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];

            long a = map.lessSumCount( sum - lower + 1);
            long b = map.lessSumCount(sum - upper);

            count += a-b;
            map.add(sum);
        }
        return count;
    }

    public static class CountOfRangeSum{
        public static class SBTNode{
            Long key;
            SBTNode l;
            SBTNode r;
            int size;
            long all;

            public SBTNode(long key){
                this.key = key;
                this.size = 1;
                this.all = 1;
            }

        }

        public static class SBTMap{
            SBTNode root;
            HashSet<Long> set = new HashSet<Long>();


            public SBTNode rightRotate(SBTNode cur){
                 long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
                SBTNode left = cur.l;
                cur.l = left.r;
                left.r = cur;
                left.size = cur.size;
                cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
                left.all = cur.all;
                cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r!= null ? cur.r.all : 0) + same;
                return left;
            }

            public SBTNode leftRotate(SBTNode cur){
                long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
                SBTNode right = cur.r;
                cur.r = right.l;
                right.l = cur;
                right.size = cur.size;
                cur.size = (cur.l != null ? cur.l.size : 0 ) + (cur.r != null ? cur.r.size : 0) + 1;
                right.all = cur.all;
                cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0 ) + same;
                return right;
            }

            public SBTNode maintain(SBTNode cur){
                if(cur == null){
                    return null;
                }
                int ll = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
                int lr = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
                int rr = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
                int rl = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
                int l = cur.l != null ? cur.l.size : 0;
                int r = cur.r != null ? cur.r.size : 0;
                if(ll > r){
                    cur = rightRotate(cur);
                    cur.r = maintain(cur.r);
                    cur = maintain(cur);
                }else if(lr > r){
                    cur.l = leftRotate(cur.l);
                    cur = rightRotate(cur);
                    cur.l = maintain(cur.l);
                    cur.r = maintain(cur.r);
                    cur = maintain(cur);
                }else if(rr > l){
                    cur = leftRotate(cur);
                    cur.l = maintain(cur.l);
                    cur = maintain(cur);
                }else if(rl > l){
                    cur.r = rightRotate(cur.r);
                    cur = leftRotate(cur);
                    cur.r = maintain(cur.r);
                    cur.l = maintain(cur.l);
                    cur = maintain(cur);
                }
                return cur;
            }

            public void add(long key){
                boolean contains = set.contains(key);
                root = add(root,key,contains);
                set.add(key);
            }

            public SBTNode add(SBTNode cur,long key,boolean contains ){
                if(cur == null){
                    return new SBTNode(key);
                }else {
                    cur.all++;
                    if(cur.key == key ){
                        return cur;
                    }else {
                        if(!contains){
                            cur.size++;
                        }
                        if(key < cur.key){
                            cur.l = add(cur.l,key,contains);
                        }else if(key > cur.key){
                            cur.r = add(cur.r,key,contains);
                        }
                        return maintain(cur);
                    }
                }
            }


            public long lessSumCount(long key){
                SBTNode cur = root;
                long count = 0;
                while(cur != null){
                    if(cur.key == key){
                        return count + (cur.l != null ? cur.l.all : 0);
                    }else if(key > cur.key){
                        count += cur.all - (cur.r != null ? cur.r.all : 0);
                        cur = cur.r;
                    }else if(key < cur.key){
                        cur = cur.l;
                    }
                }
                return count;
            }

        }
    }

}
