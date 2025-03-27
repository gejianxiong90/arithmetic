package core;

public class CountAndSay {


    /**
     *1. 1
     *2. 11  1个1
     *3. 21  2个1
     *4. 1211 1个2，1个1
     *5. 111221 1个1，1个2,2个1
     * @param num
     * @return
     */

    public static String countAndSay(int num){
        if(num < 1){
            return "";
        }
        if(num == 1){
            return "1";
        }
        char[] last = countAndSay(num - 1).toCharArray();
        StringBuilder res = new StringBuilder();
        int times = 1;
        for(int i = 1; i < last.length ; i++){
            if(last[i] == last[i-1]){
                times++;
            }else {
                res.append(times);
                res.append(last[i-1]);
                times = 1;
            }
        }
        res.append(times);
        res.append(last[last.length-1]);
        return res.toString();
    }
}
