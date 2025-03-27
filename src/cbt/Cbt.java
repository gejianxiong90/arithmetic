package cbt;

import kmp.TreeEqual;

public class Cbt {

    public static class Info{
        boolean isFull;
        boolean isCbt;
        int height;

        public Info(boolean isFull,boolean isCbt,int height){
            this.isFull = isFull;
            this.isCbt = isCbt;
            this.height = height;
        }
    }

    public static  Info process(TreeEqual.Node node){
        if(node == null){
            return new Info(true,true,0);
        }

        Info left = process(node.left);
        Info right = process(node.right);

        int height = Math.max(left.height,right.height) + 1;

        boolean isFull = false;
        if(left.isFull && right.isFull && left.height == right.height){
            isFull = true;
        }
        boolean isCbt = false;
        if(isFull){
            isCbt =true;
        }else{
            if(right.isCbt && left.isCbt){
                if(right.isFull && left.isCbt && left.height == right.height+1 ){
                    isCbt = true;
                }else if(left.isFull && right.isFull && left.height == right.height + 1){
                    isCbt = true;
                }else if(left.isFull && right.isCbt && left.height == right.height){
                    isCbt = true;
                }
            }
        }

        return new Info(isFull,isCbt,height);
    }


    public static TreeEqual.Node generateRandomBST(int maxLevel,int maxValue){
        return generate(1,maxLevel,maxValue);
    }

    public static TreeEqual.Node generate(int level,int maxLevel,int maxValue){
        if(level > maxLevel || Math.random() < 0.5 ){
            return null;
        }
        TreeEqual.Node head = new TreeEqual.Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1,maxLevel,maxValue);
        head.right = generate(level + 1,maxLevel,maxValue);
        return head;
    }


    public static void main(String[] args) {
        int maxLevel =5;
        int maxValue = 100;
        int testTimes = 10000;
        for(int i = 0 ; i< testTimes ; i++){
            TreeEqual.Node head = generateRandomBST(maxLevel,maxValue);
            Info process = process(head);
            if(process == null || process.isCbt){
                System.out.println("yes.....");
            }else{
                System.out.println("no");
            }


        }
        System.out.println("jieshu");
    }

}
