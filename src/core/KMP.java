package core;

import java.util.ArrayList;

public class KMP {


    public static void main(String[] args) {

        String str = "sbcabcabc";
        String match = "abc";
        int i = kmp_indexof2(str, match);
        System.out.println(i);
    }

    public static int kmpIndexOf(String str,String match){
        if(match.length() > str.length()){
            return -1;
        }
        int[] next = getNextArr(match);
        int x = 0 ;
        int y = 0;
        char[] ch = str.toCharArray();
        char[] mch = match.toCharArray();
        while(x < ch.length && y < mch.length){
            if(ch[x] == mch[y]){
                x++;
                y++;
            }else if(next[y] == -1){ //y == 0
                x++;
            }else{
                y = next[y];
            }
        }
        return y == mch.length ? x - y : -1;
    }

    public static int[] getNextArr(String match){
        if(match.length() == 1){
            return new int[]{-1};
        }
        char[] mch = match.toCharArray();
        int[] next = new int[mch.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0; // cn位置的字符，是当前和i-1位置比较的字符
        int i = 2;
        while(i < mch.length){
            if(mch[i-1] == mch[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }

        return next;
    }


    public static int kmp_indexof(String str,String match){
        if(match.length() > str.length()){
            return -1;
        }
        int x = 0;
        int y = 0;
        char[] ch = str.toCharArray();
        char[] mch = match.toCharArray();
        int[] next = getNext(match);
        while(x < ch.length && y < mch.length){
            if(ch[x] == mch[y]){
                x++;
                y++;
            }else if(next[y] == - 1){
                x++;
            }else {
                y = next[y];
            }
        }
        return y == match.length() ? x - y : -1;
    }

    public static int[] getNext(String match){
        if(match.length() == 1) {
            return new int[]{-1};
        }
        char[] ch = match.toCharArray();
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i<match.length()){
            if(ch[i-1] == ch[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static int[] getNext2(String match){
        if( match.length() == 1){
            return new int[]{-1};
        }
        int n = match.length();
        char[] ch = match.toCharArray();
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while(i < n){
            if(ch[i - 1] == ch[cn]){
                next[i++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else {
                i++;
            }
        }
        return next;
    }

    public static int kmp_indexof2(String str,String match){
        if(match.length() > str.length()){
            return -1;
        }
        int[] next = getNext2(match);
        int x = 0;
        int y = 0;
        char[] ch = str.toCharArray();
        char[] mch = match.toCharArray();
        while(x < ch.length && y < mch.length){
            if(ch[x] == mch[y]){
                x++;
                y++;
            }else if(next[y] > -1){
                y = next[y];
            }else {
                x++;
            }
        }
        return y == match.length() ? x - y : -1;
    }































}
