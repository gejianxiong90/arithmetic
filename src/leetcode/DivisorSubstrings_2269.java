package leetcode;

public class DivisorSubstrings_2269 {

    public static void main(String[] args) {
        int i = divisorSubstrings(430043, 2);

        System.out.println(i);
    }
    public static  int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        if(s.length() < k){
            return 0;
        }
        int count = 0;
        for(int i = 0 ; i <= s.length() - k ;i++){
            String substring = s.substring(i, i + k);
            Integer integer = Integer.valueOf(substring);
            if(integer == 0) continue;
            count += num % integer == 0 ? 1 : 0;
        }
        return count;
    }
}
