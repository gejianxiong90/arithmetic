package core;

/**
 * https://leetcode.cn/problems/decode-string/?envType=company&envId=aliyun&favoriteSlug=aliyun-three-months
 */
public class DecodeString {

    public static void main(String[] args) {
        String string = decodeString("3[a]2[bc]");
        System.out.println(string);
    }

    public static String decodeString(String s) {

        String[] res =   process(s.toCharArray(),0);
        return res[0];
    }
    /**
     * retutn 0 是返回的字符串
     *        1 是来到的index
     */
    public static String[] process(char[] arr,int i){
        // Stack<Charters> stack =  new Stack<Charters>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int j = i ; j < arr.length; j++){
            if(arr[j] >= '0' && arr[j] <= '9'){
                count = count * 10 + (arr[j] - '0');
            }else if(arr[j] == '['){
                String[] res = process(arr,j+1);
                j = Integer.valueOf(res[1]) - 1;
                for(int k = 0 ; k < count;k++){
                    sb.append(res[0]);
                }
                count = 0;
                continue;
            }else if(arr[j] == ']'){
                return new String[]{sb.toString(),String.valueOf(j+1)};
            }else {
                sb.append(arr[j]);
            }
        }
        return new String[]{sb.toString(),String.valueOf(i)};

    }
}
