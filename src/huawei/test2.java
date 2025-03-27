package huawei;

import java.util.*;

public class test2 {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        // 注意 hasNext 和 hasNextLine 的区别
//        List<String> commandList = new ArrayList<String>();
//        in.nextLine();
//        while (in.hasNextLine()) { // 注意 while 处理多个 case
//            String command = in.nextLine();
//            commandList.add(command);
//
//        }
//        in.close();
//        process(commandList);

        invoke();

    }

    public static void invoke(){

        List<String> commandList = new ArrayList<String>();
        commandList.add("REQUEST=10");
        commandList.add("REQUEST=20");
        commandList.add("RELEASE=0");
        commandList.add("REQUEST=20");
        commandList.add("REQUEST=10");

        process(commandList);
    }


    public static void process(List<String> commandList){
        HashMap<Integer, Integer> addressLenMap = new HashMap<>();
        PriorityQueue<Node> nodes = new PriorityQueue<>((a,b)->b.end - a.end);
        PriorityQueue<Node> free = new PriorityQueue<>((a,b)-> a.len - b.len);
        for(String command : commandList){
            String[] split = command.split("=");
            String com = split[0];
            int num = Integer.valueOf(split[1]);
            if(com.equals("REQUEST")){
                if(num == 0 || num > 100){
                    System.out.println("error");
                    continue;
                }
                if(nodes.isEmpty()){
                    nodes.add(new Node(0,num));
                    addressLenMap.put(0,num);
                    System.out.println(0);
                }else {
                    if(free.isEmpty()){
                        if(nodes.peek().end > 99){
                            System.out.println("error");
                        }
                        int  localaddr = nodes.peek().end+1;
                        nodes.add(new Node(localaddr,num));
                        addressLenMap.put(localaddr,num);
                        System.out.println(localaddr);
                    }else {
                        if(free.peek().len >= num){
                            Node poll = free.poll();
                            nodes.add(new Node(poll.address,num));
                            addressLenMap.put(poll.address,num);
                            System.out.println(poll.address);
                        }else {
                            int  localaddr = nodes.peek().end+1;
                            nodes.add(new Node(localaddr,num));
                            addressLenMap.put(localaddr,num);
                            System.out.println(localaddr);
                        }
                    }
                }
            }else if(com.equals("RELEASE")){
                if(!addressLenMap.containsKey(num)){
                    System.out.println("error");
                }else {
                    free.add(new Node(num,addressLenMap.get(num)));
                }

            }

        }
    }


    public static class Node{
        int address;
        int len;
        int end;
        public Node(int address, int len){
            this.address = address;
            this.len = len;
            this.end = address + len - 1;
        }
    }
}
