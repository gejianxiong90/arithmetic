package core;

import java.util.LinkedList;
import java.util.Stack;

public class Calculate {

    public int calculate(String s) {
        return process(s.toCharArray(),0);
    }


    public int process(char[] arr, int index) {
      LinkedList<String> quque = new LinkedList<String>();
      int num = 0;
      while(index < arr.length){
          if(arr[index] == ' '){
              index++;
              continue;
          }
          if(arr[index] >= '0' && arr[index] <= '9'){ // 处理数字
              num = num * 10 + (arr[index++] - '0');
              continue;
          }else { // 处理符号
              if(!quque.isEmpty() && ("*".equals(quque.peekLast()) || "/".equals(quque.peekLast()))){
                  divOrMulti(quque,num);
                  num = 0;
              }else {
                  quque.addLast(String.valueOf(num));
                  num = 0;
              }
              quque.addLast(String.valueOf(arr[index++]));
          }
      }
        if(!quque.isEmpty() && ("*".equals(quque.peekLast()) || "/".equals(quque.peekLast()))){
            divOrMulti(quque,num);
        }else {
            quque.addLast(String.valueOf(num));
        }

        return addOrSub(quque);
    }

    public int addOrSub(LinkedList<String> linked){
        while(!linked.isEmpty() && linked.size() > 1){
              Integer num1 = Integer.valueOf(linked.pollFirst());
              if("+".equals(linked.pollFirst())){
                  Integer num2 = Integer.valueOf(linked.pollFirst());
                  linked.addFirst(String.valueOf(num1 + num2));
              }else {
                  Integer num2 = Integer.valueOf(linked.pollFirst());
                  linked.addFirst(String.valueOf(num1 - num2));
              }
        }
        return Integer.valueOf(linked.pollFirst());
    }
    public void divOrMulti( LinkedList<String> linked,int num){
        if("*".equals(linked.pollLast())){
            int num2 = Integer.valueOf(linked.pollLast());
            linked.addLast(String.valueOf(num2 * num));
        }else {
            int num2 = Integer.valueOf(linked.pollLast());
            linked.addLast(String.valueOf(num2 / num));
        }
    }

    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate("2*3+4"));
    }
}
