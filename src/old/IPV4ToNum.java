package old;

/**
 *
 * 题目描述
 存在一种虚拟IPv4地址，由4小节组成，每节的范围为0~255，以#号间隔，虚拟IPv4地址可以转换为一个32位的整数，例如：

 128#0#255#255，转换为32位整数的结果为2147549183（0x8000FFFF）

 1#0#0#0，转换为32位整数的结果为16777216（0x01000000）

 现以字符串形式给出一个虚拟IPv4地址，限制第1小节的范围为1128，即每一节范围分别为(1128)#(0255)#(0255)#(0~255)，要求每个IPv4地址只能对应到唯一的整数上。如果是非法IPv4，返回invalid IP
 *
 *
 */
public class IPV4ToNum {


    public static void main(String[] args) {
        int process = process("100#101#1#5");

        System.out.println(process);
    }
    public static int process(String ipv4){
        String[] arr = ipv4.split("#");

        if(arr.length != 4){
            throw new RuntimeException("invalid ip");
        }
        if(Integer.valueOf(arr[0]) > 128){
            throw new RuntimeException("invalid ip");
        }

        int res = 0;

        res |= Integer.valueOf(arr[0]) << 24;

        res |= Integer.valueOf(arr[1])<<16;

        res |= Integer.valueOf(arr[2])<<8;

        res |= Integer.valueOf(arr[3]);

        return res;
    }
}
