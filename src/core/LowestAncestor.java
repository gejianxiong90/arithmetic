package core;

public class LowestAncestor {

    public static class Info{
        Node ans;
        boolean findO1;
        boolean findO2;

        public Info(Node ans,boolean findO1,boolean findO2){
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    public static Info process(Node x,Node O1,Node O2){
        if(x == null){
            return new Info(null,false,false);
        }
        boolean findO1 = false;
        boolean findO2 = false;
        Info leftInfo = process(x.left,O1,O2);
        Info rightInfo = process(x.right,O1,O2);

        findO1 = x == O1 || leftInfo.findO1 || rightInfo.findO1;
        findO2 = x == O2 || leftInfo.findO2 || rightInfo.findO2;

        Node ans = null;

        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        }
        if(rightInfo.ans != null){
            ans = rightInfo.ans;
        }

        if(ans == null){
            if(findO1 && findO2){
                ans = x;
            }
        }
        return new Info(ans,findO1,findO2);
    }

}
