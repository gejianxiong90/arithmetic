package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 624. 数组列表中的最大距离
 中等
 相关标签
 相关企业
 给定 m 个数组，每个数组都已经按照升序排好序了。

 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。

 返回最大距离。

 示例 1：

 输入：[[1,2,3],[4,5],[1,2,3]]
 输出：4
 解释：
 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 示例 2：

 输入：arrays = [[1],[1]]
 输出：0

 */
public class maxDistance_624 {


    public static void main(String[] args) {
        ArrayList<Integer> integer0 = new ArrayList<>();
        ArrayList<Integer> integer1 = new ArrayList<>();
        ArrayList<List<Integer>> integers = new ArrayList<>();
        integer0.add(1);
        integer0.add(5);
        integer1.add(3);
        integer1.add(4);
        integers.add(integer0);
        integers.add(integer1);


        int max = maxDistance(integers);
        System.out.println(max);
    }

    public static class Node{
        int index;// 第几个桶，所在的列表编号
        int value;

        public Node(int index,int value){
            this.index = index;
            this.value = value;
        }
    }
    public static int maxDistance(List<List<Integer>> arrays) {
        PriorityQueue<Node> max = new PriorityQueue<Node>((o1, o2) -> o2.value - o1.value);
        PriorityQueue<Node> min = new PriorityQueue<Node>((o1, o2) -> o1.value - o2.value);
        for(int i = 0 ; i < arrays.size() ;i++){
            List<Integer> list = arrays.get(i);
            for(int j = 0 ; j < list.size() ;j++){
                Node node = new Node(i,list.get(j));
                max.add(node);
                min.add(node);
            }
        }
        Node maxNode = max.peek();
        Node tempMin = min.peek();;
        int res = Integer.MIN_VALUE;
        while(!min.isEmpty()){
            Node minNode = min.poll();
            if(maxNode.index != minNode.index){
                res = Math.max(res,maxNode.value - minNode.value);
                break;
            }
        }
        while(!max.isEmpty()){
             maxNode = max.poll();
            if(maxNode.index != tempMin.index){
                res = Math.max(res,maxNode.value - tempMin.value);
                break;
            }
        }
        return res;
    }
}
