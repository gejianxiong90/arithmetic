package leetcode;

import java.util.HashMap;

public class BuildTree_105 {

    /**
     * 前序中序构建
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++){
            indexMap.put(inorder[i],i);
        }
        return process(indexMap,preorder,0,preorder.length - 1,inorder,0,inorder.length - 1 );
    }

    public  TreeNode buildTree2(int[] inorder,int[] postorder){
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0 ; i < inorder.length ; i++){
            indexMap.put(inorder[i],i);
        }

        return process2(indexMap,inorder,0,inorder.length - 1,postorder,0,postorder.length - 1);
    }

    public static TreeNode process2(HashMap<Integer,Integer> indexMap,int[] in ,int L1,int R1,int[] post,int L2,int R2){
        if(L1 > R1){
            return null;
        }
        TreeNode head = new TreeNode(post[R2]);
        if(L1 == R1){
            return head;
        }
        int findIndex = indexMap.get(head.val);
        head.left = process2(indexMap,in,L1,findIndex - 1,post,L2,L2+(findIndex - L1));
        head.right = process2(indexMap,in,findIndex + 1,R1,post,L2+(findIndex - L1) + 1,R2-1);
        return head;
    }

    public static TreeNode process(HashMap<Integer, Integer> indexMap,int[] pre,int ps,int pe,int[] in ,int is,int ie){
        if(ps > pe){
            return null;
        }
        TreeNode x = new TreeNode(pre[ps]);
        if(ps == pe){ // 只有一个元素
            return x;
        }
        Integer index = indexMap.get(x.val);

        x.left = process(indexMap,pre,ps+1,ps+(index - is),in,is,index - 1);
        x.right = process(indexMap,pre,ps+(index - is) + 1,pe,in,index + 1,ie);
        return x;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
