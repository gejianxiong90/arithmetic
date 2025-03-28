package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 114. 二叉树展开为链表
 中等
 相关标签
 相关企业
 提示
 给你二叉树的根结点 root ，请你将它展开为一个单链表：

 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 展开后的单链表应该与二叉树 先序遍历 顺序相同。


 示例 1：


 输入：root = [1,2,5,3,4,null,6]
 输出：[1,null,2,null,3,null,4,null,5,null,6]
 示例 2：

 输入：root = []
 输出：[]
 示例 3：

 输入：root = [0]
 输出：[0]

 */
public class flatten_114 {


    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        process(root,list);

        for(int i = 1 ; i < list.size() ; i++){
            TreeNode pre = list.get(i-1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    public void process(TreeNode x,List<TreeNode> list){
        if(x == null){
            return;
        }
        list.add(x);
         process(x.left,list);
         process(x.right,list);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}


