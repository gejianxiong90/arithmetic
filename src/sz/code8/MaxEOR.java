package sz.code8;

public class MaxEOR {


    public static int maxEOR1(int [] arr){

        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        for(int i = 1;i < arr.length ; i++){
            eor[i] = eor[i-1] ^ arr[i];
        }

        int max = Integer.MIN_VALUE;

        // 0...j....i
        // 0..i = 0...j ^ j+1...i
        // 那么0...j = 0...i ^ j+1...i

        for (int j = 0;j < arr.length;j++ ){
            // 以j结尾的情况下
            // 0...j
            // 1...j
            // 2...j
            // [i..j] = eor[j] ^ eor[i-1]
            for(int i = 0; i <= j;i++){
                max = Math.max(max,i == 0?eor[j] : eor[j] ^ eor[i-1]);
            }
        }
        return max;
    }


    public static int maxEor2(int[] arr){
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        int eor = 0; // 0...1 异或和
        int max = Integer.MIN_VALUE;
        for(int i = 0;i < arr.length;i++){
            eor ^=  arr[i];
            max = Math.max(max,numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    // 前缀树的节点类型，每个节点向下只可能有走向0或1的路
    // node.nexts[0] == null 0方向没路
    // node.nexts[0] != null 0方向有路
    public static class Node{
        public Node[] nexts = new Node[2];
    }


    public static class NumTrie{
        public Node head = new Node();

        public void add(int newNum){
            Node cur = head;
            for(int i = 31 ; i >= 0 ; i--){
                // 从高位到低位依次取出
                // 取出最高位看有没有树
                int path = (newNum >> i) & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // sum,和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int sum) {
           int res = 0;
           Node cur = head;
           for(int more = 31 ; more >= 0 ; more--){
               int path = (sum >> more) & 1; // 他需要走的路
               // 最好的路
               int best = more == 31 ? path : (path ^ 1);
               // 实际的路
               best = cur.nexts[best] != null ? best : (best ^ 1); // 前缀树中他能走的路，比如path需要走1，但实际前缀树只有0，所以只能走0
               res |= (path ^ best) << more; // 基于原有前缀树有的路，和需要的路，得出该二进制位的最大数，将此数加到结果的二进制位上
               cur = cur.nexts[best];
           }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,4,3,5};

        int i1 = maxEOR1(arr);
        int i = maxEor2(arr);
        System.out.println(i1);
        System.out.println(i);


        System.out.println("1000 ->"+ (1000^0));
    }
}
