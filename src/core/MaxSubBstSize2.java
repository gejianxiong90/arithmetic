package core;

public class MaxSubBstSize2 {

    public static class Info{
        int subBstSize;
        int max;
        int min;
        boolean isBst;
        Node maxNode; // 附加的，表示最大bst的头节点

        public Info(int subBstSize,int max,int min,boolean isBst,Node maxNode){
            this.subBstSize = subBstSize;
            this.max = max;
            this.min = min;
            this.isBst = isBst;
            this.maxNode = maxNode;
        }
    }

    public static Info process(Node x){
        if(x == null ){
            return null;
        }

        Info left = process(x.left);
        Info right = process(x.right);
        int subBstSize = 0;
        int max = x.value;
        int min = x.value;
        Node maxNode = null;
        if(left != null){
            subBstSize = left.subBstSize;
            max = Math.max(x.value,left.max);
            min = Math.min(x.value,left.min);
            maxNode = left.maxNode;
        }
        if(right != null){
            if(right.subBstSize > subBstSize){
                maxNode = right.maxNode;
            }
            subBstSize = Math.max(subBstSize,right.subBstSize);
            max = Math.max(max,right.max);
            min = Math.min(min,right.min);
        }
        boolean isBst = false;
        if((left == null ? true : left.isBst && left.max < x.value )
                && (right == null ? true : right.isBst && right.min > x.value)
                ){

            subBstSize = (left == null ? 0 : left.subBstSize) + (right == null ? 0 : right.subBstSize) + 1;
            isBst = true;
            maxNode = x;

        }

        return new Info(subBstSize,max,min,isBst,maxNode);
    }
}
