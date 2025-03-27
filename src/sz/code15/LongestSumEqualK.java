package sz.code15;

import java.util.HashMap;
import java.util.Map;

/**
 * 在树上找到累计和等于K的，最多的节点数量
 *
 * 解： 同数组三连，最长的累加和为K
 *      利用map记录累加和--》层数
 *      层数减层数等于 节点数量
 */
public class LongestSumEqualK {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int v) {
            value = v;
        }

    }

    public static int res = 0;

    /**
     *
     * @param x 来到的节点
     * @param level 来到的第几层
     * @param preNum  之前的累加
     * @param sumLevelMap 累加和 ----》 层数
     * @param K 累加K
     */
    public static void  process(Node x, int level, int preNum, Map<Integer,Integer> sumLevelMap,int K){
        if( x == null ){
            return;
        }
        int allSum = x.value + preNum;
        if(sumLevelMap.containsKey(allSum - K)){
            res = Math.max(res,level - sumLevelMap.get(allSum - K));
        }
        if(!sumLevelMap.containsKey(allSum)){
            sumLevelMap.put(allSum,level);
        }
        process(x.left,level+1,allSum,sumLevelMap,K);
        process(x.right,level+1,allSum,sumLevelMap,K);

        if (sumLevelMap.containsKey(allSum)){
            sumLevelMap.remove(allSum);
        }
    }

    public static int longest(Node head,int K){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        process(head,0,0,map,K);
        return res;
    }

    public static void main(String[] args) {
        //                   3
        //           -2             3
        //        1      4      5      -7
        //       3 5   2 -5  -5  -3   1   5
        int K = 0;
        Node head = new Node(3);
        head.left = new Node(-2);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.left.left.left = new Node(3);
        head.left.left.right = new Node(5);
        head.left.right.left = new Node(2);
        head.left.right.right = new Node(5);

        head.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(-7);
        head.right.left.left = new Node(-5);
        head.right.left.right = new Node(-3);
        head.right.right.left = new Node(1);
        head.right.right.right = new Node(5);

        System.out.println(longest(head, K));

    }
}
