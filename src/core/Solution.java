package core;

import java.util.HashMap;
import java.util.Stack;

public class Solution {

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 将链表的节点进行反转
     *
     * @param head 原始链表头节点，可能为null
     * @param <T>  链表节点数据类型
     * @return 反转后的链表头节点
     */
    public static <T> Node<T> reverse(Node<T> head) {
        Node<T> pre = null;
        Node<T> cur = head;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    // 给定一个非空字符串，其中只包含左右小括号 ( 和 ) ，左右中括号 [ 和 ] ，左右大括号 { 和 }
// 实现一个函数，判断这个字符串中的括号是否是合法的成对出现的
// 例1：{[()()][]}{} 这是一个合法的字符串，它的括号都是成对且匹配的出现
// 例2：([)] 这不是一个合法的字符串，其中的小括号和中括号无法匹配成对
// 例3：([){} 这不是一个合法的字符串，单独出现了一个未成对的 [
    public static boolean isParenthesesValid(String str) {

        char[] chars = str.toCharArray();

        return process(str.toCharArray());
    }

    public static void main(String[] args) {
        System.out.println(isParenthesesValid("{[()()][]}{}"));
        System.out.println(isParenthesesValid("([)]"));
        System.out.println(isParenthesesValid("([){}"));
    }


    public static boolean process(char[] chars) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('(', 1);
        map.put(')', -1);
        map.put('[', 10);
        map.put(']', -10);
        map.put('{', 100);
        map.put('}', -100);

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' || chars[i] == '[' || chars[i] == '{') {
                stack.push(chars[i]);
            } else {
                char cur = chars[i];
                char find;
                if (cur == ')') {
                    find = '(';
                } else if (cur == '}') {
                    find = '{';
                } else {
                    find = '[';
                }
                int sum = 0;
                while (!stack.isEmpty() && stack.peek() != find) {
                    char pop = stack.pop();
                    sum += map.get(pop);
                }
                if (sum != 0) {
                    return false;
                }
                stack.push(cur);
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            char pop = stack.pop();
            sum += map.get(pop);
        }
        if (sum != 0) {
            return false;
        }
        return true;
    }
}
