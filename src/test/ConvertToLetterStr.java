package test;

public class ConvertToLetterStr {


    public static void main(String[] args) {
        String str = "17";
        int process = process(str.toCharArray(), 0);
        System.out.println(process);
    }

    public static int process(char[] chars,int i){
        if(chars.length == i){
            return 1;
        }
        if(chars[i] == '0'){
            return 0;
        }
        if(chars[i] == '1'){
         int res = process(chars,i + 1); // +1 +2 其实就是取数的步长
         if(i + 1 < chars.length){
             res += process(chars,i+2);
         }
         return res;
        }
        if(chars[i] == '2'){
            int res = process(chars,i+1);
            if(i + 1 < chars.length && chars[i+1] >= '0' && chars[i+1] <= '6'){
                res += process(chars,i+2);
            }
            return res;
        }
        return process(chars,i+1);
    }
}
