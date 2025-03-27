package core;

import java.util.HashSet;
import java.util.Set;

public class CountOfRangeSum1 {


     SbNode root;

     Set<Integer> set = new HashSet<Integer>();

     public void put(Integer key){
         SbNode node = findNode(root, key);
       //  boolean contains = set.contains(key);
         if(node != null){
             node.all++;
         }else{
             root = add(root,key);
         }

     }

     public SbNode add(SbNode cur ,Integer key){
         if(cur == null){
             return new SbNode(key);
         }else {
             cur.size++;
             cur.all++;
             if(cur.key.compareTo(key) < 0){
                 cur.right = add(cur.right,key);
             }else{
                 cur.left = add(cur.left,key);
             }
             return maintain(cur);
         }
     }

     public int lessKeyCount(Integer key){
         SbNode cur = root;
         int ans = 0;
         while(cur != null){
             if(cur.key.compareTo(key) == 0 ){
                 return ans + (cur.left == null ? 0 : cur.left.all);
             }else if(cur.key.compareTo(key) < 0){
                 ans += cur.all - (cur.right == null ? 0 : cur.right.all);
                 cur = cur.right;
             }else {
                 cur = cur.left;
             }
         }
         return ans;
     }

     public SbNode rigthRoate(SbNode cur){
         int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
         SbNode left = cur.left;
         cur.left = left.right;
         left.right = cur;
         left.size = cur.size;
         left.all = cur.all;
         cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
         cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all) + same;
         return left;
     }

     public SbNode leftRoate(SbNode cur){
         int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
         SbNode right = cur.right;
         cur.right = right.left;
         right.left = cur;
         right.size = cur.size;
         right.all = cur.all;
         cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
         cur.all = (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all) + same;
         return right;
     }

     public SbNode maintain(SbNode cur){
         if(cur == null){
             return null;
         }
         int ll = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
         int l = cur.left != null ?cur.left.size : 0;
         int rr = cur.right != null && cur.right.right != null ? cur.right.right.size :0;
         int r = cur.right != null ? cur.right.size : 0;
         int lr = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
         int rl = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;

         if(ll > r){
             cur = rigthRoate(cur);
             cur.right = maintain(cur.right);
             cur = maintain(cur);
         }else if(lr > r ){
             cur.left = leftRoate(cur.left);
             cur = rigthRoate(cur);
             cur.left = maintain(cur.left);
             cur.right = maintain(cur.right);
             cur = maintain(cur);
         }else if(rr > l){
             cur = leftRoate(cur);
             cur.left = maintain(cur.left);
             cur = maintain(cur);
         }else if(rl > l){
             cur.right = rigthRoate(cur.right);
             cur = leftRoate(cur);
             cur.right = maintain(cur.right);
             cur.left = maintain(cur.left);
             cur = maintain(cur);
         }
         return cur;
     }

     public SbNode findNode(SbNode cur,Integer key){
         SbNode node =cur;
         while(node != null){
             if(node.key.compareTo(key) == 0){
                 return node;
             }else if(node.key.compareTo(key) > 0){
                 node = node.left;
             }else{
                 node = node.right;
             }
         }
         return node;
     }



    public static class SbNode{
        Integer key;
        int all;
        int size;
        SbNode left;
        SbNode right;

        public SbNode(Integer key){
            this.key = key;
            this.all = 1;
            this.size = 1;
        }
    }



    public int countRangeSum(int[] nums, int lower, int upper) {
         int ans = 0;
        CountOfRangeSum1 countOfRangeSum1 = new CountOfRangeSum1();
        int sum = 0;
        countOfRangeSum1.put(0);
        for(int i = 0 ; i < nums.length ; i++){
            sum += nums[i];
            int a = countOfRangeSum1.lessKeyCount(sum - lower + 1);
            int b = countOfRangeSum1.lessKeyCount(sum - upper);
             ans += a - b;
            countOfRangeSum1.put(sum);
        }

        return ans;

    }


    public static void main(String[] args) {
        CountOfRangeSum1 countOfRangeSum1 = new CountOfRangeSum1();
        int[] arr = {14,-30,22,8,3,20,-11,8,7,-9,-7,-13,-5,-22,11,23,15,29,-17,-27,-2,14,14,11,22,-14,-27,-5,-9,-21,22,3,12,7,-13,14,-23,-16,12,-4,21,-8,-18,22,17,-19,-22,8,2,-2,24,-12,-24,-16,-2,19,7,7,-20,8};

        int i = countOfRangeSum1.countRangeSum(arr, 39, 88);

        System.out.println(i);
    }
    }
