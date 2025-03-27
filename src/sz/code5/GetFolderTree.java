package sz.code5;

import java.util.TreeMap;

/**
 * 根据 a\b\c   b\e   c\d 打印目录树，子目录添加空格
 */
public class GetFolderTree {


    public static class Node{
        public String path;

        public TreeMap<String,Node> nextMap;

        public Node(String p){
            this.path = p;
            nextMap = new TreeMap<>();
        }
    }

    public static Node generateFolderTree(String[] folderPaths){
        Node head = new Node("");
        for(String foldPath : folderPaths){
            String[] paths = foldPath.split("\\\\");
            Node cur = head;
            for(int i = 0; i < paths.length ; i++){
                if(!cur.nextMap.containsKey(paths[i])){
                    cur.nextMap.put(paths[i],new Node(paths[i]));
                }
                cur = cur.nextMap.get(paths[i]);
            }
        }
        return head;
    }

    public static void print(String[] folderPaths){
        if(folderPaths == null || folderPaths.length == 0){
            return;
        }
        Node head = generateFolderTree(folderPaths);

        printProcess(head,0);
    }

    public static void printProcess(Node node,int level){
        if(level != 0){
            System.out.println(get4nSpace(level)+node.path);
        }
        for(Node next : node.nextMap.values()){
            printProcess(next,level + 1);
        }
    }

    public static String get4nSpace(int n){
        String res = "";
        for(int i = 1; i < n;i++){
            res += "  ";
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = {"a\\b\\c","a\\b","d\\e"};
        print(strs);
    }
}
