package core;

public class ODTest {


    public static void main(String[] args) {


//        String[] words = {"cat","bt","hat","tree"};
//        String chars = "atach??";
//
//        int i = process2(words, chars);
//
//        System.out.println(i);

        process(100);
    }

    public static void process(int n){
        Counter counter = new Counter();
        int a = a(n, counter);

        System.out.println(counter.val);
    }

    public static class Counter{
        int val;
    }

    public static int a(int n,Counter counter){
        if(n < 7){
            return counter.val;
        }
        return b(n - 1,counter) + b(n-2,counter);
    }

    public static int b(int n ,Counter counter){
        if(n == 7){
            return counter.val++;
        }
        return a(n -1,counter) + a(n -2,counter);
    }


    public static int process2(String[] words,String chars){
        int[][] dic = new int[words.length][26];
        int total = 0;
        char[] ch = chars.toCharArray();
        for(int i = 0 ; i < words.length ; i++){
            char[] word = words[i].toCharArray();
            int count = 0;
            for(int j = 0 ; j < word.length ; j++){
                dic[i][word[j] - 'a']++;
                count++;
            }
            for(int k = 0 ; k < ch.length ;k++){
                if(ch[k] == '?' || dic[i][ch[k] - 'a'] > 0 ){
                    count--;
                    dic[i][ch[k] - 'a']--;
                }
            }

            if(count <= 0){
                total++;
            }

        }



        return total;
    }
}
