package old;

import java.util.ArrayList;
import java.util.List;

public class PreInBuilderTree {


    public static class Node{
        int value;
        Node left;
        Node right;

    }

    public static Node buildTree(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if(preStart > preEnd){
            return null;
        }
        Node root = new Node();
        root.value = pre[preStart];
        int inIndex = 0;
        for(int i = inStart ;i <=inEnd ;i++){
            if(root.value == in[i]){
                inIndex = i;
                break;
            }
        }
        int leftSize = inIndex - inStart;
        root.left = buildTree(pre,preStart+1,preStart+leftSize,in,inStart,inIndex-1);
        root.right = buildTree(pre,preStart+leftSize+1,preEnd,in,inIndex+1,inEnd);
        return root;
    }

    public static int setNewValue(Node root){
        if(root == null){
            return 0;
        }
        int leftSum = setNewValue(root.left);
        int rightSum = setNewValue(root.right);
        int old = root.value;
        root.value = leftSum + rightSum;
        return root.value + old;
    }

    public static void in(Node root,List<Integer> list){
        if(root == null){
            return ;
        }
        in(root.left,list);
        list.add(root.value);
        in(root.right,list);
    }


    public static void main(String[] args) {
        int[] in = {-3,12,6,8,9,-10,-7};
        int[] pre = {8,12,-3,6,-10,9,-7};
        Node node = buildTree(pre, 0, 6, in, 0, 6);

        setNewValue(node);
        ArrayList<Integer> integers = new ArrayList<>();
        in(node,integers);

        System.out.println(integers);
    }
}
