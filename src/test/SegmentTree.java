package test;

public class SegmentTree {


    private int Max;
    private int[] arr;
    private int[] sum;
    private int[] lazy;
    private int[] change;
    private boolean[] update;

    public SegmentTree(int [] origin){
        Max = origin.length + 1;
        arr = new int[Max];
        for(int i = 0 ; i < origin.length ; i++){
            arr[i+1] = origin[i];
        }
        sum = new int[Max << 2];
        lazy = new int[Max << 2];
        change = new int[Max << 2];
        update = new boolean[Max << 2];
    }


    public void bulid(int l,int r,int rt){
        if(l ==  r){
            sum[rt] = arr[l];
            return;
        }
        int mid = (r + l) >> 1;
        bulid(l,mid,rt<<1);
        bulid(mid+1,r,rt << 1 | 1);
        pushUp(rt);
    }

    public void pushUp(int rt){
       sum[rt] = sum[rt<<1] + sum[rt<<1 | 1];
    }

    /**
     * L .. R 范围加上c
     * l ...R
     *
     */
    public void add(int L,int R,int c,int l,int r,int rt){
        if(L <= l && r <= R){
            sum[rt] += (r - l + 1) * c;
            lazy[rt] += c;
            return;
        }

        int mid = (r + l) >> 1;
        pushDown(mid - l + 1,r - mid,rt);
        if(L <= mid){
            add(L,R,c,l,mid,rt << 1);
        }
        if(R > mid){
            add(L,R,c,mid+1,r,rt << 1|1 );
        }
        pushUp(rt);
    }


    public void update(int L,int R,int c,int l,int r,int rt){
        if(L <= l &&  r <=R ){
            update[rt] = true;
            change[rt] = c;
            sum[rt] = (r-l+1) * c;
            lazy[rt] = 0;
            return;
        }

        int mid = (r+l) >> 1;
        pushDown(mid - l +1,r-mid,rt);
        if(L <= mid){
            update(L,R,c,l,mid,rt << 1);
        }
        if(R > mid){
            update(L,R,c,mid+1,r,rt << 1 | 1);
        }
        pushUp(rt);
    }


    public void pushDown(int ln, int rn,int rt){
        if(update[rt]){
            update[rt<<1] = true;
            update[rt<<1 | 1] = true;
            change[rt<<1] = change[rt];
            change[rt<<1 | 1] = change[rt];
            sum[rt << 1] = ln * change[rt];
            sum[rt << 1 | 1] = rn * change[rt];
            lazy[rt<< 1] = 0;
            lazy[rt << 1 | 1] = 0;
            update[rt] = false;
        }
        if(lazy[rt] > 0){
            sum[rt << 1] += ln * lazy[rt];
            sum[rt << 1 | 1] += rn * lazy[rt];

            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            lazy[rt] = 0;
        }
    }

    public int query(int L,int R,int l,int r,int rt){
        if(L <= l && r <= R){
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(mid - l + 1,r-mid,rt);
        int ans = 0;
        if(L <= mid){
            ans += query(L,R,l,mid,rt << 1);
        }
        if(R > mid){
            ans += query(L,R,mid+1,r,rt<<1 | 1);
        }
        return ans;
    }


    public static void main(String[] args) {

        int[] origin = {1,2,3,4,5,6,7,8,9};
        int N = origin.length;
        int root = 1;
        int S = 1;
        SegmentTree segmentTree = new SegmentTree(origin);
        segmentTree.bulid(S,N,root);
        int L = 1;
        int R = 9 - 1;
        segmentTree.add(L,R,2,S,N,1);

        int query = segmentTree.query(L, R, S, N, 1);

        System.out.println(query);

        segmentTree.update(L,R,2,S,N,1);

        int query1 = segmentTree.query(L, R, S, N, 1);

        System.out.println(query1);


        segmentTree.add(L,R - 1,5,S,N,1);

        segmentTree.update(L,R-2,2,S,N,1);

        int query2 = segmentTree.query(L, R, S, N, 1);

        System.out.println(query2);


    }
}
