package sz.code10;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定 xy的坐标点数组，求某个直线经过最多多少个点
 */

public class MaxPointsOneLine {


    public static class Point{
        public int x;
        public int y;

        Point(){
            this.x = 0;
            this.y = 0;
        }

        Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int maxPoints(Point[] points){
        if(points == null){
            return 0;
        }
        if(points.length <= 2){
            return points.length;
        }

        HashMap<Integer, Map<Integer,Integer>> map = new HashMap<>();
        int result = 0;
        for(int i = 0; i <points.length ;i++){
            map.clear();
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for(int j = i+1; j < points.length;j++){
                Point pointI = points[i];
                Point pointJ = points[j];
                int x = pointI.x - pointJ.x;
                int y = pointI.y - pointJ.y;
                if(x == 0 && y==0){
                    samePosition++;
                }else if(x == 0){
                    sameX++;
                }else if(y == 0){
                    sameY++;
                }else {

                    int gcd = gcd(x,y);
                    x /= gcd;
                    y /= gcd;

                    if(!map.containsKey(x)){
                        map.put(x,new HashMap<>());
                    }

                    if(!map.get(x).containsKey(y)){
                        map.get(x).put(y,0);
                    }
                    map.get(x).put(y,map.get(x).get(y) + 1);
                    line = Math.max(line,map.get(x).get(y));
                }

            }
            result = Math.max(result,Math.max(Math.max(sameX,sameY),line) + samePosition);
        }

        return result;
    }


    public static int gcd(int a,int b){
        return b == 0 ? a : gcd(b,a%b);
    }

}
