package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RangeMerge {

    public  static class Range{
        int start;
        int end;

        public Range(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    public static class RangeComparator implements Comparator<Range> {

        public int compare(Range o1,Range o2){
            return o1.start - o2.start;
        }
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 1){
            return intervals;
        }

        Range[] arr = new Range[intervals.length];
        for(int i = 0 ; i < intervals.length ; i++){
            arr[i] = new Range(intervals[i][0],intervals[i][1]);
        }
        Arrays.sort(arr,new RangeComparator());
        List<Range> resList = new ArrayList<Range>();
        int tempStart = arr[0].start;
        int tempEnd = arr[0].end;
        for(int j = 1 ; j < arr.length ; j++){
            Range range = arr[j];
            if(range.start > tempEnd){
                resList.add(new Range(tempStart,tempEnd));
                tempStart = range.start;
                tempEnd = range.end;
            }else {
                tempEnd = Math.max(tempEnd,range.end);
            }
        }
        resList.add(new Range(tempStart,tempEnd));
        int[][] ans = new int[resList.size()][2];
        for(int j = 0 ; j < resList.size();j++){
            Range r = resList.get(j);
            ans[j][0] = r.start;
            ans[j][1] = r.end;
        }

        return ans;
    }

    public static void main(String[] args) {
        RangeMerge rangeMerge = new RangeMerge();
        int[][] intervals = {{1,4},{5,6}};
        int[][] merge = rangeMerge.merge(intervals);


        System.out.println(merge);
    }

}
