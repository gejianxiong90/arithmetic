package sz.code13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二维数组board，和一个字符串组成的列表words，可以从任何位置出发，每一步可以走上、下、左、右四个方向，
 * 但是一条路径走过不能重复走，返回words中哪些单子被走出来
 * 例：
 * board = {
 *     {'o','a','a','n'},
 *     {'e','t','a','e'},
 *     {'i','h','k','r'},
 *     {'i','f','l','v'}
 * }
 *
 * words = {"oath","pea","eat","rain"}
 *
 * 输出：{“eat”，“oath”}
 *
 *
 * 解：将words加入到前缀树中，
 * 通过前缀树能过滤字符在不在前缀树，前缀树用pass和end记录到达几次和结尾几次
 * 走出后pass-- end--
 * 走前判断pass是否0
 */
public class WordSearch {
    public static class TrieNode{
        public TrieNode[] next;
        public int pass;
        public int end;
    }

    public static void fillWord(TrieNode head , String word){
        head.pass++;
        char[] chars = word.toCharArray();
        TrieNode node = head;
        int index = 0;
        for(char cha : chars){
            index = cha - 'a';
            if(node.next[index] == null){
                TrieNode trieNode = new TrieNode();
                node.next[index] = trieNode;
            }
            node = node.next[index];
            node.pass++;
        }
        node.end++;
    }


    public static List<String> findWords(char[][] board,String[] words){
        TrieNode head = new TrieNode();
        HashSet<String> set = new HashSet<>();
        for (String word : words){
            if(!set.contains(word)){
                fillWord(head,word);
                set.add(word);
            }
        }

        ArrayList<String> res = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();
        for(int row = 0; row < board.length;row++){
            for (int col = 0 ; col < board[0].length;col++){
                process(board,row,col,path,head,res);
            }
        }

        return res;
    }


    // 从board[row][col]位置的字符出发，
    // 之前的路径上，走过的字符，记录在path里
    // cur还没有登上，有待检查能不能登上去的前缀树的节点
    // 如果找到words中的某个str，就记录在 res里
    // 返回值，从row,col 出发，一共找到了多少个str
    public static int process(
            char[][] board,
            int row, int col,
            LinkedList<Character> path,
            TrieNode cur,
            List<String> res) {

        char cha = board[row][col];
        if(cha == 0){ // 这个是之前走过的位置
            return 0;
        }
        int index = cha - 'a';
        if(cur.next[index] == null || cur.next[index].pass == 0){
            return 0;
        }
        cur = cur.next[index];
        path.addLast(cha);

        int fix = 0; // 从row col 出发，后续一共搞定了多少答案
        if(cur.end > 0 ){
            res.add(generatePath(path));
            fix++;
            cur.end--;
        }
        board[row][col] = 0;

        if(row > 0){
            fix += process(board,row-1,col,path,cur,res);
        }
        if(row < board.length - 1){
            fix += process(board,row + 1,col,path,cur,res);
        }

        if(col > 0){
            fix += process(board,row,col - 1,path,cur,res);
        }
        if(col < board[0].length - 1){
            fix += process(board,row,col+1,path,cur,res);
        }
        board[row][col] = cha;
        path.pollLast();
        cur.pass -= fix;

        return fix;
    }

    public static String generatePath(LinkedList<Character> path){
        char[] res = new char[path.size()];
        int index = 0;
        for(Character cha : path){
            res[index++] =cha;
        }

       return  String.valueOf(res);
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
    }


    public static void fillWords(TrieNode head,String word){
        head.pass++;
        char[] chars = word.toCharArray();
        TrieNode node = head;
        for(int i = 0 ; i <chars.length ; i++){
            int index = chars[i] - 'a';
            if(head.next[index] == null){
                head.next[index] = new TrieNode();
            }
            head.next[index].pass++;
            node = head.next[index];
        }
        node.end++;
    }


    public static int process(char[][] board,int i,int j,TrieNode head,LinkedList<Character> path,List<String> res){
        char cha = board[i][j];
        if(cha == 0){
            return 0;
        }

        int index = cha - 'a';
        if(head.next[index] == null || head.next[index].pass ==0 ){
            return 0;
        }
       // head.next[index].pass--;
        path.addLast(cha);
        int fix = 0;
        if(head.next[index].end > 0){
            res.add(generatePath(path));
            head.next[index].end--;
            fix++;
        }
        board[i][j] = 0;
       if(i+1 < board.length){
           fix += process(board,i+1,j,head.next[index],path,res);
       }
       if(i -1 >= 0){
            fix += process(board,i-1,j,head.next[index],path,res);
       }
       if(j + 1 < board[0].length){
            fix += process(board,i , j+1,head.next[index],path,res);
       }
       if(j - 1 >= 0){
            fix += process(board,i,j-1,head.next[index],path,res);
       }
       head.next[index].pass -= fix;
        path.pollLast();
        board[i][j] = cha;

        return fix;
    }
}
