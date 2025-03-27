package sz.code22;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 类似正则匹配，给一个只包含数字和字母的str，和包含符号“.”（匹配一个字符）和“*”（匹配任意 0 或多个）的exp字符串，返回true或false表示能不能匹配
 * .可以在任何位置出现，表示匹配任何字符
 * *号之前必须有数字、字母或者"." （表示a* 表示可以有0个a，1个a，2个a 。。。。。）, *不可以连续出现
 *
 */
public class RegularExpressionMatch {

    public static boolean isMatch(String str,String exp){
        if(str == null || exp == null){
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return   isValid(s,e) && process(s,e,0,0);
    }

    public static boolean process(char[] s,char[] e, int si,int ei){
        if(ei == e.length){
            return si == s.length;
        }
        // ei+1 不是* 的情况
        if(ei + 1 != e.length && (e[ei] == s[si] || e[ei + 1] != '*')){
            return si != s.length
                    && (e[ei] == s[si] || e[ei] == '.')
                    && process(s,e,si+1,ei+1);
        }
        // ei + 1 是*
        // 尝试 ei和ei+1 共同的部分，匹配str可能的前缀
        while (si != s.length && (s[si] == e[ei] || e[ei] == '.')){
            if (process(s,e,si,ei+2)){
                return true;
            }
            si++;
        }
        return process(s,e,si,ei+2);
    }

    public static boolean isValid(char[] s,char[] e){
        // s中不能有 ./*
        for(int i = 0 ; i < s.length ; i++){
            if(s[i] == '*' || s[i] == '.'){
                return false;
            }
        }

        // 开头e[0]不能是*，没有相邻的*
        for(int i = 0 ; i < e.length ; i++){
            if(e[i] == '*' && (i == 0 || e[i - 1] == '*')){
                return false;
            }
        }
        return true;
    }

    // e[ei...] 能否拼出s[si....]
    // ei 不可以压中 *
    // 永远是讨论 ei+1 位置是不是*
    public static boolean process2(char[] s,char[] e,int si,int ei){
        if(ei == e.length){
            return s.length == si;
        }
        // ei + 1 不是 *
        // 意味下一个不是*,ei和ei+1都不是*
        // ei + 1 位置已经越界了，所以ei+1肯定不是*
        if(ei + 1 == e.length || e[ei + 1] != '*'){
            return si != s.length // si位置也必须要有字符
                    && (e[ei] == s[si] || e[ei] == '.')
                    && process2(s,e,si+1,ei+1);

        }
        // ei + 1 位置是 *
        while(si != s.length  && (e[ei] == s[si] || e[ei] == '.')){
            if(process2(s,e,si,ei + 2)){
                return true;
            }
            si++;
        }
        return process2(s,e,si,ei+2);
    }



    public static void main(String[] args) {
        String str = "abcccdefg";
        String exp = ".*";
        System.out.println(isMatch(str, exp));



    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new IComparator());
        for(ListNode node : lists){
            if(node != null){
                queue.add(node);
            }
        }
        if(queue.isEmpty()){
            return null;
        }

        ListNode head = queue.poll();
        ListNode pre = head;
        if(pre.next != null){
            queue.add(pre.next);
        }
        while(!queue.isEmpty()){
            ListNode node =  queue.poll();
            pre.next = node;
            if(node.next != null){
                queue.add(node.next);
            }

        }

        return head;

    }


    public static class IComparator implements Comparator<ListNode>{
        public int compare(ListNode o1, ListNode o2){
            return o1.val - o2.val;
        }
    }

}
