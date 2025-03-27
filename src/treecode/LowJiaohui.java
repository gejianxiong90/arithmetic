package treecode;

public class LowJiaohui {


    public static void main(String[] args) {

    }

    public static class Info{
        Node ans;
        boolean findO1;
        boolean findO2;

        public Info(Node ans, boolean findO1, boolean findO2) {
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }


    public static Info process(Node x, Node o1,Node o2){
        if(x == null){
            return new Info(null,false,false);
        }
        Info left = process(x.left, o1, o2);
        Info right = process(x.right, o1, o2);

        boolean findO1 = x == o1|| left.findO1 ||right.findO1;
        boolean findO2 = x == o2 || left.findO2 || right.findO2;
        Node ans = null;

        // 1 在左数上提前交汇
        // 2 在右数上提前交汇
        // 3 没有交汇，但在左树右数都找到，就是当前节点
        if(left.ans != null){
            ans = left.ans;
        }
        if(right.ans != null){
            ans = right.ans;
        }
        if (ans == null){
            if(findO1 && findO2){
                ans = x;
            }
        }
        return new Info(ans,findO1,findO2);

    }
}
