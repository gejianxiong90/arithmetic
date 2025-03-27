package sz.code24;

/**
 * 给定一个二叉树头节点head，如果在X节点上放置相机，那么X父节点和X的左右子节点都可以被覆盖，返回如果把所有数都覆盖至少需要多少个相机
 *
 *         a
 *        /
 *      x
 *     / \
 *   j    k
 *
 *   x覆盖的是a、j、k和自己
 *
 *   解：讨论左右节点被覆盖的情况下有没有放相机
 *       自己有没有被覆盖，是否需要放相机
 *
 */
public class MinCameraCover {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static class Info{
        public int uncovered; // 未覆盖所放的相机数量
        public int coveredHasCamera; // 覆盖并且当前放了相机，放相机数量
        public int coveredNoCamera; // 覆盖并且当前未放相机，放相机的数量

        public Info(int uncovered, int coveredHasCamera, int coveredNoCamera){
            this.uncovered = uncovered;
            this.coveredHasCamera = coveredHasCamera;
            this.coveredNoCamera = coveredNoCamera;
        }
    }

    public static Info process(Node x){
        if(x == null){
            return new Info(Integer.MAX_VALUE,0,0);
        }
        Info left = process(x.left);
        Info right = process(x.right);

        int uncoverNum = left.coveredNoCamera + right.coveredNoCamera; // 当前节点不覆盖，一定是左右都不放相机

        int coverNoCamera = Math.min(left.coveredHasCamera+right.coveredHasCamera,  // 当前节点覆盖且不放相机，左右都放、左放右不放、右放左不放
                Math.min(left.coveredHasCamera + right.coveredNoCamera,
                        right.coveredHasCamera + left.coveredNoCamera));

        int coverHasCamera = Math.min(left.uncovered,Math.min(left.coveredHasCamera,left.coveredNoCamera)) +   // 既然当前节点放相机了，取左右最小值相加再+1（不关心左右放不放和是否覆盖）
                Math.min(right.uncovered,Math.min(right.coveredHasCamera,right.coveredNoCamera)) + 1;

        return new Info(uncoverNum,coverHasCamera,coverNoCamera);
    }


    public static enum Status{
        UN_COVERED,COVER_HAS_CAMERA,COVER_NO_CAMERA;
    }

    public static class Data{
        public Status status;
        public int num;

        public Data(Status status,int num){
            this.status = status;
            this.num = num;
        }
    }

    public static Data process2(Node x){
        if(x == null){
            new Data(Status.COVER_NO_CAMERA,0); // 空节点 覆盖了但没有相机
        }
        Data left = process2(x.left);
        Data right = process2(x.right);

        if(left.status == Status.UN_COVERED || right.status == Status.UN_COVERED){ // 左右有没覆盖的，我自己必须要放相机
            return new Data(Status.COVER_HAS_CAMERA,left.num+right.num+1);
        }

        if(left.status == Status.COVER_HAS_CAMERA || right.status == Status.COVER_HAS_CAMERA){ // 左右存在相机，则我自己不用放
            return new Data(Status.COVER_NO_CAMERA,left.num + right.num);
        }
        return new Data(Status.UN_COVERED,left.num+right.num); // 左右孩子，不存没被覆盖，并且也都没相机。那就是我自己没覆盖
    }


    public static int minCamera(Node x){
        Info process = process(x);
        return Math.min(process.uncovered + 1,Math.min(process.coveredHasCamera,process.coveredNoCamera));
    }

    public static int minCamera2(Node x){
        Data data = process2(x);
        return data.status == Status.UN_COVERED ? data.num + 1 : data.num;
    }
}
