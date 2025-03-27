package core;

public class MaxSubBstSize {

    public static class Info{
        boolean isAllBst;
        Integer maxVal;
        Integer minVal;
        Integer bstSize;

        public Info(boolean isAllBst,Integer maxVal,Integer minVal,Integer bstSize){
            this.isAllBst = isAllBst;
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.bstSize = bstSize;
        }
    }

    public static Info getMaxSubBstSize(Node x){
        if(x == null){
            return new Info(true,null,null,0);
        }

        Info left = getMaxSubBstSize(x.left);
        Info right = getMaxSubBstSize(x.right);
        Integer maxVal = null;

        if(left.maxVal != null){
            maxVal = Math.max(left.maxVal,x.value);
        }
        if(right.maxVal != null){
            maxVal = Math.max(right.maxVal,x.value);
        }
        Integer minVal = null;

        if(left.minVal != null){
            minVal = Math.min(x.value,left.minVal);
        }
        if(right.minVal != null){
            minVal = Math.min(x.value,right.minVal);
        }

        boolean isAllBst = false;
        int bstSize = Math.max(right.bstSize,left.bstSize);
        if(left.isAllBst && right.isAllBst && (left.maxVal == null || left.maxVal < x.value ) && (right.minVal == null || right.minVal > x.value)){
            isAllBst = true;
            bstSize = right.bstSize + left.bstSize + 1;
        }


        return new Info(isAllBst,maxVal,minVal,bstSize);

    }
}
