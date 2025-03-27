package leetcode;

import java.util.Iterator;
import java.util.TreeSet;

public class AddTwoNumbers {


//    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
//    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
//    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//    示例：
//
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/add-two-numbers
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



    public static void main(String[] args){
      //  System.out.println(5/10);

        String solve = solve("0", "0");

        System.out.println(solve);

        double x = 1.45;
        x++;
        System.out.println(String.format("%.1f",x));
        char a = 'A';
        System.out.println((int)a);

        TreeSet<String> set = new TreeSet<String>();




    }



    public static  String solve (String s, String t) {
        // write code here
        if("".equals(s)){
            return t;
        }
        if("".equals(t)){
            return s;
        }
        int sindex = s.length()-1;
        int tindex = t.length()-1;
        int carry = 0;
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        StringBuilder sb =  new StringBuilder();

        while(sindex >=0 ||  tindex >= 0){
            int temp = 0;
            if(sindex < 0){
                temp = 0 + (tChar[tindex] - '0') + carry;
            }else if(tindex < 0){
                temp = (sChar[sindex] - '0') + 0 + carry;
            }else {
                temp =   (sChar[sindex] - '0') + (tChar[tindex] - '0') + carry;
            }

            sb.append(temp % 10);

            carry =  temp / 10;

            sindex--;
            tindex--;

        }

        if(carry > 0){
            sb.append(1);
        }

        return sb.reverse().toString();

    }

    // done
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode custor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0){
            int i = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 :l2.val) + carry;
            carry = i / 10;

             ListNode tmp = new ListNode(i % 10);
             custor.next = tmp;
             custor = tmp;

             if(l1 != null)l1 = l1.next;

             if(l2 != null)l2 = l2.next;

        }
        return root.next;
    }

      public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
}
