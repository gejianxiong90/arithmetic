package sz.code9;

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2,返回累加和前K大的数
 *
 * 累加和必须包含 arr1和arr2的其中一个数
 *
 *
 * 解法： top1一定是arr1和arr2最后一个数，将两数相加，如假设最后一个都为3 arr1[3]+arr2[3]
 *        将arr1[2]+arr2[3]和arr1[3]+arr2[2] 加入到大根堆，直到k的数组加满
 */
public class TopKSumCrossTwoArrays {

    public static class Node{
        // arr1的下标
        int index1;
        int index2;
        int sum;

        public Node(int index1,int index2,int sum){
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index1 == node.index1 &&
                    index2 == node.index2;
        }

        @Override
        public int hashCode() {

            return Objects.hash(index1, index2);
        }
    }

    public static int[] topKSum(int[] arr1,int[] arr2,int topK){
        if( topK > arr1.length * arr2.length){
            return null;
        }
        int[] topKArr = new int[topK];
        int topIndex = 0;
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.sum - o1.sum);
//        topKArr[0] = arr1[arr1.length - 1] + arr2[arr2.length - 1];
//        topIndex++;
        heap.add(new Node(arr1.length-1,arr2.length-1,arr1[arr1.length - 1] + arr2[arr2.length - 1]));
        HashSet<Node> nodes = new HashSet<>();
        boolean[][] set = new boolean[arr1.length][arr2.length];
        set[arr1.length-1][arr2.length-1] = true;
        while (topIndex < topK){
            Node node = heap.poll();
            topKArr[topIndex++] = node.sum;
            if(node.index1 - 1 >= 0 && !set[node.index1 -1][node.index2]){
                heap.add(new Node(node.index1-1,node.index2,arr1[node.index1-1] + arr2[node.index2]));
                set[node.index1 - 1][node.index2] = true;
            }
            if(node.index2 - 1 >= 0 && !set[node.index1][node.index2 - 1]){
                heap.add(new Node(node.index1,node.index2-1,arr1[node.index1] + arr2[node.index2-1]));
                set[node.index1][node.index2 - 1] = true;

            }
        }
        return topKArr;
    }


    public static void main(String[] args) {
        int[] arr1 = {1,2,4,5};
        int[] arr2 = {2,4,6};

        int[] ints = topKSum(arr1, arr2, 3);

        System.out.println(ints);
    }
}
