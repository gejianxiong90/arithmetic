package sz.code10;

/**
 * 给定一个字符串，删除重复的并且保证删完的字典序最大
 *
 * 解：先统计每个字符的次频率
 *     再从左往右遍历依次 --词频，其中有词频为0是停止，在左边挑选字典序最高的保留，截取保留字符的后一个字符到最右，再replaceAll保留的字符。再重复以上过程
 *
 */
public class RemoveDuplicateLettersLessLexi {

    public static String remove(String str){

        if(str == null || str.length() < 2){
            return str;
        }

        char[] chars = str.toCharArray();

        int[] map = new int[256];

        for(int i = 0 ; i <chars.length ; i++ ){
            map[str.charAt(i)]++;
        }
        int minAscIndex = 0;
        for(int i = 0 ; i < chars.length ;i++){
            if( --map[str.charAt(i)] == 0){
                break;
            }else {
                minAscIndex = str.charAt(minAscIndex) > str.charAt(i) ? i : minAscIndex;
            }
        }

        return String.valueOf(str.charAt(minAscIndex))+
                remove(str.substring(minAscIndex+1).replaceAll(String.valueOf(str.charAt(minAscIndex)),""));
    }

    public static void main(String[] args) {
        String str = "ccdadbcdb";
        String remove = remove(str);

        System.out.println(remove);
    }
}
