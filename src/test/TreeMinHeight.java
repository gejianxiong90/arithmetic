package test;

import core.Node;

public class TreeMinHeight {

    public static  int p(Node x){
        if(x.left == null && x.right == null){
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if(x.left != null){
            leftH = p(x.left);
        }
        int rightH = Integer.MAX_VALUE;
        if(x.right != null){
            rightH = p(x.right);
        }
        return 1 + Math.min(leftH,rightH);
    }


    public static void morris(Node x){
        Node cur = x;
        int minheight = Integer.MAX_VALUE;
        int curheight = 0;
        while (cur != null){
            Node mostRight = cur.left;
            int leftheight = 1;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    leftheight++;
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    curheight++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else { // 一定是第二次的来到，mostright.right == cur
                    if(cur.left == null){
                        minheight = Math.min(minheight,curheight);
                    }
                    curheight -= leftheight;
                    mostRight.right = null;
                }
            }else {
                curheight++;
            }
            cur = cur.right;
        }
        int finalRightCount = 0;
        while(x != null){
            finalRightCount++;
            x = x.right;
        }
        minheight = Math.min(finalRightCount,minheight);
        System.out.println(minheight);
    }

    public static void main(String[] args) {
        Node node = Node.generateTree();
        int p = p(node);
        System.out.println(p);
       morris(node);
    }



}
