package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode 84
 */
public class LargestRectangleArea {

    public static int largestRectangleArea(int[] heights) {
        if(heights == null){
            return 0;
        }
        if(heights.length == 1){
            return heights[0];
        }
        if(heights.length == 2){
            return Math.min(heights[0],heights[1]) * 2;
        }
        int maxArea = 0;
        int[][] ans = new int[heights.length][2];
        Stack<List<Integer>> stack =  new Stack<List<Integer>>();
        for(int i = 0 ; i < heights.length; i++){
            while(!stack.isEmpty() && heights[stack.peek().get(0)] > heights[i]){
                List<Integer> curList = stack.pop();
                for(Integer cur : curList){
                    int leftVal = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                    ans[cur][0] = leftVal;
                    ans[cur][1] = i;
                    maxArea = Math.max(maxArea, heights[cur] * (i - leftVal - 1));
                }
            }
            if(!stack.isEmpty() && heights[stack.peek().get(0)] == heights[i]){
                stack.peek().add(i);
            }else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                stack.push(list);
            }
        }

        while(!stack.isEmpty()){
            List<Integer> curList = stack.pop();
            for(Integer cur : curList){
                int leftVal = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                ans[cur][0] = leftVal;
                ans[cur][1] = -1;
                maxArea = Math.max(maxArea, heights[cur] * (heights.length - leftVal - 1));
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int i = largestRectangleArea(heights);

        System.out.println(i);
    }
}
