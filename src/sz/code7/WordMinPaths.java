package sz.code7;

import java.util.*;


/**
 * start 变成 end的最短路径
 * 必须在给定的list中取得
 * 打印所有最短路径
 *
 */
public class WordMinPaths {

    public static List<List<String>> findMinPaths(String start,String end,List<String> list){
        list.add(start);
        Map<String, List<String>> nexts = getNexts(list);
        Map<String, Integer> distances = getDistances2(start, nexts);

        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();

        getShortestPaths2(start,end,nexts,distances,pathList,res);
        return res;

    }

    // 现在来到了什么：cur
    // 目的地：end
    // 邻居表：nexts
    // 最短距离表：distances
    // 沿途走过的路径：path上{....}
    // 答案往res里放，收集所有的最短路径
    private static void getShortestPaths(String cur,String to,
                                         Map<String,List<String>> nexts,
                                         Map<String,Integer> distances,
                                         LinkedList<String> path,
                                         List<List<String>> res){
        path.add(cur);
        if(to.equals(cur)){
            res.add(new LinkedList<String>(path));
        }else {
            for(String next : nexts.get(cur)){
                if(distances.get(next) == distances.get(cur) + 1){
                    getShortestPaths(next,to,nexts,distances,path,res);
                }
            }
        }
        path.pollLast();
    }


    public static Map<String,Integer> getDistances(String start,Map<String, List<String>> nexts){
        Map<String, Integer> distances = new HashMap<>();
        distances.put(start,0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for(String next : nexts.get(cur)){
                if(!set.contains(next)){
                    distances.put(next,distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    public static Map<String,List<String>> getNexts(List<String> words){
        Set<String> dict = new HashSet<>(words);
        Map<String, List<String>> nexts = new HashMap<>(); // 存放每个字符到所有的路径
        for(int i = 0; i < words.size(); i++){
            nexts.put(words.get(i),getNext(words.get(i),dict));
        }
        return nexts;
    }


    // word 所有可能的排列和筛选
    private static List<String> getNext(String word,Set<String> dict){
        List<String> res = new ArrayList<>();
        char[] chs = word.toCharArray();
        for(char cur = 'a';cur <='z';cur++){
            for(int i = 0; i < chs.length ; i++){
                if(chs[i] != cur){
                    char tmp = chs[i];
                    chs[i] = cur;
                    if(dict.contains(String.valueOf(chs))){
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }


    public static Map<String,Integer> getDistances2(String start,Map<String, List<String>> nexts){
        Map<String, Integer> res = new HashMap<>();
        res.put(start,0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for(String next : nexts.get(cur)){
               if(!set.contains(next)){
                   res.put(next,res.get(cur) + 1);
                   set.add(next);
                   queue.add(next);
               }
           }
        }
        return res;
    }

    // 现在来到了什么：cur
    // 目的地：end
    // 邻居表：nexts
    // 最短距离表：distances
    // 沿途走过的路径：path上{....}
    // 答案往res里放，收集所有的最短路径
    private static void getShortestPaths2(String cur,String to,
                                         Map<String,List<String>> nexts,
                                         Map<String,Integer> distances,
                                         LinkedList<String> path,
                                         List<List<String>> res){
        path.add(cur);
        if(to.equals(cur)){
            res.add(new LinkedList<>(path));
        }
        for(String next : nexts.get(cur)){
            if(distances.get(cur) + 1 == distances.get(next)){
                getShortestPaths2(next,to,nexts,distances,path,res);
            }
        }
        path.pollLast();

    }


    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }
}
