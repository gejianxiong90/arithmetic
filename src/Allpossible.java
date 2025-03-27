import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Allpossible {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(nums, 0, nums.length - 1,ans);
        System.out.println(ans);
        return ans;
    }

    /**
     *
     * @param arr
     * @param start 以这个作为开始，对后面的进行swap
     * @param end
     * @param res
     */
    public static void process(int[] arr, int start, int end,List<List<Integer>> res) {
        if (start == end) {
            res.add(copyRes(arr));
        } else {
            for (int i = start; i <= end; i++) {
                swap(arr, start, i);
                process(arr, start + 1, end,res);
                swap(arr, start, i);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static List<Integer> copyRes(int[] arr) {
        List<Integer> integers = new ArrayList<>();
        for (int val : arr) {
            integers.add(val);
        }
        return integers;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        permute(arr);
    }
}
