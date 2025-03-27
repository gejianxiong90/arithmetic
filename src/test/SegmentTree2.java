package test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SegmentTree2 {

    int MAX;
    int[] arr;
    int[] sum;
    int[] lazy;
    int[] change;
    boolean[] update;

    public SegmentTree2(int[] origin){
        MAX = origin.length+1;
        arr = new int[MAX];
        for(int i = 0;i<origin.length;i++){
            arr[i + 1] = origin[i];
        }
        sum = new int[MAX << 2];
        lazy = new int[MAX << 2];
        change = new int[MAX << 2];
        update = new boolean[MAX << 2];
    }

    public void bulid(int L,int R,int rt){
        if(L == R){ // basecase 这个区间只有一个数
            sum[rt] = arr[L];
            return;
        }

        int mid = (R+L ) >> 1;
        bulid(L,mid,rt << 1);
        bulid(mid+1,R, rt << 1 | 1);
        pushUp(rt);
    }

    public void pushUp(int rt){
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }


    public void add(int L,int R,int C,int l,int r,int rt){
        if(L <= l && r <= R){
            sum[rt] += (r - l + 1) * C;
            lazy[rt] += C;
            return;
        }
        int mid = (r+l) >> 1;
        pushDown(mid - l + 1,r - mid,rt);

        if( L <= mid){
            add(L,R,C,l,mid,rt<<1);
        }
        if(R > mid){
            add(L,R,C,mid+1,r,rt << 1 | 1);
        }

        pushUp(rt);

    }


    public void update(int L,int R,int C,int l,int r,int rt){
        if(L <= l && r <= R){
            sum[rt] = (r - l + 1) * C;
            update[rt] = true;
            change[rt] = C;
            lazy[rt] = 0;
            return;
        }
        int mid = r + l >> 1;
        pushDown(mid - l + 1,r - mid,rt);
        if(L <= mid){
            update(L,R,C,l,mid,rt << 1);
        }
        if(R > mid){
            update(L,R,C,mid+1,r,rt << 1 | 1);
        }
        pushUp(rt);
    }

    public int query(int L,int R,int l,int r,int rt){
        if(L <= l && r <= R){
            return sum[rt];
        }
        int mid = r + l >> 1;
        pushDown(mid - l + 1,r - mid,rt);
        int ans = 0;
        if(L <= mid){
            ans += query(L,R,l,mid,rt << 1);
        }
        if(R > mid){
            ans += query(L,R,mid+1,r,rt << 1 | 1);
        }
        return ans;

    }

    public void pushDown(int ln,int rn,int rt){
        if(update[rt]){
            sum[rt << 1] = ln * change[rt];
            sum[rt << 1 | 1] = rn * change[rt];
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            update[rt] = false;
        }
        if(lazy[rt] >0 ){
            sum[rt << 1] += ln * lazy[rt];
            sum[rt << 1 | 1] += rn * lazy[rt];
            lazy[rt << 1] = lazy[rt];
            lazy[rt << 1 | 1] = lazy[rt];
            lazy[rt] = 0;
        }

    }

    public static void main(String[] args) {
        int[] origin = {1,2,3,4};
        SegmentTree2 segmentTree2 = new SegmentTree2(origin);
        segmentTree2.bulid(1,4,1);

        segmentTree2.add(1,3,2,1,4,1);

        segmentTree2.update(1,4,2,1,4,1);


        System.out.println(segmentTree2.sum);
        System.out.println(segmentTree2.lazy);
        System.out.println( segmentTree2.query(1,3,1,4,1));
    }

}
