package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permute {

    public static List<List<Integer>> permute(int[] nums) {

        List ans = new ArrayList<ArrayList<Integer>>();

        HashSet<Integer> rest = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        for(Integer num : nums){
            rest.add(num);
        }
        process(rest,path,ans);
        return ans;

    }

    public static void process(HashSet<Integer> rest,List<Integer> path, List<List<Integer>> ans){
        if(rest.isEmpty()){
            ans.add(path);
        }else {
            for(Integer i : rest){
                ArrayList<Integer> pathc = new ArrayList<>(path);
                pathc.add(i);
                HashSet<Integer> integers = cloneAndRemove(rest, i);
                process(integers,pathc,ans);
            }
        }
    }

    public static HashSet<Integer> cloneAndRemove(HashSet<Integer> res,int num){
        HashSet<Integer> integers = new HashSet<>();

        for(Integer integer : res){
            if(integer != num){
                integers.add(integer);
            }
        }
        return integers;
    }

    public static List<Integer> process(int[] nums,int i,List<Integer> path,int k){
        if(i == nums.length || i < 0 || k == nums.length){
            return path;
        }
        List<Integer> process = null;
        for(int j = i ; j < nums.length;j++){
            path.add(nums[j]);
            process = process(nums, j + 1, path, k + 1);
        }

        return process;
    }


    public static void main(String[] args) {
        int[] arr = {0,1,2};

        List<List<Integer>> permute = permute(arr);

        System.out.println(permute);

        }
}
