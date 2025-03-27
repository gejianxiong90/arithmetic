package test;

import java.util.HashSet;

public class LightMinCount {

    public static void main(String[] args) {
        String road = "xx....x.";
        Integer process = process(road.toCharArray(), 0, new HashSet<>());

        System.out.println( minLight2(road));


        System.out.println(process);

    }

    public static Integer minLight2(String road){
        char[] chars = road.toCharArray();
        int index = 0 ;
        int light = 0;
        while (index < chars.length){
            if(chars[index] == 'x'){
                index++;
            }else {
                light++;
                if(index + 1 == chars.length){
                    break;
                }
                if(chars[index+1] == 'x'){
                    index = index + 2;
                } else {
                    index = index + 3;
                }
            }
        }

        return light;
    }

    public static Integer process(char[] str, int index, HashSet<Integer> light){
        if(str.length == index ){
            for(int i = 0 ; i < str.length ; i++){
                if(str[i] == '.'){
                    if(!light.contains(i) && !light.contains(i-1) && !light.contains(i + 1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return light.size();
        }else {
            Integer no = process(str,index + 1,light);
            Integer yes = Integer.MAX_VALUE ;
            if(str[index] == '.'){
                light.add(index);
                yes = process(str,index+1,light);
                light.remove(index);
            }

            return Math.min(no,yes);

        }
    }
}
